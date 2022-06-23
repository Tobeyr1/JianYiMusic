package com.tobery.personalmusic;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

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
        if (!url.isEmpty()) {
            RequestOptions options = new RequestOptions()
                    //这里我通过添加参数DiskCaheStrategy.RESPURCE来使其缓存我们定好的图片大小样式，而不是缓存原图片大小
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
}
