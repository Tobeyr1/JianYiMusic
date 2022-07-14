package com.tobery.personalmusic.ui.daily;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.ViewExtensionKt;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.databinding.ActivityMinePlayListBinding;
import com.tobery.personalmusic.entity.home.RecommendListEntity;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


public class MinePlayListActivity extends BaseActivity {

    private ActivityMinePlayListBinding binding;
    private MinePlayListViewModel viewModel;
    private PlayListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMinePlayListBinding.inflate(getLayoutInflater());
        viewModel  = new ViewModelProvider(this).get(MinePlayListViewModel.class);
        setContentView(binding.getRoot());
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        initRecycle();
        initObserver();
    }

    private void initRecycle() {
        adapter = new PlayListAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.content.rvPlayList.setLayoutManager(manager);
        binding.content.rvPlayList.setAdapter(adapter);
        binding.content.rvPlayList.setHasFixedSize(true);
    }

    private void initObserver() {
        viewModel.getPlayList().observe(this, playList -> {
            ViewExtensionKt.printLog(playList.getMessage());
            if (playList.getStatus() == Status.SUCCESS){
                adapter.setDataList(playList.getData().getPlaylist().getTracks());
            }
        });
    }
}