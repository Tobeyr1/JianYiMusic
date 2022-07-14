package com.tobery.personalmusic.ui.home;


import static com.tobery.personalmusic.util.Constant.KEY_MAIN_UI;


import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.musicplay.MusicInfo;
import com.tobery.personalmusic.entity.LoginEntity;
import com.tobery.personalmusic.entity.UserDetailEntity;
import com.tobery.personalmusic.entity.home.RecentSongInfoEntity;
import com.tobery.personalmusic.entity.home.RecommendListEntity;
import com.tobery.personalmusic.http.Retrofit.RetrofitUtils;
import com.tobery.personalmusic.ui.home.menu.UserInfoUi;
import com.tobery.personalmusic.util.ContextProvider;
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
public class MainViewModel extends ViewModel {

    private SavedStateHandle state;

    public UserInfoUi ui;

    private String userInfo;

    public ObservableField<String> currentSongUrl = new ObservableField<>("");
    public ObservableField<String> currentSongName = new ObservableField<>("");
    public MusicInfo currentMusicInfo;

    public MainViewModel(SavedStateHandle savedStateHandle) {
        this.state = savedStateHandle;
        ui = state.get(KEY_MAIN_UI) == null ? new UserInfoUi(new ObservableField<>(""), new ObservableField<>(""), new ObservableInt(0), new ObservableField<>(""), new ObservableField<>(""), new ObservableField<>(""), new ObservableField<>("")) : state.get(KEY_MAIN_UI);
    }

    public void initUi() {
        userInfo = SharePreferencesUtil.getInstance(ContextProvider.get().getContext())
                .getUserInfo();
        LoginEntity data = new Gson().fromJson(userInfo, LoginEntity.class);
        if (null != data.getProfile()) {
            ui.nickname.set(data.getProfile().getNickname());
            ui.imageUrl.set(data.getProfile().getAvatarUrl());
            ui.userId.set(data.getProfile().getUserId());
            ui.signature.set(data.getProfile().getSignature());
            ui.follows.set(data.getProfile().getFollows()+"关注");
            ui.followeds.set(data.getProfile().getFolloweds()+"粉丝");
        }
    }

    public LiveData<ApiResponse<UserDetailEntity>> getUserDetails() {
        return RetrofitUtils.getmApiUrl().getUserDetails(ui.userId.get());
    }

    public LiveData<ApiResponse<RecentSongInfoEntity>> getRecentSong(){
        return RetrofitUtils.getmApiUrl().getRecentSong(1);
    }


}
