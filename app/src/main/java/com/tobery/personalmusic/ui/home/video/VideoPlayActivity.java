package com.tobery.personalmusic.ui.home.video;

import static com.tobery.personalmusic.util.Constant.LIVE_INFO;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.databinding.ActivityVideoPlayBinding;
import com.tobery.personalmusic.entity.home.LookLiveEntity;
import com.tobery.personalmusic.util.StatusBarUtil;

import xyz.doikki.videocontroller.StandardVideoController;

public class VideoPlayActivity extends BaseActivity {

    private ActivityVideoPlayBinding binding;

    private LookLiveEntity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        data = (LookLiveEntity) getIntent().getSerializableExtra(LIVE_INFO);
        binding.setLive(data);
        binding.player.setUrl(data.getLiveUrl().getHlsPullUrl());
        StandardVideoController controller = new StandardVideoController(this);
        controller.addDefaultControlComponent(data.getTitle(), true);
        binding.player.setVideoController(controller); //设置控制器
        binding.player.start(); //开始播放，不调用则不自动播放
        Glide.with(this).load(data.getBgCoverUrl()).centerCrop().into(binding.imgBg);
        //填充状态栏
        StatusBarUtil.setTransparentForWindow(this);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.imgUser.getLayoutParams();
        params.topMargin = StatusBarUtil.getStatusBarHeight(this);
        binding.imgUser.setLayoutParams(params);
        binding.imgClose.setOnClickListener(view -> {
            binding.player.release();
            finish();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.player.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.player.release();
    }
}