package com.tobery.personalmusic.ui.daily;

import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.tobery.livedata.call.livedatalib.Status;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.entity.MusicInfo;
import com.tobery.personalmusic.BaseFragment;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.FragmentDailySongsBinding;
import com.tobery.personalmusic.entity.home.DailySongsEntity;
import com.tobery.personalmusic.ui.song.CurrentSongPlayActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.Constant;
import com.tobery.personalmusic.util.StatusBarUtil;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @Package: com.tobery.personalmusic.ui.home.dailSongsFragment
 * @ClassName: DailySongsFragment
 * @Author: Tobey_r1
 * @CreateDate: 2022/7/20 23:46
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/20 23:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DailySongsFragment extends BaseFragment {

    private FragmentDailySongsBinding binding;

    private DailySongsAdapter adapter;
    private DailySongsViewModel viewModel;
    private ArrayList<MusicInfo> songList = new ArrayList<>();


    @Override
    public void onBackPressed() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDailySongsBinding.inflate(inflater,container,false);
        viewModel = new ViewModelProvider(this).get(DailySongsViewModel.class);
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
        StatusBarUtil.setColor(getActivity(),getResources().getColor(R.color.colorPrimary,null),0);
        binding.tvDay.setText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "");
        binding.tvMonth.setText("/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"");
        binding.title.ivBack.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                Navigation.findNavController(view).navigateUp();
            }
        });
        binding.rvPlayTop.setOnClickListener(v -> {
            if (ClickUtil.enableClick()){
                MusicPlay.playMusicByList(songList,0);
                startActivity(new Intent(getActivity(), CurrentSongPlayActivity.class)
                        .putExtra(MUSIC_INFO,songList.get(0)));
            }
        });
    }

    private void initObserver() {
        viewModel.getDailySongs().observe(getViewLifecycleOwner(), dailySongsEntityApiResponse -> {
            if (dailySongsEntityApiResponse.getStatus() == Status.SUCCESS){
                adapter.setDataList(dailySongsEntityApiResponse.getData().getData().getDailySongs(),dailySongsEntityApiResponse.getData().getData().getRecommendReasons());
                for (DailySongsEntity.DataEntity.SongsEntity data: dailySongsEntityApiResponse.getData().getData().getDailySongs()){
                    MusicInfo musicInfo = new MusicInfo();
                    musicInfo.setSongUrl(Constant.SONG_URL+data.getId());
                    musicInfo.setSongId(String.valueOf(data.getId()));
                    String songName = data.getName();
                    if (data.getTns() != null){ //外语翻译歌名
                        songName += "("+data.getTns().get(0)+")";
                    }
                    musicInfo.setSongName(songName);
                    musicInfo.setArtist(data.getAr().get(0).getName());
                    musicInfo.setSongCover(data.getAl().getPicUrl());
                    songList.add(musicInfo);
                }
            }
        });
    }

    private void initRecycle() {
        adapter = new DailySongsAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvDaily.setLayoutManager(manager);
        binding.rvDaily.setAdapter(adapter);
        binding.rvDaily.setHasFixedSize(true);
    }




}
