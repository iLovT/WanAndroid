package com.jzh.wanandroid.data.db.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "KNOWLEDGE_RESPONSE_DATA".
*/
public class KnowledgeResponseDataDao extends AbstractDao<KnowledgeResponseData, Void> {

    public static final String TABLENAME = "KNOWLEDGE_RESPONSE_DATA";

    /**
     * Properties of entity KnowledgeResponseData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property CourseId = new Property(0, Integer.class, "courseId", false, "COURSE_ID");
        public final static Property Id = new Property(1, Integer.class, "id", false, "ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Order = new Property(3, Integer.class, "order", false, "ORDER");
        public final static Property ParentChapterId = new Property(4, Integer.class, "parentChapterId", false, "PARENT_CHAPTER_ID");
        public final static Property Visible = new Property(5, Integer.class, "visible", false, "VISIBLE");
        public final static Property DataList = new Property(6, String.class, "dataList", false, "DATA_LIST");
    }

    private final KnowledgeResponseData dataListConverter = new KnowledgeResponseData();

    public KnowledgeResponseDataDao(DaoConfig config) {
        super(config);
    }
    
    public KnowledgeResponseDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"KNOWLEDGE_RESPONSE_DATA\" (" + //
                "\"COURSE_ID\" INTEGER," + // 0: courseId
                "\"ID\" INTEGER," + // 1: id
                "\"NAME\" TEXT," + // 2: name
                "\"ORDER\" INTEGER," + // 3: order
                "\"PARENT_CHAPTER_ID\" INTEGER," + // 4: parentChapterId
                "\"VISIBLE\" INTEGER," + // 5: visible
                "\"DATA_LIST\" TEXT);"); // 6: dataList
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"KNOWLEDGE_RESPONSE_DATA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, KnowledgeResponseData entity) {
        stmt.clearBindings();
 
        Integer courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindLong(1, courseId);
        }
 
        Integer id = entity.getId();
        if (id != null) {
            stmt.bindLong(2, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        Integer order = entity.getOrder();
        if (order != null) {
            stmt.bindLong(4, order);
        }
 
        Integer parentChapterId = entity.getParentChapterId();
        if (parentChapterId != null) {
            stmt.bindLong(5, parentChapterId);
        }
 
        Integer visible = entity.getVisible();
        if (visible != null) {
            stmt.bindLong(6, visible);
        }
 
        List dataList = entity.getDataList();
        if (dataList != null) {
            stmt.bindString(7, dataListConverter.convertToDatabaseValue(dataList));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, KnowledgeResponseData entity) {
        stmt.clearBindings();
 
        Integer courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindLong(1, courseId);
        }
 
        Integer id = entity.getId();
        if (id != null) {
            stmt.bindLong(2, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        Integer order = entity.getOrder();
        if (order != null) {
            stmt.bindLong(4, order);
        }
 
        Integer parentChapterId = entity.getParentChapterId();
        if (parentChapterId != null) {
            stmt.bindLong(5, parentChapterId);
        }
 
        Integer visible = entity.getVisible();
        if (visible != null) {
            stmt.bindLong(6, visible);
        }
 
        List dataList = entity.getDataList();
        if (dataList != null) {
            stmt.bindString(7, dataListConverter.convertToDatabaseValue(dataList));
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public KnowledgeResponseData readEntity(Cursor cursor, int offset) {
        KnowledgeResponseData entity = new KnowledgeResponseData( //
            cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0), // courseId
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // order
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // parentChapterId
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // visible
            cursor.isNull(offset + 6) ? null : dataListConverter.convertToEntityProperty(cursor.getString(offset + 6)) // dataList
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, KnowledgeResponseData entity, int offset) {
        entity.setCourseId(cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOrder(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setParentChapterId(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setVisible(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setDataList(cursor.isNull(offset + 6) ? null : dataListConverter.convertToEntityProperty(cursor.getString(offset + 6)));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(KnowledgeResponseData entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(KnowledgeResponseData entity) {
        return null;
    }

    @Override
    public boolean hasKey(KnowledgeResponseData entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}