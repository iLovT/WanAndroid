package com.jzh.wanandroid;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.di.component.ApplicationComponent;
import com.jzh.wanandroid.di.component.DaggerApplicationComponent;
import com.jzh.wanandroid.di.module.ApplicationModule;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * author:jzh
 * desc:application
 * Date:2018/08/16 09:58
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class MyApp extends Application {
    public static MyApp instance;
    private Set<Activity> allActivities;
    /**
     * 数据管理器
     */
    @Inject
    public DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new DeliveryHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context);
            }
        });
    }

    /**
     * 解决方法数超出限制，在5.0以下分包操作
     *
     * @param base 上下文
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        BGASwipeBackHelper.init(this, null);
        instance = this;
        //实例化dagger
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
        uMengInit();
    }

    /**
     * 友盟配置
     */
    private void uMengInit() {
        /*
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        //日志开关
        if (BuildConfig.LOG_DEBUG) {
            UMConfigure.setLogEnabled(true);
        }
        /*
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);
        /*
         * EScenarioType.E_UM_NORMAL 普通统计场景，如果您在埋点过程中没有使用到
         U-Game统计接口，请使用普通统计场景。
         EScenarioType.E_UM_GAME 游戏场景 ，如果您在埋点过程中需要使用到U-Game
         统计接口，则必须设置游戏场景，否则所有的U-Game统计接口不会生效。
         */
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //secretkey设置接口，防止AppKey被盗用，secretkey需要网站申请
        //设置是否将自动捕获到的程序异常传回服务器
        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    public static MyApp getInstance() {
        if (instance == null) {
            synchronized (MyApp.class) {
                if (instance == null) {
                    instance = new MyApp();
                }
            }
        }
        return instance;
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    /**
     * 添加activity
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 移除activity
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    /**
     * 退出
     */
    public void exit() {
        for (Activity activity : allActivities) {
            if (activity != null) {
                activity.finish();
            }
        }

    }
}
