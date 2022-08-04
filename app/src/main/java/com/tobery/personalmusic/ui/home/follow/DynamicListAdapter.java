package com.tobery.personalmusic.ui.home.follow;


import static com.tobery.personalmusic.ui.home.follow.FollowFragment.eventType;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.tobery.personalmusic.BindingAdapter;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.databinding.ItemDynamicListBinding;
import com.tobery.personalmusic.databinding.ItemFollowsListBinding;
import com.tobery.personalmusic.entity.follow.DynamicListEntity;
import com.tobery.personalmusic.entity.follow.FollowListEntity;
import com.tobery.personalmusic.util.ClickUtil;

import org.json.JSONException;
import org.json.JSONObject;

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
public class DynamicListAdapter extends RecyclerView.Adapter<DynamicListViewHolder> {

    private final List<DynamicListEntity.EventEntity> dataList = new ArrayList<>();

    private final Context mContext;

    private ItemDynamicListBinding binding;

    public DynamicListAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public DynamicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemDynamicListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DynamicListViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setDataList(List<DynamicListEntity.EventEntity> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicListViewHolder holder, int position) {
        DynamicListEntity.EventEntity bean = dataList.get(position);
        binding.setUser(bean.getUser());
        binding.setMessage(bean);
        binding.setSparse(eventType);
        if (bean.getPics() != null && bean.getPics().size() !=0){
            SparseArray<String> picSparse = new SparseArray<>();
            int size = bean.getPics().size();
            for (int i =0;i<size;i++) {
                picSparse.set(i,bean.getPics().get(i).getOriginUrl());
            }
            binding.setPicture(picSparse);
        }
        try {
            JSONObject object = new JSONObject(bean.getJson());
            binding.tvMessage.setText(object.getString("msg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

class DynamicListViewHolder extends RecyclerView.ViewHolder {

    public DynamicListViewHolder(ItemDynamicListBinding binding) {
        super(binding.getRoot());
    }
}
