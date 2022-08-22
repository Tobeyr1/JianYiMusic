package com.tobery.personalmusic.ui.home.video;

import static com.tobery.personalmusic.util.Constant.LIVE_INFO;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.databinding.ActivityVideoPlayBinding;
import com.tobery.personalmusic.entity.home.LookLiveEntity;

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
        binding.player.setUrl(data.getLiveUrl().getHlsPullUrl());
        StandardVideoController controller = new StandardVideoController(this);
        controller.addDefaultControlComponent(data.getTitle(), true);
        binding.player.setVideoController(controller); //设置控制器
        binding.player.start(); //开始播放，不调用则不自动播放
        Glide.with(this).load(data.getBgCoverUrl()).centerCrop().into(binding.imgBg);
    }
}