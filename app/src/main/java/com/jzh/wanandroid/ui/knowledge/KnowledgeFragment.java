package com.jzh.wanandroid.ui.knowledge;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.adapter.KnowledgeAdapter;
import com.jzh.wanandroid.data.db.model.KnowledgeResponseData;
import com.jzh.wanandroid.entity.knowledge.KnowledgeResponse;
import com.jzh.wanandroid.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author:jzh
 * desc:知识体系
 * Date:2018/08/20 14:36
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class KnowledgeFragment extends BaseFragment implements KnowledgeMvpView, OnRefreshListener, BaseQuickAdapter.OnItemClickListener {
    @Inject
    KnowledgePresenter<KnowledgeMvpView> mPresenter;
    @BindView(R.id.fragment_knowledge_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.fragment_knowledge_refresh)
    SmartRefreshLayout mRefresh;
    private List<KnowledgeResponseData> datas = new ArrayList<>();
    private KnowledgeAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initWidget(View view) {
        getFragmentComponent().inject(this);
        mPresenter.onAttach(this);
        setUnBinder(ButterKnife.bind(this, view));
        setHeadVisibility(View.GONE);
        setRreshParams();
        setRecyclerParams();
        datas = MyApp.getInstance().mDataManager.getKnowledgeData();
        if (datas == null || datas.size() <= 0) {
            showLoading(getString(R.string.loading));
            mPresenter.doGetKnowledgeCall();
        } else {
            setData();
        }
    }

    private void setRecyclerParams() {
        List<KnowledgeResponseData> responseDatas = new ArrayList<>();
        adapter = new KnowledgeAdapter(responseDatas);
        adapter.openLoadAnimation();
        adapter.setOnItemClickListener(this);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(adapter);
    }

    private void setRreshParams() {
        mRefresh.setHeaderHeight(100f);
        mRefresh.setEnableRefresh(false);
        mRefresh.setEnableLoadMore(false);
        mRefresh.setEnableLoadMoreWhenContentNotFull(false);
        mRefresh.setOnRefreshListener(this);
    }

    private void setData() {
        mRefresh.setEnableRefresh(true);
        adapter.replaceData(datas);
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
        mPresenter.doGetKnowledgeCall();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onSucc(KnowledgeResponse datas) {
        if (mRefresh.getState() == RefreshState.Refreshing) {
            mRefresh.finishRefresh(true);
        }
        this.datas = datas.getDatas();
        setData();
    }

    @Override
    public void onFail(String msg) {
        if (null == adapter || adapter.getData().size() <= 0) {
            mRefresh.setEnableRefresh(false);
            showErrorLayout(msg);
        } else {
            if (mRefresh.getState() == RefreshState.Refreshing) {
                mRefresh.finishRefresh(false);
            }
        }
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.doGetKnowledgeCall();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (datas.get(position).getDataList() == null || datas.get(position).getDataList().size() <= 0) {
            onToastWarn(R.string.no_more_data);
            return;
        }
        Intent intent = new Intent(getActivity(), KnowledgeDetailActivity.class);
        intent.putExtra(KnowledgeDetailActivity.DATA_KEY, this.adapter.getData().get(position));
        startActivity(intent);
    }
}
