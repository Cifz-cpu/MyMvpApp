package wz.cifz.zx.ui.IViewBinder;

import wz.cifz.zx.moudle.bean.NewsDetail;
import wz.cifz.zx.moudle.bean.PicDetail;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/31.
 */

public interface INewDetail {
    void load();
    void initView();
    void setData(NewsDetail detail);
    void loadFailed();
}
