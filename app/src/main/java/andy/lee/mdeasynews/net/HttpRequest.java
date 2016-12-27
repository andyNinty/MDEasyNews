package andy.lee.mdeasynews.net;

import java.util.concurrent.TimeUnit;

import andy.lee.mdeasynews.base.NetConfig;
import andy.lee.mdeasynews.bean.HttpResult;
import andy.lee.mdeasynews.bean.NewsList;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * andy.lee.easynews.net
 * Created by andy on 16-11-7.
 */

public class HttpRequest {

    private Retrofit mRetrofit;
    private NewsApi mNewsApi;

    private static HttpRequest instance = null;


    public static HttpRequest getInstance() {
        synchronized (HttpRequest.class) {
            if (instance == null) {
                instance = new HttpRequest();
            }
        }
        return instance;
    }


    private HttpRequest() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(NetConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(NetConfig.BASE_URL)
                .build();

        mNewsApi = mRetrofit.create(NewsApi.class);
    }

    public void getNews(Subscriber<HttpResult<NewsList>> subscriber, int num, int page, String apikey) {
        mNewsApi.getNews(num, page, apikey)
//                .map(new HttpResultFunc<List<NewsList>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCode() != 0) {
                throw new ApiException(httpResult.getCode());
            }
            return (T) httpResult.getNewslist();
        }
    }


}
