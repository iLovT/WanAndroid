package com.jzh.wanandroid.entity.todo;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/29 13:55
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class TodoListResponse implements PropertyConverter<List<TodoListResponse>, String> {


    private Long date;
    @Convert(converter = TodoListBean.class, columnType = String.class)
    private List<TodoListBean> todoList;

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public List<TodoListBean> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoListBean> todoList) {
        this.todoList = todoList;
    }

    @Override
    public List<TodoListResponse> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        TodoListResponse[] todoListResponses = new Gson().fromJson(databaseValue, TodoListResponse[].class);
        return Arrays.asList(todoListResponses);
    }

    @Override
    public String convertToDatabaseValue(List<TodoListResponse> entityProperty) {
        return new Gson().toJson(entityProperty);
    }

    public static class TodoListBean implements PropertyConverter<List<TodoListBean>, String> {
        /**
         * completeDate : 1535472000000
         * completeDateStr : 2018-08-29
         * content : dddd
         * date : 1535472000000
         * dateStr : 2018-08-29
         * id : 1831
         * status : 1
         * title : dddd
         * type : 0
         * userId : 9206
         */

        private Long completeDate;
        private String completeDateStr;
        private String content;
        private Long date;
        private String dateStr;
        private Long id;
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

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
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

        @Override
        public List<TodoListBean> convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            TodoListBean[] todoListBeen = new Gson().fromJson(databaseValue, TodoListBean[].class);
            return Arrays.asList(todoListBeen);
        }

        @Override
        public String convertToDatabaseValue(List<TodoListBean> entityProperty) {
            return new Gson().toJson(entityProperty);
        }
    }
}
