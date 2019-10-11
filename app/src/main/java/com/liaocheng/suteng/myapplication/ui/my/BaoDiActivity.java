package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.BaoDiPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.BaoDiContent;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.ApliyDialog;
import com.liaocheng.suteng.myapplication.view.PassWordDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaoDiActivity extends BaseActivity<BaoDiPresenter> implements BaoDiContent.View {
    @BindView(R.id.myToolBar)
    MyToolBar myToolBar;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.tvTime3)
    TextView tvTime3;
    @BindView(R.id.rl3)
    RelativeLayout rl3;
    @BindView(R.id.tvTime4)
    TextView tvTime4;
    @BindView(R.id.rl4)
    RelativeLayout rl4;

    @Override
    public int getLayoutId() {
        return R.layout.activity_baodi;
    }

    @Override
    public void initEventAndData() {
        myToolBar.setBackFinish().setTitleText("保底费");
        mPresenter.IdentityInfo("");
    }

    @Override
    public void user_memberPay(PayModel payModel) {
        if (mPayType.equals("1") || mPayType.equals("2")) {
            Util.Pay(mPayType, payModel, mContext);
        } else {
            ToastUtil.show("支付成功");
            finish();
        }
    }

    @Override
    public void checkSecondPassword() {
        mPresenter.user_memberPay(mPayType,type);//付款
    }
    String member = "0";
    @Override
    public void IdentityInfo(AuthBean authBean) {
         member = authBean.member;
        if (member.equals("0")){
            rl1.setVisibility(View.VISIBLE);
            rl2.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);
        }
        if (member.equals("1")){
            rl1.setVisibility(View.GONE);
            rl2.setVisibility(View.VISIBLE);
            rl3.setVisibility(View.VISIBLE);
            rl4.setVisibility(View.GONE);
            tvTime3.setText("到期时间："+authBean.memberEndTime+"");
        }
        if (member.equals("2")){
            rl1.setVisibility(View.VISIBLE);
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            rl4.setVisibility(View.VISIBLE);
            tvTime4.setText("到期时间："+authBean.memberEndTime+"");
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
String type = "0";
    @OnClick({R.id.rl1, R.id.rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl1:
                if (member.equals("0")){
                    type = "1";
                    pay(500+"");
                }else {
                    if (member.equals("1")){
                        ToastUtil.show("当前还未到期，请勿重复支付");
                    }
                    if (member.equals("2")){
                        ToastUtil.show("你已支付700，请到期后再购买");
                    }
                }
                break;
            case R.id.rl2:
                if (member.equals("0")){
                    type = "2";
                    pay(700+"");
                }else {
                    if (member.equals("1")){
                        ToastUtil.show("你已支付500，请到期后再购买");
                    }
                    if (member.equals("2")){
                        ToastUtil.show("当前还未到期，请勿重复支付");
                    }
                }
                break;
        }
    }

    PassWordDialog passWordDialog;
    String mPayType;
    String num = "";
    private void pay(final String num) {
        ApliyDialog dialog = new ApliyDialog(BaoDiActivity.this, R.style
                .transparentFrameWindowStyle, num + "",
                new ApliyDialog.SelectDialogCancelListener() {
                    @Override
                    public void onCancelClick(String id) {

                        mPayType = id;
                        if (mPayType.equals("1") || mPayType.equals("2")) {
                            mPresenter.user_memberPay(mPayType,type);//付款
                        } else {
                            passWordDialog = new PassWordDialog(BaoDiActivity.this);
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
}
