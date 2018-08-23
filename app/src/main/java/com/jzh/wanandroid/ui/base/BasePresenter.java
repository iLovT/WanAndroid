package com.jzh.wanandroid.ui.base;

import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.network.ExceptionHandle;
import com.jzh.wanandroid.utils.AppLogger;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


/**
 * Author:jzh
 * desc:P层基类
 * Date:2018/08/16 11:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;

    private final DataManager mDataManager;

    private final CompositeDisposable mCompositeDisposable;

    private static final String TAG = "BasePresenter";

    @Inject
    public BasePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        mDataManager = dataManager;
        mCompositeDisposable = compositeDisposable;
    }


    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    //判null 防止出现null 异常
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    /**
     * 检查view是否初始化
     */
    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new MvpViewNotAttachedException();
        }
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void handleApiError(String msg) {
        AppLogger.e(TAG, msg);
        getMvpView().onToastFail(msg);
    }

    @Override
    public void handleApiCode(int code) {
        //处理 请求code
    }


    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

    /**
     * 获得处理异常统一方法,基类处理，如有特殊需求，需要对非正常异常情况进行处理，请单独重写
     *
     * @param isClick 是否显示消息为 xxx 点击重试
     * @return consumer
     */
    public Consumer getConsumer(final boolean isClick) {
        return new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                throwable.printStackTrace();
                if (!isViewAttached()) {
                    return;
                }
                getMvpView().hideErrorLayout();
                getMvpView().hideLoading();
                getMvpView().hideProgressLoadingDialog();
                handleApiError(ExceptionHandle.handleException(throwable, isClick).message);
            }
        };
    }

    /**
     * 针对不吐司，只显示异常消息的情况
     */
    public Consumer otherConsumer(final boolean isClick) {
        return new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                throwable.printStackTrace();
                if (!isViewAttached()) {
                    return;
                }
                getMvpView().hideErrorLayout();
                getMvpView().hideLoading();
                getMvpView().hideProgressLoadingDialog();
                getErrorMsg(ExceptionHandle.handleException(throwable, isClick).message);
            }
        };
    }

    public void getErrorMsg(String msg) {

    }

}
