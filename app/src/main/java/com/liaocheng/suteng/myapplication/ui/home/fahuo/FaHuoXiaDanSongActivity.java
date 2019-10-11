package com.liaocheng.suteng.myapplication.ui.home.fahuo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.FaDanXiaDanModel;
import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;
import com.liaocheng.suteng.myapplication.model.FindAreaWeightBean;
import com.liaocheng.suteng.myapplication.model.FindParcelInsuranceBean;
import com.liaocheng.suteng.myapplication.model.OrderCalculateBean;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.presenter.FaHuoXiaDanPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.FaHuoXiaDanContent;
import com.liaocheng.suteng.myapplication.ui.home.address.AddressList;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.adapter.MyTimeAdapter;
import com.liaocheng.suteng.myapplication.ui.my.JiJiaBiaoZhun;
import com.liaocheng.suteng.myapplication.ui.my.UpDataMyInFoActivity;
import com.liaocheng.suteng.myapplication.ui.my.UpdatePhoneZhiFuMiMaActivity;
import com.liaocheng.suteng.myapplication.ui.my.XieYiActivity;
import com.liaocheng.suteng.myapplication.ui.my.YouHuiQuanActivity;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.ApliyDialog;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;
import com.liaocheng.suteng.myapplication.view.BaoJiaDialog;
import com.liaocheng.suteng.myapplication.view.PassWordDialog;
import com.liaocheng.suteng.myapplication.view.SelectDialog;
import com.liaocheng.suteng.myapplication.view.YouHuiQuanDialog;
import com.liaocheng.suteng.myapplication.view.ZhongLiangDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class FaHuoXiaDanSongActivity extends BaseActivity<FaHuoXiaDanPresenter> implements FaHuoXiaDanContent.View {

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
    @BindView(R.id.linFa)
    LinearLayout linFa;
    @BindView(R.id.tvDiZhiFa)
    TextView tvDiZhiFa;
    @BindView(R.id.tvDiZhiXiangQingFa)
    TextView tvDiZhiXiangQingFa;
    @BindView(R.id.tvTelFa)
    TextView tvTelFa;
    @BindView(R.id.linAddress)
    LinearLayout linAddress;
    @BindView(R.id.tvDiZhiChangYongFa)
    TextView tvDiZhiChangYongFa;
    @BindView(R.id.relFa)
    RelativeLayout relFa;
    @BindView(R.id.tvShouJL)
    TextView tvShouJL;
    @BindView(R.id.linShou)
    LinearLayout linShou;
    @BindView(R.id.tvDiZhiShou)
    TextView tvDiZhiShou;
    @BindView(R.id.tvDiZhiXiangQingShou)
    TextView tvDiZhiXiangQingShou;
    @BindView(R.id.tvTelShou)
    TextView tvTelShou;
    @BindView(R.id.linAddressShou)
    LinearLayout linAddressShou;
    @BindView(R.id.tvDiZhiChangYongShou)
    TextView tvDiZhiChangYongShou;
    @BindView(R.id.relShou)
    RelativeLayout relShou;
    @BindView(R.id.tvMore)
    TextView tvMore;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvGongJu)
    TextView tvGongJu;
    @BindView(R.id.tvZhongLiang)
    TextView tvZhongLiang;
    @BindView(R.id.cbBaoWen)
    CheckBox cbBaoWen;
    @BindView(R.id.tvBaoJia)
    TextView tvBaoJia;
    @BindView(R.id.tvHuoType)
    TextView tvHuoType;
    @BindView(R.id.etMsg)
    EditText etMsg;
    @BindView(R.id.etTip)
    EditText etTip;
    @BindView(R.id.etTel)
    EditText etTel;
    @BindView(R.id.ckTY)
    CheckBox ckTY;
    @BindView(R.id.tvXieYi)
    TextView tvXieYi;
    @BindView(R.id.linOther)
    LinearLayout linOther;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvBianZun)
    TextView tvBianZun;
    @BindView(R.id.tvXiaDan)
    TextView tvXiaDan;
    @BindView(R.id.linInfo)
    LinearLayout linInfo;
    @BindView(R.id.scrollView)
    LinearLayout scrollView;
    @BindView(R.id.tvYouHuiQuan)
    TextView tvYouHuiQuan;
    @BindView(R.id.ckTY1)
    CheckBox ckTY1;
    @BindView(R.id.tvXieYi1)
    TextView tvXieYi1;
    @BindView(R.id.tvYouHuiQuan1)
    TextView tvYouHuiQuan1;
    @BindView(R.id.linXieYi)
    LinearLayout linXieYi;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fahuoxiadan_song;
    }

    @Override
    public void initEventAndData() {
        toolbar.setTitleText("订单信息").setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(FaHuoXiaDanSongActivity.this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setMessage("退出后设置的信息会丢失,是否退出", "");
                dialog.setBackgroundResource(true);
                dialog.setVisibilityBtn(true);
                dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        dialog.dismiss();
                        finish();
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
        });
        initData();

        etTip.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //s:变化后的所有字符
