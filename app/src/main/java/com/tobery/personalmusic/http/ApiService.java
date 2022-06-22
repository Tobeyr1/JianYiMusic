package com.tobery.personalmusic.http;


import androidx.lifecycle.LiveData;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.entity.Login_Bean;
import com.tobery.personalmusic.entity.MainRecommendListBean;
import com.tobery.personalmusic.entity.SearchHotDetail_Bean;
import com.tobery.personalmusic.entity.banner_bean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface  ApiService {
    @GET("banner") // 首页轮播
    LiveData<ApiResponse<banner_bean>> getBanner(@Query("type") int type);
    @GET("recommend/resource") //推荐歌单
    LiveData<ApiResponse<MainRecommendListBean>> getRecommendPlayList();
    @GET("login/cellphone")
    Observable<Login_Bean> login(@Query("phone") String phone, @Query("password") String password);
    @GET("search/hot/detail")
    Observable<SearchHotDetail_Bean> getSearchHotDetail();
    @POST("http://tobeyr1.cn/record/crashAdd")
    Observable<Login_Bean> report(@Query("crash_info")String ex, @Query("crash_time")String time, @Query("device_info")String deviceInfo);
}
