package com.jzh.wanandroid.ui.project;

import android.view.View;

import com.jzh.wanandroid.entity.project.ProjectListResponse;
import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * author:jzh
 * desc:
 * Date:2018/08/24 10:55
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface ProjectListMvpView extends MvpView {
    void onSucc(ProjectListResponse response);

    void onFail(String msg);
    void onCollectionSucc(View view,int position);

    void onUnCollectionSucc(View view,int position);

    interface ProjectListMvpPresenter<v extends ProjectListMvpView> extends MvpPresenter<v> {
        void doGetProjectListCall(Integer cid, int offset, boolean isClick);
        void doCollection(Long id, View view, int position);

        void doUnCollection(Long id, View view, int position);
    }
}
