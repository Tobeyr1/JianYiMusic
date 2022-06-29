package com.tobery.personalmusic.music

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.lzx.starrysky.notification.imageloader.ImageLoaderCallBack
import com.lzx.starrysky.notification.imageloader.ImageLoaderStrategy
import com.tobery.personalmusic.R

class GlideImageLoader :ImageLoaderStrategy {
    override fun loadImage(context: Context, url: String?, callBack: ImageLoaderCallBack) {
        Glide.with(context).asBitmap().load(url).placeholder(R.drawable.ic_banner_loading).into(object : CustomTarget<Bitmap?>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                callBack.onBitmapLoaded(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                callBack.onBitmapFailed(placeholder)
            }

        })
    }
}