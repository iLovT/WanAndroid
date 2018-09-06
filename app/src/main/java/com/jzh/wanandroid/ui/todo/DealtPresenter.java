package com.jzh.wanandroid.ui.todo;

import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.entity.todo.TodoDeleteResponse;
import com.jzh.wanandroid.entity.todo.TodoResponse;
import com.jzh.wanandroid.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author:jzh
 * desc:
 * Date:2018/08/30 17:08
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class DealtPresenter<v extends DealtMvpView> extends BasePresenter<v> implements DealtMvpView.DealtMvpPresenter<v> {
    @Inject
    public DealtPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doTodoListCall(int type, boolean isClick) {
        getCompositeDisposable().add(getDataManager().doGetTodoApiCall(type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TodoResponse>() {
                    @Override
                    public void accept(@NonNull TodoResponse response) throws Exception {
                        getMvpView().hideLoading();
                        getMvpView().hideProgressLoadingDialog();
                        if (response.getErrorCode() >= 0) {
                            getMvpView().onSucc(response);
                        } else {
                            getMvpView().onFail(response.getErrorMsg());
                        }
                    }
                }, getConsumer(isClick)));
    }

    @Override
    public void doDeleteTodoCall(Long id) {
        getCompositeDisposable().add(
                getDataManager().doDeleteTodoApiCall(id).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<TodoDeleteResponse>() {
                            @Override
                            public void accept(@NonNull TodoDeleteResponse todoDeleteResponse) throws Exception {
                                getMvpView().hideProgressLoadingDialog();
                                if (todoDeleteResponse.getErrorCode() >= 0) {
                                    getMvpView().onDeleteSucc("删除成功");
                                } else {
                                    getMvpView().onToastFail("" + todoDeleteResponse.getErrorMsg());
                                }
                            }
                        }, getConsumer(false)));
    }

    @Override
    public void handleApiError(String msg) {
        super.handleApiError(msg);
        getMvpView().onFail("" + msg);
    }
}
