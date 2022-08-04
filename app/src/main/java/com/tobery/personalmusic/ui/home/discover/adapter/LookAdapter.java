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
import com.tobery.personalmusic.databinding.ItemRecommendDiscoverBinding;
import com.tobery.personalmusic.entity.home.LookLiveEntity;

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
public class LookAdapter extends ListAdapter<LookLiveEntity,LookViewHolder> {

    private final Context mContext;


    public LookAdapter(Context context) {
        super(new lookItemCallback());
        this.mContext = context;
    }

    @NonNull
    @Override
    public LookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecommendDiscoverBinding binding = ItemRecommendDiscoverBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LookViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull LookViewHolder holder, int position) {
        LookLiveEntity bean = getItem(position);
        holder.tvTitle.setText(bean.getTitle());
        //holder.tvCount.setText(bean.getResources().get(0).getResourceExtInfo().getPlayCount());
        BindingAdapter.loadRadiusImage(holder.imRecommend,bean.getVerticalCover());

    }

}

class LookViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvCount;
    ImageView imRecommend;

    public LookViewHolder(ItemRecommendDiscoverBinding binding) {
        super(binding.getRoot());
        tvTitle = binding.recommendTitle;
        imRecommend = binding.imgRecommend;
        tvCount = binding.playCount;
    }
}
class lookItemCallback extends DiffUtil.ItemCallback<LookLiveEntity>{

    @Override
    public boolean areItemsTheSame(@NonNull LookLiveEntity oldItem, @NonNull LookLiveEntity newItem) {
        return oldItem.getLiveId() == newItem.getLiveId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull LookLiveEntity oldItem, @NonNull LookLiveEntity newItem) {
        return oldItem.getLiveId() == newItem.getLiveId() && oldItem.getLiveUrl() == newItem.getLiveUrl();
    }
}
