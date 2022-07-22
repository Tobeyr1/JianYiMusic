package com.tobery.lib.util

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

/**
 * dp2px
 * sp2px
 */
object PxUtils {
    @JvmStatic
    fun dpToPx(dp: Int, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    @JvmStatic
    fun spToPx(sp: Int, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    @JvmStatic
    fun getScreenHeight(context: Context): Int = context.resources.displayMetrics.heightPixels

    @JvmStatic
    fun getScreenWidth(context: Context):Int = context.resources.displayMetrics.widthPixels

}