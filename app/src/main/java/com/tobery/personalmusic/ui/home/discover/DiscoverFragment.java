package com.tobery.personalmusic.ui.home.discover;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.personalmusic.databinding.FragmentDiscoverBinding;
import com.tobery.personalmusic.entity.banner_bean;
import com.tobery.personalmusic.entity.home.BannerExtInfoEntity;
import com.tobery.personalmusic.entity.home.HomeDiscoverEntity;
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
    }

    private void initObserver() {
        /*viewModel.getBanner().observe(getViewLifecycleOwner(), banner_beanApiResponse -> {
            if (banner_beanApiResponse.getStatus() == Status.SUCCESS){
                initData(banner_beanApiResponse.getData().getBanners());
            }
        });*/

        /*viewModel.getRecommendList().observe(getViewLifecycleOwner(), mainRecommendListBeanApiResponse -> {
            if (mainRecommendListBeanApiResponse.getStatus() == Status.SUCCESS){
                adapter.setDataList(mainRecommendListBeanApiResponse.getData().getRecommend());
            }
        });*/

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
                        case "HOMEPAGE_BLOCK_LISTEN_LIVE":

                            break;
                        case "HOMEPAGE_BLOCK_STYLE_RCMD":

                            break;
                        case "HOMEPAGE_BLOCK_MGC_PLAYLIST":
                            break;

                    }
                    Log.e("解析后",block.getBlockCode());
                }
                initData(viewModel.bannerList.getBanners());
                adapter.setDataList(viewModel.recommendList);

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
