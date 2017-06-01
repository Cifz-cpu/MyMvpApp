package wz.cifz.zx.injecct.component;

import dagger.Component;
import wz.cifz.zx.injecct.moudle.NewsDetailModule;
import wz.cifz.zx.ui.view.NewsDetailActivity;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/31.
 */
@Component(modules = NewsDetailModule.class)
public interface NewsComponent {
    void inject(NewsDetailActivity activity);
}