//                Toast.makeText(getContext(), "变化:"+s+";"+start+";"+before+";"+count, Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化:"+s+";"+start+";"+before+";"+count);
                if (TextUtils.isEmpty(etTip.getText())){
//                    tvNum.setText(mLuFei +"");
                }else {
                    String num = etTip.getText().toString();
                    double a = Double.valueOf(mLuFei);
                    double b = Double.valueOf(num);
                    double c = a+b;
                    BigDecimal bd   =   new   BigDecimal(c);
                double    tip   =   bd.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    tvNum.setText(tip +"");

                }
                tip = etTip.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
//                Toast.makeText(getContext(), "变化前:"+s+";"+start+";"+count+";"+after, Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化前:"+s+";"+start+";"+count+";"+after);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数
//                Toast.makeText(getContext(), "变化后:"+s+";", Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化后:"+s+";");
                if (TextUtils.isEmpty(etTip.getText())){
//                    tvNum.setText(mLuFei +"");
                }else {
                    String num = etTip.getText().toString();
                    double a = Double.valueOf(mLuFei);
                    double b = Double.valueOf(num);
                    double c = a+b;
                    BigDecimal bd   =   new   BigDecimal(c);
                    double    tip   =   bd.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    tvNum.setText(tip +"");

                }
                tip = etTip.getText().toString();
            }
        });

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
//        mLocationOption.setOnceLocationLatest(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }

    double lon;
    double lat;
    String mQu;
    String mCity;
    int dingwei = 1;
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
                    if (dingwei==1){
                        mPresenter.initMap(mapView, lat + "", lon + "", sendLat, sendLong, latitude, longitude, 2);
//                    mPresenter.findAreaWeight("",sendLat + "", sendLong + "","2");
                        mPresenter.findParcelInsurance("",sendLat + "", sendLong + "");
                    }
                    dingwei ++;
//                    mPresenter.initMap(mapView,lat+"",lon+"", "36.46807421644299", "115.95567726928711", "36.47028295377302", "115.97867989379883",1);
//                    LatLng start = new LatLng(lat, lon);
                    LatLng start = new LatLng(Double.parseDouble(sendLat), Double.parseDouble(sendLong));
                    LatLng end = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
