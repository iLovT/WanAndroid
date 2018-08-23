package com.jzh.wanandroid.ui.splash;

import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import com.jzh.wanandroid.R;
import com.jzh.wanandroid.ui.base.BaseActivity;
import com.jzh.wanandroid.ui.login.LoginActivity;
import com.jzh.wanandroid.ui.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * author:jzh
 * desc:开屏页
 * Date:2018/08/16 14:54
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.ac_splash;
    }

    @Override
    protected void beForSet() {
        super.beForSet();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        hideBottomUIMenu();
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected void initWidget() {
        setHeadVisibility(View.GONE);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                goActivity(MainActivity.class);
                finish();
            }
        };
        timer.schedule(task, 3000);

    }
}
