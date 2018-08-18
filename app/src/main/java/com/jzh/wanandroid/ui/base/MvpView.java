package com.jzh.wanandroid.ui.base;

import android.support.annotation.StringRes;


/**
 * Author:jzh
 * desc:控件基本功能接口
 * Date:2018/08/16 11:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface MvpView {

    void showLoading(String msg);

    void hideLoading();


    /**
     * 显示错误布局
     *
     * @param msg msg
     */
    void showErrorLayout(String msg);

    /**
     * 隐藏错误布局
     */
    void hideErrorLayout();

    void showProgressLoadingDialog(String msg);

    void hideProgressLoadingDialog();

    void onToast(@StringRes int resId);

    void onToast(String message);

    void onToastSucc(String message);

    void onToastSucc(@StringRes int resId);

    void onToastFail(String message);

    void onToastFail(@StringRes int resId);

    void onToastInfo(String message);

    void onToastInfo(@StringRes int resId);

    void onToastWarn(String message);

    void onToastWarn(@StringRes int resId);

    void onToastNormal(String message);

    void onToastNormal(@StringRes int resId);

    /**
     * @return 判断网络是否连接
     */
    boolean isNetworkConnected();

    /**
     * 隐藏软键盘
     */
    void hideKeyboard();

}
