package com.jzh.wanandroid.data.db.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOGIN_DB_DATA".
*/
public class LoginDbDataDao extends AbstractDao<LoginDbData, Void> {

    public static final String TABLENAME = "LOGIN_DB_DATA";

    /**
     * Properties of entity LoginDbData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Username = new Property(0, String.class, "username", false, "USERNAME");
        public final static Property Password = new Property(1, String.class, "password", false, "PASSWORD");
    }


    public LoginDbDataDao(DaoConfig config) {
        super(config);
    }
    
    public LoginDbDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOGIN_DB_DATA\" (" + //
                "\"USERNAME\" TEXT," + // 0: username
                "\"PASSWORD\" TEXT);"); // 1: password
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOGIN_DB_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LoginDbData entity) {
        stmt.clearBindings();
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(1, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(2, password);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LoginDbData entity) {
        stmt.clearBindings();
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(1, username);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(2, password);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public LoginDbData readEntity(Cursor cursor, int offset) {
        LoginDbData entity = new LoginDbData( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // username
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // password
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LoginDbData entity, int offset) {
        entity.setUsername(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setPassword(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(LoginDbData entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(LoginDbData entity) {
        return null;
    }

    @Override
    public boolean hasKey(LoginDbData entity) {
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
