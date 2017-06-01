package wz.cifz.zx.injecct.component;

import dagger.Component;
import wz.cifz.zx.injecct.moudle.PicModule;
import wz.cifz.zx.ui.view.PicDetailActivity;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

@Component(modules = PicModule.class)
public interface PicComponent {
    void inject(PicDetailActivity activity);
}
