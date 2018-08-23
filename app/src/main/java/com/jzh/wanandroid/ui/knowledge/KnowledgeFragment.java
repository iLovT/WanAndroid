package com.jzh.wanandroid.ui.knowledge;

import android.view.View;

import com.jzh.wanandroid.ui.base.BaseFragment;

/**
 * author:jzh
 * desc:知识体系
 * Date:2018/08/20 14:36
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class KnowledgeFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initWidget(View view) {
        setHeadVisibility(View.GONE);
    }

    @Override
    protected void lazyLoad() {

    }
}
