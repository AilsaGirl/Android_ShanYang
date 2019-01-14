package com.liaocheng.suteng.myapplication.ui.home.jiedan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.presenter.DingDanInfoPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.DingDanInfoContent;
import com.liaocheng.suteng.myapplication.ui.my.YouHuiQuanActivity;
import com.liaocheng.suteng.myapplication.view.YouHuiQuanDialog;
import com.liaocheng.suteng.myapplication.view.ZhuanRangDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class DingDanInfoActivity extends BaseActivity<DingDanInfoPresenter> implements DingDanInfoContent.View {
    @BindView(R.id.toolbar)
    MyToolBar toolbar;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.tvJuLi)
    TextView tvJuLi;
    @BindView(R.id.relHead)
    RelativeLayout relHead;
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
    @BindView(R.id.tvMore)
    TextView tvMore;
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
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvBianHao)
    TextView tvBianHao;
    @BindView(R.id.tvShiJian)
    TextView tvShiJian;
    @BindView(R.id.linFeiYong)
    LinearLayout linFeiYong;
    @BindView(R.id.tvQiangDan)
    TextView tvQiangDan;
    @BindView(R.id.tvQuanCheng)
    TextView tvQuanCheng;
    @BindView(R.id.scrollView)
    ScrollView scrollView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_dingdan_info;
    }

    /*定位相关*/
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private void initListener() {
        //初始化定位
        mLocationClient = new AMapLocationClient(mContext.getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }

    double lon;
    double lat;
    String mQu;
    String mCity;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    mQu = aMapLocation.getDistrict();
                    mCity = aMapLocation.getCity();
                    lon = aMapLocation.getLongitude();
                    lat = aMapLocation.getLatitude();
                    SPCommon.setString("lon", aMapLocation.getLongitude() + "");
                    SPCommon.setString("lat", aMapLocation.getLatitude() + "");
                    SPCommon.setString("qu", aMapLocation.getDistrict() + "");
                    SPCommon.setString("city", aMapLocation.getCity() + "");
                    if (status.equals("2")||status.equals("3")){
                        LatLng start = new LatLng(lat, lon);
                        LatLng end = new LatLng(Double.parseDouble(starWeiDu), Double.parseDouble(starJingdu));
                        tvJuLi.setText("距离发货地"+String.format("%.2f", getDistance(start,end))+"公里");
                    }
                    if (status.equals("5")){
                        LatLng start = new LatLng(lat, lon);
                        LatLng end = new LatLng(Double.parseDouble(endWeiDu), Double.parseDouble(endJingdu));
                        tvJuLi.setText("距离送货地"+String.format("%.2f", getDistance(start,end))+"公里");
                    }
                    mPresenter.initMap(mapView, lat + "", lon + "", starWeiDu, starJingdu, endWeiDu, endJingdu, 1);
//                    mPresenter.initMap(mapView,lat+"",lon+"", "36.46807421644299", "115.95567726928711", "36.47028295377302", "115.97867989379883",1);

//        distance = AMapUtils.calculateLineDistance(latlngA, marker.getPosition());//计算距离


                } else {

                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("yue.huang", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    String mCode;
    public double getDistance(LatLng start, LatLng end) {

        double lon1 = (Math.PI / 180) * start.longitude;
        double lon2 = (Math.PI / 180) * end.longitude;
        double lat1 = (Math.PI / 180) * start.latitude;
        double lat2 = (Math.PI / 180) * end.latitude;

        // 地球半径
        double R = 6371;

        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1))
                * R;

        return d ;
    }

    @Override
    public void initEventAndData() {
        toolbar.setBackFinish().setTitleText("订单详情");
//        mPresenter.initMap(mapView,lat+"",lon+"", "36.46807421644299", "115.95567726928711", "36.47028295377302", "115.97867989379883");
        Intent intent = getIntent();
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
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
        if (DingDanBean.status.equals("2")) {
            tvQiangDan.setText("抢单");
        }
        if (DingDanBean.status.equals("3")) {
            tvQiangDan.setText("转让订单");
        }
        starWeiDu = DingDanBean.sendLat;
        starJingdu = DingDanBean.sendLong;
        endWeiDu = DingDanBean.latitude;
        endJingdu = DingDanBean.longitude;
        switch (DingDanBean.status) {
            case "1"://未付款
                break;
            case "2"://未被抢
                mapView.setVisibility(View.VISIBLE);
                initListener();
                break;
            case "3"://取货中
                mapView.setVisibility(View.VISIBLE);
                initListener();
                break;
            case "4"://已完成
                mapView.setVisibility(View.GONE);
                break;
            case "5"://送货中
                mapView.setVisibility(View.VISIBLE);
                initListener();
                break;
            case "6"://待提交
                mapView.setVisibility(View.VISIBLE);
                initListener();
                break;
            case "7"://已退款
                mapView.setVisibility(View.GONE);
                break;
            case "8"://指定接单中
                mapView.setVisibility(View.VISIBLE);
                initListener();
                break;
            case "9"://转让订单中
                mapView.setVisibility(View.VISIBLE);
                initListener();
                break;
        }
        tvJuLi.setText("距离取货地");
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


    }

    String mShouHuoTel;
    String mFaHUoTel;

    @Override
    public void order_grab() {
        ToastUtil.show("抢单成功");
        mPresenter.getDingDa(mCode);
    }

    @Override
    public void transferOrder() {
        ToastUtil.show("处理成功");
        finish();
    }

    boolean isJieDan = true;
    boolean isZhuanDan = true;

    @OnClick({R.id.tvQiangDan, R.id.tvMore,R.id.ivFaTel,R.id.ivShouTel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvQiangDan:
                if (status.equals("2")) {
                    mPresenter.order_grab(mCode);//抢单
                } else if (status.equals("3")) {
                    final ZhuanRangDialog dialog = new ZhuanRangDialog(DingDanInfoActivity.this);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.setVisibilityBtn(true);
                    dialog.setYesOnclickListener("确定", new ZhuanRangDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick(String tel) {
                            mPresenter.transferOrder(mCode, tel);
                            dialog.dismiss();

                        }
                    });
                    dialog.setOnOnclickListener("取消", new ZhuanRangDialog.onOnOnclickListener() {
                        @Override
                        public void onOnClick() {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }

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
            case R.id.ivFaTel:
                Intent intent = new Intent(Intent.ACTION_DIAL);

                Uri data = Uri.parse("tel:" + mFaHUoTel);

                intent.setData(data);

                startActivity(intent);
                break;
            case R.id.ivShouTel:
                Intent intents = new Intent(Intent.ACTION_DIAL);

                Uri datas = Uri.parse("tel:" + mShouHuoTel);

                intents.setData(datas);

                startActivity(intents);
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
