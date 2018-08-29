package com.jzh.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.data.db.model.KnowledgeResponseData;

import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/27 15:32
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeResponseData, BaseViewHolder> {
    StringBuilder content;

    public KnowledgeAdapter(@Nullable List<KnowledgeResponseData> data) {
        super(R.layout.adapter_knowledge, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeResponseData item) {
        content = new StringBuilder();
        if (item.getDataList() != null && item.getDataList().size() > 0) {
            for (KnowledgeResponseData data : item.getDataList()) {
                content.append(data.getName() + "   ");
            }
        }
        helper.setText(R.id.adapter_knowledge_title, TextUtils.isEmpty(item.getName()) ? "" : item.getName())
                .setText(R.id.adapter_knowledge_content, TextUtils.isEmpty(content.toString()) ? "" : content.toString());
        content = null;
    }
}
