package com.jzh.wanandroid.data.db;


import com.jzh.wanandroid.data.db.model.KnowledgeResponseData;
import com.jzh.wanandroid.data.db.model.NavigationResponseData;
import com.jzh.wanandroid.data.db.model.ProjectTypeResponseData;
import com.jzh.wanandroid.data.db.model.TodoListResponseData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author:jzh
 * desc:业务操作数据库接口,定义具体业务方法
 * Date:2018/08/16 11:14
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
public interface DbHelper {
    /**
     * 保存项目分类数据
     *
     * @param datas datas
     */
    Observable<Boolean> saveProjectTypeData(List<ProjectTypeResponseData> datas);

    /**
     * 获取项目分类数据
     *
     * @return datas
     */
    List<ProjectTypeResponseData> getProjectTypeData();

    /**
     * 保存知识体系数据
     *
     * @param datas datas
     * @return Boolean
     */
    Observable<Boolean> saveKnowledgeData(List<KnowledgeResponseData> datas);

    /**
     * @return datas
     */
    List<KnowledgeResponseData> getKnowledgeData();

    /**
     * 保存导航数据
     *
     * @param datas datas
     * @return boolean
     */
    Observable<Boolean> saveNavigationData(List<NavigationResponseData> datas);

    /**
     * 获取导航数据
     *
     * @return datas
     */
    List<NavigationResponseData> getNavigationData();

    /**
     * 保存todo数据
     *
     * @param datas datas
     * @return true or false
     */
    Observable<Boolean> saveTodoListData(TodoListResponseData datas);

    /**
     * 获取todo数据
     *
     * @return datas
     */
    TodoListResponseData getTodoListData();


}
