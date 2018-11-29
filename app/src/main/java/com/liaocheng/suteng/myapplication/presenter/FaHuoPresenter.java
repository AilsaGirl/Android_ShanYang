package com.liaocheng.suteng.myapplication.presenter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RidePath;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.JavaTypeUtil;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.FaDanXiaDanModel;
import com.liaocheng.suteng.myapplication.model.OrderCalculateBean;
import com.liaocheng.suteng.myapplication.presenter.contract.FaHuoContact;
import com.liaocheng.suteng.myapplication.util.mapUtil.RitUtil;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LHB on 2018/7/23 0023.
 */

public class FaHuoPresenter extends RxPresenter<FaHuoContact.View> implements FaHuoContact.Presenter, LocationSource, AMapLocationListener {
    public String mWeiDu, mJingDu;
    public AMap aMap;

    private Marker locationMarker;

    private RouteSearch mSearch;

    private RideRouteResult mRideRouteResult;
    //定位服务类。此类提供单次定位、持续定位、地理围栏、最后位置相关功能
    private AMapLocationClient aMapLocationClient;
    private OnLocationChangedListener listener;
    //定位参数设置
    private AMapLocationClientOption aMapLocationClientOption;

    public void initMap(Context context,MapView mapView, final String starWeiDu, final String starJingdu, final String endWeiDu, final String endJingdu) {
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置地图类型
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);

            MyLocationStyle locationStyle = new MyLocationStyle();
            locationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.dingwei));
            locationStyle.strokeColor(Color.BLUE);
            locationStyle.strokeWidth(5);
            aMap.setMyLocationStyle(locationStyle);

            // 设置定位监听
            aMap.setLocationSource(this);
            aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            // 设置定位的类型为定位模式，参见类AMap。
            aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
            // 设置为true表示系统定位按钮显示并响应点击，false表示隐藏，默认是false
            aMap.setMyLocationEnabled(true);

            aMapLocationClient = new AMapLocationClient(context);
            aMapLocationClient.setLocationListener(this);
            List<Poi> poiList = new ArrayList();
            poiList.add(new Poi("商家", new LatLng(JavaTypeUtil.StringToDouble( starWeiDu), JavaTypeUtil.StringToDouble(starJingdu)), ""));


            LatLng p1 = new LatLng(JavaTypeUtil.StringToDouble(starWeiDu), JavaTypeUtil.StringToDouble(starJingdu));
            LatLng p2 = new LatLng(JavaTypeUtil.StringToDouble(endWeiDu), JavaTypeUtil.StringToDouble(endJingdu));

            AmapNaviParams params = new AmapNaviParams(new Poi("取餐点", p1, ""), null, new Poi("送餐点", p2, ""), AmapNaviType.DRIVER);
            params.setUseInnerVoice(true);
            //初始化定位参数
            aMapLocationClientOption = new AMapLocationClientOption();
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置是否返回地址信息（默认返回地址信息）
            aMapLocationClientOption.setNeedAddress(true);
            //设置是否只定位一次,默认为false
            aMapLocationClientOption.setOnceLocation(false);
            //设置是否强制刷新WIFI，默认为强制刷新
            aMapLocationClientOption.setWifiActiveScan(true);
            //设置是否允许模拟位置,默认为false，不允许模拟位置
            aMapLocationClientOption.setMockEnable(false);
            //设置定位间隔,单位毫秒,默认为2000ms
            aMapLocationClientOption.setInterval(2000);
            //给定位客户端对象设置定位参数
            aMapLocationClient.setLocationOption(aMapLocationClientOption);
            //启动定位
            aMapLocationClient.startLocation();

            };
        }

    /**
     * 定位回调监听，当定位完成后调用此方法
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(listener!=null && aMapLocation!=null) {
            listener.onLocationChanged(aMapLocation);// 显示系统小蓝点
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                aMapLocation.getLatitude();//获取经度
                aMapLocation.getLongitude();//获取纬度;
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getRoad();//街道信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("Tomato","location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }


    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        listener = onLocationChangedListener;
    }


    @Override
    public void deactivate() {

    }

    @Override
    public void detail(String token, String orderType, String sendName, String sendPhone, String sendAddress, String sendConcreteAdd, String sendDetailAdd, String sendLat, String sendLong, String receiveName, String contactPhone, String receiveAddress, String receiveConcreteAdd, String receiveDetailAdd, String longitude, String latitude, String tip, String coupon, String goods, String weight, String appointTime, String description, String trafficTool, String incubator, String receivePhone, String parcel_insurance_id) {
        addSubscribe(Api.createTBService().order_create( SPCommon.getString("token",""), orderType,  sendName,  sendPhone,  sendAddress,  sendConcreteAdd,  sendDetailAdd,  sendLat,  sendLong,  receiveName,  contactPhone,  receiveAddress, receiveConcreteAdd, receiveDetailAdd, longitude, latitude, tip, coupon, goods, weight, appointTime, description, trafficTool, incubator, receivePhone, parcel_insurance_id)
                .compose(RxUtil.<BaseResponse<FaDanXiaDanModel>>rxSchedulerHelper())
                .compose(RxUtil.<FaDanXiaDanModel>handleResult())
                .subscribeWith(new CommonSubscriber<FaDanXiaDanModel>(mContext, true) {
                    @Override
                    protected void _onNext(FaDanXiaDanModel commonRes) {
                        if (commonRes != null) {
                            mView.setData(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(1, message);
                    }
                })
        );
    }

    @Override
    public void orderNum(String token, String orderType, String sendLat, String sendLong, String longitude, String latitude, String tip, String coupon, String weight, String trafficTool, String parcel_insurance_id) {
        addSubscribe(Api.createTBService().order_calculate( SPCommon.getString("token",""), orderType,  sendLat,  sendLong,  longitude,  latitude,  tip,  coupon,  weight,  trafficTool,  parcel_insurance_id)
                .compose(RxUtil.<BaseResponse<OrderCalculateBean>>rxSchedulerHelper())
                .compose(RxUtil.<OrderCalculateBean>handleResult())
                .subscribeWith(new CommonSubscriber<OrderCalculateBean>(mContext, true) {
                    @Override
                    protected void _onNext(OrderCalculateBean commonRes) {
                        if (commonRes != null) {
                            mView.setNum(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(1, message);
                    }
                })
        );
    }
}
