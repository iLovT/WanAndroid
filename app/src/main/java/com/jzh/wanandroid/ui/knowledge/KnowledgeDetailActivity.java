package com.jzh.wanandroid.ui.knowledge;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jzh.wanandroid.R;
import com.jzh.wanandroid.adapter.KnowledgeIndicatorAdapter;
import com.jzh.wanandroid.adapter.KnowledgePageAdapter;
import com.jzh.wanandroid.data.db.model.KnowledgeResponseData;
import com.jzh.wanandroid.ui.base.BaseActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:jzh
 * desc:
 * Date:2018/08/27 16:34
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class KnowledgeDetailActivity extends BaseActivity {
    @BindView(R.id.ac_knowledge_detail_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.ac_knowledge_detail_viewpager)
    ViewPager mViewpager;
    public static String DATA_KEY = "data_key";
    private KnowledgeResponseData datas;
    private List<KnowledgeListFragment> fragmentDatas;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_knowledge_detail;
    }

    @Override
    protected void initWidget() {
        setUnBinder(ButterKnife.bind(this));
        fragmentDatas = new ArrayList<>();
        datas = (KnowledgeResponseData) getIntent().getSerializableExtra(DATA_KEY);
        if (datas.getDataList().size() == 1) {
            setTitle(datas.getDataList().get(0).getName(), true, false, false);
            magicIndicator.setVisibility(View.GONE);
            initViewpager();
        } else {
            setTitle(datas.getName(), true, false, false);
            initIndicator();
        }
    }

    private void initIndicator() {
        magicIndicator.setBackgroundColor(ContextCompat.getColor(this, R.color.indicator_background_color));
        CommonNavigator commonNavigator = new CommonNavigator(this);
        if (datas.getDataList().size() <= 5) {
            commonNavigator.setAdjustMode(true);
        }
        commonNavigator.setAdapter(new KnowledgeIndicatorAdapter(datas.getDataList(), mViewpager));
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewpager);
        initViewpager();
    }

    private void initViewpager() {
        for (int i = 0; i < datas.getDataList().size(); i++) {
            KnowledgeListFragment fragment = KnowledgeListFragment.getInstance(datas.getDataList().get(i).getId());
            fragmentDatas.add(fragment);
        }
        mViewpager.setAdapter(new KnowledgePageAdapter(getSupportFragmentManager(), fragmentDatas));
    }

}
