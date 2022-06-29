package com.tobery.personalmusic.music

import android.annotation.SuppressLint
import com.lzx.starrysky.utils.KtPreferences

@SuppressLint("StaticFieldLeak")
object SpConstant: KtPreferences() {
    var HAS_PERMISSION by booleanPref()
    //播放模式
    const val REPEAT_MODE_NONE = 100     //顺序播放
    const val REPEAT_MODE_ONE = 200      //单曲播放
    const val REPEAT_MODE_SHUFFLE = 300  //随机播放
    const val REPEAT_MODE_REVERSE = 400  //倒序播放


}