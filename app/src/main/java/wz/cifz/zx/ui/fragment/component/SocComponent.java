package wz.cifz.zx.ui.fragment.component;

import dagger.Component;
import wz.cifz.zx.injecct.moudle.SocModule;
import wz.cifz.zx.ui.fragment.SocLiveFragment;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

@Component(modules = SocModule.class)
public interface SocComponent {
    void inject(SocLiveFragment fragment);
}
