package com.circle.common.util;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import com.circle.common.app.BaseApplication;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public final class CrashHandler implements UncaughtExceptionHandler {

    private static CrashHandler mInstance = new CrashHandler();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private UncaughtExceptionHandler mDefaultHandler;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return mInstance;
    }

    public void init(Context context) {
        // 获取系统默认的 UncaughtException 处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        // 设置该 CrashHandler 为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (mDefaultHandler != null && ex == null) {
            mDefaultHandler.uncaughtException(thread, ex);
            return;
        }
        saveCrash(ex);
    }

    /**
     * 保存错误信息到文件中 *
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private void saveCrash(final Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();

        // 机型 + imei + packageName + versionName
        final String crash = "hstype=" + Build.MODEL + "_"+DeviceUtil.getPhoneBrand()
                + "&ver=" + DeviceUtil.getBuildVersion() + "&imei="
                + DeviceUtil.getDeviceId(BaseApplication.getInstance()) + "&exception=[" + format.format(new Date()) + "]" + writer.toString();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        LogManager.LogShow(crash+"");
        FileUtil.writeFile(FileUtil.getDirpath(), "common_crash.txt", crash, true);
//            }
//        }).start();

    }

}