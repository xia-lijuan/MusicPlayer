package util;

import entity.Song;
import view.MusicView;

/**
 * 暂停|播放|停止线程
 *
 * @author xialijuan
 * @date 2021/10/25
 */
public class PlayStateThread extends Thread {
    /**
     * 线程状态：-1：停止    0：暂停       1：启动
     * （用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最的值）
     */
    private volatile int state = 1;
    public static volatile int time = 0;
    /**
     * 当前正在播放的Song对象
     */
    public Song song;

    public PlayStateThread(Song song) {
        this.song = song;
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (true) {
                    if (state == -1) {//停止
                        return;
                    } else if (state == 0) {//暂停
                        wait();
                    } else if (state == 1) {//启动
                        int totalLength = time++;
                        if (totalLength <= song.getTotalLength()) {
                            MusicView.songProgressBar.setValue(totalLength);
                        } else {//当前歌曲播放完后，自动播放下一首
                            ListUtil listUtil = new ListUtil();
                            listUtil.readPath();
                            Song nextSong = listUtil.byIdGetObject(this.song.getId(), 1);
                            SongUtil.playState(nextSong, 1);
                            MusicView.songProgressBar.setValue(0);
                            setStop();
                        }
                        System.out.println("PlayStateThead:" + totalLength + " " + song.getTotalLength());
                    }
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化进度数
     * @param time
     */
    public static void setTime(int time) {
        PlayStateThread.time = time;
    }

    /**
     * 线程暂停
     */
    public void setPause() {
        state = 0;
    }

    /**
     * 线程启动
     */
    public synchronized void setStart() {
        state = 1;
        notify();
    }

    /**
     * 线程停止
     */
    public void setStop() {
        state = -1;
        time = 0;
    }
}
