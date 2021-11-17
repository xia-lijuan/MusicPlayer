package event;

import entity.Song;
import util.ListUtil;
import util.SongUtil;
import view.MusicView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * 上一首|下一首按钮监听
 *
 * @author xialijuan
 * @date 2021/10/21
 */
public class ChangSongBtnListener implements ActionListener {

    /**
     * 按钮状态     -1：播放上一首歌曲  1：播放下一首歌曲
     */
    private final int state;
    /**
     * 点击上一首|下一首后的Song
     */
    private Song changedSong;
    private final ListUtil listUtil = new ListUtil();

    public ChangSongBtnListener(int state) {
        this.state = state;
    }

    public void initData() {
        if (listUtil.readPath()) {
            changedSong = SongUtil.readSong();
            if (null == changedSong) {
                int randomId = new Random().nextInt(listUtil.songs.size());
                System.out.println("ChangSongBtnListener_randomId:" + randomId);
                changedSong = listUtil.byIdGetObject(randomId, 0);

                SongUtil.playState(changedSong, -2);
            }
        } else {
            MusicView.addSongTip(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        initData();
        //获得当前正在播放的对象
        Song currentSong = SongUtil.readSong();
        if (null == currentSong) {
            return;
        }
        SongUtil.playState(currentSong, -1);
        System.out.println("ChangSongBtnListener:" + currentSong.getPath());

        changedSong = listUtil.byIdGetObject(currentSong.getId(), state);
        if (null == changedSong) {
            //播放到第一首了，且点击了上一首（播放最后一首）
            if (state == -1) {
                changedSong = listUtil.byIdGetObject(listUtil.songs.size() - 1, 0);
            } else if (state == 1) {
                changedSong = listUtil.byIdGetObject(0, 0);
            }
        }
        SongUtil.playState(changedSong, 1);
    }
}
