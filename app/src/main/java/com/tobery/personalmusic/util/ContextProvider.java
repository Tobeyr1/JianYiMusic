package com.tobery.personalmusic.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.tobery.personalmusic.ApplicationContextProvider;

public class ContextProvider {

    @SuppressLint("StaticFieldLeak")
    private static volatile ContextProvider instance;
    private final Context mContext;

    private ContextProvider(Context context) {
        mContext = context;
    }

    /**
     * 获取实例
     */
    public static ContextProvider get() {
        if (instance == null) {
            synchronized (ContextProvider.class) {
                if (instance == null) {
                    Context context = ApplicationContextProvider.context;
                    if (context == null) {
                        throw new IllegalStateException("context == null");
                    }
                    instance = new ContextProvider(context);
                }
            }
        }
        return instance;
    }

    /**
     * 获取上下文
     */
    public Context getContext() {
        return mContext;
    }

    public Application getApplication() {
        return (Application) mContext.getApplicationContext();
    }
}