//                    tvJuLi.setText("订单距离" + String.format("%.2f", getDistance(start, end)) + "公里,大约需要"+getTime(String.format("%.2f", getDistance(start, end)) )+"分钟");
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
    private int getTime(String d){
        double t = 3.00;
        int time = 30;
        double a = Double.parseDouble(d);
        if (t>a||t==a){
            return time;
        }else {
            String z = d.substring(0, d.indexOf("."))+"";
            String y = d.substring(d.indexOf(".")+1)+"";
            int x = Integer.parseInt(z);
//            int c = x/100;
            if (Integer.parseInt(y)>0){
                time =30+ (x+1-3)*6;
            }else {
                time =30+ (x-3)*6;
            }

//          int s = Integer.parseInt ( (a*100)+"")/100 + 1;
//          time = (s-1)*10;
            return time;
        }

    }
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

        return d;
    }

    //    "36.46807421644299", "115.95567726928711", "36.47028295377302", "115.97867989379883"

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        List list = new ArrayList();
        byte[] i= new byte[list.size()];
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    String tip = ""; //
    String coupon = "0.00";//优惠券
    String weight = "1";//重量:只能传数字(最多6个字符)
    String trafficTool = "1";//	交通工具: 1-二轮车 2-三轮车 3-小客车 4-小货车 5-大货车
    String parcel_insurance_id = "0";//包裹保费id(通过接口获取)

    String orderType = ""; //	String	是	订单类型: 1-帮我买 2-帮我办 3-帮我送 4-校园快送 5-合作商家 6-县域快送
    String sendName = ""; //	String	是	发单人姓名(最多20个字符)
    String sendPhone = ""; //	String	是	发单人手机号(最多11个字符)
    String sendAddress = ""; //	String	是	订单类型为1为购买地(建筑物) 订单类型为2为办事地(建筑物) 订单类型为3-6为发货地(建筑物)
    String sendConcreteAdd = ""; //	String	是	上面的详细地址信息XX省XX市XXX
    String sendDetailAdd = ""; //	String	是	上面的手写补充地址信息
    String sendLat = ""; //	String	是	订单类型为1 为购买地纬度 订单类型为 2为办事地纬度  订单类型为3-6为发货地纬度
    String sendLong = ""; //	String	是	订单类型为 1为购买地经度订单类型为 2为办事地经度  订单类型为3-6为发货地经度
    String receiveName = ""; //	String	是	收货人姓名(最多10个字符)
    String contactPhone = ""; //	String	是	收货人手机号(最多11个字符)
    String receiveAddress = ""; //	String	是	收货地(建筑物)
    String receiveConcreteAdd = ""; //	String	是	上面的详细地址信息XX省XX市XXX
    String receiveDetailAdd = ""; //	String	是	上面的手写补充地址信息
    String longitude = ""; //	String	是	收货地经度
    String latitude = ""; //	String	是	收货地纬度
    String goods = "6"; //	String	是	货物类型: 1-鲜花 2-食品 3-文体 4-蛋糕 5-电子产品 6-生活用品
    String appointTime = ""; //	String	是	约定时间
    String description = ""; //	String	是	备注(最多200个字符)
    String incubator = "0"; //	String	是	保温箱:0-不需要(未选,默认填) 1-需要
    String receivePhone = ""; //	String	是	接单人手机号(最多11个字符)
