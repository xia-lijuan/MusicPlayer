package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.Song;
import event.VoiceBtnListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import view.MusicView;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Song对象的API
 *
 * @author xialijuan
 * @date 2021/10/21
 */
public class SongUtil {

    /**
     * 只是为了调用关流方法
     */
    public static ListUtil listUtil = new ListUtil();
    public static PlayStateThread playStateThread = null;
    /**
     * 调节音量后最新的音量数据
     */
    public static double volume;

    /**
     * 播放|暂停
     *
     * @param song  当前对象
     * @param state -1：停止      0：暂停    1：播放
     * @return Song对象的MediaPlayer
     */
    public static void playState(Song song, int state) {
        if (null == song) {
            return;
        }
        //当前线程的Song对象和传入的Song不一样（播放了另一首歌曲，JProgressBar值为0）
        if (null != playStateThread && !playStateThread.song.getPath().equals(song.getPath())) {
            PlayStateThread.setTime(0);
        }
        MediaPlayer mediaPlayer = MusicView.playerList.get(song.getId());
        if (null == mediaPlayer) {
            File file = new File(song.getPath());
            //文件可能被删除，或移动了位置
            if (!file.exists()) {
                MusicView.addSongTip(0);
                return;
            }
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            MusicView.playerList.put(song.getId(), mediaPlayer);
        }
        //初始化音量
        if (0 == volume) {
            volume = song.getVolume();
        }
        song.setVolume(volume);
        mediaPlayer.setVolume(volume);
        System.out.println("SongUtil_Volume:" + song.getVolume());

        //获取歌曲总时长
        int songTotalLength = getSongTotalLength(song.getPath());
        System.out.println("SongUtil_songTotalLength:" + songTotalLength);
        song.setTotalLength(songTotalLength);
        MusicView.songProgressBar.setMaximum(songTotalLength);

        //当前对象没有播放（播放）
        if (!MediaPlayer.Status.PLAYING.equals(mediaPlayer.getStatus()) && state == 1) {
            playStateThread = new PlayStateThread(song);
            playStateThread.start();
            playStateThread.setStart();
            mediaPlayer.play();
        } else if (MediaPlayer.Status.PLAYING.equals(mediaPlayer.getStatus())) {
            if (state == 0) {
                playStateThread.setPause();
                mediaPlayer.pause();
            } else if (state == -1) {//停止
                playStateThread.setStop();
                mediaPlayer.stop();
            }
        }
        if (state == -1 || state == 0 || state == 1) {
            //主页面更换歌名
            MusicView.nameLabel.setText(song.getName());
            //播放歌曲
            MusicView.button.setText("暂停");
            //记住当前播放歌曲的信息
            new SongUtil().writeSong(song);
            VoiceBtnListener.mediaPlayer = mediaPlayer;
            VoiceBtnListener.song = song;
        }
    }

    /**
     * Song对象->json字符串
     *
     * @param song
     */
    public static String songToJson(Song song) {
        return JSON.toJSONString(song);
    }

    /**
     * json字符串->Song对象
     *
     * @param json
     */
    public static Song jsonStrToSong(String json) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return JSONObject.toJavaObject(jsonObject, Song.class);
    }

    /**
     * 获取歌曲总时长
     *
     * @param path
     * @return 秒数
     */
    public static int getSongTotalLength(String path) {
        Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);
        File file = new File(path);
        try {
            MP3File f = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            return audioHeader.getTrackLength();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 关掉所有音乐（其实只关闭了一首）
     */
    public static void stopAllSong() {
        for (Integer id : MusicView.playerList.keySet()) {
            MusicView.playerList.get(id).stop();
        }
    }

    /**
     * 从../file/currentSong.txt文件中读取Song对象并返回
     *
     * @return Song
     */
    public static Song readSong() {
        File file = new File("file\\currentSong.txt");
        if (!file.exists()) {
            try {
                String[] split = file.getPath().split("\\\\");
                new File(split[0]).mkdir();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileReader fr = null;
        BufferedReader br = null;
        Song song = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String jsonStr = br.readLine();
            song = jsonStrToSong(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            listUtil.closeReadStreamObject(br);
            listUtil.closeReadStreamObject(fr);
            return song;
        }
    }


    /**
     * 将Song对象的json字符串写入到../file/currentSong.txt文件中
     *
     * @param song 对象
     */
    public void writeSong(Song song) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("file\\currentSong.txt");
            bw = new BufferedWriter(fw);
            String jsonStr = songToJson(song);
            bw.write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            listUtil.closeWriteStreamObject(bw);
            listUtil.closeWriteStreamObject(fw);
        }
    }
}
