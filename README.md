# 项目描述

该项目主要使用了JavaSwing和播放时选择的JavaFX，主界面采用GridBagLayout布局，列表界面则是默认的BorderLayout布局。播放主要使用的MediaPlayer类，使用线程来控制播放的进度以及按钮响应的播放|暂停|更换歌曲，播放器实现了列表循环播放，记录最近一次播放的歌曲等功能。

# 项目结构

<img src="https://cdn.jsdelivr.net/gh/xialijuan0/ImageRepo@master/MusicPlayer/programStruct.jpg" alt="programStruct" style="zoom:67%;" />

# 项目功能
- 播放，暂停
- 上一首，下一首切换
- 调整音量（全局）

<img src="https://cdn.jsdelivr.net/gh/xialijuan0/ImageRepo@master/MusicPlayer/function.jpg" alt="function" style="zoom:80%;" />



# 文件介绍

**本项目引用了两个jar包，位于lib包中**

- fastjson.jar：用于json字符串和对象的互转

- jaudiotagger.jar：获取MP3的播放总时长

**下面是存储歌曲信息的两个文件**

- file/currentSong.txt：利用json存储当前播放的文件信息

|  json字段   | 字段类型 |       属性       |
| :---------: | :------: | :--------------: |
|     id      |   int    | 歌曲列表中的id名 |
|    name     |  String  |      歌曲名      |
|    path     |  String  |     歌曲路径     |
| totalLength |   int    | 歌曲总时长（秒） |
|   volume    |  double  |     全局音量     |

- file/song.txt：存储列表歌曲的所有路径


# 项目启动
- 由于jvm打包的jar文件，双击启动会出现乱码的情况，所以得**运行start.bat文件**调用jar包，以修改GBK编码为UTF-8，防止乱码导致文件找不到，报空指针异常。

- 如果start.bat和MusicPlayer在同一目录下，则修改start.bat命令为`java -jar -Dfile.encoding=utf-8 MusicPlayer.jar`
- 当jar包运行后，会在同一目录产生file文件夹，file文件夹中会包含两个文件，分别是song.txt和currentSong.txt文件




# 截图



<img src="https://cdn.jsdelivr.net/gh/xialijuan0/ImageRepo@master/MusicPlayer/MusicView.jpg" alt="MusicView" style="zoom:67%;" />

<img src="https://cdn.jsdelivr.net/gh/xialijuan0/ImageRepo@master/MusicPlayer/Play.jpg" alt="Play" style="zoom:67%;" />

<img src="https://cdn.jsdelivr.net/gh/xialijuan0/ImageRepo@master/MusicPlayer/AddSongTip.jpg" alt="AddSongTip" style="zoom:67%;" />

<img src="https://cdn.jsdelivr.net/gh/xialijuan0/ImageRepo@master/MusicPlayer/ListView.jpg" alt="ListView" style="zoom:67%;" />

<img src="https://cdn.jsdelivr.net/gh/xialijuan0/ImageRepo@master/MusicPlayer/songDeletedOrMove.jpg" alt="songDeletedOrMove" style="zoom:67%;" />

<img src="https://cdn.jsdelivr.net/gh/xialijuan0/ImageRepo@master/MusicPlayer/ListViewShowSong.jpg" alt="ListViewShowSong" style="zoom:67%;" />

<img src="https://cdn.jsdelivr.net/gh/xialijuan0/ImageRepo@master/MusicPlayer/SongChoose.jpg" alt="SongChoose" style="zoom:67%;" />

<img src="https://cdn.jsdelivr.net/gh/xialijuan0/ImageRepo@master/MusicPlayer/ChangeVolume.jpg" alt="ChangeVolume" style="zoom:67%;" />