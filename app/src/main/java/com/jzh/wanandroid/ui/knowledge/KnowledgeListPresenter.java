package com.jzh.wanandroid.ui.knowledge;

import android.view.View;

import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.entity.home.ArticleResponse;
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
 * Date:2018/08/28 09:43
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class KnowledgeListPresenter<v extends KnowledgeListMvpView> extends BasePresenter<v> implements KnowledgeListMvpView.KnowledgeListMvpPresenter<v> {
    @Inject
    public KnowledgeListPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doGetKnowledgeListCall(int offset, Integer cid, boolean isClick) {
        getCompositeDisposable().add(
                getDataManager().doGetKnowledgeListApiCall(offset, cid).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ArticleResponse>() {
                            @Override
                            public void accept(@NonNull ArticleResponse response) throws Exception {
                                getMvpView().hideLoading();
                                getMvpView().hideErrorLayout();
                                if (response.getErrorCode() >= 0) {
                                    getMvpView().onGetKnowledgeListSucc(response);
                                } else {
                                    getMvpView().onFail(response.getErrorMsg());
                                }
                            }
                        }, getConsumer(isClick)));
    }

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
