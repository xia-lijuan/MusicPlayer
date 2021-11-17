package event;

import entity.Song;
import util.ListUtil;
import util.SongUtil;
import view.MusicView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * 暂停|播放按钮监听
 *
 * @author xialijuan
 * @date 2021/10/23
 */
public class PlayBtnListener implements ActionListener {

    private final JButton button;
    /**
     * 当前播放的Song对象
     */
    private Song song;
    /**
     * 用于标志是否弹出添加歌曲提示
     */
    private boolean flag;

    public PlayBtnListener(JButton button) {
        this.button = button;
    }

    public void initData() {
        //一定要重新赋值，否则弹出过提示后，flag一直是true，播放|暂停功能将会失效
        flag = false;
        ListUtil listUtil = new ListUtil();
        boolean addList = listUtil.readPath();
        Song resentSong = SongUtil.readSong();
        //添加过歌曲但没听过，从歌曲列表中随机播放一首
        if (addList && null == resentSong) {
            int randomId = new Random().nextInt(listUtil.songs.size());
            System.out.println("PlayBtnListener_randomId:" + randomId);

            song = listUtil.byIdGetObject(randomId, 0);
            SongUtil.playState(song, -2);
        } else if (addList) {//听过（包括已添加歌曲）
            song = resentSong;

            //-2：代表既不播放、停止也不暂停，只是为了将MediaPlayer对象存入HashMap中，便于以后取出
            SongUtil.playState(song, -2);
        } else {//既没添加过又没听过
            MusicView.addSongTip(1);
            flag = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        initData();
        if (flag) {
            return;
        }
        if (button.getText().equals("播放")) {//启动->暂停
            SongUtil.playState(song, 1);
            button.setText("暂停");
        } else if (button.getText().equals("暂停")) {//暂停->启动
            SongUtil.playState(song, 0);
            button.setText("播放");
        }
    }
}
