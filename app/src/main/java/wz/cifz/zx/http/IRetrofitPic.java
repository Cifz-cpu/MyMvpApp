package wz.cifz.zx.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wz.cifz.zx.moudle.bean.AumsePic;
import wz.cifz.zx.moudle.bean.PicDetail;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

public interface IRetrofitPic {
    @GET("list")
    Observable<AumsePic> getAumsePic(@Query("id") int id,@Query("rows") int rows);

    @GET("show")
    Observable<PicDetail> getPicDetail(@Query("id") long id);
}
