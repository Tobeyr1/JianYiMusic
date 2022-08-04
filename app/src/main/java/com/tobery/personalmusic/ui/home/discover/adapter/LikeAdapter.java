package com.tobery.personalmusic.ui.home.discover.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.tobery.personalmusic.BindingAdapter;
import com.tobery.personalmusic.databinding.ItemLikeDiscoverBinding;
import com.tobery.personalmusic.entity.home.HomeDiscoverEntity;

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
public class LikeAdapter extends ListAdapter<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity,LikeViewHolder> {

    private final Context mContext;


    public LikeAdapter(Context context) {
        super(new likeItemCallback());
        this.mContext = context;
    }

    @NonNull
    @Override
    public LikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLikeDiscoverBinding binding = ItemLikeDiscoverBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LikeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeViewHolder holder, int position) {
        HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity bean = getItem(position);
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

class likeItemCallback extends DiffUtil.ItemCallback<HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity>{

    @Override
    public boolean areItemsTheSame(@NonNull HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity oldItem, @NonNull HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity newItem) {
        return oldItem.getCreativeId().equals(newItem.getCreativeId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity oldItem, @NonNull HomeDiscoverEntity.DataEntity.BlocksEntity.CreativesEntity newItem) {
        return oldItem.getCreativeId().equals(newItem.getCreativeId());
    }
}
