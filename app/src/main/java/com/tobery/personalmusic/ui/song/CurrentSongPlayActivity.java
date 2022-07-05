package com.tobery.personalmusic.ui.song;


import static android.media.MediaPlayer.MetricsConstants.PLAYING;
import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.MusicInfo;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.OnMusicPlayProgressListener;
import com.tobery.musicplay.OnMusicPlayStateListener;
import com.tobery.musicplay.ViewExtensionKt;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivityCurrentSongPlayBinding;
import com.tobery.personalmusic.entity.LrcEntry;
import com.tobery.personalmusic.entity.LyricEntity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.StatusBarUtil;
import com.tobery.personalmusic.util.TimeUtil;

public class CurrentSongPlayActivity extends BaseActivity {

    private ActivityCurrentSongPlayBinding binding;
    private ObjectAnimator rotationAnim;
    private MusicInfo musicInfo;
    private SongPlayViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCurrentSongPlayBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(SongPlayViewModel.class);
        setContentView(binding.getRoot());
        initAnim();
        initView();
        initObserver();
    }

    private void initObserver() {
        viewModel.getLyric(Long.parseLong(musicInfo.getSongId()))
                .observe(this, lyricEntityApiResponse -> {
                    if (lyricEntityApiResponse.getStatus() == Status.SUCCESS){
                        binding.lrc.loadLrc(lyricEntityApiResponse.getData().getLrc().getLyric(),lyricEntityApiResponse.getData().getTlyric().getLyric());
                    }
                });
    }

    private void initView() {
        musicInfo = getIntent().getParcelableExtra(MUSIC_INFO);
        Glide.with(this).load(musicInfo.getSongCover()).circleCrop().into(binding.ivMusicCover);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
               // .transform(new );
        Glide.with(this)
                .load(musicInfo.getSongCover())
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade(1500))
                .into(binding.imgBc);
        StatusBarUtil.setTranslucentForImageView(this,0,binding.viewTitleBg);
        MusicPlay.playMusicByInfo(musicInfo);
        loadUi();
        binding.viewBody.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                viewModel.isShowLrc = !viewModel.isShowLrc;
                showLyrics(viewModel.isShowLrc);
            }
        });
    }

    private void loadUi() {
        MusicPlay.onPlayStateListener(this, new OnMusicPlayStateListener() {
            @Override
            public void onPlayState(@NonNull String s) {
                switch (s){
                    case "PLAYING":
                        rotationAnim.start();
                        break;
                    case "BUFFERING":

                        break;
                    case "PAUSE":
                    case "IDLE":
                        rotationAnim.cancel();
                        break;
                }
            }
        });
        MusicPlay.onPlayProgressListener(this, new OnMusicPlayProgressListener() {
            @Override
            public void onPlayProgress(long curP, long duration) {
                if (binding.seekBar.getMax() != duration){
                    binding.seekBar.setMax((int) duration);
                }
                binding.tvPastTime.setText(TimeUtil.getTimeNoYMDH(curP));
                binding.lrc.updateTime(curP);
                binding.seekBar.setProgress((int) curP);
            }
        });
    }

    //根据isShowLyrics来判断是否展示歌词
    private void showLyrics(boolean isShowLyrics) {
        binding.ivMusicCover.setVisibility(isShowLyrics ? View.GONE : View.VISIBLE);
        binding.lrc.setVisibility(isShowLyrics ? View.VISIBLE : View.GONE);
    }

    private void initAnim() {
        rotationAnim = ObjectAnimator.ofFloat(binding.ivMusicCover,"rotation",360f);
        rotationAnim.setInterpolator(new LinearInterpolator());
        rotationAnim.setDuration(25000);
        rotationAnim.setRepeatCount(100000);
        rotationAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                super.onAnimationEnd(animation, isReverse);
                rotationAnim.start();
            }
        });
        //rotationAnim.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rotationAnim.cancel();
        rotationAnim.removeAllListeners();
        rotationAnim = null;
    }
}