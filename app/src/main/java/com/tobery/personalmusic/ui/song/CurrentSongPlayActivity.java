package com.tobery.personalmusic.ui.song;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;

import androidx.core.content.res.ResourcesCompat;

import com.lzx.starrysky.notification.NotificationConfig;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivityCurrentSongPlayBinding;
import com.tobery.personalmusic.util.StatusBarUtil;

public class CurrentSongPlayActivity extends BaseActivity {

    private ActivityCurrentSongPlayBinding binding;
    private ObjectAnimator rotationAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCurrentSongPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //StatusBarUtil.setColor(this, ResourcesCompat.getColor(getResources(),R.color.settings_top_bg_color,null),0);
        initAnim();

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