package com.jzh.wanandroid.ui.todo;

import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * author:jzh
 * desc:
 * Date:2018/09/01 10:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface AddTodoMvpView extends MvpView {
    void onSucc(String msg);

    interface AddTodoMvpPresenter<v extends AddTodoMvpView> extends MvpPresenter<v> {
        void doAddTodoCall(String title, String content, String date, int type);
    }
}
