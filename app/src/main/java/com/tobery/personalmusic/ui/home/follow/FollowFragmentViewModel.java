package com.tobery.personalmusic.ui.home.follow;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.entity.follow.DynamicListEntity;
import com.tobery.personalmusic.entity.follow.FollowListEntity;
import com.tobery.personalmusic.http.Retrofit.RetrofitUtils;

import retrofit2.http.Query;

public class FollowFragmentViewModel extends ViewModel {

    public LiveData<ApiResponse<FollowListEntity>> getFollowsList(Long uid){
        return RetrofitUtils.getmApiUrl().getFollowsList(uid);
    }

    public LiveData<ApiResponse<DynamicListEntity>> getDynamicList(int pageSize,Long lastTime){
        return RetrofitUtils.getmApiUrl().getDynamicList(pageSize, lastTime);
    }

}
