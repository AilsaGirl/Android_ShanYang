package com.liaocheng.suteng.myapplication.api;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.circle.common.app.AppManager;
import com.circle.common.app.BaseApplication;
import com.circle.common.util.SPCommon;

import java.io.File;



/**
 * Created by LHP on 2018/5/5.
 */

public class MyApplication extends BaseApplication {
    private static Context context;
    public static int mKeyBoardH = 0;
    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator + "CircleDemo" + File.separator + "Images"
            + File.separator;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    @Override
    public void tokenExpire() {
        SPCommon.setString("token","");
        SPCommon.setString("img","");
        SPCommon.setString("username", "");
        SPCommon.setString("name", "");
        SPCommon.setString("phone", "");
        SPCommon.setString("uid", "");
        SPCommon.setString("sex", "");

        if(SPCommon.getBoolean("tokenExpire", true)){
            SPCommon.setBoolean("tokenExpire", false);
//            Intent intent = new Intent(context, LonginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            AppManager.getAppManager().finishAllActivity();
        }
        super.tokenExpire();

    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }
}
