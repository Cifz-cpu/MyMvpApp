package wz.cifz.zx.presenter;

import javax.inject.Inject;

import wz.cifz.zx.moudle.bean.PicDetail;
import wz.cifz.zx.moudle.biz.LoadPic;
import wz.cifz.zx.ui.ILoad.ILoad;
import wz.cifz.zx.ui.IViewBinder.IMainAct;
import wz.cifz.zx.ui.IViewBinder.IPicAct;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

public class PrePicDetail extends BasePresenter implements ILoad {
    private IPicAct iPicAct;
    private LoadPic loadPic;

    @Inject
    public PrePicDetail(IPicAct iPicAct) {
        this.iPicAct = iPicAct;
        loadPic = new LoadPic();
    }

    public void loadPicDetail(long id){
        loadPic.getPicDetail(this,this,id);
    }

    @Override
    public void loadSucceed(Object data) {
        iPicAct.setData((PicDetail)data);
    }

    @Override
    public void loadFailed() {
        iPicAct.loadFailed();
    }
}
