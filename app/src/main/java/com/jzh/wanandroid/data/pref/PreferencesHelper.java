package com.jzh.wanandroid.data.pref;

/**
 * author:jzh
 * desc:Preferences 保存数据接口，定义所有保存的方法
 * Date:2018/08/16 10:00
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
public interface PreferencesHelper {
    /**
     * 设置登录状态
     *
     * @param isLogin 是否已登录
     */
    void setLoginStaus(boolean isLogin);

    /**
     * get staus
     *
     * @return is login
     */
    boolean getLoginStaus();
}
