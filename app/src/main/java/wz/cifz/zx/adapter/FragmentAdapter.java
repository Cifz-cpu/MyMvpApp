package wz.cifz.zx.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;

import wz.cifz.zx.ui.fragment.BaseFragment;

/**
 * desc  fragment 适配器
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/24.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    Context mContext;
    ArrayList<Fragment> list;

    public FragmentAdapter(Context context,FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


}
