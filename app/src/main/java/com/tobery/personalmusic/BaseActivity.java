package com.tobery.personalmusic;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 *
 * 基类
 * @version  V1.0.0
 * @author Tobey_R1
 * @since 2020年9月3日
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimary),0);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        System.gc();
        super.onDestroy();
    }
}
