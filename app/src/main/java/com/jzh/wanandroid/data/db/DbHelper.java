package com.jzh.wanandroid.data.db;


import com.jzh.wanandroid.data.db.model.ProjectTypeResponseData;

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
}