//    String sendLat = "36.46807421644299"; //	String	是	订单类型为1 为购买地纬度 订单类型为 2为办事地纬度  订单类型为3-6为发货地纬度
//    String sendLong = "115.95567726928711"; //	String	是	订单类型为 1为购买地经度订单类型为 2为办事地经度  订单类型为3-6为发货地经度
//    String longitude = "115.97867989379883"; //	String	是	收货地经度
//    String latitude = "36.47028295377302"; //	String	是	收货地纬度
    FaHuoAddressModel addressModelFa = new FaHuoAddressModel();
    FaHuoAddressModel addressModelShou = new FaHuoAddressModel();

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(RecruitEvent event) {
        if (event == null)
            return;
        if (event.getAddressModel() != null) {

            addressModelFa = event.getAddressModel();
            sendName = addressModelFa.contactName + "";
            orderType =addressModelFa.type+"";
            sendPhone = addressModelFa.contactPhone + "";
            sendAddress = addressModelFa.address + "";
            sendConcreteAdd = addressModelFa.ConcreteAdd + "";
            sendDetailAdd = addressModelFa.detailAddress + "";

            sendLat = addressModelFa.lat;
            sendLong = addressModelFa.lon;
//            mPresenter.orderNum("","3",lat+"",lon+"","","","","","","","");
        }
        if (event.getAddressModelShou() != null) {
            addressModelShou = event.getAddressModelShou();
            receiveName = addressModelShou.contactName + "";
            contactPhone = addressModelShou.contactPhone + "";
            receiveAddress = addressModelShou.address + "";
            receiveConcreteAdd = addressModelShou.ConcreteAdd + "";
            receiveDetailAdd = addressModelShou.detailAddress + "";
            latitude = addressModelShou.lat;
            longitude = addressModelShou.lon;
        }
        tvDiZhiFa.setText(sendAddress+"");
        tvDiZhiXiangQingFa.setText(sendDetailAdd+"");
        tvTelFa.setText(sendPhone+"");
        tvDiZhiShou.setText(receiveAddress+"");
        tvDiZhiXiangQingShou.setText(receiveDetailAdd+"");
        tvTelShou.setText(contactPhone+"");
//        System.out.print("哈哈哈哈哈哈哈");
//        ToastUtil.show("ahhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

        mPresenter.findAreaWeight("",sendLat + "", sendLong + "",trafficTool);
//        mPresenter.orderNum("", orderType+"", sendLat + "", sendLong + "", longitude + "", latitude + "", tip, coupon, weight, trafficTool, parcel_insurance_id);
        initListener();
        dingwei = 0;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(this);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setMessage("退出后设置的信息会丢失,是否退出", "");
            dialog.setBackgroundResource(true);
            dialog.setVisibilityBtn(true);
            dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    dialog.dismiss();
                    finish();
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
        return false;
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }
Intent intent;
    boolean isMore = false;
    @OnClick({R.id.tvDiZhiXiangQingFa, R.id.tvDiZhiChangYongFa, R.id.tvDiZhiChangYongShou, R.id.tvMore, R.id.tvTime, R.id.tvGongJu, R.id.tvZhongLiang, R.id.tvBaoJia, R.id.tvHuoType, R.id.cbBaoWen, R.id.tvXieYi, R.id.tvYouHuiQuan, R.id.ckTY1, R.id.tvXieYi1, R.id.tvYouHuiQuan1, R.id.tvBianZun, R.id.tvXiaDan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDiZhiXiangQingFa:

                break;
            case R.id.tvDiZhiChangYongFa:
                EventBus.getDefault().postSticky(new RecruitEvent(addressModelFa,addressModelShou));
                intent = new Intent(mContext, AddressList.class);
                intent.putExtra("tip",311);//32  帮我送跳地址  选择地址后直接下单
                intent.putExtra("address_data", addressModelFa);
                mContext.startActivity(intent);
                break;
            case R.id.tvDiZhiChangYongShou:
                EventBus.getDefault().postSticky(new RecruitEvent(addressModelFa,addressModelShou));
                intent = new Intent(mContext, AddressList.class);
                intent.putExtra("tip",321);//32  帮我送跳地址  选择地址后直接下单
                intent.putExtra("address_data", addressModelFa);
                mContext.startActivity(intent);
                break;
            case R.id.tvMore:
                if (isMore) {
                    isMore = false;
                    linOther.setVisibility(View.GONE);
                    linXieYi.setVisibility(View.VISIBLE);
                    if (ckTY.isChecked()){
                        ckTY1.setChecked(true);
                    }
                } else {
                    isMore = true;
                    linXieYi.setVisibility(View.GONE);
                    linOther.setVisibility(View.VISIBLE);
                    if (ckTY1.isChecked()){
                        ckTY.setChecked(true);
                    }
                }
                break;
            case R.id.tvTime:
                timeDialog.show();
                mDiaLogData();
                break;
            case R.id.tvGongJu:
                final List<String> names = new ArrayList<>();
//                交通工具: 1-二轮车 2-三轮车 3-小客车 4-小货车 5-大货车
                names.add("二轮车");
                names.add("三轮车");
                names.add("小客车");
                names.add("小货车");
                names.add("大货车");

                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        trafficTool = (position+1)+"";
                        tvGongJu.setText(names.get(position)+" >");
                        mPresenter.findAreaWeight("",sendLat + "", sendLong + "",trafficTool);
                    }
                }, names);
                break;
            case R.id.tvZhongLiang:
                final ZhongLiangDialog dialog = new ZhongLiangDialog(FaHuoXiaDanSongActivity.this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setVisibilityBtn(true);
                dialog.setYesOnclickListener("确定", new ZhongLiangDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick( String num) {
                        tvZhongLiang.setText(num+"公斤 >");
                     int a = Integer.parseInt(mZhongLiang);
                        int b = Integer.parseInt(num);
                        if (b>a){
                        weight = num+"";
                            mPresenter.orderNum("", orderType+"", sendLat + "", sendLong + "", longitude + "", latitude + "", tip, coupon, weight, trafficTool, parcel_insurance_id);
                        }
                        dialog.dismiss();

                    }
                });
                dialog.setOnOnclickListener("取消", new ZhongLiangDialog.onOnOnclickListener() {
                    @Override
                    public void onOnClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.tvBaoJia:
                showDialogBaoDan(new BaoJiaDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        parcel_insurance_id =mBaoDanList.get(position).getParcel_insurance_id()+"";
                        tvBaoJia.setText(mBaoDanList.get(position).getParcel_insurance_name()+" >");
                        mPresenter.orderNum("", orderType+"", sendLat + "", sendLong + "", longitude + "", latitude + "", tip, coupon, weight, trafficTool, parcel_insurance_id);

                    }
                }, mBaoDanList);
                break;
            case R.id.tvHuoType:
                final List<String> name = new ArrayList<>();
