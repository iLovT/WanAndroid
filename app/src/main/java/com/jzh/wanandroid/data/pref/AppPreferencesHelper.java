package com.jzh.wanandroid.data.pref;

import android.content.Context;
import android.content.SharedPreferences;


import com.jzh.wanandroid.di.ApplicationContext;
import com.jzh.wanandroid.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * author:jzh
 * desc:SharedPreferences业务操作类
 * Date:2018/08/16 10:00
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPrefs;
    private final String LOGIN_STAUS = "LOGIN_STAUS";

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setLoginStaus(boolean isLogin) {
        mPrefs.edit().putBoolean(LOGIN_STAUS, isLogin).apply();
    }

    @Override
    public boolean getLoginStaus() {
        return mPrefs.getBoolean(LOGIN_STAUS, false);
    }
}
