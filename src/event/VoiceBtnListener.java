package event;

import entity.Song;
import javafx.scene.media.MediaPlayer;
import util.SongUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 声音按钮监听
 *
 * @author xialijuan
 * @date 2021/10/25
 */
public class VoiceBtnListener extends MouseAdapter implements ChangeListener {

    private final JPopupMenu voicePopup;
    private final JSlider voiceSlider;
    /**
     * 当前正在播放的MediaPlayer对象
     */
    public static MediaPlayer mediaPlayer;

    public static Song song;

    public VoiceBtnListener() {
        voicePopup = new JPopupMenu();
        voiceSlider = new JSlider(0, 100, 30);
        voiceSlider.addChangeListener(this);
        voicePopup.add(voiceSlider);
        if (song != null) {
            voiceSlider.setValue((int) (song.getVolume() * 100));
        }
    }

    /**
     * 点击音量按钮，弹出音量进度条
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        voicePopup.show(e.getComponent(), e.getX(), e.getY());
    }

    /**
     * 音量控制
     *
     * @param e
     */
    @Override
    public void stateChanged(ChangeEvent e) {

        int sliderValue = voiceSlider.getValue();
        SongUtil.volume = sliderValue / 100.0;
        voiceSlider.setValue(sliderValue);

        if (null != mediaPlayer) {
            mediaPlayer.setVolume(sliderValue / 100.0);
        }

        System.out.println("VoiceBtnListener:" + voiceSlider.getValue() / 100.0);
    }
}
