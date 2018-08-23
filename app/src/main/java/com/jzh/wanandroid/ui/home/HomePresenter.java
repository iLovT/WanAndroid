package com.jzh.wanandroid.ui.home;

import android.view.View;

import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.entity.home.ArticleResponse;
import com.jzh.wanandroid.entity.home.BannerResponse;
import com.jzh.wanandroid.entity.home.CollectionResponse;
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
 * Date:2018/08/20 15:22
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class HomePresenter<v extends HomeMvpView> extends BasePresenter<v> implements HomeMvpView.HomeMvpPresenter<v> {
    @Inject
    public HomePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doGetBannerCall() {
        getCompositeDisposable().add(getDataManager().doGetBannerApiCall()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BannerResponse>() {
                    @Override
                    public void accept(@NonNull BannerResponse response) throws Exception {
                        if (response.getErrorCode() >= 0 && response.getData() != null) {
                            getMvpView().onSucc(response);
                        } else {
                            getMvpView().onToastFail(response.getErrorMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                    }
                }));
    }

    @Override
    public void doGetArticleCall(int offset, boolean isClick) {
        getCompositeDisposable().add(
                getDataManager().doGetArticleApiCall(offset).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ArticleResponse>() {
                            @Override
                            public void accept(@NonNull ArticleResponse response) throws Exception {
                                getMvpView().hideLoading();
                                getMvpView().hideErrorLayout();
                                if (response.getErrorCode() >= 0) {
                                    getMvpView().onGetArticleSucc(response);
                                } else {
                                    getMvpView().onFail(response.getErrorMsg());
                                }
                            }
                        }, getConsumer(isClick)));
    }

    /**
     * 收藏
     */
    @Override
    public void doCollection(Long id, final View view, final int position) {
        getCompositeDisposable().add(getDataManager().doCollectionArticleApiCall(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CollectionResponse>() {
                    @Override
                    public void accept(@NonNull CollectionResponse collectionResponse) throws Exception {
                        if (collectionResponse.getErrorCode() >= 0) {
                            getMvpView().onCollectionSucc(view, position);
                        } else {
                            getMvpView().onToastWarn("" + collectionResponse.getErrorMsg());
                        }
                    }
                }, getConsumer(false)));
    }

    @Override
    public void doUnCollection(Long id, final View view, final int position) {
        getCompositeDisposable().add(getDataManager().doUnCollectionArticleApiCall(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CollectionResponse>() {
                    @Override
                    public void accept(@NonNull CollectionResponse collectionResponse) throws Exception {
                        if (collectionResponse.getErrorCode() >= 0) {
                            getMvpView().onUnCollectionSucc(view, position);
                        } else {
                            getMvpView().onToastWarn("" + collectionResponse.getErrorMsg());
                        }
                    }
                }, getConsumer(false)));
    }

    @Override
    public void handleApiError(String msg) {
        super.handleApiError(msg);
        getMvpView().onFail(msg);
    }
}

