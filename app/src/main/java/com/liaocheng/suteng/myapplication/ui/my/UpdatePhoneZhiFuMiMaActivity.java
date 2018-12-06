package com.liaocheng.suteng.myapplication.ui.my;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.UpdatePhoneMiMaPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.UpdatePhoneMiMaContact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePhoneZhiFuMiMaActivity extends BaseActivity<UpdatePhoneMiMaPresenter> implements UpdatePhoneMiMaContact.View{
    @BindView(R.id.etTel)
    EditText etTel;
    @BindView(R.id.etYanZhengMa)
    EditText etYanZhengMa;
    @BindView(R.id.tvSendYZM)
    TextView tvSendYZM;
    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.tvOk)
    TextView tvOk;
    @BindView(R.id.toolBar)
    MyToolBar toolBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_updatephonezhifu;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("修改支付密码").setBackFinish();
        etTel.setText(SPCommon.getString("phone","")+"");
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
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
    private boolean isclicked = true;
    @OnClick({R.id.tvSendYZM, R.id.tvOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSendYZM:
                if (isclicked == true) {
                    String Etname = etTel.getText().toString();
                    if (!TextUtils.isEmpty(Etname) && isMobile(Etname) == true) {
                        isclicked = false;
                        mPresenter.getcode(Etname);
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
            case R.id.tvOk:
                String Etname = etTel.getText().toString();
                String Et_password = etPass.getText().toString();
                String Et_Yzm = etYanZhengMa.getText().toString();
                if(!TextUtils.isEmpty(Etname) && isMobile(Etname) == true){
                    if(!TextUtils.isEmpty(Et_password)){
                        if(!TextUtils.isEmpty(Et_Yzm)){
                                mPresenter.UpdateMiMa(Et_password,Et_Yzm);
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
    private static final int NTF_SECOND = 0x1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == NTF_SECOND) {
                int i = (int) msg.obj;
                tvSendYZM.setText(i + "秒后重新发送");
//                tvGetYZM.setTextColor(getResources().getColor(R.color.white));
//                tvGetYZM.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                if (i == 0) {
                    isclicked = true;
                    tvSendYZM.setText("获取验证码");
//                    tvGetYZM.setTextColor(getResources().getColor(R.color.white));
//                    tvGetYZM.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }
        }
    };
    @Override
    public void UpdateMiMa(NullBean nullBean) {
        ToastUtil.show("修改成功");
        finish();
    }

    @Override
    public void setcode(NullBean nullBean) {
        ToastUtil.show("验证码发送成功");
    }
}
