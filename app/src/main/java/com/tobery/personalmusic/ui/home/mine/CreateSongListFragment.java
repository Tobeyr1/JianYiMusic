package com.tobery.personalmusic.ui.home.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tobery.musicplay.util.ViewExtensionKt;
import com.tobery.personalmusic.databinding.FragmentMineCreateSongListBinding;
import com.tobery.personalmusic.ui.home.MainViewModel;

import java.util.List;

public class CreateSongListFragment extends Fragment {

    private FragmentMineCreateSongListBinding binding;
    private CreateListAdapter adapter;
    private MainViewModel parentViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMineCreateSongListBinding.inflate(inflater,container,false);
        parentViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initObserver();
    }

    private void initObserver() {
        ViewExtensionKt.printLog("子页"+parentViewModel.getSongPlayList().getValue());
        parentViewModel.getSongPlayList().observe(getViewLifecycleOwner(),playlistEntities -> {
            ViewExtensionKt.printLog("是否有数据"+playlistEntities);
            adapter.setDataList(playlistEntities);
        });

    }

    private void initView() {
        adapter = new CreateListAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvCreate.setLayoutManager(manager);
        binding.rvCreate.setAdapter(adapter);
    }

}
