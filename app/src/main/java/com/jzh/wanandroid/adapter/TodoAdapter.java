package com.jzh.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.entity.todo.TodoSection;

import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/30 17:30
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class TodoAdapter extends BaseSectionQuickAdapter<TodoSection, BaseViewHolder> {
    private boolean isDone;

    public TodoAdapter(@Nullable List<TodoSection> data, boolean isDone) {
        super(R.layout.adapter_todo, R.layout.adapter_todo_head, data);
        this.isDone = isDone;
    }

    @Override
    protected void convert(BaseViewHolder helper, TodoSection item) {
        if (isDone) {
            helper.setImageResource(R.id.adapter_todo_iv, R.drawable.todo_item_not_select);
            helper.setText(R.id.adapter_todo_done_time,
                    TextUtils.isEmpty(item.t.getCompleteDateStr()) ? "" : ("完成：" + item.t.getCompleteDateStr()));
            helper.setVisible(R.id.adapter_todo_done_time, true);
        } else {
            helper.setImageResource(R.id.adapter_todo_iv, R.drawable.todo_item_select);
            helper.setVisible(R.id.adapter_todo_done_time, false);
        }
        helper.setText(R.id.adapter_todo_title, TextUtils.isEmpty(item.t.getTitle()) ? "" : item.t.getTitle())
                .setText(R.id.adapter_todo_content, TextUtils.isEmpty(item.t.getContent()) ? "" : item.t.getContent())
                .addOnClickListener(R.id.adapter_todo_iv)
                .addOnClickListener(R.id.adapter_todo_delete);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, TodoSection item) {
        helper.setText(R.id.todo_head, TextUtils.isEmpty(item.header) ? "" : item.header);
        if (isDone) {
            helper.setTextColor(R.id.todo_head, ContextCompat.getColor(mContext, R.color.done_tv_color));
            helper.setBackgroundColor(R.id.todo_head, ContextCompat.getColor(mContext, R.color.done_tv_background_color));
        } else {
            helper.setTextColor(R.id.todo_head, ContextCompat.getColor(mContext, R.color.todo_tv_color));
            helper.setBackgroundColor(R.id.todo_head, ContextCompat.getColor(mContext, R.color.todo_tv_background_color));
        }
    }
}
