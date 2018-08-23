package com.jzh.wanandroid.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.jzh.wanandroid.utils.GlideUtils;

/**
 * author:jzh
 * desc:
 * Date:2018/08/20 15:59
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class BannerHolderView implements Holder<String> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        GlideUtils.loadImage(context, data, imageView);
    }
}
