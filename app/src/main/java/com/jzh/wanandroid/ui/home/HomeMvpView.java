package com.jzh.wanandroid.ui.home;

import android.view.View;

import com.jzh.wanandroid.entity.home.ArticleResponse;
import com.jzh.wanandroid.entity.home.BannerResponse;
import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * author:jzh
 * desc:
 * Date:2018/08/20 15:23
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface HomeMvpView extends MvpView {
    void onSucc(BannerResponse response);

    void onGetArticleSucc(ArticleResponse response);

    void onFail(String msg);

    void onCollectionSucc(View view,int position);

    void onUnCollectionSucc(View view,int position);

    interface HomeMvpPresenter<v extends HomeMvpView> extends MvpPresenter<v> {
        void doGetBannerCall();

        void doGetArticleCall(int offset, boolean isClick);

        void doCollection(Long id, View view, int position);

        void doUnCollection(Long id, View view, int position);
    }
}
