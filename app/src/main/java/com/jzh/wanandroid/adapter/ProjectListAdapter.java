package com.jzh.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.entity.project.ProjectListResponse;
import com.jzh.wanandroid.utils.GlideUtils;

import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/24 11:49
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class ProjectListAdapter extends BaseQuickAdapter<ProjectListResponse.DataBean.DatasBean, BaseViewHolder> {
    public ProjectListAdapter(@Nullable List<ProjectListResponse.DataBean.DatasBean> data) {
        super(R.layout.adapter_project_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectListResponse.DataBean.DatasBean item) {
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {
            GlideUtils.loadImage(mContext, item.getEnvelopePic(), (ImageView) helper.getView(R.id.adapter_project_list_iv));
        }
        helper.setText(R.id.adapter_project_list_tv_title, TextUtils.isEmpty(item.getTitle()) ? "" : item.getTitle())
                .setText(R.id.adapter_project_list_tv_content, TextUtils.isEmpty(item.getDesc()) ? "" : item.getDesc())
                .setImageResource(R.id.adapter_project_list_collection, (item.isCollect() == null || !item.isCollect()) ? R.drawable.not_collection : R.drawable.collection)
                .setText(R.id.adapter_project_list_tv_time, TextUtils.isEmpty(item.getNiceDate()) ? "" : item.getNiceDate())
                .setText(R.id.adapter_project_list_tv_author, TextUtils.isEmpty(item.getAuthor()) ? "" : item.getAuthor())
                .addOnClickListener(R.id.adapter_project_list_collection);
    }
}
