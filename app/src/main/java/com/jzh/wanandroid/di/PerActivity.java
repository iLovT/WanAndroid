package com.jzh.wanandroid.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/**
 * Author:jzh
 * desc:依赖注入：定义注入体
 * Date:2018/08/16 10:00
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
