package com.jzh.wanandroid.network;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Author:jzh
 * desc:
 * Date:2018/08/16 11:15
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
public interface Parser<F, T> {

    T convert(F value) throws IOException;

    abstract class Factory {

        public Parser<ResponseBody, ?> responseBodyParser(Type type) {
            return null;
        }

        public Parser<?, RequestBody> requestBodyParser(Type type) {
            return null;
        }

        public Object getObject(String string, Type type) {
            return null;
        }

        public String getString(Object object) {
            return null;
        }

        public HashMap<String, String> getStringMap(Object object) {
            return null;
        }

    }
}