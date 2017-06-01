package wz.cifz.zx.moudle.biz;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wz.cifz.zx.http.HttpApiManager;
import wz.cifz.zx.moudle.IBean.ILoadPic;
import wz.cifz.zx.moudle.bean.AumsePic;
import wz.cifz.zx.moudle.bean.PicDetail;
import wz.cifz.zx.presenter.BasePresenter;
import wz.cifz.zx.ui.ILoad.ILoad;
import wz.cifz.zx.ui.ILoad.ILoadPIC;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

public class LoadPic implements ILoadPic{

    @Override
    public void getBeautifulPic(final ILoadPIC iLoad, final BasePresenter pre, int id, int rows) {
        HttpApiManager
                .getInstence()
                .getDataPic()
                .getAumsePic(id,rows)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AumsePic>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        pre.addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull AumsePic aumsePic) {
                        iLoad.LoadPicSucceesd(aumsePic);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        iLoad.LoadPicFailed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPicDetail(final ILoad iLoad, final BasePresenter pre, long id){
        HttpApiManager
                .getInstence()
                .getDataPic()
                .getPicDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PicDetail>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        pre.addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull PicDetail picDetail) {
                        iLoad.loadSucceed(picDetail);
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
