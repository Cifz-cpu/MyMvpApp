package wz.cifz.zx.moudle.biz;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wz.cifz.zx.http.HttpApiManager;
import wz.cifz.zx.moudle.bean.NewsDetail;
import wz.cifz.zx.presenter.BasePresenter;
import wz.cifz.zx.ui.ILoad.ILoad;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/31.
 */

public class LoadNewsDetail {
    public void loadNewsDetail(final ILoad iLoad, final BasePresenter pre, long id){
        HttpApiManager
                .getInstence()
                .getDataCaipu()
                .getNewsDetai(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsDetail>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        pre.addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull NewsDetail newsDetail) {
                        iLoad.loadSucceed(newsDetail);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        iLoad.loadFailed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
