package com.jzh.wanandroid.data.db.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;


/**
 * author:jzh
 * desc:
 * Date:2018/08/25 09:33
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Entity
public class KnowledgeResponseData implements PropertyConverter<List<KnowledgeResponseData>, String>, Serializable {

    /**
     * courseId : 13
     * id : 150
     * name : 开发环境
     * order : 1
     * parentChapterId : 0
     * visible : 1
     */
    @Expose
    @SerializedName("courseId")
    private Integer courseId;
    @Expose
    @SerializedName("id")
    private Integer id;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("order")
    private Integer order;
    @Expose
    @SerializedName("parentChapterId")
    private Integer parentChapterId;
    @Expose
    @SerializedName("visible")
    private Integer visible;

    @Expose
    @SerializedName("children")
    @Convert(converter = KnowledgeResponseData.class, columnType = String.class)
    private List<KnowledgeResponseData> dataList;
    static final long serialVersionUID = 42L;

    @Generated(hash = 643190541)
    public KnowledgeResponseData(Integer courseId, Integer id, String name, Integer order,
                                 Integer parentChapterId, Integer visible, List<KnowledgeResponseData> dataList) {
        this.courseId = courseId;
        this.id = id;
        this.name = name;
        this.order = order;
        this.parentChapterId = parentChapterId;
        this.visible = visible;
        this.dataList = dataList;
    }

    @Generated(hash = 1204601087)
    public KnowledgeResponseData() {
    }


    @Override
    public List<KnowledgeResponseData> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        KnowledgeResponseData[] datas = new Gson().fromJson(databaseValue, KnowledgeResponseData[].class);
        List<KnowledgeResponseData> responseDatas = Arrays.asList(datas);
        return responseDatas;
    }

    @Override
    public String convertToDatabaseValue(List<KnowledgeResponseData> entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        String response = new Gson().toJson(entityProperty);
        return response;
    }

    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return this.order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getParentChapterId() {
        return this.parentChapterId;
    }

    public void setParentChapterId(Integer parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public Integer getVisible() {
        return this.visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public List<KnowledgeResponseData> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<KnowledgeResponseData> dataList) {
        this.dataList = dataList;
    }
}