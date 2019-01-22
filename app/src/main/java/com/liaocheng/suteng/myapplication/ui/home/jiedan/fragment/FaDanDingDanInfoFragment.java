package com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.circle.common.base.BaseFragment;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.FaDanDingDanInfoPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.FaDanDingDanInfoContent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class FaDanDingDanInfoFragment extends BaseFragment<FaDanDingDanInfoPresenter> implements FaDanDingDanInfoContent.View {

    @BindView(R.id.tvCode)
    TextView tvCode;
    @BindView(R.id.tvFaName)
    TextView tvFaName;
    @BindView(R.id.tvFaTel)
    TextView tvFaTel;
    @BindView(R.id.tvFaJL)
    TextView tvFaJL;
    @BindView(R.id.tvFa)
    TextView tvFa;
    @BindView(R.id.linFa)
    LinearLayout linFa;
    @BindView(R.id.tvFaHuo)
    TextView tvFaHuo;
    @BindView(R.id.tvFaHUoXQ)
    TextView tvFaHUoXQ;
    @BindView(R.id.ivFaTel)
    ImageView ivFaTel;
    @BindView(R.id.relFa)
    RelativeLayout relFa;
    @BindView(R.id.tvShouName)
    TextView tvShouName;
    @BindView(R.id.tvShouTel)
    TextView tvShouTel;
    @BindView(R.id.tvShouJL)
    TextView tvShouJL;
    @BindView(R.id.tvShou)
    TextView tvShou;
    @BindView(R.id.linShou)
    LinearLayout linShou;
    @BindView(R.id.tvShouHuo)
    TextView tvShouHuo;
    @BindView(R.id.tvShouHUoXQ)
    TextView tvShouHUoXQ;
    @BindView(R.id.ivShouTel)
    ImageView ivShouTel;
    @BindView(R.id.tvMore)
    TextView tvMore;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvHuoType)
    TextView tvHuoType;
    @BindView(R.id.tvGongJu)
    TextView tvGongJu;
    @BindView(R.id.tvBaoWenXiang)
    TextView tvBaoWenXiang;
    @BindView(R.id.tvMsg)
    TextView tvMsg;
    @BindView(R.id.tvPaoTui)
    TextView tvPaoTui;
    @BindView(R.id.tvDingDanJiaJia)
    TextView tvDingDanJiaJia;
    @BindView(R.id.tvXiaoFei)
    TextView tvXiaoFei;
    @BindView(R.id.tvBaoJia)
    TextView tvBaoJia;
    @BindView(R.id.tvYouHuiQuan)
    TextView tvYouHuiQuan;
    @BindView(R.id.tvZhongliang)
    TextView tvZhongliang;
    @BindView(R.id.tvQuanCheng)
    TextView tvQuanCheng;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvBianHao)
    TextView tvBianHao;
    @BindView(R.id.tvShiJian)
    TextView tvShiJian;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvLaHei)
    TextView tvLaHei;
    @BindView(R.id.linFeiYong)
    LinearLayout linFeiYong;
    @BindView(R.id.tvQueRen)
    TextView tvQueRen;
    @BindView(R.id.tvJiaJia)
    TextView tvJiaJia;
    @BindView(R.id.tvCheXiao)
    TextView tvCheXiao;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    Unbinder unbinder;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.linJieDan)
    LinearLayout linJieDan;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fadan_dingdan_info;
    }

    @Override
    public void initEventAndData() {

    }

    String mCode;
    int mId;

    @SuppressLint("ValidFragment")
    public FaDanDingDanInfoFragment(int id, String code) {
        mId = id;
        mCode = code;
    }

    @Override
    public void initEventAndDataNoLazy() {
        super.initEventAndDataNoLazy();
        if (TextUtils.isEmpty(mCode)) {
            ToastUtil.show("订单号不正确");
            getActivity().finish();
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
    String starWeiDu;
    String starJingdu;
    String endWeiDu;
    String endJingdu;

    @Override
    public void setDingDa(DingDanBuyInfoModel DingDanBean) {
        status = DingDanBean.status;

        starWeiDu = DingDanBean.sendLat;
        starJingdu = DingDanBean.sendLong;
        endWeiDu = DingDanBean.latitude;
        endJingdu = DingDanBean.longitude;

//        distance = AMapUtils.calculateLineDistance(latlngA, marker.getPosition());//计算距离
        tvFaJL.setText(DingDanBean.distance + "");
        tvShouJL.setText(DingDanBean.distance + "");
        tvFaHuo.setText(DingDanBean.sendAddress + "");
        tvFaHUoXQ.setText(DingDanBean.sendConcreteAdd + "");
        tvShouHuo.setText(DingDanBean.receiveAddress + "");
        tvShouHUoXQ.setText(DingDanBean.receiveConcreteAdd + "");
        tvTime.setText("约定时间：" + DingDanBean.appointTime);
        mShouHuoTel = DingDanBean.sendPhone;
        mFaHUoTel = DingDanBean.contactPhone;
//        货品类型:1-鲜花2-食品3-文体4-蛋糕5-电子产品6-生活用品
        switch (DingDanBean.goods) {
            case "1":
                tvHuoType.setText("货品: 鲜花");
                break;
            case "2":
                tvHuoType.setText("货品: 食品");
                break;
            case "3":
                tvHuoType.setText("货品: 文体");
                break;
            case "4":
                tvHuoType.setText("货品: 蛋糕");
                break;
            case "5":
                tvHuoType.setText("货品: 电子产品");
                break;
            case "6":
                tvHuoType.setText("货品: 生活用品");
                break;
        }
//        交通工具类型:1-二轮车2-三轮车3-小客车4-小货车5-大货车
        switch (DingDanBean.trafficTool) {
            case "1":
                tvGongJu.setText("交通工具: 二轮车");
                break;
            case "2":
                tvGongJu.setText("交通工具:三轮车");
                break;
            case "3":
                tvGongJu.setText("交通工具: 小客车");
                break;
            case "4":
                tvGongJu.setText("交通工具: 小货车");
                break;
            case "5":
                tvGongJu.setText("交通工具: 大货车");
                break;
        }
        if (DingDanBean.incubator.equals("1")) {
            tvBaoWenXiang.setText("保温箱:需要");
        } else {
            tvBaoWenXiang.setText("保温箱:不需要");
        }
        receiveUserId = DingDanBean.receiveUserId;
        tvMsg.setText("备注信息：" + DingDanBean.description + "");
        tvPaoTui.setText("￥" + DingDanBean.standardTotal);
        tvDingDanJiaJia.setText("￥" + DingDanBean.addTips);
        tvXiaoFei.setText("￥" + DingDanBean.tip);
        tvBaoJia.setText("￥" + DingDanBean.parcel_insurance_fee);
        tvYouHuiQuan.setText("￥" + DingDanBean.coupon);
        tvZhongliang.setText("￥" + DingDanBean.weightPrice);
        tvQuanCheng.setText("全程约：" + DingDanBean.distance);
        tvNum.setText("接单总计：￥" + DingDanBean.total);
        tvBianHao.setText("订单编号：" + DingDanBean.orderCode);
        tvShiJian.setText("创建时间：" + DingDanBean.payTime);
        tvFaName.setText("发货人：" + DingDanBean.sendName);
        tvFaTel.setText("" + DingDanBean.sendPhone);
        tvShouName.setText("收货人：" + DingDanBean.receiveName);
        tvShouTel.setText("" + DingDanBean.contactPhone);
        tvName.setText("接单人：" + DingDanBean.transporterName);
        tvTel.setText("" + DingDanBean.transporterPhone);
        tvCode.setText("订单编号：" + DingDanBean.orderCode);

        switch (DingDanBean.status) {
            case "1"://未付款
                linJieDan.setVisibility(View.GONE);
                break;
            case "2"://未被抢
                linJieDan.setVisibility(View.GONE);
                break;
            case "3"://取货中

                break;
            case "4"://已完成

                break;
            case "5"://送货中

                break;
            case "6"://待提交

                break;
            case "7"://已退款

                break;
            case "8"://指定接单中

                break;
            case "9"://转让订单中

                break;
        }

    }

    @Override
    public void order_submit() {
        ToastUtil.show("确认成功");
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void addOrderTip(PayModel model) {
        ToastUtil.show("支付成功");
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void user_order_refund() {
        ToastUtil.show("撤销成功");
      getActivity().finish();
    }

    @Override
    public void addBlacklist() {
        ToastUtil.show("拉黑成功");
        mPresenter.getDingDa(mCode);
    }

    String mShouHuoTel;
    String mFaHUoTel;

String receiveUserId;
    boolean isJieDan = true;
    boolean isZhuanDan = true;

    @OnClick({R.id.tvQueRen, R.id.tvMore, R.id.tvJiaJia, R.id.ivShouTel, R.id.tvLaHei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvQueRen:
                mPresenter.order_submit(mCode);
                break;

            case R.id.tvMore:
                if (isShow) {
                    linFeiYong.setVisibility(View.VISIBLE);
                    isShow = false;
//                    scrollToBottom(scrollView,linFeiYong);
                } else {
                    linFeiYong.setVisibility(View.GONE);
                    isShow = true;
                }

                break;
            case R.id.tvJiaJia:
                mPresenter.addOrderTip(mCode,"");
                break;
            case R.id.tvCheXiao:
                mPresenter.user_order_refund(mCode);
                break;
            case R.id.tvLaHei:
                mPresenter.addBlacklist(receiveUserId);
                break;
        }
    }

    boolean isShow = true;

    public static void scrollToBottom(final View scroll, final View inner) {

        Handler mHandler = new Handler();

        mHandler.post(new Runnable() {
            public void run() {
                if (scroll == null || inner == null) {
                    return;
                }
                int offset = inner.getMeasuredHeight() - scroll.getHeight();
                if (offset < 0) {
                    offset = 0;
                }

                scroll.scrollTo(0, offset);
            }
        });
    }

}
