package wz.cifz.zx.ui.fragment.component;


import dagger.Component;
import wz.cifz.zx.injecct.moudle.PeoModule;
import wz.cifz.zx.ui.fragment.AmusementLiveFragment;
import wz.cifz.zx.ui.fragment.PeoLiveFragment;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

@Component(modules = PeoModule.class)
public interface PeoComponent {
    void inject(PeoLiveFragment fragment);
}
