package wz.cifz.zx.http;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wz.cifz.zx.moudle.bean.MenuList;
import wz.cifz.zx.moudle.bean.NewsDetail;
import wz.cifz.zx.moudle.bean.NewsList;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/22.
 */

public interface IRetrofitApi {

    @GET("classify")
    Observable<MenuList> getMenuList();

    @GET("list")
    Observable<NewsList> getNewsList(@Query("id") int id,@Query("page") int page);

    @GET("show")
    Observable<NewsDetail> getNewsDetai(@Query("id") long id);

}
