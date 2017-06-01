package wz.cifz.zx.injecct.moudle;

import dagger.Module;
import dagger.Provides;
import wz.cifz.zx.ui.IViewBinder.INewDetail;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/31.
 */

@Module
public class NewsDetailModule {
    private final INewDetail iNewDetail;

    public NewsDetailModule(INewDetail iNewDetail) {
        this.iNewDetail = iNewDetail;
    }

    @Provides
    INewDetail providesINewDetail(){
        return iNewDetail;
    }

}
