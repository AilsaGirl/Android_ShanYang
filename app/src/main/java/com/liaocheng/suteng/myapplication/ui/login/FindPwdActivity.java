package com.liaocheng.suteng.myapplication.ui.login;

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
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.ForgetPersenter;
import com.liaocheng.suteng.myapplication.presenter.contract.ForgetmimaContact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPwdActivity extends BaseActivity <ForgetPersenter>implements ForgetmimaContact.View {
    private boolean isclicked = true;
    @BindView(R.id.etphonenumber)
    EditText etphonenumber;
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
    private static final int NTF_SECOND = 0x1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == NTF_SECOND) {
                int i = (int) msg.obj;
                tvGetYZM.setText(i + "秒后重新发送");
                tvGetYZM.setTextColor(getResources().getColor(R.color.white));
                tvGetYZM.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                if (i == 0) {
                    isclicked = true;
                    tvGetYZM.setText("获取验证码");
                    tvGetYZM.setTextColor(getResources().getColor(R.color.white));
                    tvGetYZM.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_findpass;
    }
    @Override
    public void initEventAndData() {
    }
    @Override
    public void showError(int reqCode, String msg) {
        ToastUtil.show(msg+"");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    @OnClick({R.id.tvGetYZM, R.id.tvPPW})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetYZM:
                if(isclicked==true) {
                    String Et_phone = etphonenumber.getText().toString();
                    if (!TextUtils.isEmpty(Et_phone) && isMobile(Et_phone) == true) {
                        isclicked=false;
                        mPresenter.getforgetcode(Et_phone,"1");
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
                    }
                }
                break;
            case R.id.tvPPW:
                String Et_phone = etphonenumber.getText().toString();
                String Et_password = etPwd.getText().toString();
                String Et_yanzhengma = etYZM.getText().toString();
                if(!TextUtils.isEmpty(Et_phone) && isMobile(Et_phone) == true){
                    if(!TextUtils.isEmpty(Et_password)){
                        if(!TextUtils.isEmpty(Et_yanzhengma)){
                            mPresenter.getforgetpassword(Et_phone,Et_password,Et_yanzhengma);
                        }else {
                            ToastUtil.show("请输入验证码");
                        }
                    }else {
                     ToastUtil.show("请填写密码");
                    }
                }else {
                    ToastUtil.show("请填写正确的手机号");
                }

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
        b = m.matches() ;
        return b;
    }
    @Override
    public void setforget(NullBean nullBean) {
        ToastUtil.show("成功了");
    }
    @Override
    public void setforcode(NullBean nullBean) {
        ToastUtil.show("成功了");
    }
}
