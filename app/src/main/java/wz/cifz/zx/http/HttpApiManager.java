package wz.cifz.zx.http;


import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import wz.cifz.zx.MyApplication;
import wz.cifz.zx.utils.GlobeConfig;
import wz.cifz.zx.utils.NetUtil;

/**
 * desc 网络请求管理
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/22.
 */

public class HttpApiManager {

    private static HttpApiManager httpApiManager;
    private IRetrofitApi iRetrofitApi;
    private IRetrofitPic iRetrofitPic;

    //设缓存有效期为1天
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    public static HttpApiManager getInstence(){
        if (httpApiManager == null) {
            synchronized (HttpApiManager.class) {
                if (httpApiManager == null) {
                    httpApiManager = new HttpApiManager();
                }
            }
        }
        return httpApiManager;
    }

    /**
     * 配置网络请求
     * @return
     */
    public IRetrofitApi getDataCaipu(){
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(MyApplication.getApplication().getCacheDir(), "NewsApp"),
                1024 * 1024 * 100);
        OkHttpClient client = new OkHttpClient.Builder().cache(cache)
                .retryOnConnectionFailure(true)
                //添加应用拦截器
                .addInterceptor(new HttpInterceptor())
                .addInterceptor(new SLoggingInterceptor())
//                .addInterceptor(sRewriteCacheControlInterceptor)
////                //添加网络拦截器
//                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        if(iRetrofitApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GlobeConfig.baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            iRetrofitApi = retrofit.create(IRetrofitApi.class);
        }
        return iRetrofitApi;
    }

    /**
     * 配置图片请求
     * @return
     */
    public IRetrofitPic getDataPic(){
        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(MyApplication.getApplication().getCacheDir(), "NewsApp"),
                1024 * 1024 * 100);
        OkHttpClient client = new OkHttpClient.Builder().cache(cache)
                .retryOnConnectionFailure(true)
                //添加应用拦截器
                .addInterceptor(new HttpInterceptor())
                .addInterceptor(new SLoggingInterceptor())
//                .addInterceptor(sRewriteCacheControlInterceptor)
//                //添加网络拦截器
//                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        if(iRetrofitPic == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GlobeConfig.PicUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            iRetrofitPic = retrofit.create(IRetrofitPic.class);
        }
        return iRetrofitPic;
    }

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable(MyApplication.getApplication())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Logger.e("no network");
            }
            Response originalResponse = chain.proceed(request);

            if (NetUtil.isNetworkAvailable(MyApplication.getApplication())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

}
