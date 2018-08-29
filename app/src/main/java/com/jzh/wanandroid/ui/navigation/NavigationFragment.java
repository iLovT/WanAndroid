package com.jzh.wanandroid.ui.navigation;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.adapter.NavigationAdapter;
import com.jzh.wanandroid.data.db.model.NavigationResponseData;
import com.jzh.wanandroid.entity.navigation.NavigationResponse;
import com.jzh.wanandroid.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * author:jzh
 * desc:导航
 * Date:2018/08/20 14:37
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class NavigationFragment extends BaseFragment implements NavigationMvpView {
    @BindView(R.id.fragment_navigation_tablayout)
    VerticalTabLayout mTablayout;
    @BindView(R.id.fragment_navigation_recycler)
    RecyclerView mRecycler;
    List<NavigationResponseData> datas = new ArrayList<>();
    @Inject
    NavigationPresenter<NavigationMvpView> mPresenter;
    private int index;
    private boolean isTabClick;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initWidget(View view) {
        getFragmentComponent().inject(this);
        mPresenter.onAttach(this);
        setUnBinder(ButterKnife.bind(this, view));
        setHeadVisibility(View.GONE);
        datas = MyApp.getInstance().mDataManager.getNavigationData();
        if (datas == null || datas.size() <= 0) {
            showLoading(getString(R.string.loading));
            mPresenter.doGetNavigationCall();
        } else {
            setDatas();
        }
    }


    private void setDatas() {
        mTablayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return datas == null ? 0 : datas.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder().setContent(datas.get(position).getName())
                        .setTextSize(14).setTextColor(ContextCompat.getColor(getActivity(), R.color.title_bar)
                                , ContextCompat.getColor(getActivity(), R.color.black)).build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(linearLayoutManager);
        final NavigationAdapter adapter = new NavigationAdapter(datas);
        adapter.openLoadAnimation();
        mRecycler.setAdapter(adapter);
        mTablayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                isTabClick = true;
                if (adapter.getItemCount() > position) {
                    mRecycler.smoothScrollToPosition(position);
                }
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (index != firstPosition && !isTabClick) {
                    index = firstPosition;
                    mTablayout.setTabSelected(firstPosition);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    isTabClick = false;
                }
            }
        });
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void getErrorViewClick() {
        super.getErrorViewClick();
        showLoading(getString(R.string.loading));
        hideErrorLayout();
        mPresenter.doGetNavigationCall();
    }

    @Override
    public void onSucc(NavigationResponse response) {
        datas = response.getData();
        setDatas();
    }

    @Override
    public void onFail(String msg) {
        showErrorLayout(msg);
    }
}
