package com.jzh.wanandroid.network;

import com.jzh.wanandroid.entity.login.LoginResponse;
import com.jzh.wanandroid.entity.login.RegisterResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Author:jzh
 * desc:所有网络请求方法的实现
 * Date:2018/08/16 09:54
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Singleton
public class AppApiHelper implements ApiHelper {
    private Retrofit retrofit;

    @Inject
    public AppApiHelper() {
        retrofit = RetrofitManager.getInstance(ApiEndPoint.BASE_URL);
    }


    @Override
    public Observable<LoginResponse> doLoginApiCall(String username, String password) {
        return retrofit.create(ApiService.class).doLoginCall(username, password);
    }

    @Override
    public Observable<RegisterResponse> doRegisterApiCall(String username, String password, String repassword) {
        return retrofit.create(ApiService.class).doRegisterCall(username, password, repassword);
    }
}
