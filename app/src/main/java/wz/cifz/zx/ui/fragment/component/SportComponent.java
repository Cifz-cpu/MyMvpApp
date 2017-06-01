package wz.cifz.zx.ui.fragment.component;

import dagger.Component;
import wz.cifz.zx.injecct.moudle.SportModule;
import wz.cifz.zx.ui.fragment.SportLiveFragment;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

@Component(modules = SportModule.class)
public interface SportComponent {
    void inject(SportLiveFragment fragment);
}
