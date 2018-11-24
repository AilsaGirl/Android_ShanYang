package com.liaocheng.suteng.myapplication.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.api.MyApplication;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.LoginPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.LoginContact;
import com.liaocheng.suteng.myapplication.ui.MainActivity;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContact.View {

    private IWXAPI wxApi;
    private UMShareAPI umShareAPI = null;
    private SHARE_MEDIA platform = null;

    @BindView(R.id.tvDuanXin)
    TextView tvDuanXin;
    @BindView(R.id.tvMiMa)
    TextView tvMiMa;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.loginUserNameLayout)
    LinearLayout loginUserNameLayout;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.tvGetYZM)
    TextView tvGetYZM;
    @BindView(R.id.loginPwd)
    RelativeLayout loginPwd;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.tvPPW)
    TextView tvPPW;
    @BindView(R.id.tvFindPW)
    TextView tvFindPW;
    @BindView(R.id.linZhuCe)
    LinearLayout linZhuCe;
    @BindView(R.id.ivWeiXin)
    ImageView ivWeiXin;
    @BindView(R.id.ivQQ)
    ImageView ivQQ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initEventAndData() {
        wxApi = WXAPIFactory.createWXAPI(this, MyApplication.wechatAppid, false);
        wxApi.registerApp(MyApplication.wechatAppid);

    }

    @Override
    public void showError(int reqCode, String msg) {
        if (!TextUtils.isEmpty(msg)){
            ToastUtil.show( CommonUtil.splitMsg(msg));
        }
    }
    Intent intent;
    @OnClick({R.id.tvDuanXin, R.id.tvMiMa, R.id.tvGetYZM, R.id.loginBtn, R.id.tvPPW, R.id.tvFindPW, R.id.ivWeiXin, R.id.ivQQ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDuanXin:
                linZhuCe.setVisibility(View.GONE);
                tvGetYZM.setVisibility(View.VISIBLE);
                tvDuanXin.setTextColor(0xffE03D91);
                tvMiMa.setTextColor(0xff333333);
                etPwd.setHint("请输入验证码");
                break;
            case R.id.tvMiMa:
                linZhuCe.setVisibility(View.VISIBLE);
                tvGetYZM.setVisibility(View.GONE);
                tvDuanXin.setTextColor(0xff333333);
                tvMiMa.setTextColor(0xffE03D91);
                etPwd.setHint("请输入密码");
                break;
            case R.id.tvGetYZM:
                break;
            case R.id.loginBtn:
//                mPresenter.login("","");
//                mPresenter.logins("","");
                intent = new Intent();
                intent.setClass(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tvPPW:
                intent = new Intent();
                intent.setClass(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tvFindPW:
                intent = new Intent();
                intent.setClass(this,FindPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.ivWeiXin:
                if (!isWxInstall(this)) {
                    ToastUtil.show("您还未安装客户端");
                    return;
                }
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                wxApi.sendReq(req);
                break;
            case R.id.ivQQ:
                umShareAPI = UMShareAPI.get(this);
                platform = SHARE_MEDIA.QQ;
                final UMAuthListener umAuthListener = new UMAuthListener() {
                    String openid;
                    String screen_name;
                    String profile_image_url;
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        ToastUtil.show("取消授权");

                    }
                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        ToastUtil.show("授权失败");
                    }
                };
                umShareAPI.getPlatformInfo(this, platform, umAuthListener);
                break;
        }
    }

    @Override
    public void loginSuccess(NullBean loginBean) {
        ToastUtil.show("成功了");
    }

    @Override
    public void loginSuc(NullBean loginBean) {
        ToastUtil.show("哈哈哈哈哈哈");
    }
    public boolean isWxInstall(Context context) {
        return wxApi.isWXAppInstalled();
    }
}
