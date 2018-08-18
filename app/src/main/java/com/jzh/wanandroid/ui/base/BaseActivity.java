package com.jzh.wanandroid.ui.base;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.di.component.ActivityComponent;
import com.jzh.wanandroid.di.component.DaggerActivityComponent;
import com.jzh.wanandroid.di.module.ActivityModule;
import com.jzh.wanandroid.listener.OnMultiClickListener;
import com.jzh.wanandroid.utils.CommonUtils;
import com.jzh.wanandroid.utils.NetworkUtils;
import com.jzh.wanandroid.utils.ScreenSwitch;
import com.jzh.wanandroid.utils.toast.Toasty;
import com.jzh.wanandroid.widget.LVCircularSmile;
import com.jzh.wanandroid.widget.ProgressLoadingDialog;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * Author:jzh
 * desc:activity基类
 * Date:2018/08/16 11:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView, BaseFragment.Callback, BGASwipeBackHelper.Delegate {

    private Unbinder mUnBinder;
    private FragmentManager fragmentManager;
    private BaseFragment showFragment;
    private View contentViewV;
    private FrameLayout contentView;
    protected RelativeLayout headLayout;
    protected Button leftBtn;
    protected TextView titleTv, save_tv_locatoin;
    private ImageView searchIv;
    public ImageView rightIv;
    private LVCircularSmile loadingDialogIv;
    public PopupWindow loadingPop;
    private Thread popTimeThread;
    private ActivityComponent mActivityComponent;
    private String NETWORK_ERROR_TAG = "NETWORK_ERROR_TAG";
    private String NETWORK_LOADING_TAG = "NETWORK_LOADING_TAG";
    private ProgressLoadingDialog mProgressDialog;
    private String TAG = "BaseActivity";
    /**
     * 判断是否需要检测权限，防止不停的弹框
     */
    public boolean isNeedCheck = true;
    private static final int PERMISSON_REQUESTCODE = 0;

    protected ImmersionBar mImmersionBar;
    protected BGASwipeBackHelper mSwipeBackHelper;
    private boolean mStateEnable = true;

    /**
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initWidget();

    /**
     * 事件绑定
     */
    protected void eventOnClick() {
    }

    /**
     * 设置屏幕全屏等初始化操作
     */
    protected void beForSet() {

    }

    protected void initWidget(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MyApp) getApplication()).getComponent())
                .build();
        beForSet();
        super.setContentView(R.layout.title_base);
        initViews();
        if (getLayoutId() != 0) {
            contentViewV = LayoutInflater.from(this).inflate(getLayoutId(), null);
            settingContentView();
        }
        initWidget();
        eventOnClick();
        initWidget(savedInstanceState);
        MyApp.getInstance().addActivity(this);
        if (headLayout.getVisibility() == View.VISIBLE) {
            initImmersionBar();
        }
    }

    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarColor(R.color.title_bar).init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStateEnable = true;
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    private void initViews() {
        contentView = super.findViewById(R.id.layout_container);
        headLayout = super.findViewById(R.id.layout_head);
        leftBtn = super.findViewById(R.id.btn_title_back);
        titleTv = findViewById(R.id.tv_title_name);
        searchIv = super.findViewById(R.id.iv_search);
        rightIv = super.findViewById(R.id.iv_right);
        save_tv_locatoin = super.findViewById(R.id.save_tv_locatoin);
    }

    private void settingContentView() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        contentView.removeAllViews();
        contentView.addView(contentViewV, lp);
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置。
        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return 是否支持
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }

    /**
     * 设置头部是否可见
     *
     * @param visibility 是否可见
     */
    public void setHeadVisibility(int visibility) {
        if (null != headLayout) {
            headLayout.setVisibility(visibility);
        }
    }

    /**
     * 设置标题
     *
     * @param titleId 标题
     */
    public void setTitle(int titleId, boolean isShowLeft, boolean isShowSearch, boolean isShowRight) {
        titleTv.setText(getString(titleId));
        if (isShowLeft) {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideKeyboard();
                    finish();
                }
            });
        } else {
            leftBtn.setVisibility(View.INVISIBLE);
        }
        if (isShowSearch) {
            searchIv.setVisibility(View.VISIBLE);
        } else {
            searchIv.setVisibility(View.INVISIBLE);
        }
        if (isShowRight) {
            rightIv.setVisibility(View.VISIBLE);
        } else {
            rightIv.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title, boolean isShowLeft, boolean isShowSearch, boolean isShowRight) {
        titleTv.setText(title);
        if (isShowLeft) {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideKeyboard();
                    finish();
                }
            });
        } else {
            leftBtn.setVisibility(View.INVISIBLE);
        }
        if (isShowSearch) {
            searchIv.setVisibility(View.VISIBLE);
        } else {
            searchIv.setVisibility(View.INVISIBLE);
        }
        if (isShowRight) {
            rightIv.setVisibility(View.VISIBLE);
        } else {
            rightIv.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * @return 得到搜索控件
     */
    public ImageView getSearchImageView() {
        return searchIv;
    }

    /**
     * @return 得到右边控件
     */
    public ImageView getRightImageView() {
        return rightIv;
    }

    /**
     * @return 得到左边返回控件
     */
    public Button getLeftButton() {
        return leftBtn;
    }

    /**
     * 检查权限
     *
     * @param permissions 权限组
     * @since 2.5.0
     */
    public void checkPermissions(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList
                        && needRequestPermissonList.size() > 0) {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    Method method = getClass().getMethod("requestPermissions", new Class[]{String[].class,
                            int.class});

                    method.invoke(this, array, PERMISSON_REQUESTCODE);
                }
            }
        } catch (Throwable e) {
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return 集合
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= 23
                && getApplicationInfo().targetSdkVersion >= 23) {
            try {
                for (String perm : permissions) {
                    Method checkSelfMethod = getClass().getMethod("checkSelfPermission", String.class);
                    Method shouldShowRequestPermissionRationaleMethod = getClass().getMethod("shouldShowRequestPermissionRationale",
                            String.class);
                    if ((Integer) checkSelfMethod.invoke(this, perm) != PackageManager.PERMISSION_GRANTED
                            || (Boolean) shouldShowRequestPermissionRationaleMethod.invoke(this, perm)) {
                        needRequestPermissonList.add(perm);
                    }
                }
            } catch (Throwable e) {

            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否所有的权限都已经授权
     *
     * @param grantResults 权限组
     * @return 是否已授权
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    @Override
    protected void onResume() {
        mStateEnable = true;
        super.onResume();
        MobclickAgent.onResume(this);//友盟基本数据统计
    }

    /**
     * 显示缺少权限提示信息
     *
     * @since 2.5.0
     */
    public void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApp.getInstance().exit();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
        isNeedCheck = true;

    }

    /**
     * 是否拥有所有需要的权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasAllPermissions(String... permissions) {
        for (String permission : permissions) {
            if (hasPermission(permission))
                return true;
        }
        return false;
    }

    /**
     * 单个权限检查
     *
     * @param permission
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

//    /**
//     * 权限申请
//     *
//     * @param permissions
//     * @param requestCode
//     */
//    @TargetApi(Build.VERSION_CODES.M)
//    public void requestPermissionsSafely(String[] permissions, int requestCode) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(permissions, requestCode);
//        }
//    }
//


    /**
     * 显示异常信息
     *
     * @param msg 要显示的信息，可以为空，为空则显示默认信息
     */
    @Override
    public void showErrorLayout(String msg) {
        hideErrorLayout();
        View error_view = View.inflate(this, R.layout.base_network_error, null);
        TextView tv = (TextView) error_view.findViewById(R.id.base_error_tv);
        tv.setText(TextUtils.isEmpty(msg) ? getString(R.string.network_error) : msg);
        contentView.addView(error_view);
        error_view.setTag(NETWORK_ERROR_TAG);
        error_view.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                getErrorViewClick();
            }
        });
    }

    /**
     * 得到错误布局的点击事件
     */
    public void getErrorViewClick() {

    }

    /**
     * 隐藏异常信息
     */
    @Override
    public void hideErrorLayout() {
        if (null != contentView) {
            if (null != contentView.findViewWithTag(NETWORK_ERROR_TAG))
                contentView.removeView(contentView.findViewWithTag(NETWORK_ERROR_TAG));
        }
    }

    /**
     * 显示内嵌加载框
     *
     * @param msg
     */
    @Override
    public void showLoading(String msg) {
        hideLoading();
        View popLoadingView = View.inflate(this, R.layout.enter_loading, null);
        TextView loadingPopText = popLoadingView.findViewById(R.id.tv_dialog_loading_text);
        loadingPopText.setText(TextUtils.isEmpty(msg) ? getResources().getString(R.string.loading) : msg);
        LVCircularSmile ivLoad = popLoadingView.findViewById(R.id.dialog_lv_eat_view);
        ivLoad.startAnim();
        contentView.addView(popLoadingView);
        popLoadingView.setTag(NETWORK_LOADING_TAG);
    }


    /**
     * 隐藏内嵌加载框
     */
    @Override
    public void hideLoading() {
        if (null != contentView) {
            if (null != contentView.findViewWithTag(NETWORK_LOADING_TAG))
                contentView.removeView(contentView.findViewWithTag(NETWORK_LOADING_TAG));
        }
    }


    /**
     * 确定控件渲染完成后才能执行dialog操作，否则容易抛异常
     *
     * @param hasFocus 是否有焦点
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && loadingPop != null && !isFinishing()) {
            loadingPop.showAtLocation(contentView, Gravity.CENTER, 0, 0);
        }
    }


    /**
     * 显示progressbar dialog
     *
     * @param msg msg
     */
    @Override
    public void showProgressLoadingDialog(String msg) {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this, msg);
    }

    /**
     * 隐藏progressbar dialog
     */
    @Override
    public void hideProgressLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onToast(int resId) {
        onToast(getString(resId));
    }

    @Override
    public void onToast(String message) {
        showSnackBar(message == null ? getString(R.string.some_error) : message, 1);
    }

    @Override
    public void onToastSucc(String message) {
        showSnackBar(message == null ? getString(R.string.some_error) : message, 1);
    }

    @Override
    public void onToastSucc(@StringRes int resId) {
        onToastSucc(getString(resId));
    }

    @Override
    public void onToastFail(String message) {
        showSnackBar(message == null ? getString(R.string.some_error) : message, 2);
    }

    @Override
    public void onToastFail(@StringRes int resId) {
        onToastFail(getString(resId));
    }

    @Override
    public void onToastInfo(String message) {
        showSnackBar(message == null ? getString(R.string.some_error) : message, 3);
    }

    @Override
    public void onToastInfo(@StringRes int resId) {
        onToastInfo(getString(resId));
    }

    @Override
    public void onToastWarn(String message) {
        showSnackBar(message == null ? getString(R.string.some_error) : message, 4);
    }

    @Override
    public void onToastWarn(@StringRes int resId) {
        onToastWarn(getString(resId));
    }

    @Override
    public void onToastNormal(String message) {
        showSnackBar(message == null ? getString(R.string.some_error) : message, 5);
    }

    @Override
    public void onToastNormal(@StringRes int resId) {
        onToastNormal(getString(resId));
    }

    private Toast toasty;

    /**
     * 显示吐司 如果需要定义其他类型，请自己重新定义。
     *
     * @param message msg
     * @param type    type
     */
    private void showSnackBar(String message, int type) {
        switch (type) {
            case 1:
                if (null == toasty) {
                    toasty = Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT);
                } else {
                    toasty.cancel();
                    toasty = Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT);
                }
                break;
            case 2:
                if (null == toasty) {
                    toasty = Toasty.error(getApplicationContext(), message, Toast.LENGTH_SHORT);
                } else {
                    toasty.cancel();
                    toasty = Toasty.error(getApplicationContext(), message, Toast.LENGTH_SHORT);
                }
                break;
            case 3:
                if (null == toasty) {
                    toasty = Toasty.info(getApplicationContext(), message, Toast.LENGTH_SHORT);
                } else {
                    toasty.cancel();
                    toasty = Toasty.info(getApplicationContext(), message, Toast.LENGTH_SHORT);
                }
                break;
            case 4:
                if (null == toasty) {
                    toasty = Toasty.warning(getApplicationContext(), message, Toast.LENGTH_SHORT);
                } else {
                    toasty.cancel();
                    toasty = Toasty.warning(getApplicationContext(), message, Toast.LENGTH_SHORT);
                }
                break;
            case 5:
                if (null == toasty) {
                    toasty = Toasty.normal(getApplicationContext(), message, Toast.LENGTH_SHORT);
                } else {
                    toasty.cancel();
                    toasty = Toasty.normal(getApplicationContext(), message, Toast.LENGTH_SHORT);
                }

                break;
        }
        if (null != toasty) {
            toasty.show();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        View view = this.getCurrentFocus();
        assert view != null && view.getWindowToken() != null;
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        //界面销毁时应清除dialog的状态
        if (loadingPop != null && loadingPop.isShowing()) {
            loadingPop.dismiss();
            if (loadingDialogIv != null)
                loadingDialogIv.stopAnim();
        }
        if (null != popTimeThread) {
            popTimeThread.interrupt();
        }
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
        MyApp.getInstance().removeActivity(this);
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);//友盟基本数据统计
    }

    // 跳转
    public void goActivityForResult(Class<?> descClass, int requestCode) {
        ScreenSwitch.switchActivity(this, descClass, null, requestCode);
    }

    public void goActivityForResult(Class<?> descClass, Bundle bundle, int requestCode) {
        ScreenSwitch.switchActivity(this, descClass, bundle, requestCode);
    }

    public void goActivity(Class<?> descClass, Bundle bundle) {
        goActivityForResult(descClass, bundle, 0);
    }

    public void goActivity(Class<?> descClass) {
        goActivity(descClass, null);
    }

    public void showFragment(int resId, BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (showFragment != null) {
            fragmentTransaction.hide(showFragment);
        }
        Fragment mFragment = fragmentManager.findFragmentByTag(fragment.getClass().getName());
        if (mFragment != null) {
            fragmentTransaction.show(mFragment);
            showFragment = (BaseFragment) mFragment;
        } else {
            fragmentTransaction.add(resId, fragment, fragment.getClass().getName());
            showFragment = fragment;
        }
        if (mStateEnable)
            fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mStateEnable = false;
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        mStateEnable = false;
        super.onStop();
    }
}
