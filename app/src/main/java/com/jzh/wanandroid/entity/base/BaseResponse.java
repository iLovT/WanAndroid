package com.jzh.wanandroid.entity.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * author:jzh
 * desc:基返回体
 * Date:2018/08/17 10:18
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class BaseResponse {
    @Expose
    @SerializedName("errorCode")
    private Integer errorCode;
    @Expose
    @SerializedName("errorMsg")
    private String errorMsg;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
