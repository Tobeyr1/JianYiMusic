package com.tobery.personalmusic.ui.home;

import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;
import static com.tobery.personalmusic.util.Constant.SONG_URL;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tobery.lib.util.navigation.KeepCurrentStateFragment;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.OnMusicPlayStateListener;
import com.tobery.musicplay.entity.MusicInfo;
import com.tobery.musicplay.entity.PlayManger;
import com.tobery.musicplay.util.ViewExtensionKt;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivityMainBinding;
import com.tobery.personalmusic.entity.home.RecentSongInfoEntity;
import com.tobery.personalmusic.entity.home.RecommendListEntity;
import com.tobery.personalmusic.ui.home.discover.DiscoverFragment;
import com.tobery.personalmusic.ui.home.mine.MineFragment;
import com.tobery.personalmusic.ui.home.podcast.PodcastFragment;
import com.tobery.personalmusic.ui.song.CurrentSongPlayActivity;
import com.tobery.personalmusic.util.ClickUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private MainViewModel viewModel;

    private ActivityMainBinding binding;

    private BottomNavigationView navigationBarView;

    private ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.songBar.setBottom(viewModel);
        setContentView(binding.getRoot());
        initBottomBar();
        initFragment();
        initView();
        initObserver();
    }

    private void initBottomBar() {
        viewModel.getRecentSong().observe(this,recentSongInfoEntityApiResponse -> {
            if (recentSongInfoEntityApiResponse.getStatus() == Status.SUCCESS ){
                RecentSongInfoEntity.RecentDataEntity.ListEntity.DataEntity data =recentSongInfoEntityApiResponse.getData().getData().getList()
                        .get(0).getData();
                viewModel.currentSongName.set(data.getName());
                viewModel.currentSongUrl.set(data.getAl().getPicUrl());
                MusicInfo musicInfo = new MusicInfo();
                musicInfo.setArtist(data.getAr().get(0).getName());
                musicInfo.setSongId(data.getId()+"");
                musicInfo.setSongName(data.getName());
                musicInfo.setSongCover(data.getAl().getPicUrl());
                musicInfo.setSongUrl(SONG_URL+data.getId());
                viewModel.currentMusicInfo = musicInfo;
            }
        });

        MusicPlay.onPlayStateListener(this, new OnMusicPlayStateListener() {
            @Override
            public void onPlayState(@NonNull PlayManger playManger) {
                viewModel.currentSongUrl.set(playManger.getSongInfo().getSongCover());
                viewModel.currentSongName.set(playManger.getSongInfo().getSongName());
                switch (playManger.getStage()){
                    case PlayManger.PAUSE:
                    case PlayManger.IDLE:
                        binding.songBar.ivBottomPlay.setImageResource(R.drawable.shape_play);
                        break;
                    case PlayManger.PLAYING:
                        binding.songBar.ivBottomPlay.setImageResource(R.drawable.shape_pause_black);
                        viewModel.currentMusicInfo = playManger.getSongInfo();
                        break;
                    case PlayManger.BUFFERING:
                        ViewExtensionKt.printLog("缓冲");
                        break;
                    case PlayManger.SWITCH:
                        viewModel.currentMusicInfo = playManger.getSongInfo();
                        break;

                }
            }
        });

        binding.songBar.rootBottomBar.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                MusicPlay.playMusicByInfo(viewModel.currentMusicInfo);
                startActivity(new Intent(this, CurrentSongPlayActivity.class)
                        .putExtra(MUSIC_INFO, viewModel.currentMusicInfo));
            }
        });
        binding.songBar.ivBottomPlay.setOnClickListener(view -> {
            if (MusicPlay.isPlaying()){
                MusicPlay.pauseMusic();
            }else if (MusicPlay.isPaused()){
                MusicPlay.restoreMusic();
            }else {
                MusicPlay.playMusicByInfo(viewModel.currentMusicInfo);
            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new DiscoverFragment());
        fragments.add(new PodcastFragment());
        fragments.add(new MineFragment());
    }

    private void initObserver() {
    }

    @SuppressLint("NonConstantResourceId")
    private void initView() {
        navigationBarView = binding.bottomNav;
        NavController navController = Navigation.findNavController(findViewById(R.id.nav_host_fragment));
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        Navigator navigator = new KeepCurrentStateFragment(
                this,
                navHostFragment.getChildFragmentManager(),
                R.id.nav_host_fragment
        );
        navController.getNavigatorProvider().addNavigator(navigator);
        navController.setGraph(R.navigation.nav_graph,getIntent().getExtras());
        initViewPager();
        setDrawMenu();
    }

    private void initViewPager() {
        navigationBarView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.discoverFragment:
                    binding.homeViewpager.setCurrentItem(0,true);
                    break;
                case R.id.podcastFragment:
                    binding.homeViewpager.setCurrentItem(1,true);
                    break;
                case R.id.myFragment:
                    binding.homeViewpager.setCurrentItem(2,true);
                    break;
            }
            return true;
        });
        binding.homeViewpager.setUserInputEnabled(false); //禁止滑动
        binding.homeViewpager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
        binding.homeViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.bottomNav.getMenu().getItem(position).setChecked(true);
            }
        });
    }

    @SuppressLint("NewApi")
    private void setDrawMenu() {
        binding.homeTopLeftBtn.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                binding.homeDrawerMenu.openDrawer(GravityCompat.START);
            }
        });
        binding.homeDrawerMenu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        binding.homeDrawerMenu.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {}

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                binding.homeDrawerMenu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                //binding.homeDrawerMenu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {}
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (binding.homeDrawerMenu.isDrawerOpen(GravityCompat.START)){
            binding.homeDrawerMenu.closeDrawer(GravityCompat.START);
        }else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}