package com.tobery.personalmusic.ui.home.mine;

import static com.tobery.personalmusic.util.Constant.PLAYLIST_ID;
import static com.tobery.personalmusic.util.Constant.PLAY_NAME;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.tobery.musicplay.util.ViewExtensionKt;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.FragmentMineCreateSongListBinding;
import com.tobery.personalmusic.entity.user.UserPlayEntity;
import com.tobery.personalmusic.ui.home.MainViewModel;

import java.util.ArrayList;
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

    @SuppressLint("SetTextI18n")
    private void initObserver() {
        List<UserPlayEntity.PlaylistEntity> dataList = new ArrayList<>();
        parentViewModel.getSongPlayList().observe(getViewLifecycleOwner(), userPlayEntity -> {
            int size = userPlayEntity.getPlaylist().size();
            dataList.clear();
            for (int i=1;i< size;i++){
                if (userPlayEntity.getPlaylist().get(i).getUserId() == parentViewModel.ui.userId.get()){
                    dataList.add(userPlayEntity.getPlaylist().get(i));
                }
            }
            binding.tvCreateNum.setText("创建歌单（"+dataList.size()+"个)");
            adapter.setDataList(dataList);
        });

    }

    private void initView() {
        adapter = new CreateListAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvCreate.setLayoutManager(manager);
        binding.rvCreate.setNestedScrollingEnabled(false);
        binding.rvCreate.setAdapter(adapter);
        adapter.setOnItemClick((coverId, playName) -> {
            Bundle bundle = new Bundle();
            bundle.putLong(PLAYLIST_ID,coverId);
            bundle.putString(PLAY_NAME,playName);
            Navigation.findNavController(binding.rvCreate).navigate(R.id.navigation_play_list,bundle);
        });
    }

}
