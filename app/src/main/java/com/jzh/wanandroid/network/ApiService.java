package com.jzh.wanandroid.network;

import com.jzh.wanandroid.entity.login.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author:jzh
 * desc:retrofit apiservice
 * Date:2018/08/18 10:40
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public interface ApiService {
    /**
     * 登录
     */
    @POST("user/login")
    Observable<LoginResponse> doLoginCall(@Query("username") String username, @Query("password") String password);
}
