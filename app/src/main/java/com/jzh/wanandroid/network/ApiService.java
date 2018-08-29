package com.jzh.wanandroid.network;

import com.jzh.wanandroid.entity.home.ArticleResponse;
import com.jzh.wanandroid.entity.home.BannerResponse;
import com.jzh.wanandroid.entity.home.CollectionResponse;
import com.jzh.wanandroid.entity.knowledge.KnowledgeResponse;
import com.jzh.wanandroid.entity.login.LoginResponse;
import com.jzh.wanandroid.entity.login.RegisterResponse;
import com.jzh.wanandroid.entity.navigation.NavigationResponse;
import com.jzh.wanandroid.entity.project.ProjectListResponse;
import com.jzh.wanandroid.entity.project.ProjectTypeResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    /**
     * 获取banner
     *
     * @return banner response
     */
    @GET("banner/json")
    Observable<BannerResponse> doGetBannerCall();

    @GET("article/list/{offset}/json")
    Observable<ArticleResponse> doGetArticleCall(@Path("offset") int offset);

    @POST("lg/collect/{id}/json")
    Observable<CollectionResponse> doCollectionArticleCall(@Path("id") Long id);

    @POST("lg/uncollect_originId/{id}/json")
    Observable<CollectionResponse> doUnCollectionArticleCall(@Path("id") Long id);

    @GET("project/tree/json")
    Observable<ProjectTypeResponse> doGetProjectTypeCall();

    @GET("project/list/{offset}/json")
    Observable<ProjectListResponse> doGetProjectListCall(@Path("offset") int offset, @Query("cid") Integer cid);

    @GET("tree/json")
    Observable<KnowledgeResponse> doGetKnowledgeCall();

    @GET("article/list/{offset}/json")
    Observable<ArticleResponse> doGetKnowledgeListCall(@Path("offset") int offset, @Query("cid") Integer cid);

    @GET("navi/json")
    Observable<NavigationResponse> doGetNavigationCall();
}
