package com.jzh.wanandroid.ui.navigation;

import android.view.View;

import com.jzh.wanandroid.ui.base.BaseFragment;

/**
 * author:jzh
 * desc:导航
 * Date:2018/08/20 14:37
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class NavigationFragment extends BaseFragment {
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
