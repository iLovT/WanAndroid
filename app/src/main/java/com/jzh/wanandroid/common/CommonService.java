package com.jzh.wanandroid.common;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * author:jzh
 * desc:
 * Date:2018/09/03 17:23
 * Email:jzh970611@163.com
 * Github:https://github.com/iLovT
 */

public class CommonService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }
}
