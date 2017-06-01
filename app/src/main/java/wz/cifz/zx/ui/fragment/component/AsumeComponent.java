package wz.cifz.zx.ui.fragment.component;

import dagger.Component;
import wz.cifz.zx.injecct.moudle.AsumeModule;
import wz.cifz.zx.ui.fragment.AmusementLiveFragment;
import wz.cifz.zx.ui.fragment.EduLiveFragment;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/27.
 */
@Component(modules = AsumeModule.class)
public interface AsumeComponent {
    void inject(AmusementLiveFragment fragment);
}
