package wz.cifz.zx.ui.fragment.IView;

import wz.cifz.zx.moudle.bean.AumsePic;
import wz.cifz.zx.moudle.bean.NewsList;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

public interface IAumseView extends BaseIView {
    void load();
    void initView();
    void setData(NewsList list);
    void loadPic();
    void setPic(AumsePic aumsePic);
}
