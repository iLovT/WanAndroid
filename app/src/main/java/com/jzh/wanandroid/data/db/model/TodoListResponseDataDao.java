package com.jzh.wanandroid.data.db.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.jzh.wanandroid.entity.todo.TodoListResponse;
import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TODO_LIST_RESPONSE_DATA".
*/
public class TodoListResponseDataDao extends AbstractDao<TodoListResponseData, Void> {

    public static final String TABLENAME = "TODO_LIST_RESPONSE_DATA";

    /**
     * Properties of entity TodoListResponseData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Type = new Property(0, Integer.class, "type", false, "TYPE");
        public final static Property TodoList = new Property(1, String.class, "todoList", false, "TODO_LIST");
        public final static Property DoneList = new Property(2, String.class, "doneList", false, "DONE_LIST");
    }

    private final TodoListResponse todoListConverter = new TodoListResponse();
    private final TodoListResponse doneListConverter = new TodoListResponse();

    public TodoListResponseDataDao(DaoConfig config) {
        super(config);
    }
    
    public TodoListResponseDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TODO_LIST_RESPONSE_DATA\" (" + //
                "\"TYPE\" INTEGER," + // 0: type
                "\"TODO_LIST\" TEXT," + // 1: todoList
                "\"DONE_LIST\" TEXT);"); // 2: doneList
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TODO_LIST_RESPONSE_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TodoListResponseData entity) {
        stmt.clearBindings();
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(1, type);
        }
 
        List todoList = entity.getTodoList();
        if (todoList != null) {
            stmt.bindString(2, todoListConverter.convertToDatabaseValue(todoList));
        }
 
        List doneList = entity.getDoneList();
        if (doneList != null) {
            stmt.bindString(3, doneListConverter.convertToDatabaseValue(doneList));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TodoListResponseData entity) {
        stmt.clearBindings();
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(1, type);
        }
 
        List todoList = entity.getTodoList();
        if (todoList != null) {
            stmt.bindString(2, todoListConverter.convertToDatabaseValue(todoList));
        }
 
        List doneList = entity.getDoneList();
        if (doneList != null) {
            stmt.bindString(3, doneListConverter.convertToDatabaseValue(doneList));
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public TodoListResponseData readEntity(Cursor cursor, int offset) {
        TodoListResponseData entity = new TodoListResponseData( //
            cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0), // type
            cursor.isNull(offset + 1) ? null : todoListConverter.convertToEntityProperty(cursor.getString(offset + 1)), // todoList
            cursor.isNull(offset + 2) ? null : doneListConverter.convertToEntityProperty(cursor.getString(offset + 2)) // doneList
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TodoListResponseData entity, int offset) {
        entity.setType(cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0));
        entity.setTodoList(cursor.isNull(offset + 1) ? null : todoListConverter.convertToEntityProperty(cursor.getString(offset + 1)));
        entity.setDoneList(cursor.isNull(offset + 2) ? null : doneListConverter.convertToEntityProperty(cursor.getString(offset + 2)));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(TodoListResponseData entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(TodoListResponseData entity) {
        return null;
    }

    @Override
    public boolean hasKey(TodoListResponseData entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
