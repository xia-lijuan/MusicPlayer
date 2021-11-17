package event;

import view.ListView;
import view.MusicView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 列表按钮监听
 * @author xialijuan
 * @date 2021/10/17
 */
public class ListListener implements ActionListener {

    private ListView listView = null;

    /**
     * 当'列表'按钮被按下后，执行此方法（以免弹出多个列表界面）
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        MusicView.listClick++;
        if (MusicView.listClick == 1) {
            listView = new ListView();
        }

        //当listBtn被点击偶数次后，隐藏listFrame窗口
        listView.setFrameState(MusicView.listClick % 2 != 0);
    }
}
