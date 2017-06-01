package wz.cifz.zx.ui.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import wz.cifz.zx.R;
import wz.cifz.zx.adapter.FragmentAdapter;
import wz.cifz.zx.injecct.component.DaggerMainComponent;
import wz.cifz.zx.injecct.moudle.MainModule;
import wz.cifz.zx.moudle.bean.MenuList;
import wz.cifz.zx.presenter.PreMainLoadMenu;
import wz.cifz.zx.ui.IViewBinder.IMainAct;
import wz.cifz.zx.ui.fragment.AmusementLiveFragment;
import wz.cifz.zx.ui.fragment.EduLiveFragment;
import wz.cifz.zx.ui.fragment.FinanceLiveFragment;
import wz.cifz.zx.ui.fragment.PeoLiveFragment;
import wz.cifz.zx.ui.fragment.SocLiveFragment;
import wz.cifz.zx.ui.fragment.SportLiveFragment;
import wz.cifz.zx.utils.ToastUtils;

@Route(path = "/main/main")
public class MainActivity extends AppCompatActivity implements IMainAct {

    @Inject
    PreMainLoadMenu preMainLoadMenu;

    ArrayList<Fragment> frList;
    FragmentAdapter fragmentAdapter;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.my_tab)
    TabLayout myTab;
    @BindView(R.id.my_pager)
    ViewPager myPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        load();
    }

    @Override
    public void replaceFragment() {

    }

    @Override
    public void load() {
        preMainLoadMenu.loadMenu();
    }

    @Override
    public void initView() {
        //注入
        DaggerMainComponent
                .builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    @Override
    public void setData(MenuList list) {
        AmusementLiveFragment amusementLiveFragment = new AmusementLiveFragment();
        EduLiveFragment eduLiveFragment = new EduLiveFragment();
        FinanceLiveFragment financeLiveFragment = new FinanceLiveFragment();
        PeoLiveFragment peoLiveFragment = new PeoLiveFragment();
        SocLiveFragment socLiveFragment = new SocLiveFragment();
        SportLiveFragment sportLiveFragment = new SportLiveFragment();

        frList = new ArrayList<>();

        frList.add(peoLiveFragment);
        frList.add(amusementLiveFragment);
        frList.add(financeLiveFragment);
        frList.add(sportLiveFragment);
        frList.add(eduLiveFragment);
        frList.add(socLiveFragment);


        fragmentAdapter = new FragmentAdapter(getApplicationContext(), getSupportFragmentManager(), frList);
        myPager.setOffscreenPageLimit(6);
        myPager.setAdapter(fragmentAdapter);
        myTab.setupWithViewPager(myPager, true);
        for (int i = 0; i < list.getTngou().size(); i++) {
            myTab.getTabAt(i).setText(list.getTngou().get(i).getName());
        }

    }

    @Override
    public void loadFailed() {
        ToastUtils.showToast("菜单加载失败");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu:
                ToastUtils.showToast("点击了");
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preMainLoadMenu.dispose();
    }

}