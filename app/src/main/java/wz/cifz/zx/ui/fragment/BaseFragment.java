package wz.cifz.zx.ui.fragment;

import android.support.v4.app.Fragment;

import wz.cifz.zx.ui.fragment.IView.BaseIView;
import wz.cifz.zx.utils.ShowProgressDialogUtils;
import wz.cifz.zx.utils.ToastUtils;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/27.
 */

public abstract class BaseFragment extends Fragment implements BaseIView{


    @Override
    public void loadFailed() {
        ToastUtils.showToast("网络错误");
    }

    @Override
    public void ShowDialog() {
//        ShowProgressDialogUtils.ShowYuyueDialog(getActivity(),"正在加载");
    }

    @Override
    public void HideDialog() {
//        ShowProgressDialogUtils.cancleDialog();
    }


}