//               	货物类型: 1-鲜花 2-食品 3-文体 4-蛋糕 5-电子产品 6-生活用品
                name.add("鲜花");
                name.add("食品");
                name.add("文体");
                name.add("蛋糕");
                name.add("电子产品");
                name.add("生活用品");

                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        goods = (position+1)+"";
                        tvHuoType.setText(name.get(position)+" >");
                        mPresenter.orderNum("", orderType+"", sendLat + "", sendLong + "", longitude + "", latitude + "", tip, coupon, weight, trafficTool, parcel_insurance_id);

                    }
                }, name);
                break;
            case R.id.cbBaoWen:
                if (cbBaoWen.isChecked()){
                    incubator = "1";
                }else {
                    incubator = "0";
                }

                break;
            case R.id.tvXieYi:
                 intent = new Intent();
                intent.setClass(mContext,XieYiActivity.class);
                intent.putExtra("code",2001+"");
                startActivity(intent);
                break;
            case R.id.tvYouHuiQuan:
                if(Double.parseDouble(tvNum.getText().toString())>10){
                    intent = new Intent(mContext, YouHuiQuanActivity.class);
                    intent.putExtra("type", 3);
                    startActivityForResult(intent, 120);
                }else {
                    ToastUtil.show("订单金额大于10元支持使用优惠券");
                }
                break;
            case R.id.ckTY1:
                break;
            case R.id.tvXieYi1:
                 intent = new Intent();
                intent.setClass(mContext,XieYiActivity.class);
                intent.putExtra("code",2001+"");
                startActivity(intent);
                break;
            case R.id.tvYouHuiQuan1:
                if(Double.parseDouble(tvNum.getText().toString())>10){
                    intent = new Intent(mContext, YouHuiQuanActivity.class);
                    intent.putExtra("type", 3);
                    startActivityForResult(intent, 120);
                }else {
                    ToastUtil.show("订单金额大于10元支持使用优惠券");
                }

                break;
            case R.id.tvBianZun:
//                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
//                    return;
//                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, JiJiaBiaoZhun.class);
                intent.putExtra("lat",sendLat);
                intent.putExtra("lon",sendLong);
                intent.putExtra("type",addressModelFa.type);
                startActivity(intent);
                break;
            case R.id.tvXiaDan:
                if (!ckTY.isChecked()&&!ckTY1.isChecked()){
                    ToastUtil.show("请阅读协议");
                    return;
                }
