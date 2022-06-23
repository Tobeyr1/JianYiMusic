package com.tobery.personalmusic.util;

import static com.tobery.personalmusic.util.Constant.APP_SHARED_SAVE;
import static com.tobery.personalmusic.util.Constant.AUTH_TOKEN;
import static com.tobery.personalmusic.util.Constant.USER_INFO;
import static com.tobery.personalmusic.util.Constant.USER_PHONE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tobery.personalmusic.entity.LoginEntity;

/**
 * @Package: com.tobery.personalmusic.util
 * @ClassName: SharePreferencesUtil
 * @Author: Tobey_r1
 * @CreateDate: 2022/6/15 22:46
 * @Description: java类作用描述
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/15 22:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SharePreferencesUtil {

    private static volatile SharePreferencesUtil mInstance;

    private static SharedPreferences sharedPref;

    private static SharedPreferences.Editor editor;

    private SharePreferencesUtil() {
    }

    public static SharePreferencesUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SharePreferencesUtil.class) {
                if (mInstance == null) {
                    init(context);
                    mInstance = new SharePreferencesUtil();
                }
            }
        }
        return mInstance;
    }

    private static void init(Context context) {
        if (sharedPref == null) {
            sharedPref = context.getSharedPreferences(
                    APP_SHARED_SAVE, Context.MODE_PRIVATE);
        }
        editor = sharedPref.edit();
    }


    public String getAuthToken(String key) {
        return getString(key);
    }

    public void saveUserInfo(LoginEntity bean, String phoneNumber) {
        if (bean.getBindings().size() > 1) {
            saveAuthToken(AUTH_TOKEN, bean.getBindings().get(1).getTokenJsonStr());
        }
        saveAccountNum(phoneNumber);
        String userInfo = new Gson().toJson(bean);
        saveString(USER_INFO, userInfo);
    }

    public String getUserInfo() {
        return getString(USER_INFO);
    }

    public void savePermissionDeniedNum(String key, int value) {
        saveInt(key, value);
    }

    public int getPermissionDeniedNum(String key) {
        return getInt(key);
    }

    private void saveAuthToken(String key, String value) {
        saveString(key, value);
    }

    private void saveAccountNum(String phoneNumber) {
        saveString(USER_PHONE, phoneNumber);
    }

    private void saveString(String key, String value) {
        editor.putString(key, value).apply();
    }
    private String getString(String key) {
        return sharedPref.getString(key, "");
    }

    private int getInt(String key) {
        return sharedPref.getInt(key,0);
    }

    private void saveInt(String key, int defaultValue) {
        editor.putInt(key,defaultValue).apply();
    }
}
