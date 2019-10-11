package com.liaocheng.suteng.myapplication.presenter;


import android.app.Activity;
import android.text.TextUtils;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.response.CommonRes;
import com.circle.common.util.DeviceUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.LoginBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;
import com.liaocheng.suteng.myapplication.model.event.WeChatEvent;
import com.liaocheng.suteng.myapplication.presenter.contract.LoginContact;
import com.liaocheng.suteng.myapplication.util.Util;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by wei on 2018/1/11 0028.
 * 各种繁杂过程    接口请求等
 */

public class LoginPresenter extends RxPresenter<LoginContact.View> implements LoginContact.Presenter, IUiListener {

    //短信登录
    @Override
    public void login(String username, String password) {

        addSubscribe(Api.createTBService().user_login_byIdentifyCode(username, password)
                .compose(RxUtil.<BaseResponse<LoginBean>>rxSchedulerHelper())
                .compose(RxUtil.<LoginBean>handleResult())
                .subscribeWith(new CommonSubscriber<LoginBean>(mContext, true) {
                    @Override
                    protected void _onNext(LoginBean commonRes) {
                        if (commonRes != null) {
                            mView.loginSuc(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );

    }

    //用户名密码登录
    @Override
    public void logins(String username, String password) {
        addSubscribe(Api.createTBService().user_login(username, password)
                .compose(RxUtil.<BaseResponse<LoginBean>>rxSchedulerHelper())
                .compose(RxUtil.<LoginBean>handleResult())
                .subscribeWith(new CommonSubscriber<LoginBean>(mContext, true) {
                    @Override
                    protected void _onNext(LoginBean commonRes) {
                        if (commonRes != null) {
                            mView.loginSuc(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    @Override
    public void getforgetcode(String number, String code) {
        addSubscribe(Api.createTBService().forgetcode(number, "20")
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {
                        if (commonRes != null) {
                            mView.setforcode(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    private Tencent mTencent;
    private IWXAPI mWXApi;
    private final String WECHAT_LOGIN_TYPE = "2";
    public final String QQ_LOGIN_TYPE = "1";

    @Override
    public void loginEvent() {
        addRxBusSubscribe(WeChatEvent.class, new Consumer<WeChatEvent>() {
            @Override
            public void accept(@NonNull WeChatEvent weChatEvent) throws Exception {
                if (weChatEvent != null && !TextUtils.isEmpty(weChatEvent.getmWeChatCode())) {
                    getWeChatOrTencentLogin(WECHAT_LOGIN_TYPE, weChatEvent.getmWeChatCode());
                } else {
                    ToastUtil.show("登录error");
                }
            }
        });
    }

    @Override
    public void qqLogin() {
        if (mTencent == null) {
            mTencent = Tencent.createInstance(Util.getQQAppId(), mContext);
        }
        mTencent.logout(mContext);
        mTencent.login((Activity) mContext, "all", this);
    }

    @Override
    public void weChatLogin() {
        if (mWXApi == null) {
            mWXApi = WXAPIFactory.createWXAPI(mContext, Util.getWechatAppId(), true);
        }
        if (!mWXApi.isWXAppInstalled()) {
            ToastUtil.show("请安装微信！");
        }
        mWXApi.registerApp(Util.getWechatAppId());
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        mWXApi.sendReq(req);
    }

    /**
     * uuid	Y String	手机设备识别号
     * type	Y		String	登录方式1微信2QQ
     * code	N	''	String微信code（type为1时传）
     * openid N	''	String	QQ对应openid （type为2时传）
     * access_token N	''	String QQtoken（type为2时传）
     * flag Y	''	String	手机类型填ios或/android
     */
    @Override
    public void getWeChatOrTencentLogin(String type, String code) {
        SPCommon.setString("code", code);
        SPCommon.setString("ltype", type);
        addSubscribe(Api.createTBService().weChatOrQQLogin(type, code)
                .compose(RxUtil.<BaseResponse<ThirdLoginModel>>rxSchedulerHelper())
                .compose(RxUtil.<ThirdLoginModel>handleResult())
                .subscribeWith(new CommonSubscriber<ThirdLoginModel>(mContext, true) {
                    @Override
                    protected void _onNext(ThirdLoginModel userInfoCommonRes) {
                        if (userInfoCommonRes != null) {
                            mView.setWeChatOrTencentLogin(userInfoCommonRes);
                            return;
                        } else {
                            mView.showError(0, "");
                        }
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showError(1, "");
                    }
                }));
    }

    //QQ登录三方监听
    @Override
    public void onComplete(Object o) {
        if (o == null) {
            ToastUtil.show("登录失败");
            return;
        }
        try {
            JSONObject jsonResponse = (JSONObject) o;
            if (jsonResponse.length() == 0) {
                ToastUtil.show("登录失败");
                return;
            }
            String openid = null;
            String access_token = null;
            String expires = null;
            int ret = jsonResponse.getInt("ret");
            if (ret == 0) {
                if (!TextUtils.isEmpty(jsonResponse.getString("openid"))) {
                    openid = jsonResponse.getString("openid");
                }
                if (!TextUtils.isEmpty(jsonResponse.getString("access_token"))) {
                    access_token = jsonResponse.getString("access_token");
                }
                if (!TextUtils.isEmpty(jsonResponse.getString("expires_in"))) {
                    expires = jsonResponse.getString("expires_in");
                }
                mTencent.setOpenId(openid);
                mTencent.setAccessToken(access_token, expires);
                UserInfo userInfo = new UserInfo(mContext, mTencent.getQQToken());
                userInfo.getUserInfo(new IUiListener() {//判断用户细信息
                    @Override
                    public void onComplete(Object o) {
                        if (o == null) {
                            return;
                        }
                        try {
                            JSONObject jsonObject = (JSONObject) o;
                            int ret = jsonObject.getInt("ret");
                            if (ret == 100030) {
                                //权限不够，需要增量授权
                                Runnable runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        mTencent.reAuth((Activity) mContext, "all", new IUiListener() {
                                            @Override
                                            public void onComplete(Object o) {
                                                getUserInfo();
//                                                getWeChatOrTencentLogin(QQ_LOGIN_TYPE, mTencent.getAccessToken());
                                            }

                                            @Override
                                            public void onError(UiError uiError) {

                                            }

                                            @Override
                                            public void onCancel() {

                                            }
                                        });
                                    }
                                };
                                ((Activity) mContext).runOnUiThread(runnable);
                            } else {
                                getUserInfo();
//                                getWeChatOrTencentLogin(QQ_LOGIN_TYPE, mTencent.getAccessToken());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(UiError uiError) {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
//                if (!TextUtils.isEmpty(openid)&&!TextUtils.isEmpty(access_token)) {
//                    mPresenter.getWeChatOrTencentLogin(DeviceUtil.getDeviceId(mContext),
//                            mPresenter.QQ_LOGIN_TYPE, "", openid, access_token, mPresenter.ANDROID_FLAG);
//                }else{
//                    ToastUtil.show("数据有误，请稍后再试！");
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo() {
        QQToken token = mTencent.getQQToken();
        UserInfo   mInfo = new UserInfo(mContext, token);
        mInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object object) {
                JSONObject jb = (JSONObject) object;
                try {
                 String   name = jb.getString("nickname");
                 String   figureurl = jb.getString("figureurl_qq_2");  //头像图片的url
                    /*Log.i("imgUrl",figureurl.toString()+"");*/
                    SPCommon.setString("threename",name);
                    SPCommon.setString("threeimg",figureurl);
                    SPCommon.setString("img",figureurl);
                    getWeChatOrTencentLogin(QQ_LOGIN_TYPE, mTencent.getAccessToken());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                ToastUtil.show("获取用户信息失败");
            }

            @Override
            public void onCancel() {
            }
        });
    }




    @Override
    public void onError(UiError uiError) {
        ToastUtil.show(uiError.errorMessage);
    }

    @Override
    public void onCancel() {
        ToastUtil.show("取消登录");
    }

    public void onDestroy() {
        if (mTencent != null) {
            mTencent = null;
        }
        if (mWXApi != null) {
            mWXApi = null;
        }
    }


}
