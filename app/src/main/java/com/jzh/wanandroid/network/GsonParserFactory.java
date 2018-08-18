package com.jzh.wanandroid.network;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Author:jzh
 * desc:
 * Date:2018/08/16 11:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public final class GsonParserFactory extends Parser.Factory {

    private final Gson gson;

    public GsonParserFactory() {
        this.gson = new Gson();
    }

    public GsonParserFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Parser<ResponseBody, ?> responseBodyParser(Type type) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonResponseBodyParser<>(gson, adapter);
    }

    @Override
    public Parser<?, RequestBody> requestBodyParser(Type type) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyParser<>(gson, adapter);
    }

    @Override
    public Object getObject(String string, Type type) {
        try {
            return gson.fromJson(string, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getString(Object object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public HashMap<String, String> getStringMap(Object object) {
        try {
            Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();
            return gson.fromJson(gson.toJson(object), type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
