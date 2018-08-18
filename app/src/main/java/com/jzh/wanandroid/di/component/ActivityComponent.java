package com.jzh.wanandroid.di.component;


import com.jzh.wanandroid.di.PerActivity;
import com.jzh.wanandroid.di.module.ActivityModule;
import com.jzh.wanandroid.ui.login.LoginActivity;

import dagger.Component;


/**
 * Author:jzh
 * desc:依赖注入 activity 基本依赖：定义需要注入的业务类，具体实现
 * Date:2018/08/16 9:51
 * Email:jzh970611@163.com
 * GitHub:https://github.com/iLovT
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);

}
