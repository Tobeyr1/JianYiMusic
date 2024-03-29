package com.tobery.personalmusic.ui.song;


import static com.tobery.personalmusic.util.Constant.KEY_MAIN_UI;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableLong;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.musicplay.MusicPlay;
import com.tobery.personalmusic.entity.LoginEntity;
import com.tobery.personalmusic.entity.LrcEntry;
import com.tobery.personalmusic.entity.LyricEntity;
import com.tobery.personalmusic.entity.SongEntity;
import com.tobery.personalmusic.entity.UserDetailEntity;
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
public class SongPlayViewModel extends ViewModel {

    private SavedStateHandle state;

    public UserInfoUi ui;

    private String userInfo;

    public Boolean isShowLrc = false;

    public ObservableField<String> currentSongUrl = new ObservableField<>("");

    public ObservableLong currentSongId = new ObservableLong();

    public SongPlayViewModel(SavedStateHandle savedStateHandle) {
        this.state = savedStateHandle;
       // ui = state.get(KEY_MAIN_UI) == null ? new UserInfoUi(new ObservableField<>(""), new ObservableField<>(""), new ObservableInt(0), new ObservableField<>(""), new ObservableField<>(""), new ObservableField<>("")) : state.get(KEY_MAIN_UI);
    }


    public LiveData<ApiResponse<LyricEntity>> getLyric() {
        return RetrofitUtils.getmApiUrl().getLyric(currentSongId.get());
    }

    public LiveData<ApiResponse<SongEntity>> getSongInfo(long id){
        return RetrofitUtils.getmApiUrl().getSongUrl(id);
    }

    public void musicPlayOrPause(){
        if (MusicPlay.isPlaying()){
            MusicPlay.pauseMusic();
        }else {
            MusicPlay.restoreMusic();
        }
    }

    public void next(){
        MusicPlay.skipToNext();
    }

    public void previous(){
        MusicPlay.skipToPrevious();
    }

}
