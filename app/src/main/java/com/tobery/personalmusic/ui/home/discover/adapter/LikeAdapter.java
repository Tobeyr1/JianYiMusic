package com.tobery.personalmusic.ui.home.discover.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.tobery.personalmusic.BindingAdapter;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ItemLikeDiscoverBinding;
import com.tobery.personalmusic.databinding.ItemRecommendDiscoverBinding;
import com.tobery.personalmusic.entity.home.HomeDiscoverEntity;
import com.tobery.personalmusic.entity.home.LookLiveEntity;

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
public class LikeAdapter extends RecyclerView.Adapter<LikeViewHolder> {

    private final List<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity> dataList = new ArrayList<>();

    private final Context mContext;


    public LikeAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public LikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLikeDiscoverBinding binding = ItemLikeDiscoverBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LikeViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(List<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull LikeViewHolder holder, int position) {
        HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity bean = dataList.get(position);
        int num = bean.getResources().size();
        switch (num){
            case 1:
                holder.tvTitleOne.setText(bean.getResources().get(0).getUiElement().getMainTitle().getTitle());
                BindingAdapter.loadRadiusImage(holder.imgOne,bean.getResources().get(0).getUiElement().getImage().getImageUrl());
                break;
            case 2:
                holder.tvTitleOne.setText(bean.getResources().get(0).getUiElement().getMainTitle().getTitle());
                BindingAdapter.loadRadiusImage(holder.imgOne,bean.getResources().get(0).getUiElement().getImage().getImageUrl());

                holder.tvTitleTwo.setText(bean.getResources().get(1).getUiElement().getMainTitle().getTitle());
                BindingAdapter.loadRadiusImage(holder.imgTwo,bean.getResources().get(1).getUiElement().getImage().getImageUrl());
                break;
            case 3:

                holder.tvTitleOne.setText(bean.getResources().get(0).getUiElement().getMainTitle().getTitle());
                BindingAdapter.loadRadiusImage(holder.imgOne,bean.getResources().get(0).getUiElement().getImage().getImageUrl());

                holder.tvTitleTwo.setText(bean.getResources().get(1).getUiElement().getMainTitle().getTitle());
                BindingAdapter.loadRadiusImage(holder.imgTwo,bean.getResources().get(1).getUiElement().getImage().getImageUrl());

                holder.tvTitleThree.setText(bean.getResources().get(2).getUiElement().getMainTitle().getTitle());
                BindingAdapter.loadRadiusImage(holder.imgThree,bean.getResources().get(2).getUiElement().getImage().getImageUrl());
                break;
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class LikeViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitleOne,tvTitleThree,tvTitleTwo, tvSubOne,tvSubTwo,tvSubThree;
    ImageView imgOne,imgTwo,imgThree;

    public LikeViewHolder(ItemLikeDiscoverBinding binding) {
        super(binding.getRoot());
        tvTitleOne = binding.likeTitleOne;
        tvTitleTwo = binding.likeTitleTwo;
        tvTitleThree = binding.likeTitleThree;
        imgOne = binding.imgLikeOne;
        imgTwo = binding.imgLikeTwo;
        imgThree = binding.imgLikeThree;
        tvSubOne = binding.likeSubTitleOne;
        tvSubTwo = binding.likeSubTitleTwo;
        tvSubThree = binding.likeSubTitleThree;
    }
}
