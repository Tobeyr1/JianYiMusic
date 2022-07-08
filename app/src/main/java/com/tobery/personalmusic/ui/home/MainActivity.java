package com.tobery.personalmusic.ui.home;

import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.tobery.musicplay.MusicInfo;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.OnMusicPlayStateListener;
import com.tobery.musicplay.PermissionChecks;
import com.tobery.musicplay.PlayConfig;
import com.tobery.musicplay.PlayManger;
import com.tobery.musicplay.ViewExtensionKt;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivityMainBinding;
import com.tobery.personalmusic.ui.home.discover.DiscoverFragment;
import com.tobery.personalmusic.ui.home.mine.MineFragment;
import com.tobery.personalmusic.ui.home.podcast.PodcastFragment;
import com.tobery.personalmusic.ui.song.CurrentSongPlayActivity;
import com.tobery.personalmusic.util.ClickUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private MainViewModel viewModel;

    private ActivityMainBinding binding;

    private NavigationBarView navigationBarView;

    private ArrayList<Fragment> fragments;

    PermissionChecks checks;

    private final String[] APP_PERMISSIONS = new ArrayList<String>(){
        {
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            add(Manifest.permission.READ_EXTERNAL_STORAGE);
            add(Manifest.permission.RECORD_AUDIO);
            add(Manifest.permission.READ_PHONE_STATE);
        }
    }.toArray(new String[0]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        checks = new PermissionChecks(this);
        checks.requestPermissions(APP_PERMISSIONS, it ->{
            if (it){
               // MusicPlay.initConfig(this,new PlayConfig());
            }else {

            }
            return null;
        });
        initFragment();
        initView();
        initObserver();
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
        initViewPager();
        setDrawMenu();
        if (MusicPlay.getNowPlayingSongInfo() != null){
            initBottomBar();
            binding.songBar.rootBottomBar.setOnClickListener(view -> {
                if (ClickUtil.enableClick()){
                    startActivity(new Intent(this, CurrentSongPlayActivity.class)
                            .putExtra(MUSIC_INFO, MusicPlay.getNowPlayingSongInfo()));
                }
            });
            binding.songBar.tvSongName.setText(MusicPlay.getNowPlayingSongInfo().getSongName());
        }
        binding.songBar.ivBottomPlay.setOnClickListener(view -> {

        });
    }

    private void initBottomBar() {
        MusicPlay.onPlayStateListener(this, new OnMusicPlayStateListener() {
            @Override
            public void onPlayState(@NonNull PlayManger playbackStage) {
                switch (playbackStage.getStage()){
                    case PlayManger.PAUSE:
                    case PlayManger.IDLE:
                        binding.songBar.ivBottomPlay.setImageResource(R.drawable.shape_play);
                        break;
                    case PlayManger.PLAYING:
                        binding.songBar.ivBottomPlay.setImageResource(R.drawable.shape_pause_black);
                        break;
                    case PlayManger.BUFFERING:
                        ViewExtensionKt.printLog("缓冲");
                        break;
                    case PlayManger.SWITCH:

                        break;

                }
            }
        });

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