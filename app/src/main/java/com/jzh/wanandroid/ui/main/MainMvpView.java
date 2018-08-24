package com.jzh.wanandroid.ui.main;

import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * author:jzh
 * desc:
 * Date:2018/08/23 16:35
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface MainMvpView extends MvpView {


    interface MainMvpPresenter<v extends MainMvpView> extends MvpPresenter<v> {
        /**
         * 获取项目分类
         */
        void doGetProjectType();
    }
}
