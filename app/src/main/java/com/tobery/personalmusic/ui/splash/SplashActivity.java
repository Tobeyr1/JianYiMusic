package com.tobery.personalmusic.ui.splash;

import static com.tobery.personalmusic.util.Constant.AUTH_TOKEN;
import static com.tobery.personalmusic.util.Constant.DENIED_PERMISSION;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;

import com.hjq.toast.ToastUtils;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.PermissionChecks;
import com.tobery.musicplay.PlayConfig;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivitySplashBinding;
import com.tobery.personalmusic.entity.RefreshLogin;
import com.tobery.personalmusic.http.Retrofit.RetrofitUtils;
import com.tobery.personalmusic.ui.home.MainActivity;
import com.tobery.personalmusic.ui.login.LoginActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.SharePreferencesUtil;

import java.util.ArrayList;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;

    ActivityResultLauncher<Intent> requestIntentLauncher;

    private AlertDialog dialog;

    PermissionChecks checks;

    private final String[] APP_PERMISSIONS = new ArrayList<String>(){
        {
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            add(Manifest.permission.READ_EXTERNAL_STORAGE);
            add(Manifest.permission.RECORD_AUDIO);
            add(Manifest.permission.READ_PHONE_STATE);
        }
    }.toArray(new String[0]);

    private PlayConfig playConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checks = new PermissionChecks(this);
        playConfig = new PlayConfig();
        initView();
    }

    private void initView() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPermissions();
            }
        },2500);

        requestIntentLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
            if (result.getResultCode() == RESULT_OK){
                checkPermissions();
            }
        });

        binding.btnPhoneLogin.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkPermissions() {
        checks.requestPermissions(APP_PERMISSIONS, it ->{
            if (it){
                MusicPlay.initConfig(this,playConfig);
                String token = SharePreferencesUtil.getInstance(this).getAuthToken(AUTH_TOKEN);
                if (TextUtils.isEmpty(token)) {
                    binding.group.setVisibility(View.GONE);
                    binding.groupTop.setVisibility(View.VISIBLE);
                } else {
                    RetrofitUtils.getmApiUrl().refresh().observe(this, refreshLoginApiResponse -> {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    });
                }
            }else {
                int lastDeniedNum = SharePreferencesUtil.getInstance(this).getPermissionDeniedNum(DENIED_PERMISSION);
                int currentDeniedNum = lastDeniedNum +1;
                SharePreferencesUtil.getInstance(this).savePermissionDeniedNum(DENIED_PERMISSION,currentDeniedNum);
                ToastUtils.show(getString(R.string.permission_denied));
                if (currentDeniedNum >= 2){
                    showExplain();
                }else {
                    finish();
                }
            }
            return null;
        });
    }

    private void showExplain() {
        dialog = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.need_ask_permission))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package",getPackageName(),null);
                        intent.setData(uri);
                        requestIntentLauncher.launch(intent);
                    }
                })
                .setNegativeButton(getString(R.string.btn_denied), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                })
                .create();
        dialog.show();
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