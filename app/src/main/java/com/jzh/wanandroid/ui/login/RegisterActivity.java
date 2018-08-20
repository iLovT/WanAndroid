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
import com.jzh.wanandroid.ui.base.BaseActivity;
import com.jzh.wanandroid.ui.main.MainActivity;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author:jzh
 * desc:注册(＾－＾)
 * Date:2018/08/20 09:25
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class RegisterActivity extends BaseActivity implements TextView.OnEditorActionListener, RegisterMvpView {
    @BindView(R.id.edit_account)
    MaterialEditText username;
    @BindView(R.id.edit_psd)
    MaterialEditText password;
    @BindView(R.id.pwd_visible)
    ImageView pwdVisible;
    @BindView(R.id.edit_psd_sure)
    MaterialEditText password_again;
    @BindView(R.id.pwd_visible2)
    ImageView pwdVisible2;
    @BindView(R.id.iv_register)
    Button iv_register;
    private boolean isVisible = true, isVisible2 = true;
    @Inject
    RegisterPresenter<RegisterMvpView> mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_register;
    }

    @Override
    protected void initWidget() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);
        setHeadVisibility(View.GONE);
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
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
                    if (!TextUtils.isEmpty(getPassWord()) && !TextUtils.isEmpty(getAgainPassWord())) {
                        iv_register.setBackgroundResource(R.drawable.btn_selector);
                        iv_register.setEnabled(true);
                    }
                } else {
                    iv_register.setBackgroundResource(R.drawable.btn_not_selector);
                    iv_register.setEnabled(false);
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
                    if (!TextUtils.isEmpty(getUserName()) && !TextUtils.isEmpty(getAgainPassWord())) {
                        iv_register.setBackgroundResource(R.drawable.btn_selector);
                        iv_register.setEnabled(true);
                    }
                } else {
                    pwdVisible.setVisibility(View.GONE);
                    iv_register.setBackgroundResource(R.drawable.btn_not_selector);
                    iv_register.setEnabled(false);
                }

            }
        });
        password_again.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && s.length() > 0) {
                    pwdVisible2.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(getUserName()) && !TextUtils.isEmpty(getPassWord())) {
                        iv_register.setBackgroundResource(R.drawable.btn_selector);
                        iv_register.setEnabled(true);
                    }
                } else {
                    pwdVisible2.setVisibility(View.GONE);
                    iv_register.setBackgroundResource(R.drawable.btn_not_selector);
                    iv_register.setEnabled(false);
                }

            }
        });
        password_again.setOnEditorActionListener(this);
    }

    private String getUserName() {
        return username.getText().toString().trim();
    }

    private String getPassWord() {
        return password.getText().toString().trim();
    }

    private String getAgainPassWord() {
        return password_again.getText().toString().trim();
    }

    @OnClick({R.id.pwd_visible, R.id.pwd_visible2, R.id.iv_register, R.id.tv_register})
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
                if (!TextUtils.isEmpty(getPassWord()) && getPassWord().length() > 0) {
                    Selection.setSelection(password.getText(), getPassWord().length());
                }
                break;
            case R.id.pwd_visible2:
                if (isVisible2) {
                    isVisible2 = false;
                    pwdVisible2.setBackgroundResource(R.drawable.password_visible);
                    password_again.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    isVisible2 = true;
                    pwdVisible2.setBackgroundResource(R.drawable.password_invisible);
                    password_again.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                if (!TextUtils.isEmpty(getAgainPassWord()) && getAgainPassWord().length() > 0) {
                    Selection.setSelection(password_again.getText(), getAgainPassWord().length());
                }
                break;
            case R.id.iv_register:
                hideKeyboard();
                if (getUserName().length() < 6 || getPassWord().length() < 6) {
                    onToastWarn(R.string.user_password_not_null_six);
                    return;
                }
                if (!getPassWord().equals(getAgainPassWord())) {
                    onToastWarn(R.string.disaccord);
                    return;
                }
                showProgressLoadingDialog(getString(R.string.register_loading));
                mPresenter.doRegisterCall(getUserName(), getPassWord(), getAgainPassWord());
                break;
            case R.id.tv_register:
                goActivity(LoginActivity.class);
                finish();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        hideKeyboard();
        if (TextUtils.isEmpty(getUserName())) {
            onToastWarn(R.string.username_not_null);
            return false;
        }
        if (TextUtils.isEmpty(getPassWord())) {
            onToastWarn(R.string.password_not_null);
            return false;
        }
        if (TextUtils.isEmpty(getAgainPassWord())) {
            onToastWarn(R.string.password_not_null);
            return false;
        }
        if (getUserName().length() < 6 || getPassWord().length() < 6) {
            onToastWarn(R.string.user_password_not_null_six);
            return false;
        }
        if (!getPassWord().equals(getAgainPassWord())) {
            onToastWarn(R.string.disaccord);
            return false;
        }
        showProgressLoadingDialog(getString(R.string.register_loading));
        mPresenter.doRegisterCall(getUserName(), getPassWord(), getAgainPassWord());
        return true;
    }

    @Override
    public void onSucc() {
        goActivity(MainActivity.class);
        finish();
    }
}
