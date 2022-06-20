package com.tobery.personalmusic.ui.login;

import static com.tobery.personalmusic.util.Constant.KEY_LOGIN_UI;

import androidx.databinding.ObservableField;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.tobery.personalmusic.entity.Login_Bean;
import com.tobery.personalmusic.http.Retrofit.RetrofitUtils;

import io.reactivex.Observable;

/**
 * @Package: com.tobery.personalmusic.ui
 * @ClassName: LoginViewModel
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/12 23:20
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 23:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginViewModel extends ViewModel {
    private SavedStateHandle state;

    public LoginUi ui;


    public LoginViewModel(SavedStateHandle savedStateHandle) {
        this.state = savedStateHandle;
        ui = state.get(KEY_LOGIN_UI) == null?new LoginUi(new ObservableField<>(""),new ObservableField<>("")):state.get(KEY_LOGIN_UI);
    }

    public Observable<Login_Bean> login() {
        return RetrofitUtils.getmApiUrl().login(ui.userName.get(),ui.password.get());
    }


}

