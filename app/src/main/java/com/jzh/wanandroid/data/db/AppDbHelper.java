package com.jzh.wanandroid.data.db;


import android.database.sqlite.SQLiteException;

import com.jzh.wanandroid.data.db.model.DaoMaster;
import com.jzh.wanandroid.data.db.model.DaoSession;
import com.jzh.wanandroid.data.db.model.ProjectTypeResponseData;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Author:jzh
 * desc:数据库操作业务实现类
 * Date:2018/08/16 11:14
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }


    @Override
    public Observable<Boolean> saveProjectTypeData(final List<ProjectTypeResponseData> datas) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                if (null != datas && datas.size() > 0) {
                    //先清空之前的数据再插入，防止数据重复
                    mDaoSession.getProjectTypeResponseDataDao().deleteAll();
                    mDaoSession.getProjectTypeResponseDataDao().insertOrReplaceInTx(datas);
                }
                return true;
            }
        });
    }

    @Override
    public List<ProjectTypeResponseData> getProjectTypeData() {
        try {
            return mDaoSession.getProjectTypeResponseDataDao().queryBuilder()
                    .list();
        } catch (SQLiteException e) {
            return null;
        }
    }
}
