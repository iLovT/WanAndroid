package com.jzh.wanandroid.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzh.wanandroid.R;


/**
 * Author:jzh
 * desc:
 * Date:2018/08/16 11:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class ProgressLoadingDialog extends Dialog {

    public ProgressLoadingDialog(Context context) {
        super(context, R.style.Custom_Progress);

    }

    public ProgressLoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    /**
     * 当窗口焦点改变时调用
     */
    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = findViewById(R.id.spinnerImageView);
        // 获取ImageView上的动画背景
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        // 开始动画
        spinner.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_loading_dialog);
        // 按返回键是否取消
        setCancelable(true);
        // 设置居中
        getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 设置背景层透明度
        lp.dimAmount = 0.2f;
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(false);
        msgTextView = findViewById(R.id.message);
    }


    /**
     * 给Dialog设置提示信息
     *
     * @param message msg
     */
    public void setMessage(CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            findViewById(R.id.message).setVisibility(View.GONE);
        } else {
            findViewById(R.id.message).setVisibility(View.VISIBLE);
            TextView txt = findViewById(R.id.message);
            txt.setText(message);
            txt.invalidate();
        }
    }

    private  TextView msgTextView;


    public void setTextMessage(String message) {

        if (msgTextView != null) {
            msgTextView.setText(message);
        }
    }
}
