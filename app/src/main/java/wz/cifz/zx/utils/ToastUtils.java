package wz.cifz.zx.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * desc Toast管理
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/22.
 */
public class ToastUtils {

    private static Context sContext;
    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    private ToastUtils() {
        throw new RuntimeException("ToastUtils cannot be initialized!");
    }

    public static void init(Context context) {
        sContext = context;
    }

    public static void showToast(String s) {
        if (toast == null) {
            toast = Toast.makeText(sContext, s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
            oneTime = twoTime;
        }
    }

    public static void showToast(int resId) {
        showToast(sContext.getString(resId));
    }
}
