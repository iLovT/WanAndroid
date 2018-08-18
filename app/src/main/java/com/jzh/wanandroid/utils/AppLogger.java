package com.jzh.wanandroid.utils;

import android.util.Log;

import com.jzh.wanandroid.BuildConfig;


/**
 * Author:jzh
 * desc:log utils
 * Date:2018/08/16 11:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class AppLogger {

    public static void i(String tag, String s) {
        if (BuildConfig.LOG_DEBUG) {
            Log.i(tag, s);
        }
    }

    public static void e(String tag, String s) {
        if (BuildConfig.LOG_DEBUG) {
            Log.e(tag, s);
        }
    }

    /**
     * 华为荣耀7x机型无法显示log.d
     */
    public static void d(String tag, String s) {
        if (BuildConfig.LOG_DEBUG) {
            Log.d(tag, s);
        }
    }

    public static void w(String tag, String s) {
        if (BuildConfig.LOG_DEBUG) {
            Log.w(tag, s);
        }
    }
}
