package wz.cifz.zx.ui.fragment.presenter;

import javax.inject.Inject;

import wz.cifz.zx.moudle.bean.NewsList;
import wz.cifz.zx.moudle.biz.LoadNewsList;
import wz.cifz.zx.presenter.BasePresenter;
import wz.cifz.zx.ui.ILoad.ILoad;
import wz.cifz.zx.ui.ILoad.ILoadMore;
import wz.cifz.zx.ui.fragment.IView.IFragmentView;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/27.
 */

public class PrePeoNewsList extends BasePresenter implements ILoad, ILoadMore {
    IFragmentView iFragmentAmuse;
    LoadNewsList loadNewsList;

    @Inject
    public PrePeoNewsList(IFragmentView iFragmentAmuse) {
        this.iFragmentAmuse = iFragmentAmuse;
        loadNewsList = new LoadNewsList();
    }

    public void loadMoreData(int id , int page){
        loadNewsList.loadMoreData(this,this,id,page);
    }

    public void loadNewsList(int id , int page){
//        iFragmentAmuse.ShowDialog();
        loadNewsList.getNewsList(this,this,id,page);
    }

    @Override
    public void loadSucceed(Object data) {
        iFragmentAmuse.HideDialog();
        iFragmentAmuse.setData((NewsList) data);
    }

    @Override
    public void loadFailed() {
        iFragmentAmuse.HideDialog();
        iFragmentAmuse.loadFailed();
    }

    @Override
    public void LoadMoreSucceesd(NewsList newsList) {
        iFragmentAmuse.LoadMore(newsList);
    }

    @Override
    public void LoadMoreFailed() {
        iFragmentAmuse.loadFailed();
    }
}
