package com.tobery.personalmusic.ui.home.discover;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.entity.home.BannerExtInfoEntity;
import com.tobery.personalmusic.entity.home.HomeDiscoverEntity;
import com.tobery.personalmusic.entity.home.LookLiveEntity;
import com.tobery.personalmusic.http.Retrofit.RetrofitUtils;

import java.util.Calendar;
import java.util.List;

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

    public BannerExtInfoEntity bannerList;

    public List<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity> recommendList;

    public List<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity> selfMgcList;

    public List<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity> likeList;

    public List<LookLiveEntity>  lookLiveList;


    public LiveData<ApiResponse<HomeDiscoverEntity>> requireDiscover(Boolean refresh){
        return RetrofitUtils.getmApiUrl().requireHomeDiscover(refresh);
    }

}
