package com.liaocheng.suteng.myapplication.ui.home.jiedan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.DingDanBuyInfoPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.DingDanBuyInfoContent;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.PingJiaActivity;
import com.liaocheng.suteng.myapplication.ui.my.UpdatePhoneZhiFuMiMaActivity;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.ApliyDialog;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;
import com.liaocheng.suteng.myapplication.view.JiaJiaDialog;
import com.liaocheng.suteng.myapplication.view.PassWordDialog;
import com.liaocheng.suteng.myapplication.view.ZhuanRangDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DingDanBuyInfoActivity extends BaseActivity<DingDanBuyInfoPresenter> implements DingDanBuyInfoContent.View {
    @BindView(R.id.toolbar)
    MyToolBar toolbar;
    @BindView(R.id.tvMsg)
    TextView tvMsg;
    @BindView(R.id.tvRen)
    TextView tvRen;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvMoreAddress)
    TextView tvMoreAddress;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvHuoKuan)
    TextView tvHuoKuan;
    @BindView(R.id.tvXiaoFei)
    TextView tvXiaoFei;
    @BindView(R.id.tvBianHao)
    TextView tvBianHao;
    @BindView(R.id.tvShiJian)
    TextView tvShiJian;
    @BindView(R.id.tvJieDanRen)
    TextView tvJieDanRen;
    @BindView(R.id.tvJieDanRenTel)
    TextView tvJieDanRenTel;
    @BindView(R.id.tvShanChu)
    TextView tvShanChu;
    @BindView(R.id.tvZhiFu)
    TextView tvZhiFu;
    @BindView(R.id.linJieDan)
    LinearLayout linJieDan;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.linFuKuan)
    LinearLayout linFuKuan;
    @BindView(R.id.tvQiangDan)
    TextView tvQiangDan;
    @BindView(R.id.tvCheXiao)
    TextView tvCheXiao;
    @BindView(R.id.tvJiaJia)
    TextView tvJiaJia;
    @BindView(R.id.tvQueQen)
    TextView tvQueQen;
    @BindView(R.id.linQuHuo)
    LinearLayout linQuHuo;
    @BindView(R.id.tvZhuanRang)
    TextView tvZhuanRang;
    @BindView(R.id.tvChouJin)
    TextView tvChouJin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dingdan_buy_info;
    }

    boolean isJieDanYuan;
    String mCode;
    int mType =0;
    @Override
    public void initEventAndData() {
        toolbar.setTitleText("订单详情").setBackFinish();
        Intent intent = getIntent();
        isJieDanYuan = intent.getBooleanExtra("isJieDanYuan", false);
        mCode = intent.getStringExtra("code");
        mType = intent.getIntExtra("type",1);
        if (TextUtils.isEmpty(mCode)) {
            ToastUtil.show("订单号不正确");
            finish();
            return;
        } else {
            if (mType==0){
                mPresenter.order_info_detail(mCode);
            }
            if (mType==1){
                mPresenter.queryReceiveOrderDetail(mCode);
            }
            if (mType==2){
                mPresenter.getDingDa(mCode);
            }

        }

    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
        if (reqCode == 1) {
            finish();
        }

    }

    String status;
    String mNum = "";
