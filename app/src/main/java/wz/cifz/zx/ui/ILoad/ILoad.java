package wz.cifz.zx.ui.ILoad;


/**
 * desc 加载接口
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/22.
 */

public interface ILoad<T> {
    void loadSucceed(T data);
    void loadFailed();
}
