package com.jzh.wanandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jzh.wanandroid.R;

/**
 * author:jzh
 * desc:glide
 * Date:2018/08/20 15:14
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class GlideUtils {
    public static void loadImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)    //加载成功之前占位图
                .error(R.drawable.image_load_error)    //加载错误之后的错误图
                .centerCrop();
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
