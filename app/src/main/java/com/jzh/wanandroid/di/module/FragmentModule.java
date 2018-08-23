package com.jzh.wanandroid.di.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jzh.wanandroid.di.ActivityContext;


import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * author:jzh
 * desc:
 * Date:2018/08/20 15:46
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mFragment.getActivity();
    }

    @Provides
    Fragment provideFragment() {
        return mFragment;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
