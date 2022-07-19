package com.tobery.lib.util

import android.view.View
import com.tobery.lib.util.ClickEvent.lastClickTime


const val VIEW_CLICK_INTERVAL_TIME = 800L

object ClickEvent {
    var lastClickTime: Long = 0L
}

interface OnSingleClickListener {

    fun onSingleClick(view: View)
}

fun View.setOnSingleClickListener(clickListener: (view: View) -> Unit) {
    setOnClickListener {
        if (lastClickTime + VIEW_CLICK_INTERVAL_TIME < System.currentTimeMillis()) {
            lastClickTime = System.currentTimeMillis()
            clickListener.invoke(this)
        }
    }
}

fun View.setOnSingleClickListener(clickListener: OnSingleClickListener?) {
    setOnClickListener {
        if (lastClickTime + VIEW_CLICK_INTERVAL_TIME < System.currentTimeMillis()) {
            lastClickTime = System.currentTimeMillis()
            clickListener?.onSingleClick(this)
        }
    }
}