package wz.cifz.zx.presenter;

import javax.inject.Inject;

import wz.cifz.zx.moudle.bean.NewsDetail;
import wz.cifz.zx.moudle.biz.LoadNewsDetail;
import wz.cifz.zx.ui.ILoad.ILoad;
import wz.cifz.zx.ui.IViewBinder.INewDetail;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/31.
 */

public class PreNewsDetail extends BasePresenter implements ILoad {
    INewDetail iNewDetail;
    LoadNewsDetail loadNewsDetail;

    @Inject
    public PreNewsDetail(INewDetail iNewDetail) {
        this.iNewDetail = iNewDetail;
        loadNewsDetail = new LoadNewsDetail();
    }

    public void loadNews(long id){
        loadNewsDetail.loadNewsDetail(this,this,id);
    }

    @Override
    public void loadSucceed(Object data) {
        iNewDetail.setData((NewsDetail) data);
    }

    @Override
    public void loadFailed() {
        iNewDetail.loadFailed();
    }
}
