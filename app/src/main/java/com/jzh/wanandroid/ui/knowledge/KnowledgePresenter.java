package com.jzh.wanandroid.ui.knowledge;

import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.entity.knowledge.KnowledgeResponse;
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
 * Date:2018/08/25 11:00
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class KnowledgePresenter<v extends KnowledgeMvpView> extends BasePresenter<v> implements KnowledgeMvpView.KnowledgeMvpPresenter<v> {

    @Inject
    public KnowledgePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doGetKnowledgeCall() {
        getCompositeDisposable().add(getDataManager().
                doGetKnowledgeApiCall().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<KnowledgeResponse>() {
                    @Override
                    public void accept(@NonNull KnowledgeResponse response) throws Exception {
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
