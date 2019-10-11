package com.liaocheng.suteng.myapplication.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.ChongZhiModel;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.ChongZhiPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.ChongZhiContent;
import com.liaocheng.suteng.myapplication.view.ApliyDialog;
import com.liaocheng.suteng.myapplication.wxapi.PayResult;
import com.liaocheng.suteng.myapplication.wxapi.WeChatPayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChongZhiActivity extends BaseActivity<ChongZhiPresenter> implements ChongZhiContent.View {
    @BindView(R.id.myToolBar)
    MyToolBar myToolBar;
    TextView tvXieYi;
    @BindView(R.id.btnZhiFu)
    Button btnZhiFu;
    @BindView(R.id.etNum)
    EditText etNum;
    @BindView(R.id.tvChong1)
    TextView tvChong1;
    @BindView(R.id.tvChong2)
    TextView tvChong2;
    @BindView(R.id.tvChong3)
    TextView tvChong3;
    @BindView(R.id.tvChong4)
    TextView tvChong4;
    @BindView(R.id.tvChong5)
    TextView tvChong5;
    @BindView(R.id.tvChong6)
    TextView tvChong6;
    @BindView(R.id.linYe)
    LinearLayout linYe;
    @BindView(R.id.linChong)
    LinearLayout linChong;



    @Override
    public int getLayoutId() {
        return R.layout.activity_chongzhi;
    }
    String city = SPCommon.getString("city", "聊城");
    String area = SPCommon.getString("qu", "东昌府");
    @Override
    public void initEventAndData() {
        myToolBar.setTitleText("充值").setBackFinish();
//        mPresenter.isFirstChargedDealMoney();
        mPresenter.getAddType(area+"",city+"");
        linYe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNum.requestFocus();
                etNum.setFocusable(true);
            }
        });
        etNum.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //s:变化后的所有字符
//                Toast.makeText(getContext(), "变化:"+s+";"+start+";"+before+";"+count, Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化:"+s+";"+start+";"+before+";"+count);

                switch (mId) {
                    case "1":
                        tvChong1.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "2":
                        tvChong2.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "3":
                        tvChong3.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "4":
                        tvChong4.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "5":
                        tvChong5.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "6":
                        tvChong6.setBackgroundResource(R.mipmap.weichong);
                        break;
                }
                mId = "";
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
//                Toast.makeText(getContext(), "变化前:"+s+";"+start+";"+count+";"+after, Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化前:"+s+";"+start+";"+count+";"+after);
                switch (mId) {
                    case "1":
                        tvChong1.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "2":
                        tvChong2.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "3":
                        tvChong3.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "4":
                        tvChong4.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "5":
                        tvChong5.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "6":
                        tvChong6.setBackgroundResource(R.mipmap.weichong);
                        break;
                }
                mId = "";
            }

            @Override
            public void afterTextChanged(Editable s) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数
//                Toast.makeText(getContext(), "变化后:"+s+";", Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化后:"+s+";");
                switch (mId) {
                    case "1":
                        tvChong1.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "2":
                        tvChong2.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "3":
                        tvChong3.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "4":
                        tvChong4.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "5":
                        tvChong5.setBackgroundResource(R.mipmap.weichong);
                        break;
                    case "6":
                        tvChong6.setBackgroundResource(R.mipmap.weichong);
                        break;
                }
                mId = "";
                mNum= "";
            }
        });
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

    //    1-100送10
