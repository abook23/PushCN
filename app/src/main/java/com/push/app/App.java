package com.push.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.push.core.PushCore;

import java.util.List;

/**
 * Created by My on 2017/6/7.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化push推送服务
        if (shouldInit()) {
//            PushCore.init(this);
        }
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
