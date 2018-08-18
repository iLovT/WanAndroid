package com.jzh.wanandroid.utils;

import com.google.gson.Gson;
import com.jzh.wanandroid.network.GsonParserFactory;
import com.jzh.wanandroid.network.Parser;

/**
 * Author:jzh
 * desc:
 * Date:2018/08/16 11:14
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class ParseUtil {

    private static Parser.Factory mParserFactory;

    public static void setParserFactory(Parser.Factory parserFactory) {
        mParserFactory = parserFactory;
    }

    public static Parser.Factory getParserFactory() {
        if (mParserFactory == null) {
            mParserFactory = new GsonParserFactory(new Gson());
        }
        return mParserFactory;
    }

    public static void shutDown() {
        mParserFactory = null;
    }
}
