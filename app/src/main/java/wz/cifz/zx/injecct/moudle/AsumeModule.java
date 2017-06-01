package wz.cifz.zx.injecct.moudle;

import dagger.Module;
import dagger.Provides;
import wz.cifz.zx.ui.fragment.IView.IAumseView;
import wz.cifz.zx.ui.fragment.IView.IFragmentView;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

@Module
public class AsumeModule {
    private final IAumseView mView;

    public AsumeModule(IAumseView view) {
        mView = view;
    }
    @Provides
    IAumseView provideIFragmentAmuse() {
        return mView;
    }
}
