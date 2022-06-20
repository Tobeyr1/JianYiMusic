package com.tobery.personalmusic.ui.home.discover;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.entity.banner_bean;
import com.tobery.personalmusic.http.Retrofit.RetrofitUtils;

import java.util.Calendar;

/**
 * @Package: com.tobery.personalmusic.ui.home.discover
 * @ClassName: DiscoverFragmentViewModel
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/17 0:25
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/17 0:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DiscoverFragmentViewModel extends ViewModel {

    public String date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";

    public LiveData<ApiResponse<banner_bean>> getBanner(){
        return RetrofitUtils.getmApiUrl().getBanner(2);
    }

}
