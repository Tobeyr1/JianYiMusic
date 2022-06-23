package com.tobery.personalmusic.http.Retrofit;

import androidx.annotation.NonNull;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tobery.livedata.call.livedatalib.LiveDataCallAdapterFactory;
import com.tobery.personalmusic.http.ApiService;
import com.tobery.personalmusic.util.Constant;
import com.tobery.personalmusic.util.ContextProvider;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    /**
     * 单例模式
     */
    public static ApiService apiService;
    public static ApiService getmApiUrl(){
        if (apiService == null){
            synchronized (RetrofitUtils.class){
                if (apiService == null){
                    apiService = new RetrofitUtils().getRetrofit();
                }
            }
        }
        return apiService;
    }

    private ApiService getRetrofit() {
        //初始化Retrofit
        ApiService apiService = initRetrofit(initOkHttp()).create(ApiService.class);
        return apiService;
    }

    private OkHttpClient initOkHttp() {
        File cacheDir = ContextProvider.get().getContext().getCacheDir();
        long cacheSize = 10L * 1024L * 1024L; // 10 MiB

        //ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.getContext()));
        return new OkHttpClient().newBuilder()
                .readTimeout(Constant.DEFAULT_TIME, TimeUnit.SECONDS) //设置读取超时时间
                .connectTimeout(Constant.DEFAULT_TIME,TimeUnit.SECONDS) //设置请求超时时间
                .writeTimeout(Constant.DEFAULT_TIME,TimeUnit.SECONDS) //设置写入超时时间
                .addInterceptor(new LogInterceptor())  //添加打印拦截器
                .retryOnConnectionFailure(true) //设置出错进行重新连接
                .cache(new Cache(cacheDir, cacheSize))
                //.cookieJar(cookieJar)
                .build();
    }

    /**
     * 初始化Retrofit
     */
    @NonNull
    private Retrofit initRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Constant.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



}
