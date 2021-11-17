package util;

import entity.Song;

import java.io.*;
import java.util.Vector;

/**
 * 处理ListView的数据
 *
 * @author xialijuan
 * @date 2021/10/17
 */
public class ListUtil {

    /**
     * 存放..file/song.txt文件中的所有Song对象
     */
    public Vector<Song> songs = new Vector<>();

    /**
     * 根据当前id获得当前|上一个|下一个歌曲对象
     *
     * @param id   当前id
     * @param flag flag为0，-1，1，对应的分别是当前，上一首，下一首
     * @return 查找到的对象
     */
    public Song byIdGetObject(int id, int flag) {
        for (Song song : songs) {
            if (id + flag == song.getId()) {
                return song;
            }
        }
        return null;
    }

    /**
     * 根据歌名获得歌曲对象
     *
     * @param name 歌名
     * @return 当前歌曲对象
     */
    public Song byNameGetObject(String name) {
        for (Song song : songs) {
            if (name.equals(song.getName())) {
                return song;
            }
        }
        return null;
    }


    /**
     * 获取歌名集合
     *
     * @return
     */
    public Vector<String> getSongName() {
        Vector<String> songNames = new Vector<>();
        for (Song song : songs) {
            songNames.add(song.getName());
        }
        return songNames;
    }

    /**
     * 从路径中提取歌曲名
     *
     * @param path 歌曲所在的路径
     * @return 歌曲名
     */
    public static String getSongName(String path) {
        String[] split = path.split("\\\\");
        return split[split.length - 1];
    }

    /**
     * 将文件夹中的所有mp3文件路径存放在../file/song.txt中（当列表界面中没有歌曲，执行该方法）
     *
     * @param folder mp3文件的父路径
     */
    public void writePath(File folder) {
        String[] list = folder.list();
        if (list == null) {
            return;
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("file\\song.txt");
            bw = new BufferedWriter(fw);
            int id = 0;
            for (String songName : list) {
                //只写mp3文件
                if (songName.endsWith(".mp3")) {
                    String path = folder.getPath() + "\\" + songName + "\r\n";
                    songs.add(new Song(id++, songName, path, 0.3));
                    bw.write(path);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeWriteStreamObject(bw);
            closeWriteStreamObject(fw);
        }
    }

    /**
     * 读取以前存储歌曲名的文件
     *
     * @return 是否有数据
     */
    public boolean readPath() {
        File songsFile = new File("file\\song.txt");
        //文件不存在，则说明之前没有缓存数据
        if (!songsFile.exists()) {
            try {
                songsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(songsFile);
            br = new BufferedReader(fr);
            String path = br.readLine();
            if (path == null || "".equals(path) || path.length() == 1 && path.charAt(0) == '\uFEFF') {//排除是\ufeff：用来标识文件的编码方式的
                return false;
            }
            int id = 0;
            while (path != null) {
                songs.add(new Song(id++, getSongName(path), path, 0.3));
                path = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeReadStreamObject(br);
            closeReadStreamObject(fr);
        }
        return true;
    }

    /**
     * 关闭字符输入流对象
     *
     * @param r
     */
    public void closeReadStreamObject(Reader r) {
        if (r != null) {
            try {
                r.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭字符输出流对象
     *
     * @param w
     */
    public void closeWriteStreamObject(Writer w) {
        if (w != null) {
            try {
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
