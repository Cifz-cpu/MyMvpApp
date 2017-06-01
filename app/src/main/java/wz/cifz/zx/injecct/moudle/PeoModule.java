package wz.cifz.zx.injecct.moudle;

import dagger.Module;
import dagger.Provides;
import wz.cifz.zx.ui.fragment.IView.IFragmentView;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

@Module
public class PeoModule {
    private final IFragmentView mView;

    public PeoModule(IFragmentView view) {
        mView = view;
    }
    @Provides
    IFragmentView provideIFragmentAmuse() {
        return mView;
    }
}
