package wz.cifz.zx.injecct.moudle;

import dagger.Module;
import dagger.Provides;
import wz.cifz.zx.ui.fragment.IView.IFragmentView;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */
@Module
public class SocModule {
    private final IFragmentView iFragmentView;

    public SocModule(IFragmentView iFragmentView) {
        this.iFragmentView = iFragmentView;
    }

    @Provides
    IFragmentView providesIFragmentView(){
        return  iFragmentView;
    }

}
