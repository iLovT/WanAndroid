package com.jzh.wanandroid.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jzh.wanandroid.R;
import com.jzh.wanandroid.data.db.model.KnowledgeResponseData;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

/**
 * author:jzh
 * desc:knowledge viewpager指示器 adapter
 * Date:2018/08/24 10:20
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class KnowledgeIndicatorAdapter extends CommonNavigatorAdapter {
    private List<KnowledgeResponseData> datas;
    private ViewPager viewPager;

    public KnowledgeIndicatorAdapter(List<KnowledgeResponseData> datas, ViewPager viewPager) {
        this.datas = datas;
        this.viewPager = viewPager;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
        simplePagerTitleView.setText(datas.get(index).getName());
        simplePagerTitleView.setNormalColor(ContextCompat.getColor(context, R.color.indicator_normal_color));
        simplePagerTitleView.setSelectedColor(ContextCompat.getColor(context, R.color.indicator_select_color));
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(index);
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setColors(ContextCompat.getColor(context, R.color.indicator_select_color));
        return indicator;
    }
}
