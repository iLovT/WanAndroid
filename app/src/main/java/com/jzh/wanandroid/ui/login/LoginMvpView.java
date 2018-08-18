package com.jzh.wanandroid.ui.login;

import com.jzh.wanandroid.entity.login.LoginResponse;
import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * @author jzh
 *         desc:
 *         Date:2018/08/17 10:11
 *         Email:jzh970611@163.com
 *         Github:https://github.com/iLovT
 */

public interface LoginMvpView extends MvpView {
    void onSucc(LoginResponse response);

    void onFail(String msg);

    interface LoginMvpPresenter<v extends LoginMvpView> extends MvpPresenter<v> {
        void doLoginCall(String username, String password);
    }
}
