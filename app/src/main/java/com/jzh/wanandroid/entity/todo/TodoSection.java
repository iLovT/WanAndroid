package com.jzh.wanandroid.entity.todo;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * author:jzh
 * desc:
 * Date:2018/08/31 15:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class TodoSection extends SectionEntity<TodoListResponse.TodoListBean> {
    public TodoSection(String header) {
        super(true, header);
    }

    public TodoSection(TodoListResponse.TodoListBean datas) {
        super(datas);
    }
}
