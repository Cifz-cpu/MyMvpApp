package wz.cifz.zx.ui.fragment.component;

import dagger.Component;
import wz.cifz.zx.injecct.moudle.EduModule;
import wz.cifz.zx.ui.fragment.EduLiveFragment;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

@Component(modules = EduModule.class)
public interface EduComponent {
    void inject(EduLiveFragment fragmentf);
}
