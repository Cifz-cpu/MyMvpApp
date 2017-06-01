package wz.cifz.zx.ui.fragment.IView;


import wz.cifz.zx.moudle.bean.NewsList;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/27.
 */

public interface BaseIView {
    void loadFailed();
    void ShowDialog();
    void HideDialog();
    void LoadMore(NewsList newsList);
}
