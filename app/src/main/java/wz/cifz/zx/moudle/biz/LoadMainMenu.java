package wz.cifz.zx.moudle.biz;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wz.cifz.zx.http.HttpApiManager;
import wz.cifz.zx.moudle.IBean.IMainMenu;
import wz.cifz.zx.moudle.bean.MenuList;
import wz.cifz.zx.presenter.BasePresenter;
import wz.cifz.zx.ui.ILoad.ILoad;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

public class LoadMainMenu  implements IMainMenu {

    @Override
    public void getDataFromServer(final ILoad iLoad, final BasePresenter pre) {
        HttpApiManager.getInstence().getDataCaipu()
                .getMenuList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MenuList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        pre.addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull MenuList menuList) {
                            iLoad.loadSucceed(menuList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("neteroor",e.getMessage());
                        iLoad.loadFailed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
