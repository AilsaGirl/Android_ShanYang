package com.liaocheng.suteng.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;
import com.liaocheng.suteng.myapplication.presenter.BangDingPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.BangDingContent;
import com.liaocheng.suteng.myapplication.ui.MainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BangDingActivity extends BaseActivity<BangDingPresenter> implements BangDingContent.View {
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.loginUserNameLayout)
    LinearLayout loginUserNameLayout;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etYZM)
    EditText etYZM;
    @BindView(R.id.tvGetYZM)
    TextView tvGetYZM;
    @BindView(R.id.loginPwd)
    RelativeLayout loginPwd;
    @BindView(R.id.tvPPW)
    TextView tvPPW;
    @BindView(R.id.tvFindPW)
    TextView tvFindPW;
    @BindView(R.id.linZhuCe)
    LinearLayout linZhuCe;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bangding;
    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void showError(int reqCode, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            ToastUtil.show(CommonUtil.splitMsg(msg));
        }
    }

    @Override
    public void setCode() {
        ToastUtil.show("发送成功！");
    }

    @Override
    public void userBindThird(ThirdLoginModel mBean) {
        ToastUtil.show("登录成功！");
        SPCommon.setString("token",mBean.token);
        SPCommon.setString("userId",mBean.userId);
        SPCommon.setString("phone",etName.getText().toString());
     Intent   intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    private static final int NTF_SECOND = 0x1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == NTF_SECOND) {
                int i = (int) msg.obj;
                tvGetYZM.setText(i + "秒后重新发送");
                if (i == 0) {
                    isclicked = true;
                    tvGetYZM.setText("获取验证码");
                }
            }
        }
    };
    Intent intent;
    boolean isclicked = true;
    @OnClick({R.id.tvGetYZM, R.id.tvFindPW, R.id.linZhuCe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetYZM:
                if (TextUtils.isEmpty(etName.getText().toString())){
                    ToastUtil.show("手机号不能为空");
                    return;
                }else {
                    if (isclicked == true) {
                        String Etname = etName.getText().toString();
                        if (!TextUtils.isEmpty(Etname) && isMobile(Etname) == true) {
                            isclicked = false;
                            mPresenter.getCode(Etname, "");
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
            case R.id.tvFindPW:
                intent = new Intent();
                intent.setClass(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.linZhuCe:
                if (TextUtils.isEmpty(etName.getText().toString())){
                    ToastUtil.show("手机号不能为空");
                    return;
                }
                if (!isMobile(etName.getText().toString())){
                    ToastUtil.show("手机号不正确");
                    return;
                }
                if (TextUtils.isEmpty(etYZM.getText().toString())){
                    ToastUtil.show("验证码不能为空");
                    return;
                }
                mPresenter.userBindThird(SPCommon.getString("ltype",""),SPCommon.getString("code",""),etName.getText().toString(),SPCommon.getString("threename",""),SPCommon.getString("threeimg",""),etYZM.getText().toString());
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
}
