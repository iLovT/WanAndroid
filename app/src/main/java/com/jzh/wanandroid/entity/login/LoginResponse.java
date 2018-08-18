package com.jzh.wanandroid.entity.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jzh.wanandroid.entity.base.BaseResponse;

import java.util.List;

/**
 * author:jzh
 * desc:登录返回体
 * Date:2018/08/17 10:14
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class LoginResponse extends BaseResponse {

    /**
     * data : {"collectIds":[],"email":"","icon":"","id":9206,"password":"yzy0607","token":"","type":0,"username":"13479697150"}
     */
    @Expose
    @SerializedName("data")
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * collectIds : []
         * email :
         * icon :
         * id : 9206
         * password :
         * token :
         * type : 0
         * username :
         */

        @Expose
        @SerializedName("email")
        private String email;
        @Expose
        @SerializedName("icon")
        private String icon;
        @Expose
        @SerializedName("id")
        private Integer id;
        @Expose
        @SerializedName("password")
        private String password;
        @Expose
        @SerializedName("token")
        private String token;
        @Expose
        @SerializedName("type")
        private Integer type;
        @Expose
        @SerializedName("username")
        private String username;
        @Expose
        @SerializedName("collectIds")
        private List<Integer> collectIds;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }
    }
}
