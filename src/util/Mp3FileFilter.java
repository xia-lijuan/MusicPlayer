package util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * MP3文件过滤
 * @author xialijuan
 * @date 2021/10/20
 */
public class Mp3FileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        String name = f.getName();
        return f.isDirectory() || name.toLowerCase().endsWith(".mp3");
    }

    @Override
    public String getDescription() {
        return "*.mp3";
    }
}
