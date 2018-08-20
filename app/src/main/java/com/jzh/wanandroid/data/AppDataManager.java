package com.jzh.wanandroid.data;

import android.content.Context;


import com.jzh.wanandroid.data.db.DbHelper;
import com.jzh.wanandroid.data.pref.PreferencesHelper;
import com.jzh.wanandroid.di.ApplicationContext;
import com.jzh.wanandroid.entity.login.LoginResponse;
import com.jzh.wanandroid.entity.login.RegisterResponse;
import com.jzh.wanandroid.network.ApiHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * author:jzh
 * desc:数据访问管理总接口实现,所有项目数据层都通过该方式实现
 * Date:2018/08/16 10:00
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final ApiHelper mApiHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context, DbHelper dbHelper,
                          ApiHelper apiHelper,
                          PreferencesHelper preferencesHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public String handleResponseCode(int code) {
        // 处理code
        return null;
    }


    @Override
    public void setLoginStaus(boolean isLogin) {
        mPreferencesHelper.setLoginStaus(isLogin);
    }

    @Override
    public boolean getLoginStaus() {
        return mPreferencesHelper.getLoginStaus();
    }

    @Override
    public Observable<LoginResponse> doLoginApiCall(String username, String password) {
        return mApiHelper.doLoginApiCall(username, password);
    }

    @Override
    public Observable<RegisterResponse> doRegisterApiCall(String username, String password, String repassword) {
        return mApiHelper.doRegisterApiCall(username, password, repassword);
    }
}
