package entity;

/**
 * @author xialijuan
 * @date 2021/10/12
 */
public class Song {
    /**
     * 歌曲编号
     */
    private int id;
    /**
     * 读到的字符串为：歌手-歌曲名
     */
    private String name;
    /**
     * 歌曲路径
     */
    private String path;
    /**
     * 歌曲总时长（秒）
     */
    private int totalLength;
    /**
     * 音量大小（0-1）
     */
    private double volume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Song() {
    }

    public Song(int id, String name, String path, double volume) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.volume = volume;
    }
}
