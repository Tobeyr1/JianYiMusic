package com.tobery.personalmusic.ui.home.mine;

import static com.tobery.personalmusic.util.Constant.PLAYLIST_ID;
import static com.tobery.personalmusic.util.Constant.PLAY_NAME;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.util.ViewExtensionKt;
import com.tobery.personalmusic.databinding.FragmentMineBinding;
import com.tobery.personalmusic.entity.UserDetailEntity;
import com.tobery.personalmusic.entity.user.UserPlayEntity;
import com.tobery.personalmusic.ui.daily.MinePlayListActivity;
import com.tobery.personalmusic.ui.home.MainViewModel;
import com.tobery.personalmusic.util.ClickUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;

public class MineFragment extends Fragment {

    private FragmentMineBinding binding;

    private MainViewModel homeViewModel;

    private MineFragmentViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMineBinding.inflate(inflater,container,false);
        homeViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel = new ViewModelProvider(this).get(MineFragmentViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setVm(homeViewModel);
        binding.setMy(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel.initUi();
        initWebView();
        initView();
        initObserver();
    }

    private void initView() {
        binding.viewLikeItem.setOnClickListener(v -> {
            if (ClickUtil.enableClick()){
                startActivity(new Intent(getActivity(), MinePlayListActivity.class)
                        .putExtra(PLAYLIST_ID,viewModel.userLikeCreator)
                        .putExtra(PLAY_NAME,"歌单"));
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        binding.webVip.getSettings().setJavaScriptEnabled(true);
    }

    private void initObserver() {

        viewModel.getVipInfo().observe(getViewLifecycleOwner(), vipInfoEntityApiResponse -> {
            if (vipInfoEntityApiResponse.getStatus() == Status.SUCCESS){
                binding.webVip.loadDataWithBaseURL(null,changeImageUrl(vipInfoEntityApiResponse.getData().getData().getRedVipDynamicIconUrl2()),"text/html", "utf-8", null);
            }
        });

        homeViewModel.getUserDetails().observe(getViewLifecycleOwner(), userDetailEntityApiResponse -> {
            if (userDetailEntityApiResponse.getStatus() == Status.SUCCESS){
                viewModel.level.set("Lv."+userDetailEntityApiResponse.getData().getLevel());
            }
        });

        viewModel.getUserPlayList(Long.valueOf(homeViewModel.ui.userId.get())).observe(getViewLifecycleOwner(), userPlayEntityApiResponse -> {
            ViewExtensionKt.printLog(userPlayEntityApiResponse.getMessage());
            if (userPlayEntityApiResponse.getStatus() == Status.SUCCESS){
                UserPlayEntity.PlaylistEntity userLike = userPlayEntityApiResponse.getData().getPlaylist().get(0);
                viewModel.mineLikeCover.set(userLike.getCoverImgUrl());
                viewModel.trackCount.set(userLike.getTrackCount()+"");
                viewModel.userLikeCreator = userLike.getId();
            }
        });
    }

    private String changeImageUrl(String url) {
        Document doc = null;
        try {
            InputStream file = getResources().getAssets().open("web/vip.html");
            doc = Jsoup.parse(file, "UTF-8", url);
            Elements pngs = doc.select("img[src$=.png]");
            pngs.attr("src",url);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null;
        return doc.toString();
    }
}
