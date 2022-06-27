package com.tobery.personalmusic.ui.home.discover;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.personalmusic.databinding.FragmentDiscoverBinding;
import com.tobery.personalmusic.entity.home.BannerExtInfoEntity;
import com.tobery.personalmusic.entity.home.HomeDiscoverEntity;
import com.tobery.personalmusic.entity.home.LookLiveEntity;
import com.tobery.personalmusic.ui.home.discover.adapter.LikeAdapter;
import com.tobery.personalmusic.ui.home.discover.adapter.LookAdapter;
import com.tobery.personalmusic.ui.home.discover.adapter.MgcAdapter;
import com.tobery.personalmusic.ui.home.discover.adapter.RecommendAdapter;
import com.tobery.personalmusic.ui.home.discover.adapter.bannerAdapter;
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
        initObserver();
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
                            Log.e("直播",lookJson);
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
                lookAdapter.setDataList(viewModel.lookLiveList);
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
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(Object data, int position) {

                    }
                });
    }

}
