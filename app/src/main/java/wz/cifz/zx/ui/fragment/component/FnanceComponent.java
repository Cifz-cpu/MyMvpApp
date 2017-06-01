package wz.cifz.zx.ui.fragment.component;


import dagger.Component;
import wz.cifz.zx.injecct.moudle.FinanceModule;
import wz.cifz.zx.injecct.moudle.PeoModule;
import wz.cifz.zx.ui.fragment.FinanceLiveFragment;
import wz.cifz.zx.ui.fragment.PeoLiveFragment;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

@Component(modules = FinanceModule.class)
public interface FnanceComponent {
    void inject(FinanceLiveFragment fragment);
}
