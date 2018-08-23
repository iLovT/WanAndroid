package com.jzh.wanandroid.ui.base;


/**
 * Author:jzh
 * desc:view 基本persenter 操作基础类，定义view 控件界面的基本操作功能
 * Date:2018/08/16 11:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(String msg);

    void handleApiCode(int code);

}
