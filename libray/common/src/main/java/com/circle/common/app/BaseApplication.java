package com.circle.common.app;

import android.app.Application;
import android.content.Context;

import com.circle.common.util.CrashHandler;

/**
 * Created by Circle on 2017/4/2 0002.
 */

public class BaseApplication extends Application {
    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
//        CrashHandler.getInstance().init(this);
    }

    public static Context getContext() {
        return baseApplication;
    }

    public static BaseApplication getInstance() {
        return baseApplication;
    }

    /**
     * token过期
     */
    public void tokenExpire() {
    }
}
