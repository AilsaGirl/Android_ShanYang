package com.liaocheng.suteng.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.RegistPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.RegisContact;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegistPresenter> implements RegisContact.View {

    @BindView(R.id.tv_registxy)
    TextView tvRegistxy;
    private boolean isclicked = true;

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
    @BindView(R.id.cb_xy)
    CheckBox cbXy;
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
    private TextView tv_registxy;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
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
        cbXy.setChecked(true);
        initview();
    }

    private void initview() {
        tv_registxy = (TextView) findViewById(R.id.tv_registxy);
        tv_registxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @OnClick({R.id.tvGetYZM, R.id.cb_xy, R.id.tvFindPW,R.id.tvPPW})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetYZM:
                if (isclicked == true) {
                    String Etname = etName.getText().toString();
                    if (!TextUtils.isEmpty(Etname) && isMobile(Etname) == true) {
                        isclicked = false;
                        mPresenter.getcode(Etname, "17");
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
                break;
            case R.id.cb_xy:

                break;
            case R.id.tvFindPW:
                break;

            case R.id.tvPPW:
                String Etname = etName.getText().toString();
                String Et_password = etPwd.getText().toString();
                String Et_Yzm = etYZM.getText().toString();
                if(!TextUtils.isEmpty(Etname) && isMobile(Etname) == true){
                    if(!TextUtils.isEmpty(Et_password)){
                        if(!TextUtils.isEmpty(Et_Yzm)){
                            if(cbXy.isChecked()){
                                mPresenter.getuserZhuce(Etname,Et_password,"",Et_Yzm);
                            }else {
                                ToastUtil.show("请仔细阅读协议并勾选");
                            }
                        }else {
                            ToastUtil.show("请输入验证码");
                        }
                    }else {
                     ToastUtil.show("请输入密码");
                    }
                }else {
                    ToastUtil.show("请输入正确的手机号");
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
        b = m.matches();
        return b;
    }

    @Override
    public void setcode(NullBean RegistCodeBean) {
        ToastUtil.show("成功了");
    }

    @Override
    public void setuserregister(NullBean nullBean) {
        ToastUtil.show("成功了");
    }

}
