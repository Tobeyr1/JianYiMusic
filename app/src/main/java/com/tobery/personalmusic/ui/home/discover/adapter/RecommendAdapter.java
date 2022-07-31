package com.tobery.personalmusic.ui.home.discover.adapter;

import static com.tobery.personalmusic.util.Constant.PLAYLIST_ID;
import static com.tobery.personalmusic.util.Constant.PLAY_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.tobery.musicplay.entity.MusicInfo;
import com.tobery.musicplay.util.ViewExtensionKt;
import com.tobery.personalmusic.BindingAdapter;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ItemRecommendDiscoverBinding;
import com.tobery.personalmusic.entity.home.HomeDiscoverEntity;
import com.tobery.personalmusic.ui.daily.MinePlayListActivity;
import com.tobery.personalmusic.util.ClickUtil;
import com.tobery.personalmusic.util.Constant;

import java.util.ArrayList;
import java.util.List;

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
public class RecommendAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity> dataList = new ArrayList<>();

    private final Context mContext;

    public RecommendAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecommendDiscoverBinding binding = ItemRecommendDiscoverBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(List<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity bean = dataList.get(position);
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

    @Override
    public int getItemCount() {
        return dataList.size();
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
