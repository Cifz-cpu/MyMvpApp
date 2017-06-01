package wz.cifz.zx.ui.fragment.presenter;

import javax.inject.Inject;

import wz.cifz.zx.moudle.bean.AumsePic;
import wz.cifz.zx.moudle.bean.NewsList;
import wz.cifz.zx.moudle.biz.LoadNewsList;
import wz.cifz.zx.moudle.biz.LoadPic;
import wz.cifz.zx.presenter.BasePresenter;
import wz.cifz.zx.ui.ILoad.ILoad;
import wz.cifz.zx.ui.ILoad.ILoadMore;
import wz.cifz.zx.ui.ILoad.ILoadPIC;
import wz.cifz.zx.ui.fragment.IView.IAumseView;
import wz.cifz.zx.ui.fragment.IView.IFragmentView;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/27.
 */

public class PreAmuseLiveload extends BasePresenter implements ILoad,ILoadMore, ILoadPIC {
    IAumseView iAumseView;
    LoadNewsList loadNewsList;
    LoadPic loadPic;

    @Inject
    public PreAmuseLiveload(IAumseView iAumseView) {
        this.iAumseView = iAumseView;
        loadNewsList = new LoadNewsList();
        loadPic = new LoadPic();
    }

    public void loadPicData(int id,int rows){
        loadPic.getBeautifulPic(this,this,id,rows);
    }

    public void loadMoreData(int id , int page){
        loadNewsList.loadMoreData(this,this,id,page);
    }

    public void load(int id , int page){
        loadNewsList.getNewsList(this,this,id,page);
    }

    @Override
    public void loadSucceed(Object data) {
        iAumseView.HideDialog();
        iAumseView.setData((NewsList)data);
    }

    @Override
    public void loadFailed() {
        iAumseView.HideDialog();
        iAumseView.loadFailed();
    }

    @Override
    public void LoadMoreSucceesd(NewsList newsList) {
        iAumseView.LoadMore(newsList);
    }

    @Override
    public void LoadMoreFailed() {
        iAumseView.loadFailed();
    }

    @Override
    public void LoadPicSucceesd(AumsePic aumsePic) {
        iAumseView.setPic(aumsePic);
    }

    @Override
    public void LoadPicFailed() {
        iAumseView.loadFailed();
    }
}
