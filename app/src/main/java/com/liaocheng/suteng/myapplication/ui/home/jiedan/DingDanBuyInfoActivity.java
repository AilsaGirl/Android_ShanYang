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

    @Override
    public int getLayoutId() {
        return R.layout.activity_dingdan_buy_info;
    }

    boolean isJieDanYuan;
    String mCode;

    @Override
    public void initEventAndData() {
        toolbar.setTitleText("订单详情").setBackFinish();
        Intent intent = getIntent();
        isJieDanYuan = intent.getBooleanExtra("isJieDanYuan", false);
        mCode = intent.getStringExtra("code");
        if (TextUtils.isEmpty(mCode)) {
            ToastUtil.show("订单号不正确");
            finish();
            return;
        } else {
            mPresenter.getDingDa(mCode);
        }

    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    String status;

    @Override
    public void setDingDa(DingDanBuyInfoModel DingDanBean) {
        if (DingDanBean.orderType.equals("1")) {
            toolbar.setTitleText("订单详情-帮我买");
        }
        if (DingDanBean.orderType.equals("2")) {
            toolbar.setTitleText("订单详情-帮我办");
        }
        tvMsg.setText(DingDanBean.description + "");
        tvRen.setText(DingDanBean.receiveName + "");
        tvTel.setText(DingDanBean.contactPhone + "");
        tvAddress.setText(DingDanBean.receiveAddress + "");
        tvMoreAddress.setText(DingDanBean.receiveConcreteAdd + "");
        tvTime.setText("约定时间：" + DingDanBean.appointTime + "");
        tvHuoKuan.setText("￥" + DingDanBean.tip + "");
        tvXiaoFei.setText("￥" + DingDanBean.addTips + "");
        tvTotal.setText("￥" + DingDanBean.total + "");
        tvBianHao.setText("订单编号：" + DingDanBean.orderCode + "");
        tvShiJian.setText("下单时间：" + DingDanBean.payTime + "");

        status = DingDanBean.status;
        if (isJieDanYuan) {
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
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.VISIBLE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
                    break;
                case "5":
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.VISIBLE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.VISIBLE);
                    tvQiangDan.setText("短信验证收货");
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
                    tvShanChu.setText("同意转单");
                    tvZhiFu.setText("拒绝转单");
                    break;
            }
        } else {
            switch (DingDanBean.status) {
                case "1":
                    linFuKuan.setVisibility(View.VISIBLE);
                    linJieDan.setVisibility(View.GONE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);

//                    String s = StringData();
//                    if (timeCount != null)
//                        timeCount.cancel();
//                    timeCount = new TimeCount(time(StringData()) - System.currentTimeMillis(), 1000);
//                    timeCount.start(); //开始倒计时
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
                    linFuKuan.setVisibility(View.GONE);
                    linJieDan.setVisibility(View.VISIBLE);
                    linQuHuo.setVisibility(View.GONE);
                    tvQiangDan.setVisibility(View.GONE);
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

        tvJieDanRen.setText(DingDanBean.transporterName + "");
        tvJieDanRenTel.setText(DingDanBean.transporterPhone + "");

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
        ToastUtil.show("支付成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void user_order_refund() {
        ToastUtil.show("撤销成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void addOrderTip(PayModel model) {
        ToastUtil.show("支付成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void order_grab() {
        ToastUtil.show("抢单成功");
        leftTime = 0;
        handler.removeCallbacks(update_thread);
        mPresenter.getDingDa(mCode);
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

    @OnClick({R.id.tvShanChu, R.id.tvZhiFu, R.id.tvQiangDan, R.id.tvCheXiao, R.id.tvJiaJia, R.id.tvQueQen,R.id.tvZhuanRang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvZhuanRang:
                mPresenter.transferOrder(mCode,"13396358230");
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
                    mPresenter.order_pay(mCode, "3");//付款
                }
                break;
            case R.id.tvQiangDan:
                if (isJieDanYuan) {
                    if (status.equals("2")) {
                        mPresenter.order_grab(mCode);//抢单
                    } else if (status.equals("5")) {
                        mPresenter.checkReceiveCode(mCode, "11111");
                    }
                } else {

                }
                break;
            case R.id.tvCheXiao:
                if (isJieDanYuan) {
                    mPresenter.getThePickup(mCode);//到店取货
                } else {
                    mPresenter.user_order_refund(mCode);//撤销
                }
                break;
            case R.id.tvJiaJia:
                if (isJieDanYuan) {
                    mPresenter.order_revoke(mCode);//退回大厅
                } else {
                    mPresenter.addOrderTip(mCode, "3");//加价
                }
                break;
            case R.id.tvQueQen:
                if (isJieDanYuan) {
                    if (status.equals("6")) {
                        mPresenter.courier_order_submit(mCode);//确认订单
                    } else if (status.equals("3")) {
                        mPresenter.order_refund(mCode);//退回接单人
                    }
                } else {
                    mPresenter.order_submit(mCode);//确认订单
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
