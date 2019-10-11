package com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.FaDanDingDanInfoPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.FaDanDingDanInfoContent;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.PingJiaActivity;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.DingDanBuyInfoActivity;
import com.liaocheng.suteng.myapplication.ui.my.UpdatePhoneZhiFuMiMaActivity;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.ApliyDialog;
import com.liaocheng.suteng.myapplication.view.JiaJiaDialog;
import com.liaocheng.suteng.myapplication.view.PassWordDialog;

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
    @BindView(R.id.linQiang)
    LinearLayout linQiang;
    @BindView(R.id.tvZhiFu)
    TextView tvZhiFu;


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
    String receiveUserId = "";
    @Override
    public void setDingDa(DingDanBuyInfoModel DingDanBean) {
        status = DingDanBean.status;
        receiveUserId = DingDanBean.receiveUserId;
        starWeiDu = DingDanBean.sendLat;
        starJingdu = DingDanBean.sendLong;
        endWeiDu = DingDanBean.latitude;
        endJingdu = DingDanBean.longitude;

//        distance = AMapUtils.calculateLineDistance(latlngA, marker.getPosition());//计算距离
        tvFaJL.setText(DingDanBean.distance + "");
        tvShouJL.setText(DingDanBean.distance + "");
        tvFaHuo.setText(DingDanBean.sendAddress + "");
        tvFaHUoXQ.setText(DingDanBean.sendConcreteAdd + ""+DingDanBean.sendDetailAdd);
        tvShouHuo.setText(DingDanBean.receiveAddress + "");
        tvShouHUoXQ.setText(DingDanBean.receiveConcreteAdd + ""+DingDanBean.receiveDetailAdd);
        tvTime.setText("约定时间：" + DingDanBean.appointTime);
        mShouHuoTel = DingDanBean.contactPhone;
        mFaHUoTel = DingDanBean.sendPhone;
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
        tvYouHuiQuan.setText("￥ - " + DingDanBean.coupon);
        tvZhongliang.setText("￥" + DingDanBean.weightPrice);
        tvQuanCheng.setText("全程约：" + DingDanBean.distance);
        tvNum.setText("发单总计：￥" + DingDanBean.total);
        tvBianHao.setText("订单编号：" + DingDanBean.orderCode);
        tvShiJian.setText("创建时间：" + DingDanBean.payTime);
        tvFaName.setText("发货人：" + DingDanBean.sendName);
        tvFaTel.setText("" + DingDanBean.sendPhone);
        tvShouName.setText("收货人：" + DingDanBean.receiveName);
        tvShouTel.setText("" + DingDanBean.contactPhone);
        tvName.setText("接单人：" + DingDanBean.transporterName);
        tvTel.setText("" + DingDanBean.transporterPhone);
        tvCode.setText("订单编号：" + DingDanBean.orderCode);
        tvBianHao.setText("订单编号：" + DingDanBean.orderCode + "");
        if (DingDanBean.status.equals("1")){
            tvShiJian.setText("创建时间：" + DingDanBean.createTime + "");
        }else{
            tvShiJian.setText("下单时间：" + DingDanBean.payTime + "");
        }
        mNum = DingDanBean.total+"";
        switch (DingDanBean.status) {
            case "1"://未付款
                tvZhiFu.setVisibility(View.VISIBLE);
                linQiang.setVisibility(View.GONE);
                linJieDan.setVisibility(View.GONE);

                break;
            case "2"://未被抢
                tvZhiFu.setVisibility(View.GONE);
                linQiang.setVisibility(View.VISIBLE);
                linJieDan.setVisibility(View.GONE);
                tvQueRen.setVisibility(View.GONE);
                break;
            case "3"://取货中

                break;
            case "4"://已完成
//                if (DingDanBean.sendIsComment.equals("0")){
//                    tvZhiFu.setVisibility(View.VISIBLE);
//                    tvZhiFu.setText("去评价");
//                }else {
//                    tvZhiFu.setVisibility(View.GONE);
//                }
                tvZhiFu.setVisibility(View.GONE);
                linQiang.setVisibility(View.GONE);

                break;
            case "5"://送货中

                break;
            case "6"://待提交
                tvZhiFu.setVisibility(View.GONE);
                linQiang.setVisibility(View.GONE);
                break;
            case "7"://已退款
                tvZhiFu.setVisibility(View.GONE);
                linQiang.setVisibility(View.GONE);
                linJieDan.setVisibility(View.GONE);
                break;
            case "8"://指定接单中
                tvZhiFu.setVisibility(View.GONE);
                linQiang.setVisibility(View.VISIBLE);
                linJieDan.setVisibility(View.GONE);
                tvQueRen.setVisibility(View.GONE);
                break;
            case "9"://转让订单中
                tvZhiFu.setVisibility(View.GONE);
                linQiang.setVisibility(View.VISIBLE);
                linJieDan.setVisibility(View.GONE);
                tvQueRen.setVisibility(View.GONE);
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
        if (mPayType.equals("1")||mPayType.equals("2")){
            Util.Pay(mPayType,model,mContext);
        }else {
            ToastUtil.show("支付成功");

        }
        mPresenter.getDingDa(mCode);
    }
    //支付成功
    @Override
    public void order_pay(PayModel model) {
        if (mPayType.equals("1")||mPayType.equals("2")){
            Util.Pay(mPayType,model,mContext);
        }else {
            ToastUtil.show("支付成功");

        }

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

    @Override
    public void checkSecondPassword() {
        if (isFuKuan){
            mPresenter.order_pay(mCode, mPayType);//付款
        }else {
            mPresenter.addOrderTip(mCode, mPayType,mTip);//加价
        }
    }

    String mShouHuoTel;
    String mFaHUoTel;

    String mPayType;
    String mTip;
    boolean isJieDan = true;
    boolean isZhuanDan = true;
    boolean isFuKuan = true;
    PassWordDialog passWordDialog;
    String mNum = "";
    @OnClick({R.id.tvQueRen, R.id.tvMore, R.id.tvJiaJia, R.id.tvCheXiao, R.id.tvLaHei,R.id.tvZhiFu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvZhiFu:
                if (status.equals("4")){

//                    Intent   intent = new Intent(mContext, PingJiaActivity.class);
//                    intent.putExtra("orderCode",mCode+"");
//                    intent.putExtra("eva_userId",receiveUserId+"");
//                    mContext.startActivity(intent);
                }else {
                    ApliyDialog dialog = new ApliyDialog(getActivity(), R.style
                            .transparentFrameWindowStyle,mNum+"",
                            new ApliyDialog.SelectDialogCancelListener() {
                                @Override
                                public void onCancelClick(String id) {

                                    mPayType = id;
                                    if (mPayType .equals("1")||mPayType .equals("2")){
                                        mPresenter.order_pay(mCode, mPayType);//付款
                                    }else {

                                        passWordDialog = new PassWordDialog(getContext());
                                        passWordDialog.show();
                                        passWordDialog.setHintText(mNum + "");
                                        passWordDialog.setMoneyNum(mNum+"");
                                        passWordDialog.setError_tishi("请输入支付密码");
                                        passWordDialog.setClick(new PassWordDialog.OnPayClickListener() {
                                            @Override
                                            public void onSetPass(String text) {
                                                isFuKuan = true;
                                                mPresenter.checkSecondPassword(text+"");
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
                    if (!getActivity().isFinishing()) {
                        dialog.show();
                    }
                }

                break;
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
                final JiaJiaDialog dialogs = new JiaJiaDialog(mContext);
                dialogs.setCanceledOnTouchOutside(true);
                dialogs.setVisibilityBtn(true);
                dialogs.setText("加价金额：");
                dialogs.setYesOnclickListener("确定", new JiaJiaDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick(final String tel) {
                        mTip = tel;
                        ApliyDialog dialog = new ApliyDialog(getActivity(), R.style
                                .transparentFrameWindowStyle, tel + "",
                                new ApliyDialog.SelectDialogCancelListener() {
                                    @Override
                                    public void onCancelClick(String id) {

                                        mPayType = id;
                                        if (mPayType.equals("1") || mPayType.equals("2")) {
                                            mPresenter.addOrderTip(mCode, mPayType, tel + "");//加价
                                        } else {
                                            isFuKuan = false;
                                            final PassWordDialog passWordDialog = new PassWordDialog(mContext);
                                            passWordDialog.show();
                                            passWordDialog.setHintText(tel + "");
                                            passWordDialog.setMoneyNum(tel + "");
                                            passWordDialog.setError_tishi("请输入支付密码");
                                            passWordDialog.setClick(new PassWordDialog.OnPayClickListener() {
                                                @Override
                                                public void onSetPass(String text) {
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
                        if (!getActivity().isFinishing()) {
                            dialog.show();
                        }
                        dialogs.dismiss();

                    }
                });
                dialogs.setOnOnclickListener("取消", new JiaJiaDialog.onOnOnclickListener() {
                    @Override
                    public void onOnClick() {
                        dialogs.dismiss();
                    }
                });
                dialogs.show();
//                mPresenter.addOrderTip(mCode,"");
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
