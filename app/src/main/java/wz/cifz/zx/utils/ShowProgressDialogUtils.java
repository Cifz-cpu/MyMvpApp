package wz.cifz.zx.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;

import wz.cifz.zx.MyApplication;
import wz.cifz.zx.R;


/**
 * Created by cifz on 2017/4/5.
 * e_mail wangzhen1798@gmail.com
 */

public class ShowProgressDialogUtils {

    static ProgressDialog progressDialog;

    public static void ShowYuyueDialog(Activity act,String message){
        progressDialog = new ProgressDialog(act, R.style.LoadingDialog);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void cancleDialog(){
        if(progressDialog.isShowing()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            },1000);

        }
    }

}
