package com.liaocheng.suteng.myapplication.ui.home.jiedan.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.ReceiveOrderModel;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.DingDanBuyInfoActivity;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.DingDanInfoActivity;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;


/**
 * 送货
 * Created by weihongfang
 */

public class SongHuoZhongAdapter extends RecyclerView.Adapter<SongHuoZhongAdapter.ViewHolder> {



    private Context mContext;

    private List<ReceiveOrderModel.ReceiveOrderBean> mList = new ArrayList<>();

    private FollowClickListener mListener;
    int type = 0;

    public SongHuoZhongAdapter(Context context, int type, FollowClickListener mListener) {
        this.mContext = context;
        this.type = type;
        this.mListener = mListener;
    }

    public void setData(List<ReceiveOrderModel.ReceiveOrderBean> mySendOrdersBean) {
        mList = mySendOrdersBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_songhuozhong, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ReceiveOrderModel.ReceiveOrderBean ordersModel = mList.get(position);
//            1	帮我买
//            2	帮我办
//            3	帮我送
//            4	同校快送
//            5	合作商家
//            6	县域快送
            if (ordersModel.orderType.equals("1")) {
                holder.tvDingDanType.setText("帮我买");
                holder.linMai.setVisibility(View.VISIBLE);
                holder.relFa.setVisibility(View.GONE);
//                holder.tvMsg.setText(ordersModel.description + "");
            }
            if (ordersModel.orderType.equals("2")) {
                holder.tvDingDanType.setText("帮我办");
                holder.linMai.setVisibility(View.VISIBLE);
                holder.relFa.setVisibility(View.GONE);
//                holder.tvMsg.setText(ordersModel.description + "");
            }
            if (ordersModel.orderType.equals("3")) {
                holder.tvDingDanType.setText("帮我送");
                holder.linMai.setVisibility(View.GONE);
                holder.relFa.setVisibility(View.VISIBLE);
            }
            if (ordersModel.orderType.equals("4")) {
                holder.tvDingDanType.setText("同校快送");
                holder.linMai.setVisibility(View.GONE);
                holder.relFa.setVisibility(View.VISIBLE);
            }
            if (ordersModel.orderType.equals("5")) {
                holder.tvDingDanType.setText("当日达");
                holder.linMai.setVisibility(View.GONE);
                holder.relFa.setVisibility(View.VISIBLE);
            }
            if (ordersModel.orderType.equals("6")) {
                holder.tvDingDanType.setText("县域快送");
                holder.linMai.setVisibility(View.GONE);
                holder.relFa.setVisibility(View.VISIBLE);
            }

            holder.tvFaHuo.setText(ordersModel.sendAddress + "");
            holder.tvFaHUoXQ.setText(ordersModel.sendConcreteAdd + "");
            holder.tvShouHuo.setText(ordersModel.receiveAddress + "");
            holder.tvShouHuoXQ.setText(ordersModel.receiveConcreteAdd + "");

            holder.tvCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + ordersModel.contactPhone);
                    intent.setData(data);
                    mContext.startActivity(intent);

                }
            });
            holder.tvQueRen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(holder.etCode.getText().toString())){
                        mListener.onBtnClick(holder.etCode.getText().toString()+"", ordersModel.orderCode);
                        holder.etCode.setText("");
                    }

                }
            });
            holder.ivPhoto1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onPhoto("1", ordersModel.orderCode);
                }
            });
            holder.ivPhoto2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onPhoto("2", ordersModel.orderCode);
                }
            });
            holder.ivPhoto3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onPhoto("3", ordersModel.orderCode);
                }
            });
            if (!TextUtils.isEmpty(ordersModel.goodsImg1)){
                Glide.with(mContext).load(ordersModel.goodsImg1).placeholder(R.mipmap.add).error(R.mipmap.add).into(holder.ivPhoto1);
            }
            if (!TextUtils.isEmpty(ordersModel.goodsImg2)){
                Glide.with(mContext).load(ordersModel.goodsImg2).placeholder(R.mipmap.add).error(R.mipmap.add).into(holder.ivPhoto2);
            }
            if (!TextUtils.isEmpty(ordersModel.goodsImg3)){
                Glide.with(mContext).load(ordersModel.goodsImg3).placeholder(R.mipmap.add).error(R.mipmap.add).into(holder.ivPhoto3);
            }
            holder.ivDingWeiFa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LATITUDE_A = Double.parseDouble(ordersModel.sendLat);
                    LONGTITUDE_A = Double.parseDouble(ordersModel.sendLong);
                    LATITUDE_B = Double.parseDouble(ordersModel.latitude);
                    LONGTITUDE_B = Double.parseDouble(ordersModel.longitude);
                    bd_encrypt(LATITUDE_A, LONGTITUDE_A, true);
                    bd_encrypt(LATITUDE_B, LONGTITUDE_B, false);
                    QIDIAN_NAME = ordersModel.sendAddress;
                    ZHONGDIAN_NAME = ordersModel.receiveAddress;
                    isQidian = true;
                    initListener();
                }
            });
            holder.ivDingWeiShou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LATITUDE_A = Double.parseDouble(ordersModel.sendLat);
                    LONGTITUDE_A = Double.parseDouble(ordersModel.sendLong);
                    LATITUDE_B = Double.parseDouble(ordersModel.latitude);
                    LONGTITUDE_B = Double.parseDouble(ordersModel.longitude);
                    bd_encrypt(LATITUDE_A, LONGTITUDE_A, true);
                    bd_encrypt(LATITUDE_B, LONGTITUDE_B, false);
                    QIDIAN_NAME = ordersModel.sendAddress;
                    ZHONGDIAN_NAME = ordersModel.receiveAddress;
                    isQidian = false;
                    initListener();
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ordersModel.orderType.equals("1") || ordersModel.orderType.equals("2")) {
//                    mPresenter.order_grab(uid);
//                    recyclerView.refresh();
                        Intent intent = new Intent();
                        intent.putExtra("code", ordersModel.orderCode);
//                        if (type == 1) {
//                            intent.putExtra("isJieDanYuan", true);
//                        } else {
//                            intent.putExtra("isJieDanYuan", false);
//                        }
                        intent.putExtra("isJieDanYuan", true);
                        intent.setClass(mContext, DingDanBuyInfoActivity.class);
                        intent.putExtra("type",1);
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("code", ordersModel.orderCode);
                        intent.setClass(mContext, DingDanInfoActivity.class);
                        mContext.startActivity(intent);
                    }
                }
            });

        }

    }

    /*定位相关*/
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private void initListener() {
        if (mLocationClient != null) {
            if (isQidian) {
                setUpGaodeAppByLoca(LONGTITUDE_A, LATITUDE_A, QIDIAN_NAME);
            } else {
                setUpGaodeAppByLoca(LONGTITUDE_B, LATITUDE_B, ZHONGDIAN_NAME);
            }
        } else {
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
//            mLocationOption.setOnceLocationLatest(true);
            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            //启动定位
            mLocationClient.startLocation();
        }


    }

    double lon;
    double lat;
    String mQu;
    String mCity;
    String mAddress;
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
                    mAddress = aMapLocation.getPoiName();
                    SPCommon.setString("lon", aMapLocation.getLongitude() + "");
                    SPCommon.setString("lat", aMapLocation.getLatitude() + "");
                    SPCommon.setString("qu", aMapLocation.getDistrict() + "");
                    SPCommon.setString("city", aMapLocation.getCity() + "");
                    if (dingwei == 1){
                        if (isQidian) {
                            setUpGaodeAppByLoca(LONGTITUDE_A, LATITUDE_A, QIDIAN_NAME);
                        } else {
                            setUpGaodeAppByLoca(LONGTITUDE_B, LATITUDE_B, ZHONGDIAN_NAME);
                        }
                    }
                   dingwei++;
                } else {

                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("yue.huang", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    //--------------以下都是高德坐标系的坐标-------------------//
    private double LATITUDE_A;  //起点纬度
    private double LONGTITUDE_A;  //起点经度

    private double LATITUDE_B;  //终点纬度
    private double LONGTITUDE_B;  //终点经度

    private String QIDIAN_NAME;
    private String ZHONGDIAN_NAME;
    boolean isQidian = true;

    //----------------以下都是百度坐标系的坐标------------------//
    private double LATITUDE_QIDIAN;  //起点纬度
    private double LONGTITUDE_QIDIAN;  //起点经度

    private double LATITUDE_ZHONGDIAN;  //终点纬度
    private double LONGTITUDE_ZHONGDIAN;  //终点经度

    /**
     * 确定起终点坐标BY高德
     */
    void setUpGaodeAppByLoca(double lonb, double latb, String name) {
        try {
            Intent intent = Intent.getIntent("androidamap://route?sourceApplication=softname&slat=" + lat + "&slon=" + lon + "&sname=" + mAddress + "&dlat=" + latb + "&dlon=" + lonb + "&dname=" + name + "&dev=0&m=0&t=3");
            if (isInstallByread("com.autonavi.minimap")) {
                mContext.startActivity(intent);
//                Log.e(TAG, "高德地图客户端已经安装") ;
            } else {
//                Log.e(TAG, "没有安装高德地图客户端") ;
                if (isQidian) {
                    setUpBaiduAPPByLoca(LONGTITUDE_QIDIAN, LATITUDE_QIDIAN, QIDIAN_NAME);
                } else {
                    setUpBaiduAPPByLoca(LONGTITUDE_ZHONGDIAN, LATITUDE_ZHONGDIAN, ZHONGDIAN_NAME);
                }


            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注意下面的起终点坐标都是百度坐标，如果使用高德坐标系有很大的误差
     */
    void setUpBaiduAPPByLoca(double lonb, double latb, String name) {
        //            intent.setData(Uri.parse("baidumap://map/direction?destination=latlng:"
//                    + lonb + ","
//                    + latb + "|name:" + name + // 终点
//                    "&mode=driving" + // 导航路线方式
//                    "&src=" + mContext.getPackageName()));

//            Intent intent = Intent.getIntent("intent://map/direction?origin=latlng:" + lat + "," + lon + "|name:" + mAddress + "&destination=latlng:" + latb + "," + lonb + "|name:" + name + "&mode=riding&src="+mContext.getPackageName()+"|"+mContext.getPackageName()+"#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
        if (isInstallByread("com.baidu.BaiduMap")) {
//                mContext.startActivity(intent);
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("baidumap://map/direction?destination=latlng:"
                    + lonb + ","
                    + latb + "|name:" + name + // 终点
                    "&mode=driving" + // 导航路线方式
                    "&src=" + mContext.getPackageName())));
//                Log.e(TAG, "百度地图客户端已经安装") ;
        } else {
            ToastUtil.show("没有安装百度或高德地图客户端");
        }
    }

    /**
     * 判断是否安装目标应用
     *
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    double bd_lat;
    double bd_lon;

    //高德转百度
    void bd_encrypt(double gg_lat, double gg_lon, boolean isQidDian) {
        double x = gg_lon, y = gg_lat;
        double z = sqrt(x * x + y * y) + 0.00002 * sin(y * Math.PI);
        double theta = atan2(y, x) + 0.000003 * cos(x * Math.PI);
        if (isQidDian) {
            LATITUDE_QIDIAN = z * cos(theta) + 0.0065;
            LONGTITUDE_QIDIAN = z * sin(theta) + 0.006;
        } else {
            LATITUDE_ZHONGDIAN = z * cos(theta) + 0.0065;
            LONGTITUDE_ZHONGDIAN = z * sin(theta) + 0.006;
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDingDanType)
        TextView tvDingDanType;
        @BindView(R.id.tvMsg)
        TextView tvMsg;
        @BindView(R.id.linMai)
        LinearLayout linMai;
        @BindView(R.id.ivFaHuo)
        ImageView ivFaHuo;
        @BindView(R.id.tvFaHuo)
        TextView tvFaHuo;
        @BindView(R.id.tvFaHUoXQ)
        TextView tvFaHUoXQ;
        @BindView(R.id.ivDingWeiFa)
        ImageView ivDingWeiFa;
        @BindView(R.id.relFa)
        RelativeLayout relFa;
        @BindView(R.id.ivShouHuo)
        ImageView ivShouHuo;
        @BindView(R.id.tvShouHuo)
        TextView tvShouHuo;
        @BindView(R.id.tvShouHuoXQ)
        TextView tvShouHuoXQ;
        @BindView(R.id.ivDingWeiShou)
        ImageView ivDingWeiShou;
        @BindView(R.id.ivPhoto1)
        ImageView ivPhoto1;
        @BindView(R.id.ivPhoto2)
        ImageView ivPhoto2;
        @BindView(R.id.ivPhoto3)
        ImageView ivPhoto3;
        @BindView(R.id.etCode)
        EditText etCode;
        @BindView(R.id.tvQueRen)
        TextView tvQueRen;
        @BindView(R.id.tvCall)
        TextView tvCall;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onBtnClick(String type, String id);
        void onPhoto(String num,String code);
    }
}