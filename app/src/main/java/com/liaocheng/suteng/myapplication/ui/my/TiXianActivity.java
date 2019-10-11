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
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.TiXianPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.TiXianContact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TiXianActivity extends BaseActivity<TiXianPresenter> implements TiXianContact.View{
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.etNum)
    EditText etNum;
    @BindView(R.id.tvZhiFuBaoHao)
    TextView tvZhiFuBaoHao;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.etMa)
    EditText etMa;
    @BindView(R.id.etMiMa)
    EditText etMiMa;
    @BindView(R.id.tvTiJiao)
    TextView tvTiJiao;
    @BindView(R.id.etZhiFuBaoHao)
    EditText etZhiFuBaoHao;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvGetYZM)
    TextView tvGetYZM;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tixian;
    }

    String mYuE = "";
    String mName = "";
    String mPhone = "";


    @Override
    public void initEventAndData() {
        toolBar.setBackFinish().setTitleText("提现");
        mYuE = getIntent().getStringExtra("num");
        mPhone = getIntent().getStringExtra("tel");
        tvNum.setText("可提现金额 "+mYuE + "元，请选择提现方式");
        tvPhone.setText("手机号：" + mPhone + "");

    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
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
    @OnClick({R.id.tvGetYZM, R.id.tvTiJiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetYZM:
                if (isclicked == true) {
                    String Etname = tvPhone.getText().toString();
                    if (!TextUtils.isEmpty(mPhone) && isMobile(mPhone) == true) {
                        isclicked = false;
                        mPresenter.getcode(mPhone, "15");
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
            case R.id.tvTiJiao:
                String num = etNum.getText().toString();
                String name = etName.getText().toString();
                String zhifubaohao = etZhiFuBaoHao.getText().toString();
                String mima = etMiMa.getText().toString();
                String yanzhengma = etMa.getText().toString();
                if (TextUtils.isEmpty(num)){
                    ToastUtil.show("提现金额不能为空");
                    return;
                }
                if (Double.parseDouble(num)<1.0){
                    ToastUtil.show("提现金额最低为1元");
                    return;
                }
                if (Double.parseDouble(num)>Double.parseDouble(mYuE)){
                    ToastUtil.show("提现金额大于可提现金额");
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    ToastUtil.show("真实姓名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(zhifubaohao)){
                    ToastUtil.show("支付宝号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(mima)){
                    ToastUtil.show("密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(yanzhengma)){
                    ToastUtil.show("验证码不能为空");
                    return;
                }
                mPresenter.user_withdraw(mima,zhifubaohao,name,num,yanzhengma);
                break;
        }
    }

    @Override
    public void setcode(NullBean RegistCodeBean) {

        ToastUtil.show("验证码发送成功");
    }

    @Override
    public void setTiXian(NullBean nullBean) {
        ToastUtil.show("提现成功,等待处理");
        finish();
    }
}
