package event;

import entity.Song;
import util.ListUtil;
import util.Mp3FileFilter;
import util.SongUtil;
import view.ListView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * MP3文件选择监听
 *
 * @author xialijuan
 * @date 2021/10/19
 */
public class FileChooseListener extends MouseAdapter {

    private final ListView listView;

    public FileChooseListener(ListView listView) {
        this.listView = listView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        String username = System.getenv().get("USERNAME");
        JFileChooser fc = new JFileChooser("C:\\Users\\" + username + "\\Music");
        //选择文件时，只能选择MP3文件
        Mp3FileFilter filter = new Mp3FileFilter();
        fc.addChoosableFileFilter(filter);
        fc.setFileFilter(filter);

        //文件选择对话框
        int val = fc.showOpenDialog(null);
        if (val == JFileChooser.APPROVE_OPTION) {
            //上一级的目录文件夹
            File parentFolder = fc.getSelectedFile().getParentFile();
            //选择文件后，再写入歌的路径
            ListUtil listUtil = new ListUtil();
            listUtil.writePath(parentFolder);
            //播放当前选择的歌曲
            String path = fc.getSelectedFile().toString();
            String songName = ListUtil.getSongName(path);
            Song song = listUtil.byNameGetObject(songName);

            //重新设置路径（把添加的\r\n删掉）
            song.setPath(song.getPath().split("\r\n")[0]);
            SongUtil.playState(song, 1);

            //更新JList里的数据
            listView.addSongName(listUtil.getSongName());
            listView.noFileStyle(false);
            System.out.println("FileChooseListener:" + path);
        }
    }
}
