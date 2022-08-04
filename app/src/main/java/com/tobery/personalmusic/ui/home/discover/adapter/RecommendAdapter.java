package com.tobery.personalmusic.ui.home.discover.adapter;

import static com.tobery.personalmusic.util.Constant.PLAYLIST_ID;
import static com.tobery.personalmusic.util.Constant.PLAY_NAME;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.tobery.personalmusic.BindingAdapter;
import com.tobery.personalmusic.databinding.ItemRecommendDiscoverBinding;
import com.tobery.personalmusic.entity.home.HomeDiscoverEntity;
import com.tobery.personalmusic.ui.daily.MinePlayListActivity;
import com.tobery.personalmusic.util.ClickUtil;

/**
 * @Package: com.tobery.personalmusic.ui.home.discover.adapter
 * @ClassName: RecommendAdapter
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/23 0:00
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/23 0:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecommendAdapter extends ListAdapter<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity,ViewHolder> {


    private final Context mContext;

    public RecommendAdapter(Context context) {
        super(new itemCallback());
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecommendDiscoverBinding binding = ItemRecommendDiscoverBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity bean = getItem(position);
        holder.tvTitle.setText(bean.getUiElement().getMainTitle().getTitle());
        //holder.tvCount.setText(bean.getResources().get(0).getResourceExtInfo().getPlayCount());
        BindingAdapter.loadRadiusImage(holder.imRecommend,bean.getUiElement().getImage().getImageUrl());
        holder.itemView.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){
                mContext.startActivity(new Intent(mContext, MinePlayListActivity.class)
                        .putExtra(PLAYLIST_ID,Long.valueOf(bean.getCreativeId()))
                        .putExtra(PLAY_NAME,bean.getUiElement().getMainTitle().getTitle()));
            }
        });
    }

}

class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvCount;
    ImageView imRecommend;

    public ViewHolder(ItemRecommendDiscoverBinding binding) {
        super(binding.getRoot());
        tvTitle = binding.recommendTitle;
        imRecommend = binding.imgRecommend;
        tvCount = binding.playCount;
    }
}

class itemCallback extends DiffUtil.ItemCallback<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity>{


    @Override
    public boolean areItemsTheSame(@NonNull HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity oldItem, @NonNull HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity newItem) {
        return oldItem.getCreativeId().equals(newItem.getCreativeId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity oldItem, @NonNull HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity newItem) {
        return oldItem.getCreativeId().equals(newItem.getCreativeId());
    }
}
