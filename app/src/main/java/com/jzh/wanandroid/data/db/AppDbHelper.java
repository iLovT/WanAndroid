package com.jzh.wanandroid.data.db;


import com.jzh.wanandroid.data.db.model.DaoMaster;
import com.jzh.wanandroid.data.db.model.DaoSession;


import javax.inject.Inject;
import javax.inject.Singleton;


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


}
