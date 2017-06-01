package wz.cifz.zx.presenter;

import javax.inject.Inject;

import wz.cifz.zx.moudle.bean.MenuList;
import wz.cifz.zx.moudle.biz.LoadMainMenu;
import wz.cifz.zx.presenter.BasePresenter;
import wz.cifz.zx.ui.ILoad.ILoad;
import wz.cifz.zx.ui.IViewBinder.IMainAct;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

public class PreMainLoadMenu extends BasePresenter implements ILoad {

    private IMainAct iMainAct;
    private LoadMainMenu loadMainMenu;

    @Inject
    public PreMainLoadMenu(IMainAct iMainAct) {
        this.iMainAct = iMainAct;
        loadMainMenu = new LoadMainMenu();
    }

    public void loadMenu(){
        loadMainMenu.getDataFromServer(this,this);
    }

    @Override
    public void loadSucceed(Object data) {
        iMainAct.setData((MenuList) data);
    }

    @Override
    public void loadFailed() {
        iMainAct.loadFailed();
    }
}
