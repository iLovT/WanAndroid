package com.jzh.wanandroid.ui.todo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.adapter.TodoIndicatorAdapter;
import com.jzh.wanandroid.adapter.TodoPageAdapter;
import com.jzh.wanandroid.ui.base.BaseFragment;
import com.jzh.wanandroid.ui.login.LoginActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:jzh
 * desc:todo代办清单
 * Date:2018/08/20 14:37
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class TodoFragment extends BaseFragment {
    @BindView(R.id.fragment_todo_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.fragment_todo_viewpager)
    ViewPager mViewpager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragmen_todo;
    }

    @Override
    protected void initWidget(View view) {
        setUnBinder(ButterKnife.bind(this, view));
        setHeadVisibility(View.GONE);
    }

    @Override
    public void getErrorViewClick() {
        super.getErrorViewClick();
        goActivity(LoginActivity.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MyApp.getInstance().mDataManager.getLoginStaus()) {
            initIndicator();
        } else {
            showErrorLayout(getString(R.string.not_login));
        }
    }

    private void initIndicator() {
        hideErrorLayout();
        magicIndicator.setVisibility(View.VISIBLE);
        mViewpager.setVisibility(View.VISIBLE);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new TodoIndicatorAdapter(getActivity(), mViewpager));
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewpager);
        initViewpager();
    }

    private void initViewpager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(DealtFragment.getInstance(false));
        fragments.add(DealtFragment.getInstance(true));
        mViewpager.setAdapter(new TodoPageAdapter(getChildFragmentManager(), fragments));
    }

    @Override
    protected void lazyLoad() {

    }

}
