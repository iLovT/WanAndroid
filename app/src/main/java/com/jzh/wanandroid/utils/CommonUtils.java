package com.jzh.wanandroid.utils;

import android.content.Context;
import android.text.TextUtils;

import com.jzh.wanandroid.R;
import com.jzh.wanandroid.widget.ProgressLoadingDialog;


/**
 * Author:jzh
 * desc:
 * Date:2018/08/16 11:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class CommonUtils {

    private CommonUtils() {
    }

    public static ProgressLoadingDialog showLoadingDialog(Context context, String message) {
        ProgressLoadingDialog progressDialog = new ProgressLoadingDialog(context);
        progressDialog.show();
        if (TextUtils.isEmpty(message)) {
            progressDialog.setMessage(context.getString(R.string.loading));
        } else {
            progressDialog.setMessage(message);
        }
        return progressDialog;
    }
}
