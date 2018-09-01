package com.jzh.wanandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/29 09:40
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class TodoPageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public TodoPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
