package com.liaocheng.suteng.myapplication.wxapi;

import android.content.Context;

import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.util.Util;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by LHP on 2017/6/15.
 * 微信支付工具类
 */

public class WeChatPayUtil {

    private IWXAPI mWechatApi;

    public WeChatPayUtil(Context context) {
        mWechatApi = WXAPIFactory.createWXAPI(context, null);
        mWechatApi.registerApp(Util.getWechatAppId());
    }

    // 检查设备是否安装微信
    private boolean CheckWeChat() {
        boolean ishas = mWechatApi.openWXApp();
        return ishas;
    }

    // 检查当前版本微信是否支持支付
    private boolean PaySupported() {
        boolean isPaySupported = mWechatApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        return isPaySupported;
    }

    //  微信支付
    public boolean WeChatPay(PayModel model) {
        if (model == null) {
            ToastUtil.show("支付方式有误！");
            return false;
        }
        boolean hasresult = CheckWeChat();
        if (!hasresult) {
            ToastUtil.show("请安装微信");
            return false;
        }

        boolean ispaysupport = PaySupported();
        if (!ispaysupport) {   // 不支持支付
            ToastUtil.show("当前微信版本不支持支付，请更新版本");
            return false;
        }
        // 支持支付
        PayReq req = new PayReq();
        req.appId = model.appid;
        req.partnerId = model.partnerid;
        req.prepayId = model.prepayid;
        req.nonceStr = model.nonce_str;
        req.timeStamp = model.timestamp;
        req.packageValue = "Sign=WXPay";
        req.sign = model.sign;
        req.extData = "app data"; // optional
        mWechatApi.sendReq(req);
        return true;
    }
}
