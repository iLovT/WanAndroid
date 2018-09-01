package com.jzh.wanandroid.di.component;

import com.jzh.wanandroid.di.PerFragment;
import com.jzh.wanandroid.di.module.FragmentModule;
import com.jzh.wanandroid.ui.home.HomeFragment;
import com.jzh.wanandroid.ui.knowledge.KnowledgeFragment;
import com.jzh.wanandroid.ui.knowledge.KnowledgeListFragment;
import com.jzh.wanandroid.ui.navigation.NavigationFragment;
import com.jzh.wanandroid.ui.project.ProjectFragment;
import com.jzh.wanandroid.ui.project.ProjectListFragment;
import com.jzh.wanandroid.ui.todo.DealtFragment;

import dagger.Component;

/**
 * author:jzh
 * desc:
 * Date:2018/08/20 15:44
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(HomeFragment homeFragment);

    void inject(ProjectFragment projectFragment);

    void inject(ProjectListFragment projectListFragment);

    void inject(KnowledgeFragment knowledgeFragment);

    void inject(KnowledgeListFragment knowledgeListFragment);

    void inject(NavigationFragment navigationFragment);

    void inject(DealtFragment dealtFragment);
}
