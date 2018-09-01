package com.jzh.wanandroid.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.adapter.ArticleAdapter;
import com.jzh.wanandroid.entity.home.ArticleResponse;
import com.jzh.wanandroid.entity.home.BannerResponse;
import com.jzh.wanandroid.ui.base.BaseFragment;
import com.jzh.wanandroid.ui.login.LoginActivity;
import com.jzh.wanandroid.ui.webview.MyWebView;
import com.jzh.wanandroid.utils.MeasureUtils;
import com.jzh.wanandroid.widget.GoodView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author:jzh
 * desc:首页
 * Date:2018/08/20 14:35
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class HomeFragment extends BaseFragment implements HomeMvpView, OnRefreshListener, OnLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener, OnItemClickListener, BaseQuickAdapter.OnItemClickListener {
    ConvenientBanner banner;
    @BindView(R.id.fragment_home_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.fragment_home_refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.fragment_home_to_top)
    ImageView toTop;
    @Inject
    HomePresenter<HomeMvpView> mPresenter;
    private List<String> bannerDatas;
    private int offset = 0;
    private View mView;
    private ArticleAdapter adapter;
    private boolean isOver = false;
    private GoodView goodView;
    private BannerResponse bannerResponse;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget(View view) {
        getFragmentComponent().inject(this);
        mPresenter.onAttach(this);
        setUnBinder(ButterKnife.bind(this, view));
        setHeadVisibility(View.GONE);
        bannerDatas = new ArrayList<>();
        showLoading(getString(R.string.loading));
        mView = View.inflate(getActivity(), R.layout.base_network_error, null);
        TextView tv = mView.findViewById(R.id.base_error_tv);
        tv.setText(getString(R.string.empty_data));
        goodView = new GoodView(getActivity());
        setBannerParams();
        setRecyclerParams();
        setRreshParams();
        mPresenter.doGetBannerCall();
        offset = 0;
        mPresenter.doGetArticleCall(offset, true);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (banner != null) {
            banner.stopTurning();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (banner != null) {
            banner.startTurning(4000);
        }
    }

    private void setBannerParams() {
        banner = new ConvenientBanner(getActivity());
        banner.setCanLoop(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MeasureUtils.dip2px(getActivity(), 180));
        banner.setLayoutParams(params);
        banner.setOnItemClickListener(this);
    }

    private void setRecyclerParams() {
        List<ArticleResponse.DataBean.DatasBean> dataBeanList = new ArrayList<>();
        adapter = new ArticleAdapter(dataBeanList);
        adapter.addHeaderView(banner);
        adapter.openLoadAnimation();
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private long totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                toTop.setVisibility(totalDy >= 300 ? View.VISIBLE : View.GONE);
            }
        });
        mRecycler.setAdapter(adapter);
    }

    private void setRreshParams() {
        mRefresh.setHeaderHeight(100f);
        mRefresh.setEnableRefresh(false);
        mRefresh.setEnableLoadMoreWhenContentNotFull(false);
        mRefresh.setOnRefreshListener(this);
        mRefresh.setOnLoadMoreListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void getErrorViewClick() {
        super.getErrorViewClick();
        hideErrorLayout();
        showLoading(getString(R.string.loading));
        mPresenter.doGetBannerCall();
        offset = 0;
        mPresenter.doGetArticleCall(offset, true);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onSucc(BannerResponse response) {
        bannerResponse = response;
        if (bannerDatas != null) {
            bannerDatas.clear();
            for (int i = 0; i < response.getData().size(); i++) {
                bannerDatas.add(response.getData().get(i).getImagePath());
            }
        }
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public BannerHolderView createHolder() {
                return new BannerHolderView();
            }
        }, bannerDatas)
                .setPageIndicator(new int[]{R.drawable.kuang_corners_white_five, R.drawable.kuang_corners_red_dian_five})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).setScrollDuration(2500);
        banner.startTurning(4000);
    }

    @Override
    public void onGetArticleSucc(ArticleResponse response) {
        mRefresh.setEnableRefresh(true);
        isOver = response.getData().isOver();
        adapter.addData(response.getData().getDatas());
        if (mRefresh.getState() == RefreshState.Refreshing) {
            adapter.replaceData(response.getData().getDatas());
            mRefresh.finishRefresh(1500, true);
        } else if (mRefresh.getState() == RefreshState.Loading) {
            adapter.addData(response.getData().getDatas());
            mRefresh.finishLoadMore(true);
        }
        if (response.getData().getDatas().size() == 0) {
            adapter.setEmptyView(mView);
        }
    }

    @Override
    public void onFail(String msg) {
        if (null == adapter || adapter.getData().size() <= 0) {
            mRefresh.setEnableRefresh(false);
            showErrorLayout("" + msg);
        } else {
            if (mRefresh.getState() == RefreshState.Refreshing) {
                mRefresh.finishRefresh(false);
            } else if (mRefresh.getState() == RefreshState.Loading) {
                offset -= 1;
                mRefresh.finishLoadMore(1000, false, false);
            }
        }
    }

    /**
     * 收藏成功
     */
    @Override
    public void onCollectionSucc(View view, int position) {
        if (view == null) {
            onToastWarn(R.string.unknown_error);
            return;
        }
        adapter.getData().get(position).setCollect(true);
        ((ImageView) view).setImageResource(R.drawable.collection);
        goodView.setText(getString(R.string.collection_success));
        goodView.show(view);
    }

    /**
     * 取消收藏成功
     */
    @Override
    public void onUnCollectionSucc(View view, int position) {
        if (view == null) {
            onToastWarn(R.string.unknown_error);
            return;
        }
        adapter.getData().get(position).setCollect(false);
        ((ImageView) view).setImageResource(R.drawable.not_collection);
        goodView.setText(getString(R.string.uncollection));
        goodView.show(view);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (isOver) {
            refreshLayout.finishLoadMoreWithNoMoreData();
            return;
        }
        offset++;
        mPresenter.doGetArticleCall(offset, false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        offset = 0;
        refreshLayout.setNoMoreData(false);
        mPresenter.doGetBannerCall();
        mPresenter.doGetArticleCall(offset, false);
    }

    /**
     * banner item child click
     *
     * @param adapter  adapter
     * @param view     view
     * @param position position
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.adapter_collection:
                if (MyApp.getInstance().mDataManager.getLoginStaus()) {
                    Long id = this.adapter.getData().get(position).getId();
                    if (this.adapter.getData().get(position).isCollect()) {
                        mPresenter.doUnCollection(id, view, position);
                    } else {
                        mPresenter.doCollection(id, view, position);
                    }
                } else {
                    onToastInfo(R.string.go_login);
                    goActivity(LoginActivity.class);
                }
                break;
            default:
                break;
        }
    }

    /**
     * banner item  click
     *
     * @param position position
     */
    @Override
    public void onItemClick(int position) {
        if (bannerResponse == null || bannerResponse.getData() == null) {
            onToastWarn(R.string.unknown_error);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(MyWebView.REQUEST_WEBVIEW_URL_KEY, TextUtils.isEmpty(bannerResponse.getData().get(position).getUrl()) ? "" : bannerResponse.getData().get(position).getUrl());
        goActivity(MyWebView.class, bundle);
    }

    /**
     * adapter item click
     *
     * @param adapter  adapter
     * @param view     view
     * @param position position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (this.adapter == null || this.adapter.getData().size() <= 0) {
            onToastWarn(R.string.unknown_error);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(MyWebView.REQUEST_WEBVIEW_URL_KEY, TextUtils.isEmpty(this.adapter.getData().get(position).getLink()) ? "" : this.adapter.getData().get(position).getLink());
        goActivity(MyWebView.class, bundle);
    }


    @OnClick(R.id.fragment_home_to_top)
    public void onViewClicked() {
        if (mRecycler == null) {
            return;
        }
        mRecycler.smoothScrollToPosition(0);
    }
}