String receiveUserId = "";
String sendUserId = "";
    @Override
    public void setDingDa(DingDanBuyInfoModel DingDanBean) {
        if (DingDanBean.orderType.equals("1")) {
            toolbar.setTitleText("订单详情-帮我买");
            tvChouJin.setText("货款：");
        }
        receiveUserId = DingDanBean.receiveUserId;
        sendUserId = DingDanBean.sendUserId;
        if (DingDanBean.orderType.equals("2")) {
            toolbar.setTitleText("订单详情-帮我办");
            tvChouJin.setText("酬金：");
        }
        tvMsg.setText(DingDanBean.description + "");
        tvRen.setText(DingDanBean.receiveName + "");
        tvTel.setText(DingDanBean.contactPhone + "");
        tvAddress.setText(DingDanBean.receiveAddress + "");
        tvMoreAddress.setText(DingDanBean.receiveConcreteAdd + "" + DingDanBean.receiveDetailAdd);
        tvTime.setText("约定时间：" + DingDanBean.appointTime + "");
        tvHuoKuan.setText("￥" + DingDanBean.tip + "");
        tvXiaoFei.setText("￥" + DingDanBean.addTips + "");

        tvBianHao.setText("订单编号：" + DingDanBean.orderCode + "");
        if (DingDanBean.status.equals("1")){
            tvShiJian.setText("创建时间：" + DingDanBean.createTime + "");
        }else{
            tvShiJian.setText("下单时间：" + DingDanBean.payTime + "");
        }

        mNum = DingDanBean.total + "";
        status = DingDanBean.status;
        if (isJieDanYuan) {
            tvTotal.setText("￥" + DingDanBean.remuneration + "");
            switch (DingDanBean.status) {
                case "1":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
                    break;
                case "2":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.VISIBLE);
                    tvQueQen.setVisibility(View.GONE);
                    break;
                case "3":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.VISIBLE);
                    linQuHuo.setVisibility(View.VISIBLE);
                    tvQiangDan.setVisibility(View.GONE);
                    tvZhuanRang.setVisibility(View.VISIBLE);
                    tvCheXiao.setText("到店取货");
                    tvJiaJia.setText("退回大厅");
                    tvQueQen.setText("退回发单人");
                    break;
                case "4":
//                    if (DingDanBean.recIsComment.equals("0")){
//                        tvQiangDan.setVisibility(View.VISIBLE);
//                        tvQiangDan.setText("去评价");
//                    }else {
//                        tvQiangDan.setVisibility(View.GONE);
//                    }
                    tvQiangDan.setVisibility(View.GONE);
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.VISIBLE);
                    linQuHuo.setVisibility(View.GONE);
                    tvTel.setText("***********************");
                    tvRen.setText("***********************");
                    tvMoreAddress.setText("***********************");
                    tvAddress.setText("***********************");
                    break;
                case "5":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.VISIBLE);
                    linQuHuo.setVisibility(View.VISIBLE);
                    tvQiangDan.setVisibility(View.GONE);
                    tvZhuanRang.setVisibility(View.VISIBLE);
                    tvCheXiao.setVisibility(View.GONE);
                    tvJiaJia.setText("退回大厅");
                    tvQueQen.setText("退回发单人");

                    break;
                case "6":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.VISIBLE);
                    linQuHuo.setVisibility(View.VISIBLE);
                    tvQiangDan.setVisibility(View.GONE);
                    tvCheXiao.setVisibility(View.GONE);
                    tvJiaJia.setVisibility(View.GONE);
                    tvZhuanRang.setVisibility(View.GONE);
                    break;
                case "7":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
                    break;
                case "8":
                    linFuKuan.setVisibility(View.VISIBLE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
                    tvShanChu.setText("同意接单");
                    tvZhiFu.setText("拒绝接单");
                    break;
                case "9":
                    linFuKuan.setVisibility(View.VISIBLE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
                    tvZhiFu.setVisibility(View.GONE);
                    tvShanChu.setText("转让订单中");
//                    tvZhiFu.setText("拒绝转单");
                    break;

            }
        } else {
            tvTotal.setText("￥" + DingDanBean.total + "");
            switch (DingDanBean.status) {
                case "1":
                    linFuKuan.setVisibility(View.VISIBLE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);

                    leftTime = (time(StringData()) - System.currentTimeMillis()) / 1000;
                    handler.postDelayed(update_thread, 1000);
                    break;
                case "2":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.VISIBLE);
                    tvQiangDan.setVisibility(View.GONE);
                    tvQueQen.setVisibility(View.GONE);
                    break;
                case "3":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.VISIBLE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
                    break;
                case "4":
//                    if (DingDanBean.sendIsComment.equals("0")){
//                        tvQiangDan.setVisibility(View.VISIBLE);
//                        tvQiangDan.setText("去评价");
//                    }else {
//                        tvQiangDan.setVisibility(View.GONE);
//                    }
                    tvQiangDan.setVisibility(View.GONE);
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQueQen.setVisibility(View.GONE);

                    break;
                case "5":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
                    break;
                case "6":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.VISIBLE);
                    linQuHuo.setVisibility(View.VISIBLE);
                    tvQiangDan.setVisibility(View.GONE);
                    tvCheXiao.setVisibility(View.GONE);
                    tvJiaJia.setVisibility(View.GONE);
                    break;
                case "7":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
                    break;
                case "8":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
                    break;
                case "9":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
                    break;

            }
        }

        tvJieDanRen.setText("接单人：" + DingDanBean.transporterName + "");
        tvJieDanRenTel.setText("手机号：" + DingDanBean.transporterPhone + "");

    }

    public String StringData() {

        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        return mYear + "-" + mMonth + "-" + mDay + " 23:59:59";

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        leftTime = 0;
        handler.removeCallbacks(update_thread);
    }

    long leftTime;
    Handler handler = new Handler();
    Runnable update_thread = new Runnable() {
        @Override
        public void run() {
            leftTime--;
            if (leftTime > 0) {
                //倒计时效果展示
                String formatLongToTimeStr = formatLongToTimeStr(leftTime);
                tvShanChu.setText("删除订单\n" + formatLongToTimeStr);
                //每一秒执行一次
                handler.postDelayed(this, 1000);
            } else {//倒计时结束
                //处理业务流程

                //发送消息，结束倒计时
                Message message = new Message();
                message.what = 1;
                handlerStop.sendMessage(message);
            }
        }
    };
    final Handler handlerStop = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    finish();
                    leftTime = 0;
                    handler.removeCallbacks(update_thread);
                    break;
            }
            super.handleMessage(msg);
        }

    };

    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue();
        if (second > 60) {
            minute = second / 60;   //取整
            second = second % 60;   //取余
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = "" + hour + "：" + minute + "：" + second + "";
        return strtime;
    }

    String mYear, mMonth, mDay;

    public static long time(String _data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = format.parse(_data);
            return date.getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    //删除订单
    @Override
    public void order_cancell() {
        ToastUtil.show("删除成功");
        finish();
    }

    //支付成功
    @Override
    public void order_pay(PayModel model) {
        if (mPayType.equals("1")||mPayType.equals("2")){
            Util.Pay(mPayType,model,mContext);
        }else {
            ToastUtil.show("支付成功");

        }
//        ToastUtil.show("支付成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void user_order_refund() {
        ToastUtil.show("撤销成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
//        mPresenter.getDingDa(mCode);
        finish();
    }

    @Override
    public void addOrderTip(PayModel model) {
        if (mPayType.equals("1")||mPayType.equals("2")){
            Util.Pay(mPayType,model,mContext);
        }else {
            ToastUtil.show("支付成功");

        }
        leftTime = 0;
        handler.removeCallbacks(update_thread);
        mPresenter.getDingDa(mCode);
    }

    boolean isFuKuan = true;
    String mTip;

    @Override
    public void checkSecondPassword() {
        if (isFuKuan) {
            mPresenter.order_pay(mCode, mPayType);//付款
        } else {
            mPresenter.addOrderTip(mCode, mPayType, mTip);//加价
        }

    }

    @Override
    public void order_grab() {
        ToastUtil.show("抢单成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
//        mPresenter.getDingDa(mCode);
        Intent intent = new Intent(mContext, WoDeRenWuActivity.class);
        intent.putExtra("tab", 0);
        mContext.startActivity(intent);
        finish();
    }

    @Override
    public void order_submit() {
        ToastUtil.show("确认成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void order_revoke() {
        ToastUtil.show("撤销成功");
        finish();
    }

    @Override
    public void courier_order_submit() {
        ToastUtil.show("确认成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void checkReceiveCode() {
        ToastUtil.show("确认成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void order_refund() {
        ToastUtil.show("撤销成功");
        finish();
    }

    @Override
    public void getThePickup() {
        ToastUtil.show("取货成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void executeSpecificOrder() {
        if (isJieDan) {
            ToastUtil.show("接单成功");
            leftTime = 0;
            handler.removeCallbacks(update_thread);
            mPresenter.getDingDa(mCode);
        } else {
            ToastUtil.show("处理成功");
            finish();
        }
    }

    @Override
    public void executeTransferOrder() {
        if (isZhuanDan) {
            ToastUtil.show("接单成功");
            leftTime = 0;
            handler.removeCallbacks(update_thread);
            mPresenter.getDingDa(mCode);
        } else {
            ToastUtil.show("处理成功");
            finish();
        }
    }

    @Override
    public void transferOrder() {
        ToastUtil.show("处理成功");
        finish();
    }

    boolean isJieDan = true;
    boolean isZhuanDan = true;
    JiaJiaDialog dialog1;
    ZhuanRangDialog dialogs;

    @OnClick({R.id.tvShanChu, R.id.tvZhiFu, R.id.tvQiangDan, R.id.tvCheXiao, R.id.tvJiaJia, R.id.tvQueQen, R.id.tvZhuanRang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvZhuanRang:
                if (dialogs == null)
                    dialogs = new ZhuanRangDialog(DingDanBuyInfoActivity.this);
                dialogs.setCanceledOnTouchOutside(true);
                dialogs.setVisibilityBtn(true);
//                dialogs.setText();
                dialogs.setYesOnclickListener("确定", new ZhuanRangDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick(String tel) {
                        mPresenter.transferOrder(mCode, tel);
                        dialogs.dismiss();

                    }
                });
                dialogs.setOnOnclickListener("取消", new ZhuanRangDialog.onOnOnclickListener() {
                    @Override
                    public void onOnClick() {
                        dialogs.dismiss();
                    }
                });
                dialogs.show();
//                mPresenter.transferOrder(mCode,"13396358230");
                break;
            case R.id.tvShanChu:
                if (isJieDanYuan) {
                    if (status.equals("8")) {
                        isJieDan = true;
                        mPresenter.executeSpecificOrder(mCode, "1");
                    } else if (status.equals("9")) {
                        isZhuanDan = true;
                        mPresenter.executeTransferOrder(mCode, "1");
                    }
                } else {
                    mPresenter.order_cancell(mCode);//删除订单
                }
                break;
            case R.id.tvZhiFu:
                if (isJieDanYuan) {
                    if (status.equals("8")) {
                        isJieDan = false;
                        mPresenter.executeSpecificOrder(mCode, "0");
                    } else if (status.equals("9")) {
                        isZhuanDan = false;
                        mPresenter.executeTransferOrder(mCode, "0");
                    }
                } else {
                    ApliyDialog dialog = new ApliyDialog(DingDanBuyInfoActivity.this, R.style
                            .transparentFrameWindowStyle, mNum + "",
                            new ApliyDialog.SelectDialogCancelListener() {
                                @Override
                                public void onCancelClick(String id) {

                                    mPayType = id;
                                    if (mPayType.equals("1") || mPayType.equals("2")) {
                                        mPresenter.order_pay(mCode, mPayType);//付款
                                    } else {

                                        passWordDialog = new PassWordDialog(DingDanBuyInfoActivity.this);
                                        passWordDialog.show();
                                        passWordDialog.setHintText(mNum + "");
                                        passWordDialog.setMoneyNum(mNum + "");
                                        passWordDialog.setError_tishi("请输入支付密码");
                                        passWordDialog.setClick(new PassWordDialog.OnPayClickListener() {
                                            @Override
                                            public void onSetPass(String text) {
                                                isFuKuan = true;
                                                mPresenter.checkSecondPassword(text + "");
                                                passWordDialog.dismiss();
                                            }

                                            @Override
                                            public void onSetPwd() {
                                                passWordDialog.cancel();
                                                Intent intent = new Intent(mContext, UpdatePhoneZhiFuMiMaActivity.class);
                                                startActivity(intent);
                                                passWordDialog.dismiss();
                                            }
                                        });
                                    }
                                }
                            });
                    if (!this.isFinishing()) {
                        dialog.show();
                    }
//                    mPresenter.order_pay(mCode, "3");//付款
                }
                break;
            case R.id.tvQiangDan:
                if (isJieDanYuan) {
                    if (status.equals("2")) {

                        final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(DingDanBuyInfoActivity.this);
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.setMessage("亲，您确认要抢这个订单吗，抢单成功以后，必须要做到10分钟取货，3公里内30分钟必须送达，超过1公里加时10分钟。抢单以后不要随意撤销订单，随意撤销的话会处罚10-200元。请谨慎操作，不要胡乱抢单。", "");
                        dialog.setBackgroundResource(true);
                        dialog.setVisibilityBtn(true);
                        dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                mPresenter.order_grab(mCode);
                                dialog.dismiss();

                            }
                        });
                        dialog.setOnOnclickListener("取消", new ApplyAndAlterDialog.onOnOnclickListener() {
                            @Override
                            public void onOnClick() {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    } else if (status.equals("4")) {
//                        mPresenter.checkReceiveCode(mCode, "11111");//短信确认订单
//                        Intent   intent = new Intent(mContext, PingJiaActivity.class);
//                        intent.putExtra("orderCode",mCode+"");
//                        intent.putExtra("eva_userId",sendUserId+"");
//                        mContext.startActivity(intent);
//                        finish();
                    }
                } else {
                    if (status.equals("4")) {
//                        mPresenter.checkReceiveCode(mCode, "11111");//短信确认订单
//                        Intent   intent = new Intent(mContext, PingJiaActivity.class);
//                        intent.putExtra("orderCode",mCode+"");
//                        intent.putExtra("eva_userId",receiveUserId+"");
//                        mContext.startActivity(intent);
//                        finish();
                    }
                }
                break;
            case R.id.tvCheXiao:
                if (isJieDanYuan) {
                    mPresenter.getThePickup(mCode);//到店取货
                } else {
//                    。
//                    ToastUtil.show("2分钟以后撤销扣除订单费用的一半 10分钟之后不能撤销了");
                    mPresenter.user_order_refund(mCode);//撤销
                }
                break;
            case R.id.tvJiaJia:
                if (isJieDanYuan) {
                    final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(DingDanBuyInfoActivity.this);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.setMessage("亲，你确定要撤销订单到大厅吗？撤销以后订单会在接单大厅显示，别的接单人可以抢单了，如果发货人投诉你恶意撤销的话，我们会处罚10-200元", "");
                    dialog.setBackgroundResource(true);
                    dialog.setVisibilityBtn(true);
                    dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            mPresenter.order_revoke(mCode);//退回大厅
                            dialog.dismiss();

                        }
                    });
                    dialog.setOnOnclickListener("取消", new ApplyAndAlterDialog.onOnOnclickListener() {
                        @Override
                        public void onOnClick() {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                } else {
                    if (dialog1 == null)
                        dialog1 = new JiaJiaDialog(DingDanBuyInfoActivity.this);
                    dialog1.setCanceledOnTouchOutside(true);
                    dialog1.setVisibilityBtn(true);
                    dialog1.setText("加价金额：");
                    dialog1.setYesOnclickListener("确定", new JiaJiaDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick(final String tel) {
                            mTip = tel;
                            ApliyDialog dialog = new ApliyDialog(DingDanBuyInfoActivity.this, R.style
                                    .transparentFrameWindowStyle, tel + "",
                                    new ApliyDialog.SelectDialogCancelListener() {
                                        @Override
                                        public void onCancelClick(String id) {

                                            mPayType = id;
                                            if (mPayType.equals("1") || mPayType.equals("2")) {
                                                mPresenter.addOrderTip(mCode, mPayType, tel + "");//加价
                                            } else {

                                                passWordDialog = new PassWordDialog(DingDanBuyInfoActivity.this);
                                                passWordDialog.show();
                                                passWordDialog.setHintText(tel + "");
                                                passWordDialog.setMoneyNum(tel + "");
                                                passWordDialog.setError_tishi("请输入支付密码");
                                                passWordDialog.setClick(new PassWordDialog.OnPayClickListener() {
                                                    @Override
                                                    public void onSetPass(String text) {
                                                        isFuKuan = false;
                                                        mPresenter.checkSecondPassword(text + "");
                                                        passWordDialog.dismiss();
                                                    }

                                                    @Override
                                                    public void onSetPwd() {
                                                        passWordDialog.cancel();
                                                        Intent intent = new Intent(mContext, UpdatePhoneZhiFuMiMaActivity.class);
                                                        startActivity(intent);
                                                        passWordDialog.dismiss();
                                                    }
                                                });
                                            }
                                        }
                                    });
                            if (!DingDanBuyInfoActivity.this.isFinishing()) {
                                dialog.show();
                            }
                            dialog1.dismiss();

                        }
                    });
                    dialog1.setOnOnclickListener("取消", new JiaJiaDialog.onOnOnclickListener() {
                        @Override
                        public void onOnClick() {
                            dialog1.dismiss();
                        }
                    });
                    dialog1.show();

//                    mPresenter.addOrderTip(mCode, "3","");//加价
                }
                break;
            case R.id.tvQueQen:
                if (isJieDanYuan) {
                    if (status.equals("6")) {
//                        mPresenter.courier_order_submit(mCode);//确认订单
                        ToastUtil.show("去我的任务中完成订单");
                        Intent intent = new Intent(mContext, WoDeRenWuActivity.class);
                        intent.putExtra("tab", 1);
                        mContext.startActivity(intent);
//                        EventBus.getDefault().post(new RenWuEvent(1));
                        finish();
                    } else if (status.equals("5")) {
                        final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(DingDanBuyInfoActivity.this);
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.setMessage("亲，您确定要把订单退回给发货人吗，退回以后，订单不在接单大厅显示，钱退回给发货人软件余额，在退回之前请先和发货人联系，协商一致，如果发货人投诉您恶意撤销订单，我们会处罚10-200元", "");
                        dialog.setBackgroundResource(true);
                        dialog.setVisibilityBtn(true);
                        dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                mPresenter.order_refund(mCode);//退回接单人
                                dialog.dismiss();

                            }
                        });
                        dialog.setOnOnclickListener("取消", new ApplyAndAlterDialog.onOnOnclickListener() {
                            @Override
                            public void onOnClick() {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();

                    }
                } else {
                    mPresenter.order_submit(mCode);//确认订单
                }
                break;
        }
    }

    PassWordDialog passWordDialog;
    String mPayType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
