package com.jzh.wanandroid.ui.login;

import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * author:jzh
 * desc:
 * Date:2018/08/20 10:07
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface RegisterMvpView extends MvpView {
    void onSucc();

    interface RegisterMvpPresenter<v extends RegisterMvpView> extends MvpPresenter<v> {
        void doRegisterCall(String username, String password, String repassword);

        void doLoginCall(String username, String password);
    }
}
