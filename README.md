# 项目描述

该项目主要使用了JavaSwing和播放时选择的JavaFX，主界面采用GridBagLayout布局，列表界面则是默认的BorderLayout布局。播放主要使用的MediaPlayer类，使用线程来控制播放的进度以及按钮响应的播放|暂停|更换歌曲，播放器实现了列表循环播放，记录最近一次播放的歌曲等功能。

# 项目结构

<img src="https://img-blog.csdnimg.cn/c6f59da41b1647faa9058693cd400ae7.jpg" alt="programStruct.jpg" style="zoom: 67%;" />

# 项目功能
- 播放，暂停
- 上一首，下一首切换
- 调整音量（全局）

<img src="https://img-blog.csdnimg.cn/2886f86fe8eb494592ad1325d8ac6a31.jpg" alt="function.jpg" style="zoom: 80%;" />



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

<img src="https://img-blog.csdnimg.cn/f236674bf5f9442ebb2164f57016828b.jpg" alt="MusicView.jpg" style="zoom:67%;" />

<img src="https://img-blog.csdnimg.cn/194dda5c5cb24399b67dece8c22246e2.jpg" alt="Play.jpg" style="zoom:67%;" />

<img src="https://img-blog.csdnimg.cn/a2aff95b860e4ee8beb08a8f922b0c22.jpg" alt="AddSongTip.jpg" style="zoom:67%;" />

<img src="https://img-blog.csdnimg.cn/fca52cb5484f4cdf804c4231cf5862ba.jpg" alt="ListView.jpg" style="zoom:67%;" />

<img src="https://img-blog.csdnimg.cn/092c94a3ffd14a7e9f56fa0a9cda1ede.jpg" alt="songDeletedOrMove.jpg" style="zoom:67%;" />

<img src="https://img-blog.csdnimg.cn/a6e02674d1134ca7bbe1968ca1c687cd.jpg" alt="ListViewShowSong.jpg" style="zoom:67%;" />

<img src="https://img-blog.csdnimg.cn/c93bc3a9349c4f89acb063469333ef54.jpg" alt="SongChoose.jpg" style="zoom:67%;" />

<img src="https://img-blog.csdnimg.cn/d7f9026056974aed95c8fa37a000871e.jpg" alt="ChangeVolume.jpg" style="zoom:67%;" />