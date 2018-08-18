package com.jzh.wanandroid.ui.base;


import android.annotation.TargetApi;
import android.app.Activity;
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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.listener.OnMultiClickListener;
import com.jzh.wanandroid.utils.CommonUtils;
import com.jzh.wanandroid.utils.NetworkUtils;
import com.jzh.wanandroid.utils.ScreenSwitch;
import com.jzh.wanandroid.utils.toast.Toasty;
import com.jzh.wanandroid.widget.LVCircularSmile;
import com.jzh.wanandroid.widget.ProgressLoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

/**
 * @author Jzh
 *         desc:fragment基类
 *         Date:2018/08/16 11:16
 *         Email:jzh970611@163.com
 *         Github:https://github.com/iLovT
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity baseActivity;
    private Unbinder mUnBinder;
    private View baseView;
    private View view;
    private FrameLayout baseContentView;
    private Activity mActivity;
    private LVCircularSmile loadingDialogIv;
    private Thread popTimeThread;
    public PopupWindow loadingPop;
    protected RelativeLayout headLayout;
    protected Button leftBtn;
    protected TextView titleTv, saveTvLocation;
    private ImageView searchIv;
    public ImageView rightIv;
    private String networkErrorTag = "NETWORK_ERROR_TAG";
    private String networkLoadingTag = "NETWORK_LOADING_TAG";
    private ProgressLoadingDialog mProgressDialog;
    /**
     * 判断是否需要检测权限，防止不停的弹框
     */
    public boolean isNeedCheck = true;
    private static final int PERMISSON_REQUESTCODE = 1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    /**
     * 获取布局文件
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     *
     * @param view view
     */
    protected abstract void initWidget(View view);


    protected boolean isVisible;

    /**
     * fragment懒加载
     */
    protected abstract void lazyLoad();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.baseActivity = activity;
            activity.onFragmentAttached();
        } else {
            this.mActivity = (Activity) context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.title_base, container, false);
        initViews(baseView);
        if (getLayoutId() != 0) {
            view = inflater.inflate(getLayoutId(), container, false);
            baseContentView.addView(view, 0);
        }
        initWidget(view);
        lazyLoad();
        return baseView;
    }

    /**
     * 设置头部是否可见
     *
     * @param visibility visibility
     */
    public void setHeadVisibility(int visibility) {
        if (null != headLayout) {
            headLayout.setVisibility(visibility);
        }
    }

    private void initViews(View baseView) {
        baseContentView = baseView.findViewById(R.id.layout_container);
        headLayout = baseView.findViewById(R.id.layout_head);
        leftBtn = baseView.findViewById(R.id.btn_title_back);
        titleTv = baseView.findViewById(R.id.tv_title_name);
        searchIv = baseView.findViewById(R.id.iv_search);
        rightIv = baseView.findViewById(R.id.iv_right);
        saveTvLocation = baseView.findViewById(R.id.save_tv_locatoin);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 在这里实现Fragment数据的懒加载
     *
     * @param isVisibleToUser Fragment UI对用户是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
        if (loadingPop != null && loadingPop.isShowing()) {
            loadingPop.dismiss();
            if (loadingDialogIv != null) {
                loadingDialogIv.stopAnim();
            }
        }
        if (null != popTimeThread) {
            popTimeThread.interrupt();
        }
    }

    /**
     * 检查权限
     *
     * @param permissions
     * @since 2.5.0
     */
    public void checkPermissions(String... permissions) {
        try {
            if (Build.VERSION.SDK_INT >= 23
                    && getActivity().getApplicationInfo().targetSdkVersion >= 23) {
                List<String> needRequestPermissonList = findDeniedPermissions(permissions);
                if (null != needRequestPermissonList
                        && needRequestPermissonList.size() > 0) {
                    String[] array = needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]);
                    requestPermissions(array, PERMISSON_REQUESTCODE);
                }
            }
        } catch (Throwable e) {
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= 23
                && getActivity().getApplicationInfo().targetSdkVersion >= 23) {
            try {
                for (String perm : permissions) {
                    if (!hasPermission(perm) || shouldShowRequestPermissionRationale(perm)) {
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
     * @param grantResults
     * @return
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

    @Override
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

    /**
     * 显示缺少权限提示信息
     *
     * @since 2.5.0
     */
    public void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
        intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
        startActivity(intent);
        isNeedCheck = true;
    }

    /**
     * 是否拥有所有需要的权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasAllPermissions(String... permissions) {
        for (String permission : permissions) {
            if (hasPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否拥有单个权限
     *
     * @param permission
     * @return
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                getActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * 显示异常信息
     *
     * @param msg 要显示的信息，可以为空，为空则显示默认信息
     */
    @Override
    public void showErrorLayout(String msg) {
        hideErrorLayout();
        View view = View.inflate(getActivity(), R.layout.base_network_error, null);
        TextView tv = (TextView) view.findViewById(R.id.base_error_tv);
        tv.setText(TextUtils.isEmpty(msg) ? getString(R.string.network_error) : msg);
        baseContentView.addView(view);
        view.setTag(networkErrorTag);
        view.setOnClickListener(new OnMultiClickListener() {
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
        if (null != baseContentView) {
            if (null != baseContentView.findViewWithTag(networkErrorTag)) {
                baseContentView.removeView(baseContentView.findViewWithTag(networkErrorTag));
            }
        }
    }

    public View getErrorLayout() {
        return null == baseContentView.findViewWithTag(networkErrorTag) ? null : baseContentView.findViewWithTag(networkErrorTag);
    }

    @Override
    public void showLoading(String msg) {
        View popLoadingView = View.inflate(getContext(), R.layout.enter_loading, null);
        TextView loadingPopText = popLoadingView.findViewById(R.id.tv_dialog_loading_text);
        loadingPopText.setText(TextUtils.isEmpty(msg) ? getResources().getString(R.string.loading) : msg);
        LVCircularSmile ivLoad = popLoadingView.findViewById(R.id.dialog_lv_eat_view);
        ivLoad.startAnim();
        baseContentView.addView(popLoadingView);
        popLoadingView.setTag(networkLoadingTag);
    }

    @Override
    public void hideLoading() {
        if (null != baseContentView) {
            if (null != baseContentView.findViewWithTag(networkLoadingTag)) {
                baseContentView.removeView(baseContentView.findViewWithTag(networkLoadingTag));
            }
        }
    }


    /**
     * 显示progressbar dialog
     *
     * @param msg
     */
    @Override
    public void showProgressLoadingDialog(String msg) {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(getActivity(), msg);
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

    /**
     * 设置标题
     *
     * @param titleId
     */
    public void setTitle(int titleId, boolean isShowLeft, boolean isShowSearch, boolean isShowRight) {
        titleTv.setText(getString(titleId));
        if (isShowLeft) {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != baseActivity) {
                        baseActivity.finish();
                    } else {
                        mActivity.finish();
                    }
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
     * @param title title
     */
    public void setTitle(String title, boolean isShowLeft, boolean isShowSearch, boolean isShowRight) {
        titleTv.setText(title);
        if (isShowLeft) {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != baseActivity) {
                        baseActivity.finish();
                    } else {
                        mActivity.finish();
                    }
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
                    toasty = Toasty.success(getActivity(), message, Toast.LENGTH_SHORT);
                } else {
                    toasty.cancel();
                    toasty = Toasty.success(getActivity(), message, Toast.LENGTH_SHORT);
                }
                break;
            case 2:
                if (null == toasty) {
                    toasty = Toasty.error(getActivity(), message, Toast.LENGTH_SHORT);
                } else {
                    toasty.cancel();
                    toasty = Toasty.error(getActivity(), message, Toast.LENGTH_SHORT);
                }
                break;
            case 3:
                if (null == toasty) {
                    toasty = Toasty.info(getActivity(), message, Toast.LENGTH_SHORT);
                } else {
                    toasty.cancel();
                    toasty = Toasty.info(getActivity(), message, Toast.LENGTH_SHORT);
                }
                break;
            case 4:
                if (null == toasty) {
                    toasty = Toasty.warning(getActivity(), message, Toast.LENGTH_SHORT);
                } else {
                    toasty.cancel();
                    toasty = Toasty.warning(getActivity(), message, Toast.LENGTH_SHORT);
                }
                break;
            case 5:
                if (null == toasty) {
                    toasty = Toasty.normal(getActivity(), message, Toast.LENGTH_SHORT);
                } else {
                    toasty.cancel();
                    toasty = Toasty.normal(getActivity(), message, Toast.LENGTH_SHORT);
                }
                break;
            default:
                break;
        }
        if (null != toasty) {
            toasty.show();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (baseActivity != null) {
            return baseActivity.isNetworkConnected();
        } else {
            return NetworkUtils.isNetworkConnected(mActivity);
        }
    }

    @Override
    public void onDetach() {
        if (baseActivity != null) {
            baseActivity = null;
        }
        if (null != mActivity) {
            mActivity = null;
        }
        super.onDetach();
    }

    @Override
    public void hideKeyboard() {
        if (baseActivity != null) {
            baseActivity.hideKeyboard();
        }
    }


    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (loadingPop != null && loadingPop.isShowing()) {
            loadingPop.dismiss();
            if (loadingDialogIv != null) {
                loadingDialogIv.stopAnim();
            }
        }
        if (null != popTimeThread) {
            popTimeThread.interrupt();
        }
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
        super.onDestroy();
    }

    public interface Callback {

        /**
         * attach
         */
        void onFragmentAttached();

        /**
         * detach
         *
         * @param tag tag
         */
        void onFragmentDetached(String tag);
    }

    public void goActivityForResult(Class<?> descClass, int requestCode) {
        ScreenSwitch.switchActivity(getActivity(), descClass, null, requestCode);
    }

    public void goActivityForResult(Class<?> descClass, Bundle bundle, int requestCode) {
        ScreenSwitch.switchActivity(getActivity(), descClass, bundle, requestCode);
    }

    public void goActivity(Class<?> descClass, Bundle bundle) {
        goActivityForResult(descClass, bundle, 0);
    }

    public void goActivity(Class<?> descClass) {
        goActivity(descClass, null);
    }
}
