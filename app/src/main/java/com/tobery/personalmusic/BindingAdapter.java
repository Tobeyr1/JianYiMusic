package com.tobery.personalmusic;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.tobery.personalmusic.util.ClickUtil;

import java.lang.reflect.Method;

import kotlin.Function;
import kotlin.jvm.functions.Function0;

/**
 * @Package: com.tobery.personalmusic
 * @ClassName: BindingAdapter
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/23 23:39
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/23 23:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BindingAdapter {

    @androidx.databinding.BindingAdapter({"imSrc", "error"})
    public static void loadUrl(ImageView view, @Nullable String url, Drawable error) {
        if (url != null) {
            RequestOptions options = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .circleCrop();
            Glide.with(view.getContext())
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(error)
                    .apply(options)
                    .into(view);
        }
    }

    @androidx.databinding.BindingAdapter(value = "rectangleSrc")
    public static void loadRadiusImage(ImageView view,@Nullable String url){
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_banner_loading)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transform(new CenterCrop(),new RoundedCorners(10))
                .error(R.mipmap.ic_launcher);
        Glide.with(view.getContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(options)
                .into(view);
    }

    @androidx.databinding.BindingAdapter(value = "onsingleclick")
    public static void onSingleClick(View view, final Function0  function){
        view.setOnClickListener(v -> {
            if (ClickUtil.enableClick()){
                function.invoke();
            }
        });
    }
}
