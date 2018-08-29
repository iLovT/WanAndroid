package com.jzh.wanandroid.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.data.db.model.NavigationResponseData;
import com.jzh.wanandroid.ui.webview.MyWebView;
import com.jzh.wanandroid.utils.AppLogger;
import com.jzh.wanandroid.widget.TagContainerLayout;
import com.jzh.wanandroid.widget.TagView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/28 14:10
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class NavigationAdapter extends BaseQuickAdapter<NavigationResponseData, BaseViewHolder> {
    private List<String> strDatas;

    public NavigationAdapter(@Nullable List<NavigationResponseData> data) {
        super(R.layout.adapter_navigation, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final NavigationResponseData item) {
        if (item.getArticles() == null || item.getArticles().size() <= 0) {
            return;
        }
        helper.setText(R.id.adapter_navigation_title, TextUtils.isEmpty(item.getName()) ? "" : item.getName());
        TagContainerLayout containerLayout = helper.getView(R.id.adapter_navigation_taglayout);
        strDatas = new ArrayList<>();
        for (int i = 0; i < item.getArticles().size(); i++) {
            strDatas.add(item.getArticles().get(i).getTitle());
        }
        containerLayout.setTags(strDatas);
        containerLayout.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                Bundle bundle = new Bundle();
                bundle.putString(MyWebView.REQUEST_WEBVIEW_URL_KEY, item.getArticles().get(position).getLink());
                Intent intent = new Intent(mContext, MyWebView.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });
        strDatas = null;
    }
}
