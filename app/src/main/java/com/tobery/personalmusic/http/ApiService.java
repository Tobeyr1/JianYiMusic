package com.tobery.personalmusic.http;


import androidx.lifecycle.LiveData;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.entity.LoginEntity;
import com.tobery.personalmusic.entity.LyricEntity;
import com.tobery.personalmusic.entity.RefreshCookieEntity;
import com.tobery.personalmusic.entity.RefreshLogin;
import com.tobery.personalmusic.entity.SearchHotDetail_Bean;
import com.tobery.personalmusic.entity.UserDetailEntity;
import com.tobery.personalmusic.entity.home.DailySongsEntity;
import com.tobery.personalmusic.entity.home.HomeDiscoverEntity;
import com.tobery.personalmusic.entity.home.RecentSongInfoEntity;
import com.tobery.personalmusic.entity.home.RecommendListEntity;
import com.tobery.personalmusic.entity.user.UserPlayEntity;
import com.tobery.personalmusic.entity.user.VipInfoEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("login/cellphone")//手机登录
    Observable<LoginEntity> login(@Query("phone") String phone, @Query("password") String password);

    @GET("search/hot/detail")
    Observable<SearchHotDetail_Bean> getSearchHotDetail();

    @GET("user/detail")//用户信息
    LiveData<ApiResponse<UserDetailEntity>> getUserDetails(@Query("uid") long userId);

    @GET("login/refresh")//刷新登录
    LiveData<ApiResponse<RefreshCookieEntity>> refreshLogin();

    @GET("homepage/block/page")//首页发现
    LiveData<ApiResponse<HomeDiscoverEntity>> requireHomeDiscover(@Query("refresh") Boolean start);

    @GET("vip/info")//vip信息
    LiveData<ApiResponse<VipInfoEntity>> getVipInfo();

    @GET("lyric") //获取歌词
    LiveData<ApiResponse<LyricEntity>> getLyric(@Query("id") long songId);

    @GET("playlist/detail")//获取歌单详情列表
    LiveData<ApiResponse<RecommendListEntity>> getPlayList(@Query("id") long creativeId);

    @GET("record/recent/song") //最近播放音乐
    LiveData<ApiResponse<RecentSongInfoEntity>> getRecentSong(@Query("limit") int limit);

    @GET("recommend/songs") //获取日推
    LiveData<ApiResponse<DailySongsEntity>> getDailySongs();

    @GET("login/refresh")//刷新登录
    LiveData<ApiResponse<RefreshLogin>> refresh();

    @GET("user/playlist") //用户歌单
    LiveData<ApiResponse<UserPlayEntity>> getUserPlayList(@Query("uid") long userId);

}
