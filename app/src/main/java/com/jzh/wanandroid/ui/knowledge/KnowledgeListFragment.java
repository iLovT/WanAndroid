package com.jzh.wanandroid.ui.knowledge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.adapter.ArticleAdapter;
import com.jzh.wanandroid.common.Constants;
import com.jzh.wanandroid.entity.home.ArticleResponse;
import com.jzh.wanandroid.ui.base.BaseFragment;
import com.jzh.wanandroid.ui.login.LoginActivity;
import com.jzh.wanandroid.ui.webview.MyWebView;
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
 * desc:
 * Date:2018/08/27 17:12
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class KnowledgeListFragment extends BaseFragment implements KnowledgeListMvpView, OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener, OnLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.fragment_knowledge_list_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.fragment_knowledge_list_refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.fragment_knowledge_list_to_top)
    ImageView toTop;
    private int cid;
    @Inject
    KnowledgeListPresenter<KnowledgeListMvpView> mPresenter;
    private int offset = 0;
    private View mView;
    private ArticleAdapter adapter;
    private boolean isOver = false;
    private GoodView goodView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge_list;
    }

    @Override
    protected void initWidget(View view) {
        getFragmentComponent().inject(this);
        mPresenter.onAttach(this);
        setUnBinder(ButterKnife.bind(this, view));
        setHeadVisibility(View.GONE);
        cid = getArguments().getInt(Constants.CID_KEY);
        showLoading(getString(R.string.loading));
        mView = View.inflate(getActivity(), R.layout.base_network_error, null);
        TextView tv = mView.findViewById(R.id.base_error_tv);
        tv.setText(getString(R.string.empty_data));
        goodView = new GoodView(getActivity());
        setRecyclerParams();
        setRreshParams();
        offset = 0;
        mPresenter.doGetKnowledgeListCall(offset, cid, true);
    }

    private void setRecyclerParams() {
        List<ArticleResponse.DataBean.DatasBean> dataBeanList = new ArrayList<>();
        adapter = new ArticleAdapter(dataBeanList);
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
        offset = 0;
        mPresenter.doGetKnowledgeListCall(offset, cid, true);
    }

    public static KnowledgeListFragment getInstance(Integer id) {
        KnowledgeListFragment knowledgeListFragment = new KnowledgeListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.CID_KEY, id);
        knowledgeListFragment.setArguments(bundle);
        return knowledgeListFragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onGetKnowledgeListSucc(ArticleResponse response) {
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
        mPresenter.doGetKnowledgeListCall(offset, cid, false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        offset = 0;
        refreshLayout.setNoMoreData(false);
        mPresenter.doGetKnowledgeListCall(offset, cid, false);
    }

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

    @OnClick(R.id.fragment_knowledge_list_to_top)
    public void onViewClicked() {
        if (mRecycler == null) {
            return;
        }
        mRecycler.smoothScrollToPosition(0);
    }
}
