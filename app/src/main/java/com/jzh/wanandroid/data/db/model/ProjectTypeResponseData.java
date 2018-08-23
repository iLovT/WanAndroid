package com.jzh.wanandroid.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;


/**
 * author:jzh
 * desc:
 * Date:2018/08/23 16:50
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Entity
public class ProjectTypeResponseData {
    /**
     * children : []
     * courseId : 13
     * id : 294
     * name : 完整项目
     * order : 145000
     * parentChapterId : 293
     * visible : 0
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

    @Generated(hash = 379619950)
    public ProjectTypeResponseData(Integer courseId, Integer id, String name,
            Integer order, Integer parentChapterId, Integer visible) {
        this.courseId = courseId;
        this.id = id;
        this.name = name;
        this.order = order;
        this.parentChapterId = parentChapterId;
        this.visible = visible;
    }

    @Generated(hash = 1879883792)
    public ProjectTypeResponseData() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(Integer parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}
