package com.jzh.wanandroid.ui.knowledge;

import com.jzh.wanandroid.entity.knowledge.KnowledgeResponse;
import com.jzh.wanandroid.ui.base.MvpPresenter;
import com.jzh.wanandroid.ui.base.MvpView;

/**
 * author:jzh
 * desc:
 * Date:2018/08/25 10:58
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface KnowledgeMvpView extends MvpView {
    void onSucc(KnowledgeResponse datas);

    void onFail(String msg);

    interface KnowledgeMvpPresenter<v extends KnowledgeMvpView> extends MvpPresenter<v> {
        void doGetKnowledgeCall();
    }
}
