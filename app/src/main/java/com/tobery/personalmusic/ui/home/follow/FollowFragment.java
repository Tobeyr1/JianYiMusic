package com.tobery.personalmusic.ui.home.follow;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tobery.livedata.call.livedatalib.ApiResponse;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.util.ViewExtensionKt;
import com.tobery.personalmusic.databinding.FragmentFollowBinding;
import com.tobery.personalmusic.entity.follow.DynamicListEntity;
import com.tobery.personalmusic.entity.follow.FollowListEntity;
import com.tobery.personalmusic.ui.home.MainViewModel;

import java.util.ArrayList;

public class FollowFragment extends Fragment {

    private FragmentFollowBinding binding;
    private FollowListAdapter adapter;
    private DynamicListAdapter dynamicAdapter;
    private MainViewModel parentViewModel;
    private FollowFragmentViewModel viewModel;

    public static final SparseArray<String> eventType = new SparseArray<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFollowBinding.inflate(inflater,container,false);
        parentViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        viewModel = new ViewModelProvider(this).get(FollowFragmentViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initObserver();
        initData();
    }

    private void initData() {
        eventType.set(18,"分享单曲");
        eventType.set(24,"分享专栏文章");
        eventType.set(13,"分享歌单");
        eventType.set(19,"分享专辑");
    }

    private void initView() {
        adapter = new FollowListAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.rvFollows.setLayoutManager(manager);
        binding.rvFollows.setAdapter(adapter);
        dynamicAdapter = new DynamicListAdapter(getContext());
        LinearLayoutManager verticalManager = new LinearLayoutManager(getContext());
        verticalManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvDynamic.setLayoutManager(verticalManager);
        binding.rvDynamic.setAdapter(dynamicAdapter);
        binding.swipeRefresh.setOnRefreshListener(() -> {
            initObserver();
            binding.swipeRefresh.setRefreshing(false);
        });
    }

    private void initObserver() {
        viewModel.getFollowsList((long) parentViewModel.ui.userId.get()).observe(getViewLifecycleOwner(), followListEntityApiResponse -> {
            if (followListEntityApiResponse.getStatus() == Status.SUCCESS && followListEntityApiResponse.getData().getFollow() != null){
                adapter.submitList(followListEntityApiResponse.getData().getFollow());
            }
        });
        long lastTime;
        if(parentViewModel.currentSaveTime !=0L) {
            lastTime =  parentViewModel.currentSaveTime;
        }else{
            lastTime =  -1;
        }
        viewModel.getDynamicList(15,lastTime).observe(getViewLifecycleOwner(), dynamicListEntityApiResponse -> {
            if (dynamicListEntityApiResponse.getStatus() == Status.SUCCESS && dynamicListEntityApiResponse.getData().getEvent() != null){
                parentViewModel.currentSaveTime = dynamicListEntityApiResponse.getData().getLasttime();
                dynamicAdapter.submitList(dynamicListEntityApiResponse.getData().getEvent());
            }
        });
    }
}
