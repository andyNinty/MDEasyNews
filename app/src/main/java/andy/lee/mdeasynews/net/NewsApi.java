package andy.lee.mdeasynews.net;

import andy.lee.mdeasynews.bean.HttpResult;
import andy.lee.mdeasynews.bean.NewsList;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * andy.lee.easynews.net
 * Created by andy on 16-11-7.
 */

public interface NewsApi {
    /**
     * 获得新的新闻
     * @param num
     * @param page
     * @return
     */
//    @GET("keji")
//    Call<HttpResult<NewsList>> getNews(@Query("num") int num, @Query("page") int page, @Header("apikey") String apikey);

    /**
     * 添加Rxjava之后的新写法
     * @param num
     * @param page
     * @param apikey
     * @return
     */


    @GET("keji")
    Observable<HttpResult<NewsList>> getNews(@Query("num") int num, @Query("page") int page, @Header("apikey") String apikey);
}

