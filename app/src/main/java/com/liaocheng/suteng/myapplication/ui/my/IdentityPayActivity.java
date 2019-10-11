package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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
import com.liaocheng.suteng.myapplication.presenter.IdentityPayPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.IdentityPayContract;
import com.liaocheng.suteng.myapplication.ui.MainActivity;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.ApliyDialog;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;
import com.liaocheng.suteng.myapplication.view.PassWordDialog;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdentityPayActivity extends BaseActivity<IdentityPayPresenter> implements IdentityPayContract.View {
    @BindView(R.id.myToolBar)
    MyToolBar myToolBar;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.tvMinZhu)
    TextView tvMinZhu;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvId)
    TextView tvId;
    @BindView(R.id.tvJiGuan)
    TextView tvJiGuan;
    @BindView(R.id.tvQiXian)
    TextView tvQiXian;
    @BindView(R.id.cb1)
    CheckBox cb1;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.cb2)
    CheckBox cb2;
    @BindView(R.id.cb3)
    CheckBox cb3;
    @BindView(R.id.cb4)
    CheckBox cb4;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.cb5)
    CheckBox cb5;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.btnOk)
    Button btnOk;
    @BindView(R.id.cb6)
    CheckBox cb6;
    @BindView(R.id.cb7)
    CheckBox cb7;
    @BindView(R.id.cb8)
    CheckBox cb8;
    @BindView(R.id.linBaoZhengJin)
    LinearLayout linBaoZhengJin;
    @BindView(R.id.linBaoXianJin)
    LinearLayout linBaoXianJin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_identitypay;
    }

    @Override
    public void initEventAndData() {
        myToolBar.setBackFinish().setTitleText("支付");
        mPresenter.IdentityInfo(SPCommon.getString("token", ""));
        mPresenter.findAreaNeedFee(city, area);
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Override
    public void IdentityInfoSucss(AuthBean mBeaan) {
        tvTel.setText("手机号：" + mBeaan.phone + "");
        tvSex.setText("性别：" + mBeaan.sex);
        tvName.setText("姓名：" + mBeaan.name);
        tvAddress.setText("住址：" + mBeaan.address);
        tvMinZhu.setText("民族：" + mBeaan.ethnic);
        tvJiGuan.setText("签发机关：" + mBeaan.organ);
        tvQiXian.setText("有效期限：" + mBeaan.validity);
        tvId.setText("身份证号：" + mBeaan.idCode);
        mPresenter.findAreaNeedFee(city, area);
    }

    @Override
    public void user_authPay(PayModel payModel) {
        if (mPayType.equals("1") || mPayType.equals("2")) {
            Util.Pay(mPayType, payModel, mContext);
        } else {
            ToastUtil.show("支付成功");
            finish();
        }
    }

    @Override
    public void checkSecondPassword() {
        mPresenter.user_authPay(SPCommon.getString("token", ""), mPayType, SPCommon.getString("city", "聊城"), SPCommon.getString("qu", "东昌府"), foregift, foregift_protocol, insurance, insurance_protocol, isNeedEquip, memberFee);//付款
    }

    PassWordDialog passWordDialog;
    String mPayType;

    String city = SPCommon.getString("city", "聊城");
    String area = SPCommon.getString("qu", "东昌府");
    String foregift = "0";//是否保证金
    String foregift_protocol = "0";//是否保证金协议扣款
    String insurance = "0";//是否保险
    String insurance_protocol = "0";
    String isNeedEquip = "0";//装备
    String memberFee = "0";//保底费：0-不交1-交5002-交700

    String foregifts;//保证金
    String insurances = "";//保险
    String trainFee = "";//装备费
    double num = 0.0;

    @Override
    public void findAreaNeedFee(BaoXianModel model) {
        foregifts = model.foregift;
        insurances = model.insurance;
        trainFee = model.trainFee;
        cb1.setText("同意提交" + model.foregift + "元保证金");
        cb2.setText("同意提交" + model.trainFee + "元(培训费和装备费)");
        cb3.setText("同意购买" + model.insurance + "元保险");
        cb4.setText("保证金协议扣款("+model.foregift+ "元保证金)");
        mPresenter.isAgreementDeductions(city, area);
    }

    @Override
    public void isAgreementDeductions(BaoXianModel model) {
        if (model.isImpFor.equals("0")) {
            linBaoZhengJin.setVisibility(View.GONE);
            linBaoXianJin.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    Intent intent;

    @OnClick({R.id.cb1, R.id.tv1, R.id.cb2, R.id.cb3, R.id.cb4, R.id.tv4, R.id.cb5, R.id.tv5, R.id.cb6, R.id.cb7, R.id.cb8, R.id.btnOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb1:
//                if (cb1.isChecked()) {
//                    cb1.setButtonDrawable(R.mipmap.zhong);
//                    cb4.setButtonDrawable(R.mipmap.weixuanzhongx);
//                    cb4.setChecked(false);
//                } else {
//                    cb1.setButtonDrawable(R.mipmap.weixuanzhongx);
//                }
                break;
            case R.id.tv1:
                intent = new Intent();
                intent.setClass(mContext, XieYiActivity.class);
                intent.putExtra("code", 1002 + "");
                startActivity(intent);
                break;
            case R.id.cb2:
                if (cb2.isChecked()) {
                    cb2.setButtonDrawable(R.mipmap.zhong);
                    cb4.setButtonDrawable(R.mipmap.weixuanzhongx);
                    cb4.setChecked(false);
                } else {
                    cb2.setButtonDrawable(R.mipmap.weixuanzhongx);
                }
                break;
            case R.id.cb3:
                if (cb3.isChecked()) {
                    cb3.setButtonDrawable(R.mipmap.zhong);
                    cb5.setButtonDrawable(R.mipmap.weixuanzhongx);
                    cb5.setChecked(false);
                } else {
                    cb3.setButtonDrawable(R.mipmap.weixuanzhongx);
                }
                break;
            case R.id.cb4:
                if (cb4.isChecked()) {
                    cb4.setButtonDrawable(R.mipmap.zhong);
                    cb2.setButtonDrawable(R.mipmap.weixuanzhongx);
                    cb2.setChecked(false);
                } else {
                    cb4.setButtonDrawable(R.mipmap.weixuanzhongx);
                }
                break;
            case R.id.tv4:
                intent = new Intent();
                intent.setClass(mContext, XieYiActivity.class);
                intent.putExtra("code", 1000 + "");
                startActivity(intent);
                break;
            case R.id.cb5:
                if (cb5.isChecked()) {
                    cb5.setButtonDrawable(R.mipmap.zhong);
                    cb3.setButtonDrawable(R.mipmap.weixuanzhongx);
                    cb3.setChecked(false);
                } else {
                    cb5.setButtonDrawable(R.mipmap.weixuanzhongx);
                }
                break;
            case R.id.tv5:
                intent = new Intent();
                intent.setClass(mContext, XieYiActivity.class);
                intent.putExtra("code", 1001 + "");
                startActivity(intent);
                break;
            case R.id.cb6:
//                if (cb6.isChecked()) {
//                    cb6.setButtonDrawable(R.mipmap.zhong);
//                    cb7.setButtonDrawable(R.mipmap.weixuanzhongx);
//                    cb7.setChecked(false);
//                } else {
//                    cb6.setButtonDrawable(R.mipmap.weixuanzhongx);
//                }
                break;
            case R.id.cb7:
//                if (cb7.isChecked()) {
//                    cb7.setButtonDrawable(R.mipmap.zhong);
//                    cb6.setButtonDrawable(R.mipmap.weixuanzhongx);
//                    cb6.setChecked(false);
//                } else {
//                    cb7.setButtonDrawable(R.mipmap.weixuanzhongx);
//                }
                break;
            case R.id.cb8:
                if (cb8.isChecked()) {
                    cb8.setButtonDrawable(R.mipmap.zhong);
                } else {
                    cb8.setButtonDrawable(R.mipmap.weixuanzhongx);
                }
                break;
            case R.id.btnOk:
                num = 0.00;
                if (!cb8.isChecked()) {
                    ToastUtil.show("请阅读并同意接单员协议");
                    return;
                }
//                if (!cb1.isChecked() && !cb4.isChecked()) {
//                    ToastUtil.show("保证金可以一次性交请，也可以协议扣款，必须选一项");
//                    return;
//                }


                if (!cb3.isChecked() && !cb5.isChecked()) {
                    insurance = "0";//保险
                    insurance_protocol = "0";
//                    num = num;
                }
                if (!cb3.isChecked() && cb5.isChecked()) {
                    insurance = "0";//保险
                    insurance_protocol = "1";
//                    num = 0;
                }
                if (cb3.isChecked() && !cb5.isChecked()) {
                    insurance = "1";//保险
                    insurance_protocol = "0";
                    num = num + Double.parseDouble(insurances);//交保险
                }
                if (!cb2.isChecked() && !cb4.isChecked()) {
                    ToastUtil.show("请选择培训费和装备费");
                    return;
                }
                if (cb2.isChecked() && !cb4.isChecked()) {
//                    foregift = "1";
                    isNeedEquip = "1";
                    foregift_protocol = "0";
                    num = num + Double.parseDouble(trainFee);//交保证斤
                }
                if (!cb2.isChecked() && cb4.isChecked()) {//不交保证斤
//                    foregift = "0";
                    isNeedEquip = "0";
                    foregift_protocol = "1";
                    num = num + 0;
                }
//                if (cb3.isChecked()) {
//                    insurance = "1";//保险
//                    insurance_protocol = "0";
//                    num = num + Double.parseDouble(insurances);//交保险
//                }else {
//                    insurance = "0";//保险
//                    insurance_protocol = "0";
//                }

//                if (cb2.isChecked()) {
//
////                    num = num+Integer.parseInt(foregifts)+ Integer.parseInt(insurances);
//                }
//                if (cb6.isChecked()){
//                    num = num +  Double.parseDouble(500+"");//交500
//                    memberFee = "1";
//                }
//                if (cb7.isChecked()){
//                    num = num +  Double.parseDouble(700+"");//交700
//                    memberFee = "2";
//                }
//                if (!cb6.isChecked()&&!cb7.isChecked()){
//                    memberFee = "0";
//                }
                final DecimalFormat df = new DecimalFormat("#.00");
//                num = ;
//                System.out.println();
                final ApplyAndAlterDialog dialogs = new ApplyAndAlterDialog(IdentityPayActivity.this);
                dialogs.setCanceledOnTouchOutside(true);
                dialogs.setMessage("一经缴费，概不退款", "亲费用一经支付，无论什么原因都不能退款，请你考虑清楚了以后操作。");
                dialogs.setBackgroundResource(true);
                dialogs.setVisibilityBtn(true);
                dialogs.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        dialogs.dismiss();
                        ApliyDialog dialog = new ApliyDialog(IdentityPayActivity.this, R.style
                                .transparentFrameWindowStyle, df.format(num) + "",
                                new ApliyDialog.SelectDialogCancelListener() {
                                    @Override
                                    public void onCancelClick(String id) {

                                        mPayType = id;
                                        if (mPayType.equals("1") || mPayType.equals("2")) {
                                            mPresenter.user_authPay(SPCommon.getString("token", ""), mPayType, SPCommon.getString("city", "聊城"), SPCommon.getString("qu", "东昌府"), foregift, foregift_protocol, insurance, insurance_protocol, isNeedEquip, memberFee);//付款
                                        } else {
                                            passWordDialog = new PassWordDialog(IdentityPayActivity.this);
                                            passWordDialog.show();
                                            if (num==0.00){
                                                passWordDialog.setHintText( "0.00");
                                                passWordDialog.setMoneyNum("0.00");
                                            }else {
                                                passWordDialog.setHintText(df.format(num) + "");
                                                passWordDialog.setMoneyNum(df.format(num) + "");
                                            }

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
                        if (!IdentityPayActivity.this.isFinishing()) {
                            dialog.show();
                            dialog.setGones();
                        }

//                            finish();
                    }
                });
                dialogs.setOnOnclickListener("取消", new ApplyAndAlterDialog.onOnOnclickListener() {
                    @Override
                    public void onOnClick() {
                        dialogs.dismiss();
                    }
                });
                dialogs.show();


                break;
        }
    }

}
