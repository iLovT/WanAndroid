package com.jzh.wanandroid.di.module;

import android.app.Application;
import android.content.Context;


import com.jzh.wanandroid.common.Constants;
import com.jzh.wanandroid.data.AppDataManager;
import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.data.db.AppDbHelper;
import com.jzh.wanandroid.data.db.DbHelper;
import com.jzh.wanandroid.data.pref.AppPreferencesHelper;
import com.jzh.wanandroid.data.pref.PreferencesHelper;
import com.jzh.wanandroid.di.ApplicationContext;
import com.jzh.wanandroid.di.DatabaseInfo;
import com.jzh.wanandroid.di.PreferenceInfo;
import com.jzh.wanandroid.network.ApiHelper;
import com.jzh.wanandroid.network.AppApiHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author:jzh
 * desc:dagger2 注入  定义注入实现 相当于 new 某个对象实现
 * Date:2018/08/16 10:00
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return Constants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return Constants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }
}
