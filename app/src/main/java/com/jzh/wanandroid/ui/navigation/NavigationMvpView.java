package com.jzh.wanandroid.ui.navigation;

import com.jzh.wanandroid.entity.navigation.NavigationResponse;
import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * author:jzh
 * desc:
 * Date:2018/08/28 11:26
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface NavigationMvpView extends MvpView {
    void onSucc(NavigationResponse response);

    void onFail(String msg);

    interface NavigationMvpPresenter<v extends NavigationMvpView> extends MvpPresenter<v> {
        void doGetNavigationCall();
    }
}
