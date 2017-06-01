package wz.cifz.zx.injecct.moudle;

import dagger.Module;
import dagger.Provides;
import wz.cifz.zx.ui.IViewBinder.IMainAct;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

@Module
public class MainModule {
    private final IMainAct mView;

    public MainModule(IMainAct view) {
        mView = view;
    }

    @Provides
    IMainAct provideIMainAct() {
        return mView;
    }
}
