package com.jzh.wanandroid.ui.project;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.adapter.ProjectIndicatorAdapter;
import com.jzh.wanandroid.adapter.ProjectPageAdapter;
import com.jzh.wanandroid.data.db.model.ProjectTypeResponseData;
import com.jzh.wanandroid.entity.project.ProjectTypeResponse;
import com.jzh.wanandroid.ui.base.BaseFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
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
    private List<ProjectListFragment> fragmentDatas;

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
        fragmentDatas = new ArrayList<>();
        datas = MyApp.getInstance().mDataManager.getProjectTypeData();
        if (datas == null || datas.size() <= 0) {
            showLoading(getString(R.string.loading));
            mPresenter.doGetProjectTypeCall();
        } else {
            initIndicator();
        }
    }


    private void initIndicator() {
        magicIndicator.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.indicator_background_color));
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new ProjectIndicatorAdapter(datas, viewPager));
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
        initViewpager();
    }

    private void initViewpager() {
        for (int i = 0; i < datas.size(); i++) {
            ProjectListFragment fragment = ProjectListFragment.getInstance(datas.get(i).getId());
            fragmentDatas.add(fragment);
        }
        viewPager.setAdapter(new ProjectPageAdapter(getChildFragmentManager(), fragmentDatas));
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
