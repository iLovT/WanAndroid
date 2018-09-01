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
import com.jzh.wanandroid.entity.todo.AddTodoResponse;
import com.jzh.wanandroid.entity.todo.TodoResponse;

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

    /**
     * 获取项目列表数据
     *
     * @param cid    id
     * @param offset 页码
     * @return projectList response
     */
    Observable<ProjectListResponse> doGetProjectListApiCall(Integer cid, int offset);

    /**
     * 获取知识体系数据
     *
     * @return datas
     */
    Observable<KnowledgeResponse> doGetKnowledgeApiCall();

    /**
     * 获取知识体系文章数据
     *
     * @param offset 页码
     * @param cid    cid
     * @return project response
     */
    Observable<ArticleResponse> doGetKnowledgeListApiCall(int offset, Integer cid);

    /**
     * 获取导航数据
     *
     * @return datas
     */
    Observable<NavigationResponse> doGetNavigationApiCall();

    /**
     * 获取todo列表数据
     *
     * @param type type
     * @return datas
     */
    Observable<TodoResponse> doGetTodoApiCall(int type);

    /**
     * 添加todo数据
     *
     * @param title   title
     * @param content content
     * @param date    日期
     * @param type    类型
     * @return response
     */
    Observable<AddTodoResponse> doAddTodoApiCall(String title, String content, String date, int type);
}
