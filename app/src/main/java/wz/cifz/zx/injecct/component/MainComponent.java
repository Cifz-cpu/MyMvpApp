package wz.cifz.zx.injecct.component;


import dagger.Component;
import wz.cifz.zx.injecct.moudle.MainModule;
import wz.cifz.zx.ui.view.MainActivity;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
