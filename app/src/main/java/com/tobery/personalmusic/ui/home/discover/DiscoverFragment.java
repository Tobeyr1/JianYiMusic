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

import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.personalmusic.databinding.FragmentDiscoverBinding;
import com.tobery.personalmusic.entity.banner_bean;

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
class DiscoverFragment extends Fragment {

    private FragmentDiscoverBinding binding;

    private DiscoverFragmentViewModel viewModel;

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
        initData();
    }

    private void initData() {
        viewModel.getBanner().observe(getViewLifecycleOwner(), new Observer<ApiResponse<banner_bean>>() {
            @Override
            public void onChanged(ApiResponse<banner_bean> banner_beanApiResponse) {
                Log.e("数据",banner_beanApiResponse.getData().getBanners().toString());
            }
        });
    }
}
