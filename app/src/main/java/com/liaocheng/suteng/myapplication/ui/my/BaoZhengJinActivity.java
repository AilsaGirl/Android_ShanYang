package com.liaocheng.suteng.myapplication.ui.my;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.BaoZhengJinPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.BaoZhengJinContent;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.ApliyDialog;
import com.liaocheng.suteng.myapplication.view.PassWordDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaoZhengJinActivity extends BaseActivity<BaoZhengJinPresenter> implements BaoZhengJinContent.View {
    @BindView(R.id.myToolBar)
    MyToolBar myToolBar;
    @BindView(R.id.tvZhuangTai)
    TextView tvZhuangTai;
    @BindView(R.id.btnJie)
    Button btnJie;
    String type = "";
    @BindView(R.id.tvNum)
    TextView tvNum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_baozhengjin;
    }
    String city = SPCommon.getString("city","聊城");
    String area  = SPCommon.getString("qu","东昌府");
    @Override
    public void initEventAndData() {
        myToolBar.setBackFinish().setTitleText("保证金");
        mPresenter.findAreaNeedFee(city,area);
        if (SPCommon.getString("auth", "").equals("2")) {
            type = "2";
        } else {
            type = "1";
        }

        if (type.equals("1")) {
            tvZhuangTai.setText("当前状态：绑定中");
            btnJie.setText("解除绑定");
        } else {
            tvZhuangTai.setText("当前状态：解除中");
            btnJie.setText("取消解除");
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

    AlertDialog.Builder builder;

    @OnClick(R.id.btnJie)
    public void onViewClicked() {
        if (type.equals("1")) {
            if (SPCommon.getString("baozhengjin", "0.00").equals("0.00")) {
                builder = new AlertDialog.Builder(mContext);
//            builder.setTitle("软件更新");
                builder.setMessage("退出接单员，未满六个月的接单员会扣25元押金");
                builder.setCancelable(false);

                // 更新
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.user_authRelieve();
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
            } else {
                builder = new AlertDialog.Builder(mContext);
//            builder.setTitle("软件更新");
                builder.setMessage("亲，你有保证金协议扣款未结清，总计" + SPCommon.getString("baozhengjin", "0.00") + "元，是否进行补齐");
                builder.setCancelable(false);

                // 更新
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pay(SPCommon.getString("baozhengjin", "0.00"));
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

        } else {
            mPresenter.user_cancellRelieve();
        }
    }

    String mPayType;
    PassWordDialog passWordDialog;

    private void pay(final String num) {
        ApliyDialog dialog = new ApliyDialog(BaoZhengJinActivity.this, R.style
                .transparentFrameWindowStyle, num + "",
                new ApliyDialog.SelectDialogCancelListener() {
                    @Override
                    public void onCancelClick(String id) {

                        mPayType = id;
                        if (mPayType.equals("1") || mPayType.equals("2")) {
                            mPresenter.repayTheArrears(mPayType, num);//付款
                        } else {
                            passWordDialog = new PassWordDialog(BaoZhengJinActivity.this);
                            passWordDialog.show();
                            passWordDialog.setHintText(num + "");
                            passWordDialog.setMoneyNum(num + "");
                            passWordDialog.setError_tishi("请输入支付密码");
                            passWordDialog.setClick(new PassWordDialog.OnPayClickListener() {
                                @Override
                                public void onSetPass(String text) {
                                    mPresenter.repayTheArrears(mPayType, text);//付款

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
    public void user_authRelieve() {
        ToastUtil.show("申请成功");
        finish();
    }

    @Override
    public void user_cancellRelieve() {
        ToastUtil.show("申请成功");
        finish();
    }

    @Override
    public void repayTheArrears(PayModel payModel) {
        if (mPayType.equals("1") || mPayType.equals("2")) {
            Util.Pay(mPayType, payModel, mContext);
        } else {
            ToastUtil.show("支付成功");
            finish();
        }
    }

    @Override
    public void findAreaNeedFee(BaoXianModel model) {
        tvNum.setText(model.foregift+"元");
    }
}
