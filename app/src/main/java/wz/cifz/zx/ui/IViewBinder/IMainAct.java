package wz.cifz.zx.ui.IViewBinder;

import wz.cifz.zx.moudle.bean.MenuList;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/22.
 */

public interface IMainAct {
    void replaceFragment();
    void load();
    void initView();
    void setData(MenuList list);
    void loadFailed();
}
