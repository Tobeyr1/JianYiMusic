package com.tobery.personalmusic.ui.song;


import static android.media.MediaPlayer.MetricsConstants.PLAYING;
import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.MusicInfo;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.OnMusicPlayProgressListener;
import com.tobery.musicplay.OnMusicPlayStateListener;
import com.tobery.musicplay.PlayManger;
import com.tobery.musicplay.ViewExtensionKt;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivityCurrentSongPlayBinding;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.StatusBarUtil;
import com.tobery.personalmusic.util.TimeUtil;

import jp.wasabeef.glide.transformations.BlurTransformation;

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
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);
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
                        binding.lrc.setListener(time -> {
                            MusicPlay.seekTo(time,true);
                            return true;
                        });
                    }
                });
    }

    private void initView() {
        musicInfo = getIntent().getParcelableExtra(MUSIC_INFO);
        initImageBg();
        MusicPlay.playMusicByInfo(musicInfo);
        loadUi();
        binding.viewBody.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                viewModel.isShowLrc = !viewModel.isShowLrc;
                showLyrics(viewModel.isShowLrc);
            }
        });
        //进度
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                MusicPlay.seekTo(seekBar.getProgress(),true);
                binding.lrc.updateTime(seekBar.getProgress());
            }
        });
        binding.lrc.setCoverChangeListener(()->{
            viewModel.isShowLrc = false;
            showLyrics(false);
        });
        binding.ivPlay.setOnClickListener(view -> {
            //MusicPlay.
        });
    }

    private void initImageBg() {
        Glide.with(this).load(musicInfo.getSongCover()).circleCrop().into(binding.ivMusicCover);
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .bitmapTransform(new BlurTransformation(25, 25));
       /* Glide.with(this)
                .load(musicInfo.getSongCover())
                .apply(options)
         .transition(new DrawableTransitionOptions().crossFade(1500))
                 .into(binding.imgBc);*/
        Glide.with(this)
                .asBitmap()
                .load(musicInfo.getSongCover())
                .transition(BitmapTransitionOptions.withCrossFade(3000))
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        binding.imgBc.setImageBitmap(resource);
                        Palette.from(resource)
                                .setRegion(10,10,10,10)
                                .maximumColorCount(3)
                                .generate(palette -> {

                                });
                    }
                });
        StatusBarUtil.setTranslucentForImageView(this,0,binding.viewTitleBg);
    }

    private void loadUi() {
        binding.tvTitle.setText(musicInfo.getSongName());
        MusicPlay.onPlayStateListener(this, new OnMusicPlayStateListener() {
            @Override
            public void onPlayState(@NonNull PlayManger playManger) {
                switch (playManger.getStage()){
                    case PlayManger.PAUSE:
                    case PlayManger.IDLE:
                        rotationAnim.cancel();
                        binding.ivPlay.setImageResource(R.drawable.shape_play_white);
                        break;
                    case PlayManger.PLAYING:
                        rotationAnim.start();
                        binding.ivPlay.setImageResource(R.drawable.shape_pause_white);
                        break;
                    case PlayManger.BUFFERING:
                        ViewExtensionKt.printLog("缓冲");
                        break;
                    case PlayManger.SWITCH:
                        ViewExtensionKt.printLog(playManger.getSongInfo().getSongName());
                        break;

                }
            }
        });
        MusicPlay.onPlayProgressListener(new OnMusicPlayProgressListener() {
            @Override
            public void onPlayProgress(long curP, long duration) {
                if (binding.seekBar.getMax() != duration){
                    binding.seekBar.setMax((int) duration);
                    binding.tvTotalTime.setText(TimeUtil.getTimeNoYMDH(duration));
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rotationAnim.cancel();
        rotationAnim.removeAllListeners();
        rotationAnim = null;
    }
}