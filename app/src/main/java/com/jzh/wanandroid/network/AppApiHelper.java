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

    @Override
    public Observable<BannerResponse> doGetBannerApiCall() {
        return retrofit.create(ApiService.class).doGetBannerCall();
    }

    @Override
    public Observable<ArticleResponse> doGetArticleApiCall(int offset) {
        return retrofit.create(ApiService.class).doGetArticleCall(offset);
    }

    @Override
    public Observable<CollectionResponse> doCollectionArticleApiCall(Long id) {
        return retrofit.create(ApiService.class).doCollectionArticleCall(id);
    }

    @Override
    public Observable<CollectionResponse> doUnCollectionArticleApiCall(Long id) {
        return retrofit.create(ApiService.class).doUnCollectionArticleCall(id);
    }

    @Override
    public Observable<ProjectTypeResponse> doGetProjectTypeApiCall() {
        return retrofit.create(ApiService.class).doGetProjectTypeCall();
    }

    @Override
    public Observable<ProjectListResponse> doGetProjectListApiCall(Integer cid, int offset) {
        return retrofit.create(ApiService.class).doGetProjectListCall(offset, cid);
    }

    @Override
    public Observable<KnowledgeResponse> doGetKnowledgeApiCall() {
        return retrofit.create(ApiService.class).doGetKnowledgeCall();
    }

    @Override
    public Observable<ArticleResponse> doGetKnowledgeListApiCall(int offset, Integer cid) {
        return retrofit.create(ApiService.class).doGetKnowledgeListCall(offset, cid);
    }

    @Override
    public Observable<NavigationResponse> doGetNavigationApiCall() {
        return retrofit.create(ApiService.class).doGetNavigationCall();
    }

    @Override
    public Observable<TodoResponse> doGetTodoApiCall(int type) {
        return retrofit.create(ApiService.class).doGetTodoListCall(type);
    }

    @Override
    public Observable<AddTodoResponse> doAddTodoApiCall(String title, String content, String date, int type) {
        return retrofit.create(ApiService.class).doAddTodoCall(title, content, date, type);
    }
}
