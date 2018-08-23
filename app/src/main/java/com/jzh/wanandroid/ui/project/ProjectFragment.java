package com.jzh.wanandroid.ui.project;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.common.Constants;
import com.jzh.wanandroid.data.db.model.ProjectTypeResponseData;
import com.jzh.wanandroid.entity.project.ProjectTypeResponse;
import com.jzh.wanandroid.ui.base.BaseFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:jzh
 * desc:项目
 * Date:2018/08/20 14:37
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class ProjectFragment extends BaseFragment implements ProjectMvpView {
    @Inject
    ProjectPresenter<ProjectMvpView> mPresenter;
    @BindView(R.id.fragment_magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.fragment_project_view_pager)
    ViewPager viewPager;
    private List<ProjectTypeResponseData> datas;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initWidget(View view) {
        getFragmentComponent().inject(this);
        mPresenter.onAttach(this);
        setUnBinder(ButterKnife.bind(this, view));
        setHeadVisibility(View.GONE);
        datas = MyApp.getInstance().mDataManager.getProjectTypeData();
        if (datas == null || datas.size() <= 0) {
            showLoading(getString(R.string.loading));
            mPresenter.doGetProjectTypeCall();
        } else {
            initIndicator();
        }
    }

    private void initIndicator() {
        magicIndicator.setBackgroundColor(Color.parseColor("#d43d3d"));
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setSkimOver(true);
        int padding = UIUtil.getScreenWidth(getActivity()) / 2;
        commonNavigator.setRightPadding(padding);
        commonNavigator.setLeftPadding(padding);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return datas == null ? 0 : datas.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(datas.get(index).getName());
                clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        viewPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onSucc(ProjectTypeResponse response) {
        datas = response.getProjectTypeResponseDatas();
        initIndicator();
    }

    @Override
    public void onFail(String msg) {
        showErrorLayout(msg);
    }

    @Override
    public void getErrorViewClick() {
        super.getErrorViewClick();
        hideErrorLayout();
        showLoading(getString(R.string.loading));
        mPresenter.doGetProjectTypeCall();
    }

}
