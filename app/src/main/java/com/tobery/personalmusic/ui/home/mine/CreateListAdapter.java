package com.tobery.personalmusic.ui.home.mine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tobery.personalmusic.BindingAdapter;
import com.tobery.personalmusic.databinding.ItemCreateListBinding;
import com.tobery.personalmusic.entity.home.LookLiveEntity;
import com.tobery.personalmusic.entity.user.UserPlayEntity;

import java.util.ArrayList;
import java.util.List;


public class CreateListAdapter extends RecyclerView.Adapter<CreateViewHolder> {

    private final List<UserPlayEntity.PlaylistEntity> dataList = new ArrayList<>();

    private final Context mContext;

    public CreateListAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public CreateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCreateListBinding binding = ItemCreateListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CreateViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(List<UserPlayEntity.PlaylistEntity> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CreateViewHolder holder, int position) {
        UserPlayEntity.PlaylistEntity bean = dataList.get(position);
        holder.tvCreateName.setText(bean.getName());
        BindingAdapter.loadRadiusImage(holder.imgCreate,bean.getCoverImgUrl());
        holder.tvCount.setText(bean.getTrackCount()+"");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class CreateViewHolder extends RecyclerView.ViewHolder {
    TextView tvCreateName, tvCount;
    ImageView imgCreate;

    public CreateViewHolder(ItemCreateListBinding binding) {
        super(binding.getRoot());
        tvCreateName = binding.tvCreateName;
        imgCreate = binding.imgCreate;
        tvCount = binding.tvCount;
    }
}
