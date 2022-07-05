package com.tobery.personalmusic.ui.song;


import static android.media.MediaPlayer.MetricsConstants.PLAYING;
import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.MusicInfo;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.OnMusicPlayProgressListener;
import com.tobery.musicplay.OnMusicPlayStateListener;
import com.tobery.musicplay.ViewExtensionKt;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.databinding.ActivityCurrentSongPlayBinding;
import com.tobery.personalmusic.entity.LrcEntry;
import com.tobery.personalmusic.util.StatusBarUtil;

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
        //StatusBarUtil.setColor(this, ResourcesCompat.getColor(getResources(),R.color.settings_top_bg_color,null),0);
        initAnim();
        initView();
        initObserver();
    }

    private void initObserver() {
        viewModel.getLyric(Long.parseLong(musicInfo.getSongId()))
                .observe(this, new Observer<ApiResponse<LrcEntry>>() {
                    @Override
                    public void onChanged(ApiResponse<LrcEntry> lrcEntryApiResponse) {
                        if (lrcEntryApiResponse.getStatus() == Status.SUCCESS){
                            ViewExtensionKt.printLog(lrcEntryApiResponse.getData().getText());
                            binding.lrc.loadLrc(lrcEntryApiResponse.getData().getText(),lrcEntryApiResponse.getData().getSecondText());
                            binding.lrc.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void initView() {
        musicInfo = getIntent().getParcelableExtra(MUSIC_INFO);
        MusicPlay.playMusicByInfo(musicInfo);
        MusicPlay.onPlayProgressListener(new OnMusicPlayProgressListener() {
            @Override
            public void onPlayProgress(long l, long l1) {
              //  ViewExtensionKt.printLog("当前总进度"+l1);
            }
        });
      /*  MusicPlay.onPlayStateListener(this, new OnMusicPlayStateListener() {
            @Override
            public void onPlayState(@NonNull PlaybackStage playbackStage) {
                switch (playbackStage.getStage()){
                    case PLAYING:
                        rotationAnim.start();
                        break;
                }
            }
        });*/
    }

    private void initAnim() {
        rotationAnim = ObjectAnimator.ofFloat(binding.ivMusicCover,"rotation",0f,359f);
        rotationAnim.setInterpolator(new LinearInterpolator());
        rotationAnim.setDuration(2000);
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