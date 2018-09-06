package com.jzh.wanandroid.ui.todo;

import com.jzh.wanandroid.entity.todo.TodoResponse;
import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * author:jzh
 * desc:
 * Date:2018/08/30 17:07
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface DealtMvpView extends MvpView {
    void onSucc(TodoResponse response);

    void onFail(String msg);

    void onDeleteSucc(String msg);

    interface DealtMvpPresenter<v extends DealtMvpView> extends MvpPresenter<v> {
        void doTodoListCall(int type, boolean isClick);

        void doDeleteTodoCall(Long id);
    }
}
