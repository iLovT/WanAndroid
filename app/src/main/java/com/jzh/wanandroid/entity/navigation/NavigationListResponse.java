package com.jzh.wanandroid.entity.navigation;

import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/28 11:00
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class NavigationListResponse implements PropertyConverter<List<NavigationListResponse>, String> {
    /**
     * apkLink :
     * author : 小编
     * chapterId : 274
     * chapterName : 个人博客
     * collect : false
     * courseId : 13
     * desc :
     * envelopePic :
     * fresh : false
     * id : 1877
     * link : http://blog.csdn.net/luoshengyang/
     * niceDate : 2018-01-07
     * origin :
     * projectLink :
     * publishTime : 1515325685000
     * superChapterId : 0
     * superChapterName :
     * tags : []
     * title : 罗升阳
     * type : 0
     * userId : -1
     * visible : 0
     * zan : 0
     */

    private String apkLink;
    private String author;
    private Integer chapterId;
    private String chapterName;
    private boolean collect;
    private Integer courseId;
    private String desc;
    private String envelopePic;
    private boolean fresh;
    private Integer id;
    private String link;
    private String niceDate;
    private String origin;
    private String projectLink;
    private long publishTime;
    private Integer superChapterId;
    private String superChapterName;
    private String title;
    private Integer type;
    private Integer userId;
    private Integer visible;
    private Integer zan;
    private List<?> tags;

    @Override
    public List<NavigationListResponse> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        NavigationListResponse[] datas = new Gson().fromJson(databaseValue, NavigationListResponse[].class);
        List<NavigationListResponse> responseDatas = Arrays.asList(datas);
        return responseDatas;
    }

    @Override
    public String convertToDatabaseValue(List<NavigationListResponse> entityProperty) {
        if (entityProperty == null) {
            return null;
        }
        String response = new Gson().toJson(entityProperty);
        return response;
    }

    public String getApkLink() {
        return apkLink;
    }

    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProjectLink() {
        return projectLink;
    }

    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(Integer superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }
}
