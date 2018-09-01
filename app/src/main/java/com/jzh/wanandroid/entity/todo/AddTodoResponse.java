package com.jzh.wanandroid.entity.todo;

import com.jzh.wanandroid.entity.base.BaseResponse;

/**
 * author:jzh
 * desc:
 * Date:2018/09/01 10:12
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class AddTodoResponse extends BaseResponse {

    /**
     * data : {"completeDate":null,"completeDateStr":"","content":"","date":1535731200000,"dateStr":"2018-09-01","id":2048,"status":0,"title":"13479697150","type":0,"userId":9206}
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
         * completeDate : null
         * completeDateStr :
         * content :
         * date : 1535731200000
         * dateStr : 2018-09-01
         * id : 2048
         * status : 0
         * title : 13479697150
         * type : 0
         * userId : 9206
         */

        private Long completeDate;
        private String completeDateStr;
        private String content;
        private Long date;
        private String dateStr;
        private Integer id;
        private Integer status;
        private String title;
        private Integer type;
        private Integer userId;

        public Long getCompleteDate() {
            return completeDate;
        }

        public void setCompleteDate(Long completeDate) {
            this.completeDate = completeDate;
        }

        public String getCompleteDateStr() {
            return completeDateStr;
        }

        public void setCompleteDateStr(String completeDateStr) {
            this.completeDateStr = completeDateStr;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Long getDate() {
            return date;
        }

        public void setDate(Long date) {
            this.date = date;
        }

        public String getDateStr() {
            return dateStr;
        }

        public void setDateStr(String dateStr) {
            this.dateStr = dateStr;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
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
    }
}
