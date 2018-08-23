package com.jzh.wanandroid.entity.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jzh.wanandroid.data.db.model.ProjectTypeResponseData;
import com.jzh.wanandroid.entity.base.BaseResponse;

import java.util.List;

/**
 * author:jzh
 * desc:项目分类response
 * Date:2018/08/23 16:37
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class ProjectTypeResponse extends BaseResponse {
    @Expose
    @SerializedName("data")
    List<ProjectTypeResponseData> projectTypeResponseDatas;

    public List<ProjectTypeResponseData> getProjectTypeResponseDatas() {
        return projectTypeResponseDatas;
    }

    public void setProjectTypeResponseDatas(List<ProjectTypeResponseData> projectTypeResponseDatas) {
        this.projectTypeResponseDatas = projectTypeResponseDatas;
    }
}
