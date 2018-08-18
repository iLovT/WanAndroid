package com.jzh.wanandroid.data;


import com.jzh.wanandroid.data.db.DbHelper;
import com.jzh.wanandroid.data.pref.PreferencesHelper;
import com.jzh.wanandroid.network.ApiHelper;


/**
 * Author:jzh
 * desc:数据访问管理基础接口
 * Date:2018/08/16 10:00
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    //处理请求回应code 编码，返回错误码对应错误信息
    String handleResponseCode(int code);
}
