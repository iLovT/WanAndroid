package com.jzh.wanandroid.network;


import com.jzh.wanandroid.entity.login.LoginResponse;

import io.reactivex.Observable;

/**
 * Author:jzh
 * desc:定义所有网络请求方法
 * Date:2018/08/16 09:53
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface ApiHelper {
    /**
     * login
     *
     * @param username user name
     * @param password password
     * @return loginresponse
     */
    Observable<LoginResponse> doLoginApiCall(String username, String password);
}
