package com.jzh.wanandroid.entity.login;

import com.jzh.wanandroid.entity.base.BaseResponse;

import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/20 10:12
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class RegisterResponse extends BaseResponse {
    /**
     * data : {"collectIds":[],"email":"","icon":"","id":9391,"password":"admin22","token":"","type":0,"username":"admin22"}
     */

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
         * id : 9391
         * password : admin22
         * token :
         * type : 0
         * username : admin22
         */

        private String email;
        private String icon;
        private Integer id;
        private String password;
        private String token;
        private Integer type;
        private String username;
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
