package com.liaocheng.suteng.myapplication.util;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Base64;

import com.alipay.sdk.app.PayTask;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.JieDanActivity;
import com.liaocheng.suteng.myapplication.wxapi.PayResult;
import com.liaocheng.suteng.myapplication.wxapi.WeChatPayUtil;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Util {
    /**
     * 将图片转换成Base64编码的字符串
     * @param path
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try{
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data,Base64.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null !=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }
    /**
     *base64编码字符集转化成图片文件。
     * @param base64Str
     * @param path 文件存储路径
     * @return 是否成功
     */
    public static boolean base64ToFile(String base64Str,String path){
        byte[] data = Base64.decode(base64Str,Base64.DEFAULT);
        for (int i = 0; i < data.length; i++) {
            if(data[i] < 0){
                //调整异常数据
                data[i] += 256;
            }
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(path);
            os.write(data);
            os.flush();
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }

    //  微信APPID
    public static String getWechatAppId() {
        String app_id = "wx4514925cc8a0a8b3";
        return app_id;
    }
    //  QQAPPID
    public static String getQQAppId() {
        String app_id = "1106274109";
        return app_id;
    }
    public static void shareWeChat(Context context, String url, String title, String content, Bitmap bmp) {
        WXWebpageObject localWXWebpageObject = new WXWebpageObject();
        localWXWebpageObject.webpageUrl = url;
        WXMediaMessage localWXMediaMessage = new WXMediaMessage(
                localWXWebpageObject);
        localWXMediaMessage.title = title;//不能太长，否则微信会提示出错。不过博主没验证过具体能输入多长。
        localWXMediaMessage.description = content;
        localWXMediaMessage.thumbData = Util.bmpToByteArray(bmp, true);
        SendMessageToWX.Req localReq = new SendMessageToWX.Req();
        localReq.transaction = System.currentTimeMillis() + "";
        localReq.message = localWXMediaMessage;
        localReq.scene = SendMessageToWX.Req.WXSceneSession;
        IWXAPI api = WXAPIFactory.createWXAPI(context,
                "wx4514925cc8a0a8b3", true);
        api.sendReq(localReq);
    }
    public static void sharePengYouQuan(Context context, String url, String title, String content, Bitmap bmp) {
        WXWebpageObject localWXWebpageObject = new WXWebpageObject();
        localWXWebpageObject.webpageUrl = url;
        WXMediaMessage localWXMediaMessage = new WXMediaMessage(
                localWXWebpageObject);
        localWXMediaMessage.title = title;//不能太长，否则微信会提示出错。不过博主没验证过具体能输入多长。
        localWXMediaMessage.description = content;
        localWXMediaMessage.thumbData = Util.bmpToByteArray(bmp, true);
        SendMessageToWX.Req localReq = new SendMessageToWX.Req();
        localReq.transaction = System.currentTimeMillis() + "";
        localReq.message = localWXMediaMessage;
        localReq.scene = SendMessageToWX.Req.WXSceneTimeline;
        IWXAPI api = WXAPIFactory.createWXAPI(context,
                "wx4514925cc8a0a8b3", true);
        api.sendReq(localReq);
    }
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void Pay(String mType,PayModel mBean, Context context){
        mContext = context;
        if (mType.equals("1")){//支付宝
            OkAliPay(mBean.data+"",context);
        }
        if (mType.equals("2")){//支付宝
            OkWechat(mBean,context);
        }
    }
    private static final int ALI_PAY_OK = 1;//阿里支付
    private static Handler mHanler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case ALI_PAY_OK://阿里支付
                    PayResult payResult = new PayResult((Map<String, String>) message.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        mPresenter.isFirstChargedDealMoney();
                        ToastUtil.show("支付成功！");
                    } else {
                        ToastUtil.show("支付失败！");
                    }
                    ((Activity) mContext).finish();
                    break;
            }
            return false;
        }
    });
    static Context mContext;
    //支付宝 付款
    private static void OkAliPay(final String aliKey, final Context mContext) {

        if (TextUtils.isEmpty(aliKey)) {
            ToastUtil.show("支付方式有误！");
            ((Activity) mContext).finish();
            return;
        }
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(((Activity) mContext));
                Map<String, String> result = alipay.payV2(aliKey, true);
                Message msg = new Message();
                msg.what = ALI_PAY_OK;
                msg.obj = result;
                mHanler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
    static WeChatPayUtil mWechatUtil;
    //微信付款
    private static void OkWechat(PayModel model, final Context mContext) {
        if (mWechatUtil == null) {
            mWechatUtil = new WeChatPayUtil(mContext);
        }
        boolean payOk = mWechatUtil.WeChatPay(model);
        if (payOk) {
//            ToastUtil.show("支付成功！");
            ((Activity) mContext).finish();
        }else{
//            ToastUtil.show("支付失败！");
        }


    }
    static int pos =0;
    static String uri;
    public static synchronized void playSound(Context context, String type) {
        if (type.equals("1")) {
            uri = "android.resource://" + context.getPackageName() + "/" + R.raw.yangjiao;
        }else if (type.equals("2")) {
            uri = "android.resource://" + context.getPackageName() + "/" + R.raw.yipeiqiang;
        }else if (type.equals("3")) {
            uri = "android.resource://" + context.getPackageName() + "/" + R.raw.dingdanwancheng;
        }else if (type.equals("4")) {
            uri = "android.resource://" + context.getPackageName() + "/" + R.raw.chexiaodingdan;
        }else if (type.equals("5")) {
            uri = "android.resource://" + context.getPackageName() + "/" + R.raw.shangjiachexiao;
        }else if (type.equals("6")) {
            uri = "android.resource://" + context.getPackageName() + "/" + R.raw.zhuandandingdan;
        }else if (type.equals("7")) {
            uri = "android.resource://" + context.getPackageName() + "/" + R.raw.zhiding;
        }
        Uri no = Uri.parse(uri);
        Ringtone mRingtone = RingtoneManager.getRingtone(context.getApplicationContext(), no);
        mRingtone.play();
//        String type;
        while( mRingtone.isPlaying())
        {
            if( !mRingtone.isPlaying())
            {
                break;
            }
        }

    }
    static List<String> msgList = new ArrayList<>();
    static int  notifyId =0;
    static String notifyTag;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void showNotifictionIcons(Context context, String textMessage, String type) {
        msgList.add(textMessage);
        notifyId++;
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (textMessage != null) {
            Intent xintent1 = new Intent(context, JieDanActivity.class);
//            xintent1.putExtra("id", textMessage.getOrder_id() + "");
            xintent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            NotificationCompat.Builder notifyBuilder =
                    new NotificationCompat.Builder(context)
//                            .setContentTitle( "" )
                            .setContentText("你有一条新订单" + "")
                            .setSmallIcon(R.mipmap.app_logo)
                            // 点击消失
                            .setAutoCancel(true)
                            // 设置该通知优先级
                            .setPriority(Notification.PRIORITY_MAX)
                            .setTicker("三羊跑腿")
                            // 通知首次出现在通知栏，带上升动画效果的
                            .setWhen(System.currentTimeMillis())
                            // 通知产生的时间，会在通知信息里显示
                            // 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                            .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND);
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(context, 0, xintent1, PendingIntent.FLAG_UPDATE_CURRENT);
            notifyBuilder.setContentIntent(resultPendingIntent);
            nm.notify(notifyId, notifyBuilder.build());
            playSound(context, "1");
        }
    }
    public static int packageCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }
    public static String packageName(Context context) {
        PackageManager manager = context.getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return name;
    }

}
