package com.tobery.personalmusic.ui.daily;

import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.entity.MusicInfo;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivityDailySongsBinding;
import com.tobery.personalmusic.entity.home.DailySongsEntity;
import com.tobery.personalmusic.ui.song.CurrentSongPlayActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.Constant;
import com.tobery.personalmusic.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.Calendar;


public class DailySongsActivity extends BaseActivity {

    private ActivityDailySongsBinding binding;
    private DailySongsAdapter adapter;
    private DailySongsViewModel viewModel;
    private ArrayList<MusicInfo> songList = new ArrayList<>();
    public String date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDailySongsBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(DailySongsViewModel.class);
        setContentView(binding.getRoot());
        initRecycle();
        initView();
        initObserver();
    }

    private void initView() {
        StatusBarUtil.setColor(this,getResources().getColor(R.color.colorPrimary,null),0);
        binding.tvDay.setText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "");
        binding.tvMonth.setText("/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"");
        binding.title.ivBack.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                finish();
            }
        });
        binding.rvPlayTop.setOnClickListener(v -> {
            if (ClickUtil.enableClick()){
                MusicPlay.playMusicByList(songList,0);
                startActivity(new Intent(this, CurrentSongPlayActivity.class)
                        .putExtra(MUSIC_INFO,songList.get(0)));
            }
        });
    }

    private void initObserver() {
        viewModel.getDailySongs().observe(this, dailySongsEntityApiResponse -> {
            if (dailySongsEntityApiResponse.getStatus() == Status.SUCCESS){
                adapter.setDataList(dailySongsEntityApiResponse.getData().getData().getDailySongs(),dailySongsEntityApiResponse.getData().getData().getRecommendReasons());
                for (DailySongsEntity.DataEntity.SongsEntity data: dailySongsEntityApiResponse.getData().getData().getDailySongs()){
                    MusicInfo musicInfo = new MusicInfo();
                    musicInfo.setSongUrl(Constant.SONG_URL+data.getId());
                    musicInfo.setSongId(String.valueOf(data.getId()));
                    String songName = data.getName();
                    if (data.getTns() != null){ //外语翻译歌名
                        songName += "("+data.getTns().get(0)+")";
                    }
                    musicInfo.setSongName(songName);
                    musicInfo.setArtist(data.getAr().get(0).getName());
                    musicInfo.setSongCover(data.getAl().getPicUrl());
                    songList.add(musicInfo);
                }
            }
        });
    }

    private void initRecycle() {
        adapter = new DailySongsAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvDaily.setLayoutManager(manager);
        binding.rvDaily.setAdapter(adapter);
        binding.rvDaily.setHasFixedSize(true);
    }
}