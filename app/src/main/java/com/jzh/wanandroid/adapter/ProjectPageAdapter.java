package com.jzh.wanandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jzh.wanandroid.ui.project.ProjectListFragment;

import java.util.List;

/**
 * author:jzh
 * desc:fragment pageadapter
 * Date:2018/08/24 10:46
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class ProjectPageAdapter extends FragmentStatePagerAdapter {
    private List<ProjectListFragment> fragments;

    public ProjectPageAdapter(FragmentManager fm, List<ProjectListFragment> fragments) {
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
