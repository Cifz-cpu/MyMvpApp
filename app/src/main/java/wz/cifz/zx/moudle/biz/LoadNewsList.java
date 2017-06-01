package wz.cifz.zx.moudle.biz;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wz.cifz.zx.http.HttpApiManager;
import wz.cifz.zx.moudle.IBean.ILoadNewsList;
import wz.cifz.zx.moudle.bean.NewsList;
import wz.cifz.zx.presenter.BasePresenter;
import wz.cifz.zx.ui.ILoad.ILoad;
import wz.cifz.zx.ui.ILoad.ILoadMore;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/27.
 */

public class LoadNewsList implements ILoadNewsList {

    @Override
    public void getNewsList(final ILoad iLoad, final BasePresenter pre, int id, int page) {
        HttpApiManager.getInstence()
                .getDataCaipu()
                .getNewsList(id,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        pre.addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull NewsList newsList) {
                        iLoad.loadSucceed(newsList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Throwablessssss",e.getLocalizedMessage()+"-------------"+e.getMessage());
                        iLoad.loadFailed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void loadMoreData(final ILoadMore iLoad, final BasePresenter pre, int id, int page){
        HttpApiManager.getInstence()
                .getDataCaipu()
                .getNewsList(id,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        pre.addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull NewsList newsList) {
                        Log.e("sssssfewweweew","LoadNewsList-------"+newsList.getTotal());
                        iLoad.LoadMoreSucceesd(newsList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Throwablessssss",e.getLocalizedMessage()+"-------------"+e.getMessage());
                        iLoad.LoadMoreFailed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
