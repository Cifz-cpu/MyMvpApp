package wz.cifz.zx.ui.ILoad;

import wz.cifz.zx.moudle.bean.NewsList;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/29.
 */

public interface ILoadMore {
    void LoadMoreSucceesd(NewsList newsList);
    void LoadMoreFailed();
}
