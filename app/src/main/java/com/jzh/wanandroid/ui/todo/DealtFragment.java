package com.jzh.wanandroid.ui.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.adapter.TodoAdapter;
import com.jzh.wanandroid.common.Constants;
import com.jzh.wanandroid.data.db.model.TodoListResponseData;
import com.jzh.wanandroid.entity.todo.TodoListResponse;
import com.jzh.wanandroid.entity.todo.TodoResponse;
import com.jzh.wanandroid.entity.todo.TodoSection;
import com.jzh.wanandroid.ui.base.BaseFragment;
import com.jzh.wanandroid.utils.AppLogger;
import com.jzh.wanandroid.utils.TypeUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author:jzh
 * desc:
 * Date:2018/08/29 09:44
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class DealtFragment extends BaseFragment implements DealtMvpView, OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.fragment_dealt_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.fragment_dealt_refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.fragment_dealt_add_iv)
    ImageView iv_add;
    private TodoListResponseData response = null;
    @Inject
    DealtPresenter<DealtMvpView> mPresenter;
    private TodoAdapter adapter;
    private boolean isDone;
    private String format = "yyyy-MM-dd";
    private boolean isAdd;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dealt;
    }

    @Override
    protected void initWidget(View view) {
        getFragmentComponent().inject(this);
        mPresenter.onAttach(this);
        setUnBinder(ButterKnife.bind(this, view));
        setHeadVisibility(View.GONE);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isDone = bundle.getBoolean(Constants.PARAMS);
        }
        iv_add.setVisibility(isDone ? View.GONE : View.VISIBLE);
        setRreshParams();
        setRecyclerParams();
        response = MyApp.getInstance().mDataManager.getTodoListData();
        if (response == null || response.getTodoList().size() <= 0) {
            showLoading(getString(R.string.loading));
            mPresenter.doTodoListCall(0, true);
        } else {
            setData();
        }
    }

    public static DealtFragment getInstance(boolean isDone) {
        DealtFragment dealtFragment = new DealtFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.PARAMS, isDone);
        dealtFragment.setArguments(bundle);
        return dealtFragment;
    }

    private void setData() {
        mRefresh.setEnableRefresh(true);
        adapter.replaceData(getTodoSection(response));
    }

    private void setRecyclerParams() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<TodoSection> datas = new ArrayList<>();
        adapter = new TodoAdapter(datas, isDone);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
        adapter.openLoadAnimation();
        mRecycler.setAdapter(adapter);
    }

    private void setRreshParams() {
        mRefresh.setHeaderHeight(100f);
        mRefresh.setEnableRefresh(false);
        mRefresh.setEnableLoadMoreWhenContentNotFull(false);
        mRefresh.setOnRefreshListener(this);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 300) {
            AppLogger.e("DealtFragment", "真的  进入了   ");
            isAdd = true;
            showProgressLoadingDialog("加载中");
            mPresenter.doTodoListCall(0, false);
        }
    }

    @OnClick(R.id.fragment_dealt_add_iv)
    public void onViewClick() {
        Intent intent = new Intent(getActivity(), AddTodoActivity.class);
        startActivityForResult(intent, 300);
    }

    @Override
    public void onSucc(TodoResponse response) {
        AppLogger.e("DealtFragment", "真的  进入了  !!!!................ ");
        mRefresh.setEnableRefresh(true);
        if (mRefresh.getState() == RefreshState.Refreshing) {
            mRefresh.finishRefresh(true);
        }
        if (isAdd) {
            adapter.addData(getTodoSection(response.getDataBean()));
            AppLogger.e("DealtFragment", "真的  进入了  !!!! ");
            isAdd = false;
            return;
        }
        this.response = response.getDataBean();
        setData();
    }

    TodoSection todoSection;

    private List<TodoSection> getTodoSection(TodoListResponseData data) {
        List<TodoSection> result = new ArrayList<>();
        List<TodoListResponse> todoListResponses = isDone ? data.getDoneList() : data.getTodoList();
        for (int i = 0; i < todoListResponses.size(); i++) {
            if (todoListResponses.get(i).getDate() != null) {
                try {
                    todoSection = new TodoSection(TypeUtils.longToString(todoListResponses.get(i).getDate(), format));
                    result.add(todoSection);
                    todoSection = null;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < todoListResponses.get(i).getTodoList().size(); j++) {
                todoSection = new TodoSection(todoListResponses.get(i).getTodoList().get(j));
                result.add(todoSection);
                todoSection = null;
            }
        }
        return result;
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
        mPresenter.doTodoListCall(0, false);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
