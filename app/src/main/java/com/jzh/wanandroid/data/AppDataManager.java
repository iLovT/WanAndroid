package com.jzh.wanandroid.data;

import android.content.Context;


import com.jzh.wanandroid.data.db.DbHelper;
import com.jzh.wanandroid.data.db.model.KnowledgeResponseData;
import com.jzh.wanandroid.data.db.model.NavigationResponseData;
import com.jzh.wanandroid.data.db.model.ProjectTypeResponseData;
import com.jzh.wanandroid.data.db.model.TodoListResponseData;
import com.jzh.wanandroid.data.pref.PreferencesHelper;
import com.jzh.wanandroid.di.ApplicationContext;
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
import com.jzh.wanandroid.entity.todo.TodoDeleteResponse;
import com.jzh.wanandroid.entity.todo.TodoResponse;
import com.jzh.wanandroid.network.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * author:jzh
 * desc:数据访问管理总接口实现,所有项目数据层都通过该方式实现
 * Date:2018/08/16 10:00
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final ApiHelper mApiHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context, DbHelper dbHelper,
                          ApiHelper apiHelper,
                          PreferencesHelper preferencesHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public String handleResponseCode(int code) {
        // 处理code
        return null;
    }


    @Override
    public void setLoginStaus(boolean isLogin) {
        mPreferencesHelper.setLoginStaus(isLogin);
    }

    @Override
    public boolean getLoginStaus() {
        return mPreferencesHelper.getLoginStaus();
    }

    @Override
    public Observable<LoginResponse> doLoginApiCall(String username, String password) {
        return mApiHelper.doLoginApiCall(username, password);
    }

    @Override
    public Observable<RegisterResponse> doRegisterApiCall(String username, String password, String repassword) {
        return mApiHelper.doRegisterApiCall(username, password, repassword);
    }

    @Override
    public Observable<BannerResponse> doGetBannerApiCall() {
        return mApiHelper.doGetBannerApiCall();
    }

    @Override
    public Observable<ArticleResponse> doGetArticleApiCall(int offset) {
        return mApiHelper.doGetArticleApiCall(offset);
    }

    @Override
    public Observable<CollectionResponse> doCollectionArticleApiCall(Long id) {
        return mApiHelper.doCollectionArticleApiCall(id);
    }

    @Override
    public Observable<CollectionResponse> doUnCollectionArticleApiCall(Long id) {
        return mApiHelper.doUnCollectionArticleApiCall(id);
    }

    @Override
    public Observable<ProjectTypeResponse> doGetProjectTypeApiCall() {
        return mApiHelper.doGetProjectTypeApiCall();
    }

    @Override
    public Observable<ProjectListResponse> doGetProjectListApiCall(Integer cid, int offset) {
        return mApiHelper.doGetProjectListApiCall(cid, offset);
    }

    @Override
    public Observable<KnowledgeResponse> doGetKnowledgeApiCall() {
        return mApiHelper.doGetKnowledgeApiCall();
    }

    @Override
    public Observable<ArticleResponse> doGetKnowledgeListApiCall(int offset, Integer cid) {
        return mApiHelper.doGetKnowledgeListApiCall(offset, cid);
    }

    @Override
    public Observable<NavigationResponse> doGetNavigationApiCall() {
        return mApiHelper.doGetNavigationApiCall();
    }

    @Override
    public Observable<TodoResponse> doGetTodoApiCall(int type) {
        return mApiHelper.doGetTodoApiCall(type);
    }

    @Override
    public Observable<AddTodoResponse> doAddTodoApiCall(String title, String content, String date, int type) {
        return mApiHelper.doAddTodoApiCall(title, content, date, type);
    }

    @Override
    public Observable<TodoDeleteResponse> doDeleteTodoApiCall(Long id) {
        return mApiHelper.doDeleteTodoApiCall(id);
    }

    @Override
    public Observable<Boolean> saveProjectTypeData(List<ProjectTypeResponseData> datas) {
        return mDbHelper.saveProjectTypeData(datas);
    }

    @Override
    public List<ProjectTypeResponseData> getProjectTypeData() {
        return mDbHelper.getProjectTypeData();
    }

    @Override
    public Observable<Boolean> saveKnowledgeData(List<KnowledgeResponseData> datas) {
        return mDbHelper.saveKnowledgeData(datas);
    }

    @Override
    public List<KnowledgeResponseData> getKnowledgeData() {
        return mDbHelper.getKnowledgeData();
    }

    @Override
    public Observable<Boolean> saveNavigationData(List<NavigationResponseData> datas) {
        return mDbHelper.saveNavigationData(datas);
    }

    @Override
    public List<NavigationResponseData> getNavigationData() {
        return mDbHelper.getNavigationData();
    }

    @Override
    public Observable<Boolean> saveTodoListData(TodoListResponseData datas) {
        return mDbHelper.saveTodoListData(datas);
    }

    @Override
    public TodoListResponseData getTodoListData() {
        return mDbHelper.getTodoListData();
    }

}
