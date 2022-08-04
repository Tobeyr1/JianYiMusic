package com.tobery.personalmusic.ui.daily;

import static com.tobery.personalmusic.util.Constant.MUSIC_INFO;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.tobery.musicplay.MusicPlay;
import com.tobery.musicplay.entity.MusicInfo;
import com.tobery.personalmusic.databinding.ItemMinePlayListBinding;
import com.tobery.personalmusic.entity.home.RecommendListEntity;
import com.tobery.personalmusic.ui.song.CurrentSongPlayActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.Constant;

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
public class PlayListAdapter extends ListAdapter<RecommendListEntity.PlaylistEntity.TracksEntity,PlayListViewHolder> {


    private final Context mContext;

    public PlayListAdapter(Context context) {
        super(new playItemCallback());
        this.mContext = context;
    }

    @NonNull
    @Override
    public PlayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMinePlayListBinding binding = ItemMinePlayListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlayListViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull PlayListViewHolder holder, int position) {
        RecommendListEntity.PlaylistEntity.TracksEntity bean = getItem(position);
        holder.tvSongName.setText(bean.getName());
        String singerName = bean.getAr().get(0).getName()+"-"+bean.getAl().getName();
        holder.tvSinger.setText(singerName);
        holder.tvNum.setText(String.valueOf(position+1));
        MusicInfo musicInfo = new MusicInfo();
        musicInfo.setSongUrl(Constant.SONG_URL+bean.getId());
        musicInfo.setSongId(String.valueOf(bean.getId()));
        musicInfo.setSongName(bean.getName());
        musicInfo.setArtist(bean.getAr().get(0).getName());
        musicInfo.setSongCover(bean.getAl().getPicUrl());
        if (bean.getAlia() != null && bean.getAlia().size() != 0){//歌曲alias
            String alias = "("+bean.getAlia().get(0)+")";
            holder.tvAlias.setText(alias);
        }
        if (bean.getFee() == 1){//1表示vip歌曲

        }
        if (bean.getSq() != null){//表示该歌曲有SQ版本

        }
        if (bean.getHr() != null){//表示有hi版

        }
        holder.itemView.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                MusicPlay.playMusicByInfo(musicInfo);
                mContext.startActivity(new Intent(
                        mContext, CurrentSongPlayActivity.class
                ).putExtra(MUSIC_INFO,musicInfo));
            }
        });
    }

}

class PlayListViewHolder extends RecyclerView.ViewHolder {
    TextView tvSongName,tvSinger,tvNum,tvAlias;

    public PlayListViewHolder(ItemMinePlayListBinding binding) {
        super(binding.getRoot());
        tvSongName = binding.tvSongName;
        tvSinger = binding.tvSinger;
        tvNum = binding.tvNumber;
        tvAlias = binding.tvAlias;
    }
}
class playItemCallback extends DiffUtil.ItemCallback<RecommendListEntity.PlaylistEntity.TracksEntity>{

    @Override
    public boolean areItemsTheSame(@NonNull RecommendListEntity.PlaylistEntity.TracksEntity oldItem, @NonNull RecommendListEntity.PlaylistEntity.TracksEntity newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull RecommendListEntity.PlaylistEntity.TracksEntity oldItem, @NonNull RecommendListEntity.PlaylistEntity.TracksEntity newItem) {
        return oldItem.getId() == newItem.getId();
    }
}