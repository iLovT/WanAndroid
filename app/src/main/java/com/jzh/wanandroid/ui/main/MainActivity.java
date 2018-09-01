package com.jzh.wanandroid.ui.main;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.network.cookies.CookiesManager;
import com.jzh.wanandroid.ui.base.BaseActivity;
import com.jzh.wanandroid.ui.home.HomeFragment;
import com.jzh.wanandroid.ui.knowledge.KnowledgeFragment;
import com.jzh.wanandroid.ui.login.LoginActivity;
import com.jzh.wanandroid.ui.navigation.NavigationFragment;
import com.jzh.wanandroid.ui.project.ProjectFragment;
import com.jzh.wanandroid.ui.todo.TodoFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Author:jzh
 * desc:主界面
 * Date:2018/08/16 11:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
public class MainActivity extends BaseActivity implements MainMvpView {
    private long exitTime = 0;
    @BindView(R.id.bbl)
    BottomBarLayout mBottomBarLayout;
    @BindView(R.id.ac_main_title_name)
    TextView title_name;
    @Inject
    MainPresenter<MainMvpView> mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        getActivityComponent().inject(this);
        mPresenter.onAttach(this);
        setUnBinder(ButterKnife.bind(this));
        setHeadVisibility(View.GONE);
        initImmersionBar();
        showFragment(R.id.fl, new HomeFragment());
        mPresenter.doGetProjectType();
        mPresenter.doGetKnowledgeCall();
        mPresenter.doGetNavigationCall();
        if (MyApp.getInstance().mDataManager.getLoginStaus()) {
            mPresenter.doTodoListCall(0);
        }
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    /**
     * 双击退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                onToastInfo(R.string.try_one_exit);
                exitTime = System.currentTimeMillis();
            } else {
                MyApp.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void eventOnClick() {
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int i, int position) {
                switch (position) {
                    case 0:
                        title_name.setText(R.string.home);
                        showFragment(R.id.fl, new HomeFragment());
                        break;
                    case 1:
                        title_name.setText(R.string.project);
                        showFragment(R.id.fl, new ProjectFragment());
                        break;
                    case 2:
                        title_name.setText(R.string.knowledge);
                        showFragment(R.id.fl, new KnowledgeFragment());
                        break;
                    case 3:
                        title_name.setText(R.string.navigation);
                        showFragment(R.id.fl, new NavigationFragment());
                        break;
                    case 4:
                        title_name.setText(R.string.todo);
                        showFragment(R.id.fl, new TodoFragment());
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @OnClick(R.id.login_out)
    public void onViewClicked() {
        MyApp.getInstance().mDataManager.setLoginStaus(false);
        CookiesManager.clearAllCookies();
        goActivity(LoginActivity.class);
    }
}
