package com.jzh.wanandroid.data.db;

import android.content.Context;


import com.jzh.wanandroid.data.db.model.DaoMaster;
import com.jzh.wanandroid.di.ApplicationContext;
import com.jzh.wanandroid.di.DatabaseInfo;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author:jzh
 * desc:数据库升级操作类
 * Date:2018/08/16 11:14
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        switch (oldVersion) {
            case 1:
            case 2:
                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
                // + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
        }
    }
}
