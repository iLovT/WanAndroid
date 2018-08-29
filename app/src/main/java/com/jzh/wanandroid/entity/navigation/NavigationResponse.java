package com.jzh.wanandroid.entity.navigation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jzh.wanandroid.data.db.model.NavigationResponseData;
import com.jzh.wanandroid.entity.base.BaseResponse;

import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/28 10:56
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class NavigationResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
    private List<NavigationResponseData> data;

    public List<NavigationResponseData> getData() {
        return data;
    }

    public void setData(List<NavigationResponseData> data) {
        this.data = data;
    }
}
