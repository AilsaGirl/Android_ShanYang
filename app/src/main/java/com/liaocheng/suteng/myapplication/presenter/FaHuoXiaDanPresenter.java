package com.liaocheng.suteng.myapplication.presenter;

import android.graphics.Color;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
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
import com.liaocheng.suteng.myapplication.model.DingDanWeiZhiBean;
import com.liaocheng.suteng.myapplication.model.FaDanXiaDanModel;
import com.liaocheng.suteng.myapplication.model.FindAreaWeightBean;
import com.liaocheng.suteng.myapplication.model.FindParcelInsuranceBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.OrderCalculateBean;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.contract.DingDanWeiZhiContent;
import com.liaocheng.suteng.myapplication.presenter.contract.FaHuoXiaDanContent;
import com.liaocheng.suteng.myapplication.util.mapUtil.RitUtil;

import java.util.ArrayList;
import java.util.List;

public class FaHuoXiaDanPresenter extends RxPresenter<FaHuoXiaDanContent.View> implements FaHuoXiaDanContent.Presenter{
    public AMap aMap;

    private Marker locationMarker;

    private RouteSearch mSearch;

    private RideRouteResult mRideRouteResult;

    public void Ride(int s){
        if (s==1){
            LatLonPoint mStarLng1 = new LatLonPoint(JavaTypeUtil.StringToDouble(starWeiDu), JavaTypeUtil.StringToDouble(starJingdu));//终点，116.481288,39.995576
            LatLonPoint mEndPoint1 = new LatLonPoint(JavaTypeUtil.StringToDouble(WeiDu), JavaTypeUtil.StringToDouble(Jingdu));//终点，116.481288,39.995576
            final RouteSearch.FromAndTo fromAndTo1 = new RouteSearch.FromAndTo(mEndPoint1,mStarLng1);
            RouteSearch.RideRouteQuery query1 = new RouteSearch.RideRouteQuery(fromAndTo1, 4);
            mSearch.calculateRideRouteAsyn(query1);
        }else {
            LatLonPoint mStarLng = new LatLonPoint(JavaTypeUtil.StringToDouble(starWeiDu), JavaTypeUtil.StringToDouble(starJingdu));//终点，116.481288,39.995576
            LatLonPoint mEndPoint = new LatLonPoint(JavaTypeUtil.StringToDouble(endWeiDu), JavaTypeUtil.StringToDouble(endJingdu));//终点，116.481288,39.995576
            final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(mStarLng, mEndPoint);
            RouteSearch.RideRouteQuery query = new RouteSearch.RideRouteQuery(fromAndTo, 4);
            mSearch.calculateRideRouteAsyn(query);
        }

    }
    int isThree;
    String WeiDu;String Jingdu;String starWeiDu;  String starJingdu;  String endWeiDu;  String endJingdu;
    public void initMap(MapView mapView, final String WeiDu, final String Jingdu, final String starWeiDu, final String starJingdu, final String endWeiDu, final String endJingdu, final int isStart) {
        this.WeiDu= WeiDu;
        this.Jingdu= Jingdu;
        this.starWeiDu= starWeiDu;
        this.starJingdu= starJingdu;
        this.endWeiDu= endWeiDu;
        this.endJingdu= endJingdu;
        this.isThree = isStart;
        if (aMap!=null){
            aMap.clear();
            aMap =null;
        }

        if (aMap == null) {
            aMap = mapView.getMap();
            //            aMap.setLocationSource(this);// 设置定位监听
            MyLocationStyle myLocationStyle = new MyLocationStyle();
/* myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
fromResource(R.mipmap.btn_voice_map_navi));// 自定义定位蓝点图标*/
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 自定义精度范围的圆形边框颜色
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));//圆圈的颜色,设为透明的时候就可以去掉园区区域了
            aMap.setMyLocationStyle(myLocationStyle);
            aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(false);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    //                    ToastUtil.show("1");
                    List<Poi> poiList = new ArrayList();
                    poiList.add(new Poi("商家", new LatLng(JavaTypeUtil.StringToDouble( starWeiDu), JavaTypeUtil.StringToDouble(starJingdu)), ""));


                    LatLng p1 = new LatLng(JavaTypeUtil.StringToDouble(starWeiDu), JavaTypeUtil.StringToDouble(starJingdu));
                    LatLng p2 = new LatLng(JavaTypeUtil.StringToDouble(endWeiDu), JavaTypeUtil.StringToDouble(endJingdu));

