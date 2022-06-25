package com.tobery.personalmusic.ui.home.discover.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tobery.personalmusic.R;
import com.tobery.personalmusic.entity.banner_bean;
import com.tobery.personalmusic.entity.home.BannerExtInfoEntity;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @Package: com.tobery.personalmusic.ui.home.discover
 * @ClassName: BannerAdapter
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/21 23:00
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/21 23:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class bannerAdapter extends BannerAdapter<BannerExtInfoEntity.BannersEntity, TitleHolder> {


    public bannerAdapter(List<BannerExtInfoEntity.BannersEntity> datas) {
        super(datas);
    }

    @Override
    public TitleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new TitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_title_image,parent,false));
    }

    @Override
    public void onBindView(TitleHolder holder, BannerExtInfoEntity.BannersEntity data, int position, int size) {
        Glide.with(holder.imageView)
                .load(data.getPic())
                .thumbnail(Glide.with(holder.itemView)
                        .load(R.drawable.ic_banner_loading))
                .into(holder.imageView);
        holder.textView.setText(data.getTypeTitle());
    }
}
class TitleHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;

    public TitleHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.bannerImage);
        textView = itemView.findViewById(R.id.bannerTitle);
    }
}