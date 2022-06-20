package com.tobery.personalmusic.ui.splash;

import static com.tobery.personalmusic.util.Constant.AUTH_TOKEN;
import static com.tobery.personalmusic.util.Constant.DENIED_PERMISSION;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivitySplashBinding;
import com.tobery.personalmusic.ui.home.MainActivity;
import com.tobery.personalmusic.ui.login.LoginActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.SharePreferencesUtil;

import java.util.ArrayList;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding binding;

    ActivityResultLauncher<String[]> requestPermissionLauncher;

    ActivityResultLauncher<Intent> requestIntentLauncher;

    private AlertDialog dialog;

    private final String[] APP_PERMISSIONS = new ArrayList<String>(){
        {
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }.toArray(new String[0]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                requestPermissionLauncher.launch(APP_PERMISSIONS);
            }
        },2500);
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            if (!result.toString().contains("false")){
                String token = SharePreferencesUtil.getInstance(this).getAuthToken(AUTH_TOKEN);
                if (TextUtils.isEmpty(token)) {
                    binding.group.setVisibility(View.GONE);
                    binding.groupTop.setVisibility(View.VISIBLE);
                } else {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
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
        });

        requestIntentLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
            if (result.getResultCode() == RESULT_OK){
                requestPermissionLauncher.launch(APP_PERMISSIONS);
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
        requestPermissionLauncher = null;
        if (dialog !=null){
            dialog.dismiss();
            dialog = null;
        }
    }
}