package com.jzh.wanandroid.ui.project;

import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.entity.project.ProjectTypeResponse;
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
 * Date:2018/08/23 17:08
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class ProjectPresenter<v extends ProjectMvpView> extends BasePresenter<v> implements ProjectMvpView.ProjectMvpPresenter<v> {

    @Inject
    public ProjectPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doGetProjectTypeCall() {
        getCompositeDisposable().add(getDataManager().
                doGetProjectTypeApiCall().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProjectTypeResponse>() {
                    @Override
                    public void accept(@NonNull ProjectTypeResponse response) throws Exception {
                        getMvpView().hideLoading();
                        if (response.getErrorCode() >= 0) {
                            getMvpView().onSucc(response);
                        } else {
                            getMvpView().onFail(response.getErrorMsg());
                        }
                    }
                }, getConsumer(true)));
    }

    @Override
    public void handleApiError(String msg) {
        super.handleApiError(msg);
        getMvpView().onFail("" + msg);
    }
}
