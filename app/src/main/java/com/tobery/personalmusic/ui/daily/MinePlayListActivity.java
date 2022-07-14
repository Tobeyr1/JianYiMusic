package com.tobery.personalmusic.ui.daily;

import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.entity.MusicInfo;
import com.tobery.musicplay.util.ViewExtensionKt;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.databinding.ActivityMinePlayListBinding;
import com.tobery.personalmusic.entity.home.RecommendListEntity;
import com.tobery.personalmusic.ui.song.CurrentSongPlayActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.Constant;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;


public class MinePlayListActivity extends BaseActivity {

    private ActivityMinePlayListBinding binding;
    private MinePlayListViewModel viewModel;
    private PlayListAdapter adapter;
    private ArrayList<MusicInfo> songList = new ArrayList<>();

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
        initView();
    }

    private void initView() {
        binding.content.rvPlayTop.setOnClickListener(v -> {
            if (ClickUtil.enableClick()){
                MusicPlay.playMusicByList(songList,0);
                startActivity(new Intent(this, CurrentSongPlayActivity.class)
                        .putExtra(MUSIC_INFO,songList.get(0)));
            }
        });
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
                for (RecommendListEntity.PlaylistEntity.TracksEntity data:playList.getData().getPlaylist().getTracks()){
                    MusicInfo musicInfo = new MusicInfo();
                    musicInfo.setSongId(String.valueOf(data.getId()));
                    musicInfo.setSongUrl(Constant.SONG_URL+data.getId());
                    musicInfo.setSongCover(data.getAl().getPicUrl());
                    musicInfo.setSongName(data.getName());
                    musicInfo.setArtist(data.getAr().get(0).getName());
                    songList.add(musicInfo);
                }
            }
        });
    }
}