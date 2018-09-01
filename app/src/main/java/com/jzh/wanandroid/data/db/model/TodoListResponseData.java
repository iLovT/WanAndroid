package com.jzh.wanandroid.data.db.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jzh.wanandroid.entity.todo.TodoListResponse;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;


/**
 * author:jzh
 * desc:
 * Date:2018/08/29 13:50
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
@Entity
public class TodoListResponseData {
    /**
     * date : 1535472000000
     * todoList : [{"completeDate":1535472000000,"completeDateStr":"2018-08-29","content":"dddd","date":1535472000000,"dateStr":"2018-08-29","id":1831,"status":1,"title":"dddd","type":0,"userId":9206},{"completeDate":1535472000000,"completeDateStr":"2018-08-29","content":"aaaa","date":1535472000000,"dateStr":"2018-08-29","id":1849,"status":1,"title":"aaa","type":0,"userId":9206}]
     */
    private Integer type;

    @Expose
    @SerializedName("todoList")
    @Convert(converter = TodoListResponse.class, columnType = String.class)
    private List<TodoListResponse> todoList;
    @Expose
    @SerializedName("doneList")
    @Convert(converter = TodoListResponse.class, columnType = String.class)
    private List<TodoListResponse> doneList;

    @Generated(hash = 623399603)
    public TodoListResponseData(Integer type, List<TodoListResponse> todoList, List<TodoListResponse> doneList) {
        this.type = type;
        this.todoList = todoList;
        this.doneList = doneList;
    }

    @Generated(hash = 1469222899)
    public TodoListResponseData() {
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<TodoListResponse> getTodoList() {
        return this.todoList;
    }

    public void setTodoList(List<TodoListResponse> todoList) {
        this.todoList = todoList;
    }

    public List<TodoListResponse> getDoneList() {
        return this.doneList;
    }

    public void setDoneList(List<TodoListResponse> doneList) {
        this.doneList = doneList;
    }


}
