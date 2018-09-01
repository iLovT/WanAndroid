package com.jzh.wanandroid.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jzh.wanandroid.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:jzh
 * desc:
 * Date:2018/08/29 09:33
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class TodoIndicatorAdapter extends CommonNavigatorAdapter {
    private List<String> datas;
    private ViewPager viewPager;

    public TodoIndicatorAdapter(Context context, ViewPager viewPager) {
        datas = new ArrayList<>();
        datas.add(context.getString(R.string.todo));
        datas.add(context.getString(R.string.complte_list));
        this.viewPager = viewPager;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int i) {
        SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
        simplePagerTitleView.setText(datas.get(i));
        simplePagerTitleView.setNormalColor(ContextCompat.getColor(context, R.color.indicator_normal_color));
        simplePagerTitleView.setSelectedColor(ContextCompat.getColor(context, R.color.indicator_select_color));
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(i);
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
