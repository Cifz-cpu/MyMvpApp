package wz.cifz.zx.ui.IViewBinder;

import wz.cifz.zx.moudle.bean.PicDetail;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

public interface IPicAct {
    void load();
    void initView();
    void setData(PicDetail list);
    void loadFailed();
}
