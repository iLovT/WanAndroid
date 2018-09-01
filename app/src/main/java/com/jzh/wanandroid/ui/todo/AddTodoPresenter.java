package com.jzh.wanandroid.ui.todo;

import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.entity.todo.AddTodoResponse;
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
 * Date:2018/09/01 10:18
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class AddTodoPresenter<v extends AddTodoMvpView> extends BasePresenter<v> implements AddTodoMvpView.AddTodoMvpPresenter<v> {
    @Inject
    public AddTodoPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doAddTodoCall(String title, String content, String date, int type) {
        getCompositeDisposable().add(
                getDataManager().doAddTodoApiCall(title, content, date, type)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<AddTodoResponse>() {
                            @Override
                            public void accept(@NonNull AddTodoResponse addTodoResponse) throws Exception {
                                getMvpView().hideProgressLoadingDialog();
                                if (addTodoResponse.getErrorCode() >= 0) {
                                    getMvpView().onSucc("添加成功");
                                } else {
                                    getMvpView().onToastFail("" + addTodoResponse.getErrorMsg());
                                }
                            }
                        }, getConsumer(false)));
    }
}
