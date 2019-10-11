/*
 * 官网地站:http://www.mob.com
 * 技术支持QQ: 4006852216
 * 官方微信:ShareSDK   （如果发布新版本的话，我们将会第一时间通过微信将版本更新内容推送给您。如果使用过程中有任何问题，也可以通过微信与我们取得联系，我们将会在24小时内给予回复）
 *
 * Copyright (c) 2013年 mob.com. All rights reserved.
 */

package com.liaocheng.suteng.myapplication.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.circle.common.base.CompositeUtil;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxBus;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseRespons;
import com.circle.common.response.BaseResponse;
import com.circle.common.response.CommonRes;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.api.MyApplication;
import com.liaocheng.suteng.myapplication.model.LoginBean;
import com.liaocheng.suteng.myapplication.model.WeiXin;
import com.liaocheng.suteng.myapplication.model.WeiXinToken;
import com.liaocheng.suteng.myapplication.model.event.WeChatEvent;
import com.liaocheng.suteng.myapplication.ui.login.BangDingActivity;
import com.liaocheng.suteng.myapplication.util.Util;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * 微信客户端回调activity示例
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI mWeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mWeApi == null) {
            mWeApi = WXAPIFactory.createWXAPI(this, Util.getWechatAppId(), false);
        }
        mWeApi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp == null) {
            ToastUtil.show("WeChat Error");
            finish();
            return;
        }
//        if (baseResp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
//            finish();
//            return;
//        }
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK://成功
                if (baseResp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {//微信登录
                    SendAuth.Resp resp = (SendAuth.Resp) baseResp;
                    String code = resp.code;
                    if (!TextUtils.isEmpty(resp.code)) {
                        getAccessToken(resp.code);

                    }
                } else {
                    EventBus.getDefault().post(new WeChatEvent(true));//微信分享成功
                }
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (baseResp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
                    EventBus.getDefault().post(new WeChatEvent(false));//微信分享失败
                }
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                if (baseResp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
                    EventBus.getDefault().post(new WeChatEvent(false));//微信分享失败
                }
                finish();
                break;
            default:
                finish();
                break;
        }
    }
    CompositeUtil compositeUtil = new CompositeUtil();
    public void getAccessToken(final String code){
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
//                "appid="+MyApplication.WECHAT_APPID+"&secret="+Constant.WECHAT_SECRET+
//                "&code="+code+"&grant_type=authorization_code";
        Api.toSubscriber(Api.toScheculer(Api.createWeiXinService().weChatToken(MyApplication.wechatAppid,MyApplication.AppSecret,code,"authorization_code"))
                .compose(RxUtil.<BaseRespons>handleResults()),
                new CommonSubscriber<BaseRespons>(this) {
            @Override
            protected void _onNext(BaseRespons res) {
                if (res!=null) {
                    getWeiXinUserInfo(res,res.unionid);
                }
            }

            @Override
            protected void _onError(String message) {
                ToastUtil.show(message);
            }
        });

//        compositeUtil.addSubscribe(Api.createWeiXinService().weChatToken(MyApplication.wechatAppid,MyApplication.AppSecret,code,"authorization_code")
//                .compose(RxUtil.<BaseRespons>rxSchedulerHelper())
//                .compose(RxUtil.<BaseRespons>handleResults()).
//                subscribeWith(new CommonSubscriber<BaseRespons>(this) {
//                    @Override
//                    protected void _onNext(BaseRespons commonRes) {
//                        if (commonRes == null) return;
//                        getWeiXinUserInfo(commonRes,code);
//                    }
//
//                    @Override
//                    protected void _onError(String message) {
//                        ToastUtil.show(message);
//                        Intent intent = new Intent();
//                        intent.setClass(WXEntryActivity.this,BangDingActivity.class);
//                        startActivity(intent);
//                    }
//                }));
    }
    public void getWeiXinUserInfo(BaseRespons weiXinToken, final String code){
//        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+
//                weiXinToken.access_token+"&openid="+weiXinToken.openid;
        Api.toSubscriber(Api.toScheculer(Api.createWeiXinService().weChatInfo(weiXinToken.access_token,weiXinToken.openid))
                        .compose(RxUtil.<BaseRespons>handleResults()),
                new CommonSubscriber<BaseRespons>(this) {
                    @Override
                    protected void _onNext(BaseRespons res) {
                        if (res!=null) {
                            SPCommon.setString("threename",res.nickname);
                            SPCommon.setString("threeimg",res.headimgurl);
                            RxBus.getDefault().post(new WeChatEvent(code));
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.show(message);
                        finish();
                    }
                });
//        compositeUtil.addSubscribe(Api.createWeiXinService().weChatInfo(weiXinToken.access_token,weiXinToken.openid)
//                .compose(RxUtil.<BaseResponse<WeiXinToken>>rxSchedulerHelper())
//                .compose(RxUtil.<WeiXinToken>handleResult()).
//                        subscribeWith(new CommonSubscriber<WeiXinToken>(this) {
//                            @Override
//                            protected void _onNext(WeiXinToken commonRes) {
//                                if (commonRes == null) return;
//                                SPCommon.setString("threename",commonRes.nickname);
//                                SPCommon.setString("threeimg",commonRes.headimgurl);
//                                RxBus.getDefault().post(new WeChatEvent(code));
//                            }
//
//                            @Override
//                            protected void _onError(String message) {
//                                ToastUtil.show(message);
//                                Intent intent = new Intent();
//                                intent.setClass(WXEntryActivity.this,BangDingActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        }));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWeApi != null) {
            mWeApi = null;
        }
    }
}

