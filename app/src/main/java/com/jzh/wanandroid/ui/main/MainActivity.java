package com.jzh.wanandroid.ui.main;

import android.view.KeyEvent;

import com.jzh.wanandroid.MyApp;
import com.jzh.wanandroid.R;
import com.jzh.wanandroid.ui.base.BaseActivity;


/**
 * Author:jzh
 * desc:主界面
 * Date:2018/08/16 11:16
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */
public class MainActivity extends BaseActivity {
    private long exitTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {

    }

    /**
     * 双击退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK && event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                onToastInfo(R.string.try_one_exit);
                exitTime = System.currentTimeMillis();
            } else {
                MyApp.getInstance().exit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
