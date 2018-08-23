package com.jzh.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.entity.home.ArticleResponse;

import java.util.List;

/**
 * author:jzh
 * desc:首页文章列表adapter
 * Date:2018/08/20 17:29
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class ArticleAdapter extends BaseQuickAdapter<ArticleResponse.DataBean.DatasBean, BaseViewHolder> {
    public ArticleAdapter(@Nullable List<ArticleResponse.DataBean.DatasBean> data) {
        super(R.layout.adapter_article, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleResponse.DataBean.DatasBean item) {
        String type = "";
        if (!TextUtils.isEmpty(item.getSuperChapterName()) && !TextUtils.isEmpty(item.getChapterName())) {
            type = item.getSuperChapterName() + "/" + item.getChapterName();
        } else {
            if (!TextUtils.isEmpty(item.getSuperChapterName())) {
                type = item.getSuperChapterName();
            }
            if (!TextUtils.isEmpty(item.getChapterName())) {
                type = item.getChapterName();
            }
        }
        boolean isNew = false;
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            if (item.getNiceDate().contains(mContext.getString(R.string.hour)) ||
                    item.getNiceDate().contains(mContext.getString(R.string.minute))
                    || item.getNiceDate().contains(mContext.getString(R.string.second))) {
                isNew = true;
            }
        }
        if (item.getTags() != null) {
            if (item.getTags().size() > 0) {
                String name = item.getTags().get(0).getName();
                helper.setVisible(R.id.adapter_tv_green_type, !TextUtils.isEmpty(name));
                helper.setText(R.id.adapter_tv_green_type, TextUtils.isEmpty(name) ? "" : name);
            }
            if (item.getTags().size() > 1) {
                LinearLayout linearLayout = helper.getView(R.id.adapter_article_linear);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.leftMargin = 5;
                for (int i = 1; i < item.getTags().size(); i++) {
                    if (!TextUtils.isEmpty(item.getTags().get(i).getName())) {
                        TextView tv = new TextView(mContext);
                        tv.setText(item.getTags().get(i).getName());
                        tv.setTextSize(12);
                        tv.setPadding(5, 0, 0, 5);
                        tv.setBackgroundResource(R.drawable.article_type);
                        tv.setTextColor(ContextCompat.getColor(mContext, R.color.title_bar));
                        tv.setLayoutParams(layoutParams);
                        linearLayout.addView(tv);
                    }
                }
            }
        } else {
            helper.setVisible(R.id.adapter_tv_green_type, false);
        }
        CharSequence content = "";
        if (!TextUtils.isEmpty(item.getTitle())) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                content = Html.fromHtml(item.getTitle(), Html.FROM_HTML_MODE_LEGACY);
            } else {
                content = Html.fromHtml(item.getTitle());
            }
        }
        helper.setText(R.id.adapter_author, TextUtils.isEmpty(item.getAuthor()) ? "" : item.getAuthor())
                .setText(R.id.adapter_tv_type, type)
                .setText(R.id.adapter_tv_content, content)
                .setText(R.id.adapter_tv_time, TextUtils.isEmpty(item.getNiceDate()) ? "" : item.getNiceDate())
                .setImageResource(R.id.adapter_collection, (item.isCollect() == null || !item.isCollect()) ? R.drawable.not_collection : R.drawable.collection)
                .setVisible(R.id.adapter_tv_red_type, isNew).setText(R.id.adapter_tv_red_type, "新")
                .addOnClickListener(R.id.adapter_collection);
    }
}
