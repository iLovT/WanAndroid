package com.jzh.wanandroid.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jzh.wanandroid.entity.navigation.NavigationListResponse;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author:jzh
 * desc:
 * Date:2018/08/28 10:58
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Entity
public class NavigationResponseData {
    @Expose
    @SerializedName("articles")
    @Convert(converter = NavigationListResponse.class, columnType = String.class)
    private List<NavigationListResponse> articles;
    private Integer cid;
    private String name;
    @Generated(hash = 694862413)
    public NavigationResponseData(List<NavigationListResponse> articles,
            Integer cid, String name) {
        this.articles = articles;
        this.cid = cid;
        this.name = name;
    }
    @Generated(hash = 707453716)
    public NavigationResponseData() {
    }
    public List<NavigationListResponse> getArticles() {
        return this.articles;
    }
    public void setArticles(List<NavigationListResponse> articles) {
        this.articles = articles;
    }
    public Integer getCid() {
        return this.cid;
    }
    public void setCid(Integer cid) {
        this.cid = cid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
