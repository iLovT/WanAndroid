package com.jzh.wanandroid.ui.login;

import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.entity.login.LoginResponse;
import com.jzh.wanandroid.entity.login.RegisterResponse;
import com.jzh.wanandroid.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author:jzh
 * desc:注册页presenter
 * Date:2018/08/20 10:09
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class RegisterPresenter<v extends RegisterMvpView> extends BasePresenter<v> implements RegisterMvpView.RegisterMvpPresenter<v> {
    @Inject
    public RegisterPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doRegisterCall(final String username, final String password, String repassword) {
        getCompositeDisposable().add(
                getDataManager().doRegisterApiCall(username, password, repassword)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<RegisterResponse>() {
                            @Override
                            public void accept(@NonNull RegisterResponse registerResponse) throws Exception {
                                getMvpView().hideProgressLoadingDialog();
                                if (registerResponse.getErrorCode() >= 0) {
                                    getMvpView().showProgressLoadingDialog(MyApp.getInstance().getString(R.string.login_loading));
                                    doLoginCall(username, password);
                                } else {
                                    getMvpView().onToastFail("" + registerResponse.getErrorMsg());
                                }
                            }
                        }, getConsumer(false)));
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
                                if (response.getErrorCode() >= 0) {
                                    getMvpView().onSucc();
                                    getDataManager().setLoginStaus(true);
                                } else {
                                    getMvpView().onToastFail("" + response.getErrorMsg());
                                }
                            }
                        }, getConsumer(false)));
    }
}
