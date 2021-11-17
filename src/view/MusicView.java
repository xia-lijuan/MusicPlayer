package view;

import entity.Song;
import event.ChangSongBtnListener;
import event.ListListener;
import event.PlayBtnListener;
import event.VoiceBtnListener;
import javafx.application.Application;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import util.SongUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * 音乐播放器主界面
 *
 * @author xialijuan
 * @date 2021/10/12
 */
public class MusicView extends Application {

    public static int listClick;
    public static JLabel nameLabel;
    public static JButton button;
    public static JProgressBar songProgressBar;
    private static JFrame mainFrame;
    /**
     * K：song的id，V：MediaPlayer
     */
    public static HashMap<Integer, MediaPlayer> playerList = new HashMap<>();

    public MusicView() {
        mainFrame = new JFrame("MP3播放器");
        mainFrame.setSize(600, 250);

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        mainFrame.setLayout(gridBagLayout);

        nameLabel = new JLabel("歌手-歌曲名");
        nameLabel.setFont(new Font("宋体", Font.BOLD, 18));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(5, 15, 5, 5);
        gridBagLayout.setConstraints(nameLabel, gridBagConstraints);
        mainFrame.add(nameLabel);
        init();

        songProgressBar = new JProgressBar();
        songProgressBar.setStringPainted(true);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(5, 15, 5, 5);
        gridBagLayout.setConstraints(songProgressBar, gridBagConstraints);
        mainFrame.add(songProgressBar);

        JButton preBtn = new JButton("上一首");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        //调整下面及prBtn所以按钮的间距
        gridBagConstraints.insets = new Insets(5, 15, 5, 5);
        gridBagLayout.setConstraints(preBtn, gridBagConstraints);
        mainFrame.add(preBtn);
        preBtn.addActionListener(new ChangSongBtnListener(-1));

        button = new JButton("播放");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagLayout.setConstraints(button, gridBagConstraints);
        mainFrame.add(button);
        button.addActionListener(new PlayBtnListener(button));

        JButton nextBtn = new JButton("下一首");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagLayout.setConstraints(nextBtn, gridBagConstraints);
        mainFrame.add(nextBtn);
        nextBtn.addActionListener(new ChangSongBtnListener(1));

        JButton listBtn = new JButton("列表");
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagLayout.setConstraints(listBtn, gridBagConstraints);
        mainFrame.add(listBtn);
        listBtn.addActionListener(new ListListener());

        JButton voiceBtn = new JButton("声音");
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagLayout.setConstraints(voiceBtn, gridBagConstraints);
        mainFrame.add(voiceBtn);
        voiceBtn.addMouseListener(new VoiceBtnListener());

        //将整个窗体居中
        mainFrame.setLocationRelativeTo(null);
        //当窗体关闭，则整个程序运行结束
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //让整个窗体可见
        mainFrame.setVisible(true);
    }

    /**
     * 弹出提示
     * @param flag 弹出不同种类提示
     */
    public static void addSongTip(int flag) {
        String message = flag == 1 ? "别急，歌曲都还没添加呢！" : "糟糕！歌曲没有找到！";
        JOptionPane.showMessageDialog(mainFrame, message, "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 将最近一次读过的歌曲名更新到主面板上
     */
    public void init() {
        Song song = SongUtil.readSong();
        if (song != null) {
            nameLabel.setText(song.getName());
            VoiceBtnListener.song = song;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void main(String[] args) {
        new MusicView();
    }
}
