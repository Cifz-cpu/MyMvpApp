package wz.cifz.zx.injecct.moudle;

import dagger.Module;
import dagger.Provides;
import wz.cifz.zx.ui.IViewBinder.IPicAct;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

@Module
public class PicModule {
    private final IPicAct mView;

    public PicModule(IPicAct view) {
        mView = view;
    }

    @Provides
    IPicAct provideIMainAct() {
        return mView;
    }
}
