package com.tobery.personalmusic.ui.home.mine;


import static com.tobery.personalmusic.util.Constant.KEY_MAIN_UI;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.entity.LoginEntity;
import com.tobery.personalmusic.entity.UserDetailEntity;
import com.tobery.personalmusic.entity.user.VipInfoEntity;
import com.tobery.personalmusic.http.Retrofit.RetrofitUtils;
import com.tobery.personalmusic.ui.home.menu.UserInfoUi;
import com.tobery.personalmusic.util.SharePreferencesUtil;

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

    public LiveData<ApiResponse<VipInfoEntity>> getVipInfo(){
        return RetrofitUtils.getmApiUrl().getVipInfo();
    }
}
