package com.jzh.wanandroid.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:jzh
 * desc:
 * Date:2018/08/16 10:41
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Entity
public class LoginDbData {
    @Expose
    @SerializedName("username")
    private String username;
    @Expose
    @SerializedName("password")
    private String password;

    @Generated(hash = 429767459)
    public LoginDbData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Generated(hash = 119041984)
    public LoginDbData() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
