package view;

import entity.Song;
import event.FileChooseListener;
import event.SongListener;
import util.ListUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * 列表界面
 *
 * @author xialijuan
 * @date 2021/10/17
 */
public class ListView {

    /**
     * 当前的列表界面
     */
    public JFrame listFrame;
    /**
     * Song对象集合
     */
    public Vector<Song> songs;
    /**
     * 歌曲名
     */
    public Vector<String> songNames;
    private final Font style = new Font("宋体", Font.BOLD, 20);

    public ListView() {
        listFrame = new JFrame("播放列表");
        listFrame.setSize(350, 370);
//        listFrame.setResizable(false);
        //只关闭当前的ListView窗口
        listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ListUtil listUtil = new ListUtil();
        songs = listUtil.songs;
        boolean read = listUtil.readPath();
        songNames = listUtil.getSongName();
        //以前读过歌曲，直接显示歌曲列表
        if (read) {
            addSongName(songNames);
            noFileStyle(false);
        }else {
            noFileStyle(true);
        }

    }
    /**
     * 将歌曲名添加到面板组件中
     */
    public void addSongName(Vector<String> songNames) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0,0));

        final JList songNameList = new JList(songNames);
        songNameList.setFont(style);
        //限制只能选择一个歌曲
        songNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(songNameList);

        panel.add(scrollPane);
        listFrame.getContentPane().add(panel);
        listFrame.setVisible(true);

        //在滚动列表显示数据
        songNameList.addListSelectionListener(new SongListener(songNameList));
    }

    /**
     * 控制窗体隐藏或显示
     *
     * @param display 是否显示
     */
    public void setFrameState(boolean display) {
        listFrame.setVisible(display);
    }

    /**
     * 以前没有读过文件歌曲，将会显示以下样式
     */
    public void noFileStyle(boolean show) {
        if (songs != null && songs.size() == 0) {
            JPanel noFilePanel = new JPanel();
            JLabel noFileLabel = new JLabel("没有歌曲，");
            JLabel chooseFolderLabel = new JLabel("选择");
            noFileLabel.setFont(style);
            chooseFolderLabel.setFont(style);
            //<u>标签为文本添加下划线
            chooseFolderLabel.setText("<html><font color=blue><u>选择");
            //鼠标移到超链接时，变成手型
            chooseFolderLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            chooseFolderLabel.addMouseListener(new FileChooseListener(this));
            noFilePanel.add(noFileLabel);
            noFilePanel.add(chooseFolderLabel);
//            noFilePanel.setVisible(show);
//            noFileLabel.setVisible(show);
//            chooseFolderLabel.setVisible(show);
            if (show) {
                listFrame.add(noFilePanel);
            }
        }
    }
}
