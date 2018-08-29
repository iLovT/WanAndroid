package com.jzh.wanandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jzh.wanandroid.ui.knowledge.KnowledgeListFragment;
import com.jzh.wanandroid.ui.project.ProjectListFragment;

import java.util.List;

/**
 * author:jzh
 * desc:fragment pageadapter
 * Date:2018/08/27 17:46
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class KnowledgePageAdapter extends FragmentStatePagerAdapter {
    private List<KnowledgeListFragment> fragments;

    public KnowledgePageAdapter(FragmentManager fm, List<KnowledgeListFragment> fragments) {
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
