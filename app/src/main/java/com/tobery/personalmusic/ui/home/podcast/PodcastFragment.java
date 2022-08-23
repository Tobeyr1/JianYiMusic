package com.tobery.personalmusic.ui.home.podcast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tobery.personalmusic.databinding.FragmentPodcastBinding;

import xyz.doikki.videocontroller.StandardVideoController;

public class PodcastFragment extends Fragment {

    private FragmentPodcastBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPodcastBinding.inflate(inflater,container,false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }
    private void initView() {
        binding.player.setUrl("http://pull0583d674.live.126.net/live/132001789a93486fa221da3e75f06da7/playlist.m3u8");
        StandardVideoController controller = new StandardVideoController(requireContext());
        controller.addDefaultControlComponent("标题", true);
        binding.player.setVideoController(controller); //设置控制器
        binding.player.start(); //开始播放，不调用则不自动播放
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.player.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.player.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.player.release();
    }
}