//
//                if (  !isMobile(etTel.getText().toString()) == true){
//                    ToastUtil.show("指定手机号不正确");
//                    return;
//                }
                if (  !isMobile(tvTelShou.getText().toString()) == true){
                    ToastUtil.show("收货手机号不正确");
                    return;
                }
                if ( ! isMobile(tvTelFa.getText().toString()) == true){
                    ToastUtil.show("发货手机号不正确");
                    return;
                }
                tip = etTip.getText().toString();
                mPresenter.orderNum("", orderType+"", sendLat + "", sendLong + "", longitude + "", latitude + "", tip, coupon, weight, trafficTool, parcel_insurance_id);
              receivePhone = etTel.getText().toString();
                description = etMsg.getText().toString();
                String time = tvTime.getText().toString();
                mPresenter.detail("",orderType,  sendName,  sendPhone,  sendAddress,  sendConcreteAdd,  sendDetailAdd,  sendLat,  sendLong,  receiveName,  contactPhone,  receiveAddress, receiveConcreteAdd, receiveDetailAdd, longitude, latitude, tip, coupon, goods, weight, tvTime.getText().toString(), description, trafficTool, incubator, receivePhone, parcel_insurance_id);
                break;
        }
    }
    //判断是不是手机号
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,5,6,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;

        if (resultCode == 120) {
            if (requestCode == 120) {
                coupon = data.getStringExtra("num");
                mPresenter.orderNum("", orderType+"", sendLat + "", sendLong + "", longitude + "", latitude + "", tip, coupon, weight, trafficTool, parcel_insurance_id);
            }
        }
    }
    //自定义弹框
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(FaHuoXiaDanSongActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }
    //自定义弹框
    private BaoJiaDialog showDialogBaoDan(BaoJiaDialog.SelectDialogListener listener, List<FindParcelInsuranceBean.DataBean> mBaoDanList) {
        BaoJiaDialog dialog = new BaoJiaDialog(FaHuoXiaDanSongActivity.this, R.style
                .transparentFrameWindowStyle,
                listener, mBaoDanList);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }
    TimeDialog timeDialog;

    private void initData() {
        timeDialog = new TimeDialog(this, R.layout.dialog_time_layout, new int[]{R.id.dialog_ok_tv});
    }

    private TextView mTime;
    private ListView mTimeList;
    private TextView today;
    private TextView tomorrow;
    private Timer timer;
    private String mYmdClickTime;
    private SimpleDateFormat format;
    private Calendar c;
    private int day;
    private static final int NTF_PRICE = 0x3;
    private static final int msgKey1 = 1;

    private void mDiaLogData() {
        //获取dialog布局窗口，进而获取其中控件id
        Window window = timeDialog.getWindow();
        mTime = (TextView) window.findViewById(R.id.Time);
        mTimeList = (ListView) window.findViewById(R.id.mTimeList);
        today = (TextView) window.findViewById(R.id.today);
        tomorrow = (TextView) window.findViewById(R.id.tomorrow);
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                today.setTextColor(Color.parseColor("#FFA500"));
                tomorrow.setTextColor(Color.parseColor("#000000"));
                day = 0;
                mTimeListData(day);
                mDiaLogData();
            }
        });
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                today.setTextColor(Color.parseColor("#000000"));
                tomorrow.setTextColor(Color.parseColor("#f47a3d"));
                day = 1;
                mTimeListData(day);
                mDiaLogData();
            }
        });
        timeDialog.setOnCenterItemClickListener(new TimeDialog.OnCenterItemClickListener() {
            @Override
            public void OnCenterItemClick(TimeDialog dialog, View view) {
                dialog.dismiss();
                timer.cancel();
                tvTime.setText(mYmdClickTime);
            }
        });
        mTimeListData(day);
        if (day == 0) {
            mTimer();
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            c = Calendar.getInstance();
            // 将时分秒,毫秒域清零
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.add(Calendar.DAY_OF_MONTH, 1);
            mTime.setText(format.format(c.getTime()));
            timer.cancel();
        }
    }

    private void mTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = msgKey1;
                mwwHandler.sendMessage(msg);
            }
        }, 0, 1000);

    }

    MyTimeAdapter myTimeAdapter;

    private void mTimeListData(final int day) {
        final List<String> timeByMinuHM = getTimeByMinuHM(day);
        myTimeAdapter = new MyTimeAdapter(timeByMinuHM, this);
        myTimeAdapter.notifyDataSetChanged();
        mTimeList.setAdapter(myTimeAdapter);
        mTimeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SimpleDateFormat format = new SimpleDateFormat("MM-dd ");
                Calendar instance = Calendar.getInstance();
                if (day == 1) {
                    instance.add(Calendar.DAY_OF_MONTH, 1);
                }
                String mClickTime = format.format(instance.getTime());
                mYmdClickTime = mClickTime + "" + timeByMinuHM.get(position);
//                ToastUtil.show(mYmdClickTime + "");
            }
        });

    }

    @SuppressLint("SimpleDateFormat")
    public List<String> getTimeByMinuHM(int sign) {

        List<String> times = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        long end = 0;
        try {
            if (sign == 0) {//今天
                end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.valueOf(String.valueOf(new Date().getTime())))) + " 23:59:59").getTime();
            } else if (sign == 1) {//明天
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
                calendar.set(Calendar.HOUR_OF_DAY, 00);
                calendar.set(Calendar.MINUTE, 00);
                calendar.set(Calendar.MILLISECOND, 00);
                end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.valueOf(String.valueOf(calendar.getTimeInMillis())))) + " 23:59:59").getTime();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        while (true) {
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 10);
            String hm = new SimpleDateFormat("HH:mm").format(calendar.getTime());
            long start = Long.valueOf(calendar.getTimeInMillis());
            if (start < end) {
                times.add(hm);
            } else {
                break;
            }
        }
        return times;

    }

    private Handler mwwHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar c = Calendar.getInstance();
                    mTime.setText(format.format(c.getTime())+" >");
