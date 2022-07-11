package com.tobery.personalmusic.ui.daily;

import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tobery.musicplay.MusicInfo;
import com.tobery.musicplay.MusicPlay;
import com.tobery.personalmusic.BindingAdapter;
import com.tobery.personalmusic.databinding.ItemDailySongBinding;
import com.tobery.personalmusic.entity.home.DailySongsEntity;
import com.tobery.personalmusic.ui.song.CurrentSongPlayActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package:
 * @ClassName: RecommendAdapter
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/23 0:00
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/23 0:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DailySongsAdapter extends RecyclerView.Adapter<DailySongsViewHolder> {

    private final List<DailySongsEntity.DataEntity.SongsEntity> dataList = new ArrayList<>();

    private final Context mContext;

    public DailySongsAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public DailySongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDailySongBinding binding = ItemDailySongBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DailySongsViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(List<DailySongsEntity.DataEntity.SongsEntity> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull DailySongsViewHolder holder, int position) {
        DailySongsEntity.DataEntity.SongsEntity bean = dataList.get(position);
        holder.tvSongName.setText(bean.getName());
        holder.tvSinger.setText(bean.getAr().get(0).getName()+"-"+bean.getAl().getName());
        BindingAdapter.loadRadiusImage(bean.getAl().getPicUrl(),holder.imgSong);
        MusicInfo musicInfo = new MusicInfo();
        musicInfo.setSongUrl(Constant.SONG_URL+bean.getId());
        musicInfo.setSongId(String.valueOf(bean.getId()));
        musicInfo.setSongName(bean.getName());
        musicInfo.setArtist(bean.getAr().get(0).getName());
        musicInfo.setSongCover(bean.getAl().getPicUrl());
        holder.itemView.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                MusicPlay.playMusicByInfo(musicInfo);
                mContext.startActivity(new Intent(
                        mContext, CurrentSongPlayActivity.class
                ).putExtra(MUSIC_INFO,musicInfo));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class DailySongsViewHolder extends RecyclerView.ViewHolder {
    TextView tvSongName,tvSinger;
    ImageView imgSong;

    public DailySongsViewHolder(ItemDailySongBinding binding) {
        super(binding.getRoot());
        tvSongName = binding.tvSongName;
        tvSinger = binding.tvSinger;
        imgSong = binding.imgSong;
    }
}
