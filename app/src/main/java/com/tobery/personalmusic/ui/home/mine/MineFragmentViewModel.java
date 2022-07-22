package com.tobery.personalmusic.ui.home.mine;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.entity.user.VipInfoEntity;
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
public class MineFragmentViewModel extends ViewModel {

    private SavedStateHandle state;
    public ObservableField<String> mineLikeCover = new ObservableField<>("");
    public ObservableField<String> trackCount = new ObservableField<>("");
    public ObservableField<String> level = new ObservableField<>("");
    public Long userLikeCreator = 0L;

    public MineFragmentViewModel(SavedStateHandle savedStateHandle) {
        this.state = savedStateHandle;
    }


    public LiveData<ApiResponse<VipInfoEntity>> getVipInfo(){
        return RetrofitUtils.getmApiUrl().getVipInfo();
    }

}
