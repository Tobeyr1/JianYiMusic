package com.tobery.personalmusic.ui.song;


import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hjq.toast.ToastUtils;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.OnMusicPlayProgressListener;
import com.tobery.musicplay.OnMusicPlayStateListener;
import com.tobery.musicplay.SpConstant;
import com.tobery.musicplay.entity.MusicInfo;
import com.tobery.musicplay.entity.PlayManger;
import com.tobery.musicplay.util.ViewExtensionKt;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivityCurrentSongPlayBinding;
import com.tobery.personalmusic.entity.SongEntity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.StatusBarUtil;
import com.tobery.personalmusic.util.TimeUtil;

import java.util.ArrayList;

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
        initView();
        initAnim();
        initObserver();
    }

    private void initObserver() {
        viewModel.getLyric()
                .observe(this, lyricEntityApiResponse -> {
                    ViewExtensionKt.printLog("当前"+lyricEntityApiResponse.getMessage());
                    if (lyricEntityApiResponse.getStatus() == Status.SUCCESS){
                        if (lyricEntityApiResponse.getData().getLrc() != null){
                            if (lyricEntityApiResponse.getData().getTlyric() != null){
                                binding.lrc.loadLrc(lyricEntityApiResponse.getData().getLrc().getLyric(),lyricEntityApiResponse.getData().getTlyric().getLyric());
                            }else {
                                binding.lrc.loadLrc(lyricEntityApiResponse.getData().getLrc().getLyric(),"");
                            }
                        }else {
                            binding.lrc.loadLrc("","");
                        }
                    }
                });
    }

    private void initView() {
        musicInfo = getIntent().getParcelableExtra(MUSIC_INFO);
        initImageBg(musicInfo);
        initListener();
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
        binding.ivPlayMode.setOnClickListener(v -> {
            if (ClickUtil.enableClick()){
                changeRepeatMode();
            }
        });
        binding.lrc.setListener(time -> {
            MusicPlay.seekTo(time,true);
            return true;
        });
        binding.lrc.setCoverChangeListener(()->{
            viewModel.isShowLrc = false;
            showLyrics(false);
        });
        binding.ivList.setOnClickListener(view -> {
            //MusicPlay.getPlayList()
        });
    }

    private void initImageBg(MusicInfo musicInfo) {
        viewModel.currentSongUrl.set(musicInfo.getSongCover());
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .bitmapTransform(new BlurTransformation(25, 30));
        viewModel.currentSongId.set(Long.parseLong(musicInfo.getSongId()));
        initObserver();
       /* Glide.with(this)
                .load(musicInfo.getSongCover())
                .apply(options)
         .transition(new DrawableTransitionOptions().crossFade(1500))
                 .into(binding.imgBc);*/
        Glide.with(this)
                .asBitmap()
                .load(musicInfo.getSongCover())
                //.placeholder()
                .transition(BitmapTransitionOptions.withCrossFade(1500))
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        binding.imgBc.setImageBitmap(resource);
                        StatusBarUtil.setTranslucentForImageView(CurrentSongPlayActivity.this,0,binding.viewTitleBg);
                        Palette.from(resource)
                                .setRegion(0,0,getScreenWidth(),getStatusBarHeight())
                                .maximumColorCount(6)
                                .generate(palette -> {
                                    Palette.Swatch mostPopularSwatch = null;
                                    for (Palette.Swatch swatch: palette.getSwatches()){
                                        if (mostPopularSwatch == null
                                        || swatch.getPopulation() > mostPopularSwatch.getPopulation()){
                                            mostPopularSwatch = swatch;
                                        }
                                    }
                                    if (mostPopularSwatch!= null){
                                        double luminance =ColorUtils.calculateLuminance(mostPopularSwatch.getRgb());
                                        // 当luminance小于0.5时，我们认为这是一个深色值.
                                        if (luminance < 0.5){
                                             setDarkStatusBar();
                                        }else {
                                            setLightStatusBar();
                                        }
                                    }
                                });
                    }
                });
        binding.tvTitle.setText(musicInfo.getSongName());
        binding.tvSinger.setText(musicInfo.getArtist());
    }

    private int getScreenWidth(){
        DisplayMetrics displayMetrics =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private int getStatusBarHeight(){
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0){
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initListener() {
        MusicPlay.onPlayStateListener(this, new OnMusicPlayStateListener() {
            @Override
            public void onPlayState(@NonNull PlayManger playManger) {
                switch (playManger.getStage()){
                    case PlayManger.PAUSE:
                    case PlayManger.IDLE:
                        rotationAnim.pause();
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
                        if (playManger.getSongInfo() != null){
                            initImageBg(playManger.getSongInfo());
                        }
                        break;
                    case PlayManger.ERROR:
                        MusicInfo musicInfo = playManger.getSongInfo();
                        int index = MusicPlay.getNowPlayingIndex();
                        ViewExtensionKt.printLog("当前下标"+index+"");
                        viewModel.getSongInfo(Long.parseLong(playManger.getSongInfo().getSongId()))
                                .observe(CurrentSongPlayActivity.this, songEntityApiResponse -> {
                                    if (songEntityApiResponse.getStatus() == Status.SUCCESS){
                                        if (songEntityApiResponse.getData().getData()!= null && songEntityApiResponse.getData().getData().get(0).getUrl() != null){
                                            musicInfo.setSongUrl(songEntityApiResponse.getData().getData().get(0).getUrl());
                                            MusicPlay.addSongInfo(musicInfo,index);
                                            MusicPlay.playMusicByInfo(musicInfo);
                                        }else {
                                            ToastUtils.show(getString(R.string.no_copyright));
                                            MusicPlay.removeSongInfoById(playManger.getSongInfo().getSongId());
                                        }
                                    }
                                });
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

    //使状态栏图标黑
    private void setLightStatusBar(){
        binding.tvTitle.setTextColor(getColor(R.color.black80));
        binding.tvSinger.setTextColor(getColor(R.color.black70));
        int flags = getWindow().getDecorView().getSystemUiVisibility();
        getWindow().getDecorView().setSystemUiVisibility(flags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    //使状态栏图标白
    private void setDarkStatusBar(){
        binding.tvTitle.setTextColor(getColor(R.color.white));
        binding.tvSinger.setTextColor(getColor(R.color.white80));
        int flags = getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        getWindow().getDecorView().setSystemUiVisibility(flags^View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void changeRepeatMode(){
        int currentModel = MusicPlay.getRepeatMode();
        switch (currentModel){
            case SpConstant.REPEAT_MODE_NONE:
                MusicPlay.setRepeatMode(SpConstant.REPEAT_MODE_ONE,true);
                ToastUtils.show(getString(R.string.repeat_one));
                break;
            case SpConstant.REPEAT_MODE_ONE:
                MusicPlay.setRepeatMode(SpConstant.REPEAT_MODE_SHUFFLE,false);
                ToastUtils.show(getString(R.string.repeat_random));
                break;
            case SpConstant.REPEAT_MODE_SHUFFLE:
                MusicPlay.setRepeatMode(SpConstant.REPEAT_MODE_NONE,true);
                ToastUtils.show(getString(R.string.repeat_none));
                break;
        }
    }

    private void initAnim() {
        rotationAnim = ObjectAnimator.ofFloat(binding.ivMusicCover, "rotation", 360f);
        rotationAnim.setDuration(25 * 1000);
        rotationAnim.setInterpolator(new LinearInterpolator());
        rotationAnim.setRepeatCount(100000);
        rotationAnim.setRepeatMode(ValueAnimator.RESTART);
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