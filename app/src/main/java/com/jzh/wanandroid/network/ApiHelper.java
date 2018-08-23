package com.jzh.wanandroid.network;


import com.jzh.wanandroid.entity.home.ArticleResponse;
import com.jzh.wanandroid.entity.home.BannerResponse;
import com.jzh.wanandroid.entity.home.CollectionResponse;
import com.jzh.wanandroid.entity.login.LoginResponse;
import com.jzh.wanandroid.entity.login.RegisterResponse;
import com.jzh.wanandroid.entity.project.ProjectTypeResponse;

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

    /**
     * 注册
     *
     * @param username   user name
     * @param password   password
     * @param repassword repassword
     * @return registerresponse
     */
    Observable<RegisterResponse> doRegisterApiCall(String username, String password, String repassword);

    /**
     * 获取banner数据
     *
     * @return banner response
     */
    Observable<BannerResponse> doGetBannerApiCall();

    /**
     * 获取首页文章列表
     *
     * @return response
     */
    Observable<ArticleResponse> doGetArticleApiCall(int offset);

    /**
     * 收藏站内文章
     *
     * @param id 文章id
     * @return collectionresponse
     */
    Observable<CollectionResponse> doCollectionArticleApiCall(Long id);

    /**
     * 取消收藏文章列表
     *
     * @param id id
     * @return collectionresponse
     */
    Observable<CollectionResponse> doUnCollectionArticleApiCall(Long id);

    /**
     * 获取项目分类
     *
     * @return projectresponse
     */
    Observable<ProjectTypeResponse> doGetProjectTypeApiCall();
}
