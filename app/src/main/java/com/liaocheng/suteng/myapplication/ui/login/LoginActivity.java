package com.liaocheng.suteng.myapplication.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
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
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.api.MyApplication;
import com.liaocheng.suteng.myapplication.model.LoginBean;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
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
    private static final int NTF_SECOND = 0x1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == NTF_SECOND) {
                int i = (int) msg.obj;
                tvGetYZM.setText(i + "秒后重新发送");
//                tvGetYZM.setTextColor(getResources().getColor(R.color.white));
//                tvGetYZM.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                if (i == 0) {
                    isclicked = true;
                    tvGetYZM.setText("获取验证码");
//                    tvGetYZM.setTextColor(getResources().getColor(R.color.white));
//                    tvGetYZM.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }
        }
    };
    int type = 1;
    Intent intent;
    String phone;
    String password;
    boolean isclicked = true;
    @OnClick({R.id.tvDuanXin, R.id.tvMiMa, R.id.tvGetYZM, R.id.loginBtn, R.id.tvPPW, R.id.tvFindPW, R.id.ivWeiXin, R.id.ivQQ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDuanXin:
                password = "";
                type = 1;
                linZhuCe.setVisibility(View.GONE);
                tvGetYZM.setVisibility(View.VISIBLE);
                tvDuanXin.setTextColor(0xffE03D91);
                tvMiMa.setTextColor(0xff333333);
                etPwd.setText("");
                etPwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_CLASS_NUMBER);
                etPwd.setHint("请输入验证码");
                break;
            case R.id.tvMiMa:
                password="";
                type = 2;
                linZhuCe.setVisibility(View.VISIBLE);
                tvGetYZM.setVisibility(View.GONE);
                tvDuanXin.setTextColor(0xff333333);
                tvMiMa.setTextColor(0xffE03D91);
                etPwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                etPwd.setText("");
                etPwd.setHint("请输入密码");
                break;
            case R.id.tvGetYZM:
                if (TextUtils.isEmpty(etName.getText().toString())){
                    ToastUtil.show("手机号不能为空");
                    return;
                }else {

                    if (isclicked == true) {
                        String Etname = etName.getText().toString();
                        if (!TextUtils.isEmpty(Etname) && isMobile(Etname) == true) {
                            isclicked = false;
                            mPresenter.getforgetcode(Etname, "");
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    for (int i = 60; i >= 0; i--) {
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();

                                        }
                                        mHandler.sendMessage(mHandler.obtainMessage(NTF_SECOND, i));
                                    }
                                }
                            }).start();
                        } else {
                            ToastUtil.show("请填写正确的手机号");
                            return;
                        }

                    }
                }
                break;
            case R.id.loginBtn:
//                mPresenter.login("","");
//                mPresenter.logins("","");
                if (TextUtils.isEmpty(etName.getText().toString())){
                    ToastUtil.show("手机号不能为空");
                    return;
                }else {
                    if (isMobile(etName.getText().toString())){
                        phone = etName.getText().toString();
                    }else {
                        ToastUtil.show("手机号不正确");
                    }
                }
                if (TextUtils.isEmpty(etPwd.getText().toString())){
                    if (type==1){
                        ToastUtil.show("验证码不能为空");
                    }else {
                        ToastUtil.show("密码不能为空");
                    }

                    return;
                }else {
                    password = etPwd.getText().toString();
                }
                if (type==1){
                    mPresenter.login(phone,password);
                }else {
                    mPresenter.logins(phone,password);

            }
                break;
            case R.id.tvPPW://注册
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
    //判断是不是手机号
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,5,6,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    @Override
    public void loginSuccess(NullBean loginBean) {

    }

    @Override
    public void loginSuc(LoginBean loginBean) {
        ToastUtil.show("登录成功！");
        SPCommon.setString("token",loginBean.token);
        SPCommon.setString("userId",loginBean.userId);
        SPCommon.setString("phone",phone);
        intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setforcode(NullBean nullBean) {
        ToastUtil.show("发送验证码成功！");
    }

    public boolean isWxInstall(Context context) {
        return wxApi.isWXAppInstalled();
    }
}
