package com.tobery.personalmusic.http;


import androidx.lifecycle.LiveData;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.entity.LoginEntity;
import com.tobery.personalmusic.entity.RefreshCookieEntity;
import com.tobery.personalmusic.entity.SearchHotDetail_Bean;
import com.tobery.personalmusic.entity.UserDetailEntity;
import com.tobery.personalmusic.entity.home.HomeDiscoverEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("login/cellphone")
        //手机登录
    Observable<LoginEntity> login(@Query("phone") String phone, @Query("password") String password);

    @GET("search/hot/detail")
    Observable<SearchHotDetail_Bean> getSearchHotDetail();

    @GET("user/detail")
        //用户信息
    LiveData<ApiResponse<UserDetailEntity>> getUserDetails(@Query("uid") long userId);

    @GET("login/refresh")//刷新登录
    LiveData<ApiResponse<RefreshCookieEntity>> refreshLogin();

    @GET("homepage/block/page")//首页发现
    LiveData<ApiResponse<HomeDiscoverEntity>> requireHomeDiscover(@Query("refresh") Boolean start);

}
