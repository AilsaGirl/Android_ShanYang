package com.circle.common.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.circle.common.app.BaseApplication;

/**
 * Created by Circle on 2017/5/27 0027.
 */

public class SPCommon {

    private static final String KEY_FIRST_STARTAPP ="first_startapp_versioncode";
    private static final String KEY_OPEN_APP_TIME ="open_app_one_time";
    // SharedPreferences
    private static SharedPreferences getSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getInstance());
    }

    public static String getString(String key, final String defaultValue) {
        return getSharedPrefs().getString(key, defaultValue);
    }

    public static void setString(final String key, final String value) {
        getSharedPrefs().edit().putString(key, value).commit();
    }

    public static boolean getBoolean(final String key, final boolean defaultValue) {
        return getSharedPrefs().getBoolean(key, defaultValue);
    }

    public static void setBoolean(final String key, final boolean value) {
        getSharedPrefs().edit().putBoolean(key, value).commit();
    }

    public static void setInt(final String key, final int value) {
        getSharedPrefs().edit().putInt(key, value).commit();
    }

    public static int getInt(final String key, final int defaultValue) {
        return getSharedPrefs().getInt(key, defaultValue);
    }

    public static void setFloat(final String key, final float value) {
        getSharedPrefs().edit().putFloat(key, value).commit();
    }

    public static float getFloat(final String key, final float defaultValue) {
        return getSharedPrefs().getFloat(key, defaultValue);
    }

    public static void setLong(final String key, final long value) {
        getSharedPrefs().edit().putLong(key, value).commit();
    }

    public static long getLong(final String key, final long defaultValue) {
        return getSharedPrefs().getLong(key, defaultValue);
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        getSharedPrefs().edit().remove(key).commit();
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        getSharedPrefs().edit().clear();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        return getSharedPrefs().contains(key);
    }
    /**
     * 是否是第一次启动app 与当前app版本号比对 不相同则为第一次启动，setFirStartApp保存当前app版本号，启动引导页面，否则进入主页
     * @return
     */
    public static String getFistStartAppVersion(){
        return getString(KEY_FIRST_STARTAPP,"");
    }

    /**
     * 设置当前app版本号
     * @param versionCode 当前app版本号
     */
    public static void setFirStartAppVersion(String versionCode){
        setString(KEY_FIRST_STARTAPP,versionCode);
    }
    /**
     * 记录APP打开时间，
     * @return
     */
    public static String getOpenAppTime(){
        return getString(KEY_OPEN_APP_TIME,"");
    }

    /**
     * 设置当前app版本号
     * @param time 打开app的时间
     */
    public static void setOpenAppTime(String time){
        setString(KEY_OPEN_APP_TIME,time);
    }
}
