package com.jzh.wanandroid.ui.project;

import com.jzh.wanandroid.entity.project.ProjectTypeResponse;
import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * author:jzh
 * desc:
 * Date:2018/08/23 17:06
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface ProjectMvpView extends MvpView {
    void onSucc(ProjectTypeResponse response);

    void onFail(String msg);

    interface ProjectMvpPresenter<v extends ProjectMvpView> extends MvpPresenter<v> {
        void doGetProjectTypeCall();
    }
}
