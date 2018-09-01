package com.jzh.wanandroid.ui.main;

import com.jzh.wanandroid.data.DataManager;
import com.jzh.wanandroid.entity.knowledge.KnowledgeResponse;
import com.jzh.wanandroid.entity.navigation.NavigationResponse;
import com.jzh.wanandroid.entity.project.ProjectTypeResponse;
import com.jzh.wanandroid.entity.todo.TodoResponse;
import com.jzh.wanandroid.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author:jzh
 * desc:
 * Date:2018/08/23 16:44
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class MainPresenter<v extends MainMvpView> extends BasePresenter<v> implements MainMvpView.MainMvpPresenter<v> {
    @Inject
    public MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void doGetProjectType() {
        getCompositeDisposable().add(getDataManager().
                doGetProjectTypeApiCall().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<ProjectTypeResponse, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull ProjectTypeResponse response) throws Exception {
                        if (response.getErrorCode() >= 0) {
                            return getDataManager().saveProjectTypeData(response.getProjectTypeResponseDatas());
                        }
                        return null;
                    }
                }).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                    }
                }, otherConsumer(false)));
    }

    @Override
    public void doGetKnowledgeCall() {
        getCompositeDisposable().add(getDataManager().
                doGetKnowledgeApiCall().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<KnowledgeResponse, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull KnowledgeResponse response) throws Exception {
                        if (response.getErrorCode() >= 0) {
                            return getDataManager().saveKnowledgeData(response.getDatas());
                        }
                        return null;
                    }
                }).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                    }
                }, otherConsumer(false)));
    }

    @Override
    public void doGetNavigationCall() {
        getCompositeDisposable().add(getDataManager().doGetNavigationApiCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<NavigationResponse, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull NavigationResponse response) throws Exception {
                        if (response.getErrorCode() >= 0) {
                            return getDataManager().saveNavigationData(response.getData());
                        }
                        return null;
                    }
                }).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                    }
                }, otherConsumer(false)));
    }

    @Override
    public void doTodoListCall(int type) {
        getCompositeDisposable().add(getDataManager().doGetTodoApiCall(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<TodoResponse, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull TodoResponse response) throws Exception {
                        if (response.getErrorCode() >= 0) {
                            return getDataManager().saveTodoListData(response.getDataBean());
                        }
                        return null;
                    }
                }).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                    }
                }, otherConsumer(false)));
    }

}