//2-200送30
//3-300送50
//4-500送100
//5-1000送220
//10-10送5
    String mId = "";

    @OnClick({R.id.tvChong1, R.id.tvChong2, R.id.tvChong3, R.id.tvChong4, R.id.tvChong5, R.id.tvChong6, R.id.tvXieYi, R.id.btnZhiFu})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tvChong1:

                etNum.setText("");
                mId = "0";
                tvChong1.setBackgroundResource(R.mipmap.chong);
                tvChong2.setBackgroundResource(R.mipmap.weichong);
                tvChong3.setBackgroundResource(R.mipmap.weichong);
                tvChong4.setBackgroundResource(R.mipmap.weichong);
                tvChong5.setBackgroundResource(R.mipmap.weichong);
                tvChong6.setBackgroundResource(R.mipmap.weichong);
                mNum = mList.get(0).getMoney()+"";
                break;
                case R.id.tvChong2:

                etNum.setText("");
                mId = "1";
                tvChong2.setBackgroundResource(R.mipmap.chong);
                tvChong1.setBackgroundResource(R.mipmap.weichong);
                tvChong3.setBackgroundResource(R.mipmap.weichong);
                tvChong4.setBackgroundResource(R.mipmap.weichong);
                tvChong5.setBackgroundResource(R.mipmap.weichong);
                tvChong6.setBackgroundResource(R.mipmap.weichong);
                mNum = mList.get(1).getMoney()+"";
                break;
            case R.id.tvChong3:

                etNum.setText("");
                mId = "2";
                tvChong3.setBackgroundResource(R.mipmap.chong);
                tvChong1.setBackgroundResource(R.mipmap.weichong);
                tvChong2.setBackgroundResource(R.mipmap.weichong);
                tvChong4.setBackgroundResource(R.mipmap.weichong);
                tvChong5.setBackgroundResource(R.mipmap.weichong);
                tvChong6.setBackgroundResource(R.mipmap.weichong);
                mNum = mList.get(2).getMoney()+"";
                break;
            case R.id.tvChong4:

                etNum.setText("");
                mId = "3";
                tvChong4.setBackgroundResource(R.mipmap.chong);
                tvChong1.setBackgroundResource(R.mipmap.weichong);
                tvChong2.setBackgroundResource(R.mipmap.weichong);
                tvChong3.setBackgroundResource(R.mipmap.weichong);
                tvChong5.setBackgroundResource(R.mipmap.weichong);
                tvChong6.setBackgroundResource(R.mipmap.weichong);
                mNum = mList.get(3).getMoney()+"";
                break;
            case R.id.tvChong5:

                etNum.setText("");
                mId = "4";
                tvChong5.setBackgroundResource(R.mipmap.chong);
                tvChong1.setBackgroundResource(R.mipmap.weichong);
                tvChong2.setBackgroundResource(R.mipmap.weichong);
                tvChong3.setBackgroundResource(R.mipmap.weichong);
                tvChong4.setBackgroundResource(R.mipmap.weichong);
                tvChong6.setBackgroundResource(R.mipmap.weichong);
                mNum = mList.get(4).getMoney()+"";
                break;
            case R.id.tvChong6:

                etNum.setText("");
                mId = "5";
                tvChong6.setBackgroundResource(R.mipmap.chong);
                tvChong1.setBackgroundResource(R.mipmap.weichong);
                tvChong2.setBackgroundResource(R.mipmap.weichong);
                tvChong3.setBackgroundResource(R.mipmap.weichong);
                tvChong4.setBackgroundResource(R.mipmap.weichong);
                tvChong5.setBackgroundResource(R.mipmap.weichong);
                mNum = mList.get(5).getMoney()+"";
                break;
            case R.id.tvXieYi:
                Intent intent = new Intent();
                intent.setClass(this, XieYiActivity.class);
                intent.putExtra("code", 3002 + "");
                startActivity(intent);
                break;
            case R.id.btnZhiFu:
                if (TextUtils.isEmpty(etNum.getText().toString()) && TextUtils.isEmpty(mId)) {
                    ToastUtil.show("请输入或选择充值金额");
                    return;
                }
                if (!TextUtils.isEmpty(etNum.getText().toString())) {
                    if (Integer.parseInt(etNum.getText().toString()) == 0) {
                        ToastUtil.show("充值金额不能为0");
                        return;
                    }
                    ApliyDialog dialog = new ApliyDialog(ChongZhiActivity.this, R.style
                            .transparentFrameWindowStyle, etNum.getText().toString() + "",
                            new ApliyDialog.SelectDialogCancelListener() {
                                @Override
                                public void onCancelClick(String id) {
                                    mType = id;
                                    mPresenter.user_recharge(etNum.getText().toString(), mType);
                                }
                            });
                    if (!this.isFinishing()) {
                        dialog.show();
                        dialog.setGone();
                    }

                }
                if (!TextUtils.isEmpty(mId)) {
                    ApliyDialog dialog = new ApliyDialog(ChongZhiActivity.this, R.style
                            .transparentFrameWindowStyle, mNum + "",
                            new ApliyDialog.SelectDialogCancelListener() {
                                @Override
                                public void onCancelClick(String id) {
                                    mType = id;
                                    mPresenter.user_rechargeDealMoney(mNum, mType);
                                }
                            });
                    if (!this.isFinishing()) {
                        dialog.show();
                        dialog.setGone();
                    }

                }
                break;
        }
    }

    String mNum = "";

    @Override
    public void isFirstChargedDealMoney(ChongZhiModel chongZhiModel) {
//        if (chongZhiModel.isCharged.equals("1")) {
//            tvChong6.setVisibility(View.INVISIBLE);
//        } else {
//            tvChong6.setVisibility(View.VISIBLE);
//        }
    }

    String mType = "";

    @Override
    public void user_recharge(final PayModel mBean) {
//        ToastUtil.show("充值成功");
        if (mType.equals("1")) {//支付宝
            OkAliPay(mBean.data + "");
        }
        if (mType.equals("2")) {//支付宝
            OkWechat(mBean);
        }
    }

    @Override
    public void user_rechargeDealMoney(PayModel mBean) {//用户充值发货余额
//        ToastUtil.show("充值成功");
        if (mType.equals("1")) {//支付宝
            OkAliPay(mBean.data + "");
        }
        if (mType.equals("2")) {//支付宝
            OkWechat(mBean);
        }

    }

    List<ChongZhiModel.DataBean> mList = new ArrayList<>();

    @Override
    public void getAddType(ChongZhiModel chongZhiModel) {
        if (chongZhiModel.getData().size() != 0 && chongZhiModel.getData().size() > 0) {
            mList.addAll(chongZhiModel.getData());
            if (chongZhiModel.getData().size()==1){
                tvChong1.setVisibility(View.VISIBLE);
                tvChong2.setVisibility(View.INVISIBLE);
                tvChong3.setVisibility(View.INVISIBLE);
                tvChong4.setVisibility(View.INVISIBLE);
                tvChong5.setVisibility(View.INVISIBLE);
                tvChong6.setVisibility(View.INVISIBLE);
                tvChong1.setText(mList.get(0).getMoney()+"\n\n"+mList.get(0).getOffer());
            }
            if (chongZhiModel.getData().size()==2){
                tvChong1.setVisibility(View.VISIBLE);
                tvChong2.setVisibility(View.VISIBLE);
                tvChong3.setVisibility(View.INVISIBLE);
                tvChong4.setVisibility(View.INVISIBLE);
                tvChong5.setVisibility(View.INVISIBLE);
                tvChong6.setVisibility(View.INVISIBLE);
                tvChong1.setText(mList.get(0).getMoney()+"\n\n"+mList.get(0).getOffer());
                tvChong2.setText(mList.get(1).getMoney()+"\n\n"+mList.get(1).getOffer());
            }
            if (chongZhiModel.getData().size()==3){
                tvChong1.setVisibility(View.VISIBLE);
                tvChong2.setVisibility(View.VISIBLE);
                tvChong3.setVisibility(View.VISIBLE);
                tvChong4.setVisibility(View.INVISIBLE);
                tvChong5.setVisibility(View.INVISIBLE);
                tvChong6.setVisibility(View.INVISIBLE);
                tvChong1.setText(mList.get(0).getMoney()+"\n\n"+mList.get(0).getOffer());
                tvChong2.setText(mList.get(1).getMoney()+"\n\n"+mList.get(1).getOffer());
                tvChong3.setText(mList.get(2).getMoney()+"\n\n"+mList.get(2).getOffer());
            }
            if (chongZhiModel.getData().size()==4){
                tvChong1.setVisibility(View.VISIBLE);
                tvChong2.setVisibility(View.VISIBLE);
                tvChong3.setVisibility(View.VISIBLE);
                tvChong4.setVisibility(View.VISIBLE);
                tvChong5.setVisibility(View.INVISIBLE);
                tvChong6.setVisibility(View.INVISIBLE);
                tvChong1.setText(mList.get(0).getMoney()+"\n\n"+mList.get(0).getOffer());
                tvChong2.setText(mList.get(1).getMoney()+"\n\n"+mList.get(1).getOffer());
                tvChong3.setText(mList.get(2).getMoney()+"\n\n"+mList.get(2).getOffer());
                tvChong4.setText(mList.get(3).getMoney()+"\n\n"+mList.get(3).getOffer());
            }
            if (chongZhiModel.getData().size()==5){
                tvChong1.setVisibility(View.VISIBLE);
                tvChong2.setVisibility(View.VISIBLE);
                tvChong3.setVisibility(View.VISIBLE);
                tvChong4.setVisibility(View.VISIBLE);
                tvChong5.setVisibility(View.VISIBLE);
                tvChong6.setVisibility(View.INVISIBLE);
                tvChong1.setText(mList.get(0).getMoney()+"\n\n"+mList.get(0).getOffer());
                tvChong2.setText(mList.get(1).getMoney()+"\n\n"+mList.get(1).getOffer());
                tvChong3.setText(mList.get(2).getMoney()+"\n\n"+mList.get(2).getOffer());
                tvChong4.setText(mList.get(3).getMoney()+"\n\n"+mList.get(3).getOffer());
                tvChong5.setText(mList.get(4).getMoney()+"\n\n"+mList.get(4).getOffer());
            }
            if (chongZhiModel.getData().size()==6){
                tvChong1.setVisibility(View.VISIBLE);
                tvChong2.setVisibility(View.VISIBLE);
                tvChong3.setVisibility(View.VISIBLE);
                tvChong4.setVisibility(View.VISIBLE);
                tvChong5.setVisibility(View.VISIBLE);
                tvChong6.setVisibility(View.VISIBLE);
                tvChong1.setText(mList.get(0).getMoney()+"\n\n"+mList.get(0).getOffer());
                tvChong2.setText(mList.get(1).getMoney()+"\n\n"+mList.get(1).getOffer());
                tvChong3.setText(mList.get(2).getMoney()+"\n\n"+mList.get(2).getOffer());
                tvChong4.setText(mList.get(3).getMoney()+"\n\n"+mList.get(3).getOffer());
                tvChong5.setText(mList.get(4).getMoney()+"\n\n"+mList.get(4).getOffer());
                tvChong6.setText(mList.get(5).getMoney()+"\n\n"+mList.get(5).getOffer());
            }
        }else {
            linChong.setVisibility(View.INVISIBLE);
        }
    }

    private final int ALI_PAY_OK = 1;//阿里支付
    private Handler mHanler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case ALI_PAY_OK://阿里支付
                    PayResult payResult = new PayResult((Map<String, String>) message.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        mPresenter.isFirstChargedDealMoney();
                        ToastUtil.show("支付成功！");
                    } else {
                        ToastUtil.show("支付失败！");
                    }
//                    ((Activity) mContext).finish();
                    break;
            }
            return false;
        }
    });

    //支付宝 付款
    private void OkAliPay(final String aliKey) {
        if (TextUtils.isEmpty(aliKey)) {
            ToastUtil.show("支付方式有误！");
            ((Activity) mContext).finish();
            return;
        }
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(((Activity) mContext));
                Map<String, String> result = alipay.payV2(aliKey, true);
                Message msg = new Message();
                msg.what = ALI_PAY_OK;
                msg.obj = result;
                mHanler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    WeChatPayUtil mWechatUtil;

    //微信付款
    private void OkWechat(PayModel model) {
        if (mWechatUtil == null) {
            mWechatUtil = new WeChatPayUtil(mContext);
        }
        boolean payOk = mWechatUtil.WeChatPay(model);
        if (payOk) {
//            ToastUtil.show("支付成功！");
        } else {
//            ToastUtil.show("支付失败！");
        }


    }
}
