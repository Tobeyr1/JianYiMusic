package com.tobery.personalmusic.ui.daily;

import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;
import static com.tobery.personalmusic.util.Constant.PLAYLIST_ID;
import static com.tobery.personalmusic.util.Constant.PLAY_NAME;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.entity.MusicInfo;
import com.tobery.musicplay.util.ViewExtensionKt;
import com.tobery.personalmusic.BaseFragment;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.FragmentPlaySongsListBinding;
import com.tobery.personalmusic.entity.home.RecommendListEntity;
import com.tobery.personalmusic.ui.song.CurrentSongPlayActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.Constant;
import com.tobery.personalmusic.util.StatusBarUtil;
import java.util.ArrayList;


import jp.wasabeef.glide.transformations.BlurTransformation;


public class PlaySongsListFragment extends BaseFragment {

    private FragmentPlaySongsListBinding binding;
    private MinePlayListViewModel viewModel;
    private PlayListAdapter adapter;
    private ArrayList<MusicInfo> songList = new ArrayList<>();


    @Override
    public void onBackPressed() {
        //Navigation.
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlaySongsListBinding.inflate(inflater,container,false);
        viewModel  = new ViewModelProvider(this).get(MinePlayListViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.currentPlayId = getArguments().getLong(PLAYLIST_ID,0L);
        loadGif();
        initRecycle();
        initObserver();
        initView();
    }

    private void loadGif() {
        Glide.with(this)
                .asGif()
                .load(R.drawable.play_list_loading)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(binding.imgLoading);
    }

    private void initView() {
        binding.rvPlayTop.setOnClickListener(v -> {
            if (ClickUtil.enableClick()){
                MusicPlay.playMusicByList(songList,0);
                startActivity(new Intent(getActivity(), CurrentSongPlayActivity.class)
                        .putExtra(MUSIC_INFO,songList.get(0)));
            }
        });
        binding.title.ivBack.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                Navigation.findNavController(view).navigateUp();
            }
        });
        binding.title.tvTitle.setText(getArguments().getString(PLAY_NAME,""));
    }

    private void initRecycle() {
        adapter = new PlayListAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvPlayList.setLayoutManager(manager);
        binding.rvPlayList.setAdapter(adapter);
        binding.rvPlayList.setNestedScrollingEnabled(false);
        //binding.content.rvPlayList.setHasFixedSize(true);
    }

    private void initObserver() {
        viewModel.getPlayList().observe(getViewLifecycleOwner(), playList -> {
            ViewExtensionKt.printLog(playList.getMessage());
            if (playList.getStatus() == Status.SUCCESS){
                binding.imgLoading.setVisibility(View.GONE);
                adapter.submitList(playList.getData().getPlaylist().getTracks());
                //initBg(playList.getData().getPlaylist().getCoverImgUrl());
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

    private void initBg(String coverImgUrl) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .bitmapTransform(new BlurTransformation(25, 30));
        Glide.with(this)
                .asBitmap()
                .load(coverImgUrl)
                //.placeholder()
                .transition(BitmapTransitionOptions.withCrossFade(1500))
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        binding.imgBg.setImageBitmap(resource);
                        StatusBarUtil.setTranslucentForImageView(getActivity(),0,binding.toolbar);
                        Palette.from(resource)
                                .setRegion(0,0,getScreenWidth(),getStatusBarHeight())
                                .maximumColorCount(6)
                                .generate(palette -> {
                                    Palette.Swatch mostPopularSwatch = null;
                                    for (Palette.Swatch swatch: palette.getSwatches()){
                                        if (mostPopularSwatch == null
                                                || swatch.getPopulation() > mostPopularSwatch.getPopulation()){
                                            mostPopularSwatch = swatch;
                                        }
                                    }
                                    if (mostPopularSwatch!= null){
                                        double luminance = ColorUtils.calculateLuminance(mostPopularSwatch.getRgb());
                                        // 当luminance小于0.5时，我们认为这是一个深色值.
                                        if (luminance < 0.5){
                                            setDarkStatusBar();
                                        }else {
                                            setLightStatusBar();
                                        }
                                    }
                                });
                    }
                });
    }

    //使状态栏图标黑
    private void setLightStatusBar(){
        int flags = getActivity().getWindow().getDecorView().getSystemUiVisibility();
        getActivity().getWindow().getDecorView().setSystemUiVisibility(flags | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    //使状态栏图标白
    private void setDarkStatusBar(){
        int flags = getActivity().getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        getActivity().getWindow().getDecorView().setSystemUiVisibility(flags^View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private int getScreenWidth(){
        DisplayMetrics displayMetrics =new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private int getStatusBarHeight(){
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0){
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }



}
