package com.tobery.personalmusic.ui.home.follow;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import com.tobery.personalmusic.BindingAdapter;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ItemFollowsListBinding;
import com.tobery.personalmusic.entity.follow.FollowListEntity;
import com.tobery.personalmusic.util.ClickUtil;

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
public class FollowListAdapter extends RecyclerView.Adapter<FollowListViewHolder> {

    private final List<FollowListEntity.FollowEntity> dataList = new ArrayList<>();

    private final Context mContext;

    public FollowListAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public FollowListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFollowsListBinding binding = ItemFollowsListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FollowListViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(List<FollowListEntity.FollowEntity> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull FollowListViewHolder holder, int position) {
        FollowListEntity.FollowEntity bean = dataList.get(position);
        holder.tvName.setText(bean.getNickname());
        BindingAdapter.loadUrl(holder.imFollow,bean.getAvatarUrl(), AppCompatResources.getDrawable(mContext,R.drawable.ic_banner_loading));
        holder.itemView.setOnClickListener(view -> {
            if (ClickUtil.enableClick()){

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class FollowListViewHolder extends RecyclerView.ViewHolder {
    TextView tvName;
    ImageView imFollow;

    public FollowListViewHolder(ItemFollowsListBinding binding) {
        super(binding.getRoot());
        tvName = binding.tvFollowName;
        imFollow = binding.imgCreate;
    }
}
