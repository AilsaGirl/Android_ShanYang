package com.circle.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.circle.common.R;
import com.circle.common.app.BaseApplication;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Circle on 2017/4/25 0025.
 */

public class CommonUtil {



    private static long lastGiftTime = 0L;

    private static long lastClickTime = 0L;
    private static String mImageName = "";

    public static String getString(String str){
        if(str == null)
            return "";
        return str;
    }


    /**
     * @return 防止多次点击
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    /**
     * @return 防止多次点击
     */
    public synchronized static boolean isFastClickCustomMill(int maxMill) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < maxMill) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * @return 礼物连刷    *
     */
    public static boolean isBrushClick(int i) {
        long time = System.currentTimeMillis();
        if (i == 0) {
            lastGiftTime = time;
            return true;
        }
        if (time - lastGiftTime < 2000) {
            lastGiftTime = time;
            return true;
        }

        return false;
    }


    /**
     * 获取网络类型
     */
    public static void blurBgPic(final Context context, final ImageView view, final String url, int defResId) {
        if (context == null || view == null) {
            return;
        }

        if (TextUtils.isEmpty(url)) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), defResId);
            int scaleRatio = 50;//可以设置模糊度哦
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / scaleRatio, bitmap.getHeight() / scaleRatio, false);
            view.setImageBitmap(FastBlurUtil.doBlur(scaledBitmap, 8, true));
        } else {
            Picasso.with(context)
                    .load(url)
                    .centerCrop()
                    .resize(200, 200)
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                            if (bitmap == null) {
                                return;
                            }
//                            int scaleRatio = 50;//可以设置模糊度哦
//                            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap,  bitmap.getWidth() / scaleRatio, bitmap.getHeight() / scaleRatio, false);

                            view.setImageBitmap(FastBlurUtil.doBlur(bitmap, 8, false));

                        }

                        @Override
                        public void onBitmapFailed(Drawable drawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable drawable) {

                        }
                    });
        }
    }

    public static int getTotalDuration(AnimationDrawable animationDrawable) {
        if (animationDrawable == null) return 0;
        int iDuration = 0;
        for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
            iDuration += animationDrawable.getDuration(i);
        }

        return iDuration;
    }



    public   static void tryRecycleAnimationDrawable(AnimationDrawable animationDrawables) {
        if (animationDrawables != null) {
            animationDrawables.stop();
            for (int i = 0; i < animationDrawables.getNumberOfFrames(); i++) {
                Drawable frame = animationDrawables.getFrame(i);
                if (frame instanceof BitmapDrawable) {
                    ((BitmapDrawable) frame).getBitmap().recycle();
                }
                frame.setCallback(null);
            }
            animationDrawables.setCallback(null);

        }
    }
    /**
     * 去除 空格/回车/制表符/换行符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
//			  空格\s、回车\n、换行符\r、制表符\t
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            boolean bl = m.matches();

            dest = m.replaceAll("");
        }
        return dest;
    }
    /**
     * 判断String 是否为空
     */
    public static boolean isEmpty(String str) {
        str = replaceBlank(str);//去除空格
        if (str == null || str.length() == 0 || ("null".equals(str))) {
            return true;
        } else {
            return false;
        }

    }
    //剪切  :
    public static String splitMsg(String msg) {
        if (!CommonUtil.isEmpty(msg)){
            String[] strs=msg.split(":");
//        ToastUtil.show(strs[1]);
            if (strs.length>1){
                return strs[1];
            }else {
                if (NetWorkUtils.isNetConnected(BaseApplication.getInstance())){
                    return "网络访问错误，请稍后再试";
                }else{
                    return "网络不可用,请检查你的网络";
                }
            }
        }
        return "网络请求失败";
    }
    //剪切  :
    public static String splitMsg(String msg,ImageView imageView,int id) {
        if (!CommonUtil.isEmpty(msg)){
            if (!NetWorkUtils.isNetConnected(BaseApplication.getContext())) {
                imageView.setImageResource(R.mipmap.no_net);
                return (BaseApplication.getContext().getString(R.string.no_net));
            }
            String[] strs=msg.split(":");
//        ToastUtil.show(strs[1]);
            if (strs.length>1){
                imageView.setImageResource(id);
                return strs[1];
            }else {
                if (NetWorkUtils.isNetConnected(BaseApplication.getInstance())){
                    imageView.setImageResource(id);
                    return "网络访问错误，请稍后再试";
                }
            }
        }
        imageView.setImageResource(id);
        return "网络请求失败";
    }
    /**
     * 判断list是否为空
     */
    public static <T> boolean isEmptyList(List<T> list) {
        if (null == list) {
            return true;
        } else if (("").equals(list)) {
            return true;
        } else if (("null").equals((list + ("")).trim())) {
            return true;
        } else if ((list + "").trim().equals("[]")) {
            return true;
        } else {
            return false;
        }

    }
    public static String appendHeadUrl() {
        String BaseUrl = "?imageView2/1/w/100/h/100";
        return BaseUrl;
    }
    // 截取图片
    public static String CutImageUrl(int width, int height) {
        String url = "?imageView2/1/w/" + width + "/h/" + height;
        return url;
    }
    /**
     * 获取版本名
     */
    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
    /**
     * 获取应用名称
     */
    public static String getApplicationName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * 获取版本号
     */
    public static String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
