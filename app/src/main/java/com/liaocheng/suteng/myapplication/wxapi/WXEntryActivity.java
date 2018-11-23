package com.liaocheng.suteng.myapplication.wxapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.api.MyApplication;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        api = WXAPIFactory.createWXAPI(this, MyApplication.wechatAppid, false);
        api.registerApp(MyApplication.wechatAppid);
        if (api.handleIntent(getIntent(), this)) {

        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        int errorCode = baseResp.errCode;
        if (baseResp.getType() == 1) {
            String code = null;
            String url = null;
            String country = null;
            String state = null;

            switch (errorCode) {
                case BaseResp.ErrCode.ERR_OK:
                    //用户同意
                    code = ((SendAuth.Resp) baseResp).code;

                    ToastUtil.show("66666666666");

                    getToken(code, MyApplication.getWXToken);
                    break;

                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    //用户拒绝
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    //用户取消
                    finish();
                    break;
                default:
                    break;
            }
        }
        if (baseResp.getType() == 2) {
            System.out.println(baseResp.getType());
            String code = null;
            switch (errorCode) {
                case BaseResp.ErrCode.ERR_OK:
                    ToastUtil.show("66666666666sssssssss");
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    //用户拒绝
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    //用户取消
                    finish();
                    break;
                default:
                    break;
            }
        }

    }

    private void getToken(String code, String getWXToken) {
    }
}
