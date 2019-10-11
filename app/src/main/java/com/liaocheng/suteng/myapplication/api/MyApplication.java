package com.liaocheng.suteng.myapplication.api;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;

import com.circle.common.app.AppManager;
import com.circle.common.app.BaseApplication;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.ui.MainActivity;
import com.liaocheng.suteng.myapplication.ui.login.LoginActivity;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;
import com.umeng.socialize.PlatformConfig;

import java.io.File;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


/**
 * Created by LHP on 2018/5/5.
 */

public class MyApplication extends BaseApplication {
    private static Context context;
    public static int mKeyBoardH = 0;
    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator + "CircleDemo" + File.separator + "Images"
            + File.separator;

    public static String wechatAppid = "wx4514925cc8a0a8b3";
    public static String AppSecret = "dc5211f70a8604bddd988c20391876a6";
    //获取微信TOKEN
    public static String getWXToken = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static String grant_type = "authorization_code";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
        PlatformConfig.setQQZone("1106274109","H3EbrcokRkP0AL7d");
        PlatformConfig.setWeixin("wx4514925cc8a0a8b3", "dc5211f70a8604bddd988c20391876a6");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);     		// 初始化 JPush


        Bugly.init(getApplicationContext(),"71c85ec391", false);
//        Bugly.init(getApplicationContext(),"46cff10a79", false);
        /**** Beta高级设置*****/
        /**
         * true表示app启动自动初始化升级模块；
         * false不好自动初始化
         * 开发者如果担心sdk初始化影响app启动速度，可以设置为false
         * 在后面某个时刻手动调用
         */
        Beta.autoInit = true;
        /**
         * true表示初始化时自动检查升级
         * false表示不会自动检查升级，需要手动调用Beta.checkUpgrade()方法
         */
        Beta.autoCheckUpgrade = true;
        /**
         * 设置升级周期为60s（默认检查周期为0s），60s内SDK不重复向后天请求策略
         */
        Beta.initDelay = 1 * 1000;
        /**
         * 设置通知栏大图标，largeIconId为项目中的图片资源；
         */
        Beta.largeIconId = R.mipmap.ic_launcher;
        /**
         * 设置状态栏小图标，smallIconId为项目中的图片资源id;
         */
        Beta.smallIconId = R.mipmap.ic_launcher;
        /**
         * 设置更新弹窗默认展示的banner，defaultBannerId为项目中的图片资源Id;
         * 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading“;
         */
        Beta.defaultBannerId = R.mipmap.ic_launcher;
        /**
         * 设置sd卡的Download为更新资源保存目录;
         * 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
         */
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次JPushInterface显示;
         */
        Beta.showInterruptedStrategy = false;

        /**
         * 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗;
         * 不设置会默认所有activity都可以显示弹窗;
         */
        Beta.canShowUpgradeActs.add(MainActivity.class);
        /**
         *  设置自定义升级对话框UI布局
         *  注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
         *  标题：beta_title，如：android:tag="beta_title"
         *  升级信息：beta_upgrade_info  如： android:tag="beta_upgrade_info"
         *  更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
         *  取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
         *  确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
         *  详见layout/upgrade_dialog.xml
         */
        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;
        BuglyStrategy s = new BuglyStrategy();
        s.setAppChannel("升级");
//        CrashReport.initCrashReport(getApplicationContext(), "98653d2ad5", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder build = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(build.build());
        }
    }

    @Override
    public void tokenExpire() {
        SPCommon.setString("token", "");
        SPCommon.setString("phone", "");
        SPCommon.setString("userId","");
        SPCommon.setString("tuisong", "");
        SPCommon.setString("zhifumima", "");
        SPCommon.setString("auth", "");
        SPCommon.setString("username", "");
        SPCommon.setString("tel","");
        SPCommon.setString("baozhengjin", "");
        SPCommon.setString("baoxian","");
        SPCommon.setBoolean("iswork", false);
        JPushInterface.deleteAlias(this, 100);
        JPushInterface.cleanTags(this, 100);
        JPushInterface.setAlias(this, //上下文对象
                "", //别名
                new TagAliasCallback() {//回调接口,i=0表示成功,其它设置失败
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        Log.d("alias","111"+i);
                    }
                });
        AppManager.getAppManager().finishAllActivity();
        if(SPCommon.getBoolean("tokenExpire", true)){
            AppManager.getAppManager().finishAllActivity();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
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
