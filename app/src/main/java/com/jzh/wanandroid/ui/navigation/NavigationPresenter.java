package com.jzh.wanandroid.ui.navigation;

import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.entity.navigation.NavigationResponse;
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
 * Date:2018/08/28 11:27
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class NavigationPresenter<v extends NavigationMvpView> extends BasePresenter<v> implements NavigationMvpView.NavigationMvpPresenter<v> {
    @Inject
    public NavigationPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doGetNavigationCall() {
        getCompositeDisposable().add(getDataManager().doGetNavigationApiCall().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NavigationResponse>() {
                    @Override
                    public void accept(@NonNull NavigationResponse response) throws Exception {
                        getMvpView().hideLoading();
                        if (response.getErrorCode() >= 0) {
                            getMvpView().onSucc(response);
                        } else {
                            getMvpView().onFail("" + response.getErrorMsg());
                        }
                    }
                }, getConsumer(true))
        );
    }

    @Override
    public void handleApiError(String msg) {
        super.handleApiError(msg);
        getMvpView().onFail("" + msg);
    }
}
