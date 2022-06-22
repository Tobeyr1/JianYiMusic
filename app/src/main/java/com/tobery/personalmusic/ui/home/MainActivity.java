package com.tobery.personalmusic.ui.home;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;
import com.tobery.personalmusic.BaseActivity;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ActivityMainBinding;
import com.tobery.personalmusic.ui.home.discover.DiscoverFragment;
import com.tobery.personalmusic.ui.home.mine.MineFragment;
import com.tobery.personalmusic.ui.home.podcast.PodcastFragment;
import com.tobery.personalmusic.util.ClickUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private MainViewModel viewModel;

    private ActivityMainBinding binding;

    private NavigationBarView navigationBarView;

    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initFragment();
        initObserver();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new DiscoverFragment());
        fragments.add(new PodcastFragment());
        fragments.add(new MineFragment());
        setFragment(0);
    }

    private void initObserver() {


    }

    @SuppressLint("NonConstantResourceId")
    private void initView() {
        navigationBarView = binding.bottomNav;
        navigationBarView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.discoverFragment:
                    setFragment(0);
                    break;
                case R.id.podcastFragment:
                    setFragment(1);
                    break;
                case R.id.myFragment:
                    setFragment(2);
                    break;
            }
            return true;
        });
        setDrawMenu();
    }

    private void setDrawMenu() {
        binding.homeTopLeftBtn.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                binding.homeDrawerMenu.openDrawer(GravityCompat.START);
            }
        });
        binding.homeDrawerMenu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        binding.homeDrawerMenu.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {}

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                binding.homeDrawerMenu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                binding.homeDrawerMenu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {}
        });
    }

    private void setFragment(int position){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_main,fragments.get(position));
        ft.commit();
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