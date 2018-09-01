package com.jzh.wanandroid.entity.todo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jzh.wanandroid.data.db.model.TodoListResponseData;
import com.jzh.wanandroid.entity.base.BaseResponse;


/**
 * author:jzh
 * desc:T~odo response
 * Date:2018/08/29 11:14
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class TodoResponse extends BaseResponse {
    @Expose
    @SerializedName("data")
    private TodoListResponseData dataBean;

    public TodoListResponseData getDataBean() {
        return dataBean;
    }

    public void setDataBean(TodoListResponseData dataBean) {
        this.dataBean = dataBean;
    }
}