                    AmapNaviParams params = new AmapNaviParams(new Poi("取餐点", p1, ""), null, new Poi("送餐点", p2, ""), AmapNaviType.DRIVER);
                    params.setUseInnerVoice(true);
                    AmapNaviPage.getInstance().showRouteActivity(mContext, params, new INaviInfoCallback() {
                        @Override
                        public void onInitNaviFailure() {

                        }

                        @Override
                        public void onGetNavigationText(String s) {

                        }

                        @Override
                        public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

                        }

                        @Override
                        public void onArriveDestination(boolean b) {

                        }

                        @Override
                        public void onStartNavi(int i) {

                        }

                        @Override
                        public void onCalculateRouteSuccess(int[] ints) {

                        }

                        @Override
                        public void onCalculateRouteFailure(int i) {

                        }

                        @Override
                        public void onStopSpeaking() {

                        }

                        @Override
                        public void onReCalculateRoute(int i) {

                        }

                        @Override
                        public void onExitPage(int i) {

                        }

                        @Override
                        public void onStrategyChanged(int i) {

                        }

                        @Override
                        public View getCustomNaviBottomView() {
                            return null;
                        }

                        @Override
                        public View getCustomNaviView() {
                            return null;
                        }

                        @Override
                        public void onArrivedWayPoint(int i) {

                        }

                    });
                }
            });
        }
        mSearch = new RouteSearch(mContext);
        mSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
            @Override
            public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

            }

            @Override
            public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

            }

            @Override
            public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

            }

            @Override
            public void onRideRouteSearched(RideRouteResult result, int errorCode) {
//                aMap.clear();// 清理地图上的所有覆盖物
                if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
                    if (result != null && result.getPaths() != null) {
                        if (result.getPaths().size() > 0) {
                            mRideRouteResult = result;
                            final RidePath ridePath = mRideRouteResult.getPaths()
                                    .get(0);
                            if (ridePath == null) {
                                return;
                            }
                            RitUtil rideRouteOverlay = new RitUtil(
                                    mContext, aMap, ridePath,
                                    mRideRouteResult.getStartPos(),
                                    mRideRouteResult.getTargetPos());
                            rideRouteOverlay.removeFromMap();
                            if (isThree==1){
                                rideRouteOverlay.addToMap(R.mipmap.marker,R.mipmap.amap_start);
                                rideRouteOverlay.zoomToSpan();
                                Ride(2);
                                isThree++;
                            }else {
                                rideRouteOverlay.addToMap(R.mipmap.amap_start,R.mipmap.amap_end);
                                rideRouteOverlay.zoomToSpan();
                            }
                            if (isStart==1){
                                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(JavaTypeUtil.StringToDouble( WeiDu), JavaTypeUtil.StringToDouble(Jingdu)), 15));
                            }else {
                                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(JavaTypeUtil.StringToDouble( endWeiDu), JavaTypeUtil.StringToDouble(endJingdu)), 15));
                            }
                        }
                    }
                }
            }
        });
        if (isThree==1){
            Ride(1);
        }else {
            Ride(2);
        }
//        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(JavaTypeUtil.StringToDouble( WeiDu), JavaTypeUtil.StringToDouble(Jingdu)), 18));

//        LatLng latLng = new LatLng(JavaTypeUtil.StringToDouble(mWeiDu), JavaTypeUtil.StringToDouble(mJingDu));
//        if (locationMarker == null) {
//            //首次定位
//            locationMarker = aMap.addMarker(new MarkerOptions().position(latLng)
//                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.elogo))
//                    .anchor(0.5f, 0.5f));
//
//            //首次定位,选择移动到地图中心点并修改级别到15级
//            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//        }


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

    @Override
    public void order_pay(String code, String type) {
        addSubscribe(Api.createTBService().order_pay(SPCommon.getString("token",""),code,type)
                .compose(RxUtil.<BaseResponse<PayModel>>rxSchedulerHelper())
                .compose(RxUtil.<PayModel>handleResult())
                .subscribeWith(new CommonSubscriber<PayModel>(mContext, true) {
                    @Override
                    protected void _onNext(PayModel commonRes) {

                        if (commonRes != null) {
                            mView.order_pay(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    @Override
    public void checkSecondPassword(String secondPwd) {
        addSubscribe(Api.createTBService().checkSecondPassword(SPCommon.getString("token",""),secondPwd)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.checkSecondPassword();
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    @Override
    public void findAreaWeight(String token, String sendLat, String sendLong, String trafficTool) {
        addSubscribe(Api.createTBService().findAreaWeight(SPCommon.getString("token",""),sendLat,sendLong,trafficTool)
                .compose(RxUtil.<BaseResponse<FindAreaWeightBean>>rxSchedulerHelper())
                .compose(RxUtil.<FindAreaWeightBean>handleResult())
                .subscribeWith(new CommonSubscriber<FindAreaWeightBean>(mContext, true) {
                    @Override
                    protected void _onNext(FindAreaWeightBean commonRes) {

                        if (commonRes != null) {
                            mView.findAreaWeight(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    @Override
    public void findParcelInsurance(String token, String sendLat, String sendLong) {
        addSubscribe(Api.createTBService().findParcelInsurance(SPCommon.getString("token",""),sendLong,sendLat)
                .compose(RxUtil.<BaseResponse<FindParcelInsuranceBean>>rxSchedulerHelper())
                .compose(RxUtil.<FindParcelInsuranceBean>handleResult())
                .subscribeWith(new CommonSubscriber<FindParcelInsuranceBean>(mContext, true) {
                    @Override
                    protected void _onNext(FindParcelInsuranceBean commonRes) {

                        if (commonRes != null) {
                            mView.findParcelInsurance(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

}
