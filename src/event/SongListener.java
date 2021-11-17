package event;

import entity.Song;
import util.ListUtil;
import util.SongUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 列表里MP3歌曲点击监听（随机选中）
 *
 * @author xialijuan
 * @date 2021/10/18
 */
public class SongListener implements ListSelectionListener {

    private final JList songList;

    public SongListener(JList songList) {
        this.songList = songList;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String currentClickSongName = (String) songList.getSelectedValue();
            if (!"".equals(currentClickSongName)) {
                ListUtil listUtil = new ListUtil();
                //先更新数据
                listUtil.readPath();
                Song song = listUtil.byNameGetObject(currentClickSongName);
                String path = song.getPath();
                System.out.println("SongListener:" + path);

                //在播放选择的歌曲时，先关掉前一个播放的（由于随机点击播放，不能确定具体哪一个对象，只能关闭所以正在播放的对象）
                SongUtil.stopAllSong();

                SongUtil.playState(song, 1);
            }
        }
    }
}
