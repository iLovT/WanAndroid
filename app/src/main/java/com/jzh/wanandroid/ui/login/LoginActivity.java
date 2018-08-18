package com.jzh.wanandroid.ui.login;

import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.entity.login.LoginResponse;
import com.jzh.wanandroid.ui.base.BaseActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author:jzh
 * desc:登录页
 * Date:2018/08/16 15:27
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class LoginActivity extends BaseActivity implements LoginMvpView, TextView.OnEditorActionListener {
    @BindView(R.id.edit_account)
    MaterialEditText username;
    @BindView(R.id.edit_psd)
    MaterialEditText password;
    @BindView(R.id.pwd_visible)
    ImageView pwdVisible;
    @BindView(R.id.iv_login)
    Button ivLogin;
    private long exitTime = 0;
    @Inject
    LoginPresenter<LoginMvpView> mPresenter;
    private boolean isVisible = true, isUserNotNull, isPassNotNull;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_login;
    }

    @Override
    protected void initWidget() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setHeadVisibility(View.GONE);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && s.length() > 0) {
                    isUserNotNull = true;
                    if (isPassNotNull) {
                        ivLogin.setBackgroundResource(R.drawable.btn_selector);
                        ivLogin.setEnabled(true);
                    }
                } else {
                    isUserNotNull = false;
                    ivLogin.setBackgroundResource(R.drawable.btn_not_selector);
                    ivLogin.setEnabled(false);
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && s.length() > 0) {
                    pwdVisible.setVisibility(View.VISIBLE);
                    isPassNotNull = true;
                    if (isUserNotNull) {
                        ivLogin.setBackgroundResource(R.drawable.btn_selector);
                        ivLogin.setEnabled(true);
                    }
                } else {
                    pwdVisible.setVisibility(View.GONE);
                    isPassNotNull = false;
                    ivLogin.setBackgroundResource(R.drawable.btn_not_selector);
                    ivLogin.setEnabled(false);
                }

            }
        });
        password.setOnEditorActionListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @OnClick({R.id.pwd_visible, R.id.iv_login, R.id.tv_register, R.id.tv_forgetpsd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pwd_visible:
                if (isVisible) {
                    isVisible = false;
                    pwdVisible.setBackgroundResource(R.drawable.password_visible);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    isVisible = true;
                    pwdVisible.setBackgroundResource(R.drawable.password_invisible);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                if (!TextUtils.isEmpty(password.getText().toString()) && password.getText().length() > 0) {
                    Selection.setSelection(password.getText(), password.getText().length());
                }
                break;
            case R.id.iv_login:
                hideKeyboard();
                if (getUsername().length() < 6 || getPassword().length() < 6) {
                    onToastWarn("用户名和密码长度不能少于六位");
                    return;
                }
                showProgressLoadingDialog(getString(R.string.login_loading));
                mPresenter.doLoginCall(getUsername(), getPassword());
                break;
            case R.id.tv_register:
                break;
            case R.id.tv_forgetpsd:
                break;
            default:
                break;
        }
    }

    /**
     * 双击退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK && event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                onToastInfo("再按一次返回键退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                MyApp.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private String getUsername() {
        return username.getText().toString().trim();
    }

    private String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        hideKeyboard();
        if (TextUtils.isEmpty(getUsername())) {
            onToastWarn("用户名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(getPassword())) {
            onToastWarn("密码不能为空");
            return false;
        }
        if (getUsername().length() < 6 || getPassword().length() < 6) {
            onToastWarn("用户名和密码长度不能少于六位");
            return false;
        }
        showProgressLoadingDialog(getString(R.string.login_loading));
        mPresenter.doLoginCall(getUsername(), getPassword());
        return true;
    }

    @Override
    public void onSucc(LoginResponse response) {

    }

    @Override
    public void onFail(String msg) {
        onToastFail(msg);
    }
}
