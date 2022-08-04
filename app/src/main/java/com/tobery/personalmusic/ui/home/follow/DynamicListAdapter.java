package com.tobery.personalmusic.ui.home.follow;


import static com.tobery.personalmusic.ui.home.follow.FollowFragment.eventType;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.tobery.personalmusic.databinding.ItemDynamicListBinding;
import com.tobery.personalmusic.entity.follow.DynamicListEntity;
import com.tobery.personalmusic.util.ClickUtil;
import org.json.JSONException;
import org.json.JSONObject;

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
public class DynamicListAdapter extends ListAdapter<DynamicListEntity.EventEntity,DynamicListViewHolder> {


    private final Context mContext;

    private ItemDynamicListBinding binding;

    public DynamicListAdapter(Context context) {
        super(new dynamicCallback());
        this.mContext = context;
    }

    @NonNull
    @Override
    public DynamicListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemDynamicListBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DynamicListViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull DynamicListViewHolder holder, int position) {
        DynamicListEntity.EventEntity bean = getItem(position);
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

}

class DynamicListViewHolder extends RecyclerView.ViewHolder {

    public DynamicListViewHolder(ItemDynamicListBinding binding) {
        super(binding.getRoot());
    }
}
class dynamicCallback extends DiffUtil.ItemCallback<DynamicListEntity.EventEntity>{

    @Override
    public boolean areItemsTheSame(@NonNull DynamicListEntity.EventEntity oldItem, @NonNull DynamicListEntity.EventEntity newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull DynamicListEntity.EventEntity oldItem, @NonNull DynamicListEntity.EventEntity newItem) {
        return oldItem.getId() == newItem.getId();
    }
}