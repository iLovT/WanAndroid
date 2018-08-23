package com.jzh.wanandroid.widget;

import android.graphics.Color;

/**
 * author:jzh
 * desc:zan and collection
 * Date:2018/08/22 10:11
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */


public interface IGoodView {
    int DISTANCE = 60;   // 默认移动距离

    int FROM_Y_DELTA = 0; // Y轴移动起始偏移量

    int TO_Y_DELTA = DISTANCE; // Y轴移动最终偏移量

    float FROM_ALPHA = 1.0f;    // 起始时透明度

    float TO_ALPHA = 0.0f;  // 结束时透明度

    int DURATION = 800; // 动画时长

    String TEXT = ""; // 默认文本

    int TEXT_SIZE = 16; // 默认文本字体大小

    int TEXT_COLOR = Color.BLACK;   // 默认文本字体颜色
}