//                    appointTime = format.format(c.getTime());
                    break;
                default:
                    break;
            }
        }
    };
    PassWordDialog passWordDialog;
    String mCode;
    String mPayType = "";
    @Override
    public void setData(FaDanXiaDanModel xiaDanModel) {
        mCode = xiaDanModel.orderCode;
        ApliyDialog dialog = new ApliyDialog(FaHuoXiaDanSongActivity.this, R.style
                .transparentFrameWindowStyle,tvNum.getText().toString() +"",
                new ApliyDialog.SelectDialogCancelListener() {
                    @Override
                    public void onCancelClick(String id) {

                        mPayType = id;
                        if (mPayType .equals("1")||mPayType .equals("2")){
                            mPresenter.order_pay(mCode, mPayType);//付款
                        }else {
                            passWordDialog = new PassWordDialog(FaHuoXiaDanSongActivity.this);
                            passWordDialog.show();
                            passWordDialog.setHintText(tvNum.getText().toString() + "");
                            passWordDialog.setMoneyNum(tvNum.getText().toString()+"");
                            passWordDialog.setError_tishi("请输入支付密码");
                            passWordDialog.setClick(new PassWordDialog.OnPayClickListener() {
                                @Override
                                public void onSetPass(String text) {
                                    mPresenter.checkSecondPassword(text+"");

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
        }
    }
    String mLuFei;
    @Override
    public void setNum(OrderCalculateBean orderCalculateBean) {
         mLuFei = orderCalculateBean.total;
        tvNum.setText(orderCalculateBean.total + "");
        tvJuLi.setText("订单距离" + orderCalculateBean.distance + "km"+ ",大约需要"+getTime(orderCalculateBean.distance+"")+"分钟");

    }
    @Override
    public void order_pay(PayModel payModel) {
        if (mPayType.equals("1")||mPayType.equals("2")){
            Util.Pay(mPayType,payModel,mContext);
        }else {
            ToastUtil.show("支付成功");
            finish();
        }
    }

    @Override
    public void checkSecondPassword() {
        mPresenter.order_pay(mCode, mPayType);//付款
    }
String mDanJia = "";
    String mZhongLiang = "";
    @Override
    public void findAreaWeight(FindAreaWeightBean findAreaWeightBean) {
        tvZhongLiang.setText(findAreaWeightBean.weight+"公斤以内 >");
        mDanJia = findAreaWeightBean.weightOverPrice+"";
        mZhongLiang = findAreaWeightBean.weight;
        weight = findAreaWeightBean.weight;
        mPresenter.orderNum("", orderType+"", sendLat + "", sendLong + "", longitude + "", latitude + "", tip, coupon, weight, trafficTool, parcel_insurance_id);

    }
List<FindParcelInsuranceBean.DataBean> mBaoDanList = new ArrayList<>();
    @Override
    public void findParcelInsurance(FindParcelInsuranceBean findParcelInsuranceBean) {

        mBaoDanList = findParcelInsuranceBean.getData();
    }


}
