package com.tobery.personalmusic.ui.home.discover;

import static com.tobery.personalmusic.util.Constant.BANNER_URI;
import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;
import static com.tobery.personalmusic.util.Constant.SONG_URL;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.entity.MusicInfo;
import com.tobery.musicplay.util.ViewExtensionKt;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.FragmentDiscoverBinding;
import com.tobery.personalmusic.entity.home.BannerExtInfoEntity;
import com.tobery.personalmusic.entity.home.HomeDiscoverEntity;
import com.tobery.personalmusic.entity.home.LookLiveEntity;
import com.tobery.personalmusic.ui.WebActivity;
import com.tobery.personalmusic.ui.daily.DailySongsActivity;
import com.tobery.personalmusic.ui.home.discover.adapter.LikeAdapter;
import com.tobery.personalmusic.ui.home.discover.adapter.LookAdapter;
import com.tobery.personalmusic.ui.home.discover.adapter.MgcAdapter;
import com.tobery.personalmusic.ui.home.discover.adapter.RecommendAdapter;
import com.tobery.personalmusic.ui.home.discover.adapter.bannerAdapter;
import com.tobery.personalmusic.ui.song.CurrentSongPlayActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.listener.OnBannerListener;
import java.util.List;

/**
 * @Package: com.tobery.personalmusic.ui.home.discover
 * @ClassName: DiscoverFragment
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/16 23:46
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/16 23:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DiscoverFragment extends Fragment {

    private FragmentDiscoverBinding binding;

    private DiscoverFragmentViewModel viewModel;

    private RecommendAdapter adapter;

    private MgcAdapter mgcAdapter;

    private LookAdapter lookAdapter;

    private LikeAdapter likeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDiscoverBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(this).get(DiscoverFragmentViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setVm(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycle();
        initView();
        initObserver();
    }

    private void initView() {
        binding.imgRecommend.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                Navigation.findNavController(view).navigate(R.id.navigation_daily);
                //startActivity(new Intent(getActivity(), DailySongsActivity.class));
            }
        });
    }

    private void initRecycle() {
        adapter = new RecommendAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.recommendRecycle.setLayoutManager(manager);
        binding.recommendRecycle.setHasFixedSize(true);
        binding.recommendRecycle.setAdapter(adapter);

        mgcAdapter = new MgcAdapter(getContext());
        LinearLayoutManager managerMgc = new LinearLayoutManager(getContext());
        managerMgc.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.mgcRecycle.setLayoutManager(managerMgc);
        binding.mgcRecycle.setHasFixedSize(true);
        binding.mgcRecycle.setAdapter(mgcAdapter);

        lookAdapter = new LookAdapter(getContext());
        LinearLayoutManager managerLook = new LinearLayoutManager(getContext());
        managerLook.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.lookRecycle.setLayoutManager(managerLook);
        binding.lookRecycle.setHasFixedSize(true);
        binding.lookRecycle.setAdapter(lookAdapter);

        likeAdapter = new LikeAdapter(getContext());
        LinearLayoutManager managerLike = new LinearLayoutManager(getContext());
        managerLike.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.likeRecycle.setLayoutManager(managerLike);
        binding.likeRecycle.setHasFixedSize(true);
        binding.likeRecycle.setAdapter(likeAdapter);
    }

    private void initObserver() {

        viewModel.requireDiscover(false).observe(getViewLifecycleOwner(), homeDiscoverEntityApiResponse -> {
            ViewExtensionKt.printLog("当前"+homeDiscoverEntityApiResponse.getMessage());
            if (homeDiscoverEntityApiResponse.getStatus() == Status.SUCCESS){
                for (HomeDiscoverEntity.DataEntity.BlocksEntity block : homeDiscoverEntityApiResponse.getData().getData().getBlocks()){
                    switch (block.getBlockCode()){
                        case "HOMEPAGE_BANNER": //banner
                            String bannerJson = new Gson().toJson(block.getExtInfo());
                            viewModel.bannerList = new Gson().fromJson(bannerJson,BannerExtInfoEntity.class);
                            break;
                        case "HOMEPAGE_BLOCK_PLAYLIST_RCMD": //推荐歌单
                            viewModel.recommendList = block.getCreatives();
                            break;
                        case "HOMEPAGE_BLOCK_LISTEN_LIVE"://直播
                            binding.tvLook.setText(block.getUiElement().getSubTitle().getTitle());
                            binding.tvLookMore.setText(block.getUiElement().getButton().getText());
                            String lookJson = new Gson().toJson(block.getExtInfo());
                            viewModel.lookLiveList = new Gson().fromJson(lookJson,new TypeToken<List<LookLiveEntity>>(){}.getType());
                            break;
                        case "HOMEPAGE_BLOCK_STYLE_RCMD"://根据xxx推荐
                            viewModel.likeList = block.getCreatives();
                            binding.tvLike.setText(block.getUiElement().getSubTitle().getTitle());
                            binding.tvLikeMore.setText(block.getUiElement().getButton().getText());
                            break;
                        case "HOMEPAGE_BLOCK_MGC_PLAYLIST"://雷达歌单
                            viewModel.selfMgcList = block.getCreatives();
                            binding.tvMgc.setText(block.getUiElement().getSubTitle().getTitle());
                            binding.tvMgcMore.setText(block.getUiElement().getButton().getText());
                            break;

                    }
                    binding.tvBottom.setText(homeDiscoverEntityApiResponse.getData().getData().getPageConfig().getNodataToast());
                }
                initData(viewModel.bannerList.getBanners());
                adapter.setDataList(viewModel.recommendList);
                mgcAdapter.setDataList(viewModel.selfMgcList);
                if (viewModel.lookLiveList != null){
                    lookAdapter.setDataList(viewModel.lookLiveList);
                }
                likeAdapter.setDataList(viewModel.likeList);
            }else {
                Log.e("报错",homeDiscoverEntityApiResponse.getMessage());
            }
        });
    }

    private void initData(List<BannerExtInfoEntity.BannersEntity> banners) {
        binding.bannerImg.setAdapter(new bannerAdapter(banners))
                .addBannerLifecycleObserver(getViewLifecycleOwner())
                .setIntercept(false) //不拦截事件
                .setBannerRound(10f)//圆角
                .setIndicator(new RectangleIndicator(getContext())) //线条指示器
                .setIndicatorHeight(5)
                .setIndicatorWidth(6,6)//选中下宽度一致
                .setIndicatorGravity(IndicatorConfig.Direction.CENTER)
                .setOnBannerListener((data, position) -> {
                    if (banners.get(position).getUrl() != null){
                        startActivity(new Intent(getActivity(), WebActivity.class)
                                .putExtra(BANNER_URI,banners.get(position).getUrl()));
                    }
                    if (banners.get(position).getSong() != null){
                        MusicInfo musicInfo = new MusicInfo();
                        musicInfo.setSongId(banners.get(position).getSong().getId()+"");
                        musicInfo.setSongName(banners.get(position).getSong().getName());
                        musicInfo.setSongCover(banners.get(position).getSong().getAl().getPicUrl());
                        musicInfo.setArtist(banners.get(position).getSong().getAr().get(0).getName());
                        musicInfo.setSongUrl(SONG_URL+banners.get(position).getSong().getId());
                        MusicPlay.playMusicByInfo(musicInfo);
                        startActivity(new Intent(getActivity(), CurrentSongPlayActivity.class)
                                .putExtra(MUSIC_INFO,musicInfo));
                    }
                    if (banners.get(position).getTargetType() == 10){//专辑
                        //banners.get(position).getTargetId();
                    }
                });
    }



}
