package com.tobery.personalmusic.ui.song;


import android.os.Bundle;

import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.databinding.ActivityCurrentSongPlayBinding;

public class CurrentSongPlayActivity extends BaseActivity {

    private ActivityCurrentSongPlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCurrentSongPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}