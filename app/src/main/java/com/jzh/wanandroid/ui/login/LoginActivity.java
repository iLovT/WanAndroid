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

import com.jzh.wanandroid.R;
import com.jzh.wanandroid.entity.login.LoginResponse;
import com.jzh.wanandroid.ui.base.BaseActivity;
import com.jzh.wanandroid.ui.main.MainActivity;
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
    @Inject
    LoginPresenter<LoginMvpView> mPresenter;
    private boolean isVisible = true;

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
        initListener();
    }

    private void initListener() {
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
                    if (!TextUtils.isEmpty(getPassword())) {
                        ivLogin.setBackgroundResource(R.drawable.btn_selector);
                        ivLogin.setEnabled(true);
                    }
                } else {
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
                    if (!TextUtils.isEmpty(getUsername())) {
                        ivLogin.setBackgroundResource(R.drawable.btn_selector);
                        ivLogin.setEnabled(true);
                    }
                } else {
                    pwdVisible.setVisibility(View.GONE);
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
                if (!TextUtils.isEmpty(getPassword()) && getPassword().length() > 0) {
                    Selection.setSelection(password.getText(), getPassword().length());
                }
                break;
            case R.id.iv_login:
                hideKeyboard();
                if (getUsername().length() < 6 || getPassword().length() < 6) {
                    onToastWarn(R.string.user_password_not_null_six);
                    return;
                }
                showProgressLoadingDialog(getString(R.string.login_loading));
                mPresenter.doLoginCall(getUsername(), getPassword());
                break;
            case R.id.tv_register:
                goActivity(RegisterActivity.class);
                break;
            case R.id.tv_forgetpsd:
                break;
            default:
                break;
        }
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
            onToastWarn(R.string.username_not_null);
            return false;
        }
        if (TextUtils.isEmpty(getPassword())) {
            onToastWarn(R.string.password_not_null);
            return false;
        }
        if (getUsername().length() < 6 || getPassword().length() < 6) {
            onToastWarn(R.string.user_password_not_null_six);
            return false;
        }
        showProgressLoadingDialog(getString(R.string.login_loading));
        mPresenter.doLoginCall(getUsername(), getPassword());
        return true;
    }

    @Override
    public void onSucc(LoginResponse response) {
        onToastSucc(R.string.login_succ);
        goActivity(MainActivity.class);
        finish();
    }

    @Override
    public void onFail(String msg) {
        onToastFail(msg);
    }
}
