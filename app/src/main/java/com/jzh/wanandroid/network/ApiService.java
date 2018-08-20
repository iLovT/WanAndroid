package com.jzh.wanandroid.network;

import com.jzh.wanandroid.entity.login.LoginResponse;
import com.jzh.wanandroid.entity.login.RegisterResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginResponse> doLoginCall(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     *
     * @param username   username
     * @param password   password
     * @param repassword repassword
     * @return registerresponse
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<RegisterResponse> doRegisterCall(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);
}
