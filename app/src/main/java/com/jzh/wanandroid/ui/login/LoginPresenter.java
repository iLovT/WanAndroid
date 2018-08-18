package com.jzh.wanandroid.ui.login;

import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.entity.login.LoginResponse;
import com.jzh.wanandroid.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author:jzh
 * desc:登录presenter
 * Date:2018/08/17 10:36
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class LoginPresenter<v extends LoginMvpView> extends BasePresenter<v> implements LoginMvpView.LoginMvpPresenter<v> {

    @Inject
    public LoginPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doLoginCall(String username, String password) {
        getCompositeDisposable().add(
                getDataManager().doLoginApiCall(username, password).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<LoginResponse>() {
                            @Override
                            public void accept(@NonNull LoginResponse response) throws Exception {
                                getMvpView().hideProgressLoadingDialog();
                                if (response.getErrorCode() > 0) {
                                    getMvpView().onSucc(response);
                                    getDataManager().setLoginStaus(true);
                                } else {
                                    getMvpView().onFail(response.getErrorMsg());
                                }
                            }
                        }, getConsumer()));
    }
}
