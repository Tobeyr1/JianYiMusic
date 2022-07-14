package com.tobery.personalmusic.ui.daily;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.entity.home.RecommendListEntity;
import com.tobery.personalmusic.http.Retrofit.RetrofitUtils;

public class MinePlayListViewModel extends ViewModel {

    private SavedStateHandle state;

    public MinePlayListViewModel(SavedStateHandle savedStateHandle) {
        this.state = savedStateHandle;
    }

    public LiveData<ApiResponse<RecommendListEntity>> getPlayList(){
        return RetrofitUtils.getmApiUrl().getPlayList(531135835);
    }
}
