package com.tobery.personalmusic.ui.home.mine;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.personalmusic.databinding.FragmentMineBinding;
import com.tobery.personalmusic.ui.home.MainViewModel;

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
        binding = FragmentMineBinding.inflate(inflater);
        homeViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel = new ViewModelProvider(this).get(MineFragmentViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setVm(homeViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel.initUi();
        initWebView();
        initObserver();
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
