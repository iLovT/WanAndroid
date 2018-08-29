package com.jzh.wanandroid.ui.knowledge;

import android.view.View;

import com.jzh.wanandroid.entity.home.ArticleResponse;
import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * author:jzh
 * desc:
 * Date:2018/08/28 09:42
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface KnowledgeListMvpView extends MvpView {
    void onGetKnowledgeListSucc(ArticleResponse response);

    void onFail(String msg);

    void onCollectionSucc(View view, int position);

    void onUnCollectionSucc(View view, int position);

    interface KnowledgeListMvpPresenter<v extends KnowledgeListMvpView> extends MvpPresenter<v> {

        void doGetKnowledgeListCall(int offset, Integer cid, boolean isClick);

        void doCollection(Long id, View view, int position);

        void doUnCollection(Long id, View view, int position);
    }
}
