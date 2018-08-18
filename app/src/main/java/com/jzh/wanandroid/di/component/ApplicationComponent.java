package com.jzh.wanandroid.di.component;

import android.app.Application;
import android.content.Context;


import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.di.ApplicationContext;
import com.jzh.wanandroid.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Author:jzh
 * desc:依赖注入 app 基本依赖：定义需要注入的业务类，具体实现
 * Date:2018/08/16 10:00
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MyApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
