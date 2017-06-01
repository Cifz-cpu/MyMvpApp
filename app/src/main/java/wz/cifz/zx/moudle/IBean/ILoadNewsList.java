package wz.cifz.zx.moudle.IBean;

import wz.cifz.zx.presenter.BasePresenter;
import wz.cifz.zx.ui.ILoad.ILoad;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/27.
 */

public interface ILoadNewsList {
    void getNewsList(ILoad iLoad, BasePresenter pre,int id,int page);
}
