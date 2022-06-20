package com.tobery.personalmusic.util;

public class ClickUtil {
    //两次按钮点击时间间隔不能少于1s
    private static final long MIN_CLICK_DELAY_TIME = 800L;
    private static long lastClickTime;

    public static boolean enableClick() {
        boolean isEnabled = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) > MIN_CLICK_DELAY_TIME) {
            isEnabled = true;
        }
        lastClickTime = curClickTime;
        return isEnabled;
    }

}
