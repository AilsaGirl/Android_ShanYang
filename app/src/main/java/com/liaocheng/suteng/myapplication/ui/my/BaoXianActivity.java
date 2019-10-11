package com.liaocheng.suteng.myapplication.ui.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.BaoXianPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.BaoXianContent;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.ApliyDialog;
import com.liaocheng.suteng.myapplication.view.PassWordDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaoXianActivity extends BaseActivity<BaoXianPresenter> implements BaoXianContent.View {
    @BindView(R.id.myToolBar)
    MyToolBar myToolBar;
    @BindView(R.id.btnFu)
    Button btnFu;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_baoxian;
    }

    @Override
    public void initEventAndData() {
        myToolBar.setTitleText("保险详情").setBackFinish();
        mPresenter.findAreaInsurance(SPCommon.getString("city", "聊城"), SPCommon.getString("qu", "东昌府"));//付款
        if (SPCommon.getString("insurance", "").equals("1") || SPCommon.getString("insuranceProtocol", "").equals("1")) {
            btnFu.setVisibility(View.GONE);
            mPresenter.IdentityInfo("");
        }
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

    PassWordDialog passWordDialog;
    String mPayType;
    String num = "";

    @OnClick(R.id.btnFu)
    public void onViewClicked() {
//        pay(num);
        if (SPCommon.getString("insuranceProtocol", "").equals("0") && SPCommon.getString("baoxian", "0.00").equals("0.00")) {
            pay(num);
        } else {
            builder = new AlertDialog.Builder(mContext);
//            builder.setTitle("软件更新");
            builder.setMessage("亲，你有保险金协议扣款未结清，总计" + SPCommon.getString("baoxian", "0.00") + "元，是否进行补齐");
            builder.setCancelable(false);

            // 更新
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pay(SPCommon.getString("baoxian", "0.00"));
                    dialog.dismiss();
                }
            });

            builder.setCancelable(true);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.show();
        }
//            mPresenter.user_insurancePay(mPayType,SPCommon.getString("city","聊城"),SPCommon.getString("qu","东昌府"));//付款
//

//

    }

    AlertDialog.Builder builder;

    private void pay(final String num) {
        ApliyDialog dialog = new ApliyDialog(BaoXianActivity.this, R.style
                .transparentFrameWindowStyle, num + "",
                new ApliyDialog.SelectDialogCancelListener() {
                    @Override
                    public void onCancelClick(String id) {

                        mPayType = id;
                        if (mPayType.equals("1") || mPayType.equals("2")) {
                            mPresenter.user_insurancePay(mPayType, SPCommon.getString("city", "聊城"), SPCommon.getString("qu", "东昌府"));//付款
                        } else {
                            passWordDialog = new PassWordDialog(BaoXianActivity.this);
                            passWordDialog.show();
                            passWordDialog.setHintText(num + "");
                            passWordDialog.setMoneyNum(num + "");
                            passWordDialog.setError_tishi("请输入支付密码");
                            passWordDialog.setClick(new PassWordDialog.OnPayClickListener() {
                                @Override
                                public void onSetPass(String text) {
                                    mPresenter.checkSecondPassword(text);//付款

                                }

                                @Override
                                public void onSetPwd() {
                                    passWordDialog.cancel();
                                    Intent intent = new Intent(mContext, UpdatePhoneZhiFuMiMaActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }

                    }
                });
        if (!this.isFinishing()) {
            dialog.show();
            dialog.setGones();
        }

    }

    @Override
    public void user_insurancePay(PayModel payModel) {
        if (mPayType.equals("1") || mPayType.equals("2")) {
            Util.Pay(mPayType, payModel, mContext);
        } else {
            ToastUtil.show("支付成功");
            finish();
        }
    }

    @Override
    public void checkSecondPassword() {
        mPresenter.user_insurancePay(mPayType, SPCommon.getString("city", "聊城"), SPCommon.getString("qu", "东昌府"));//付款
    }

    @Override
    public void findAreaInsurance(BaoXianModel baoXianModel) {
        num = baoXianModel.insurance;
    }

    @Override
    public void IdentityInfo(AuthBean authBean) {
        tvStartTime.setVisibility(View.VISIBLE);
        tvEndTime.setVisibility(View.VISIBLE);
        tvStartTime.setText("交付时间："+authBean.insurancePayTime);
        tvEndTime.setText("到期时间："+authBean.insuranceEndTime);
    }
}
