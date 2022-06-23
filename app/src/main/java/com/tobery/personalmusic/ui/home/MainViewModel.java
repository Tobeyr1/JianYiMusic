package com.tobery.personalmusic.ui.home;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.entity.UserDetailEntity;
import com.tobery.personalmusic.http.Retrofit.RetrofitUtils;

/**
 * @Package: com.tobery.personalmusic.ui
 * @ClassName: MainViewModel
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/12 16:51
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 16:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MainViewModel extends ViewModel{

    private SavedStateHandle state;

    public MainViewModel(SavedStateHandle savedStateHandle) {
        this.state = savedStateHandle;
    }

    public LiveData<ApiResponse<UserDetailEntity>> getUserDetails(){
        return RetrofitUtils.getmApiUrl().getUserDetails(374898690);
    }


}
