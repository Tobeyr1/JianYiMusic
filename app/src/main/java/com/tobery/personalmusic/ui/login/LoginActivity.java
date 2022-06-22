package com.tobery.personalmusic.ui.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hjq.toast.ToastUtils;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivityLoginBinding;
import com.tobery.personalmusic.entity.Login_Bean;
import com.tobery.personalmusic.http.Retrofit.RXHelper;
import com.tobery.personalmusic.http.Retrofit.RxExceptionUtil;
import com.tobery.personalmusic.ui.home.MainActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.SharePreferencesUtil;
import com.tobey.dialogloading.AlertDialogUtil;

import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    private LoginViewModel viewModel;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setVm(viewModel);
        initView();
    }

    private void initView() {
        binding.title.ivBack.setOnClickListener(view -> finish());
        binding.btnLogin.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                if (Objects.requireNonNull(viewModel.ui.userName.get()).isEmpty() || viewModel.ui.password.get().isEmpty()){
                    ToastUtils.show(R.string.toast_login);
                    return;
                }
                dialog = AlertDialogUtil.Companion.createLoading(this,"登录中");
                viewModel.login()
                        .compose(RXHelper.observableIO2Main(this))
                        .subscribe(new Observer<Login_Bean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Login_Bean login_bean) {
                                Log.e("数据",login_bean.toString());
                                AlertDialogUtil.Companion.closeDialog(dialog);
                                if (login_bean.getCode() == 200){
                                    SharePreferencesUtil.getInstance(LoginActivity.this).saveUserInfo(login_bean,viewModel.ui.userName.get());
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else if (login_bean.getCode() == 502){
                                    ToastUtils.show(R.string.msg_password_error);
                                }else {
                                    ToastUtils.show(R.string.msg_user_error);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                AlertDialogUtil.Companion.closeDialog(dialog);
                                onFailure(e, RxExceptionUtil.exceptionHandler(e));
                            }

                            @Override
                            public void onComplete() {}
                        });


            }
        });
    }

    private void onFailure(Throwable e, String exceptionHandler) {
        ToastUtils.show(exceptionHandler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog !=null){
            dialog.dismiss();
            dialog = null;
        }
    }
}