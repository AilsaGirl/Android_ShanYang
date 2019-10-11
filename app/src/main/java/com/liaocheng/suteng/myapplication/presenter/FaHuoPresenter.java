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

public class FaHuoPresenter extends RxPresenter{
    public String mWeiDu, mJingDu;
    public AMap aMap;

    private Marker locationMarker;

    private RouteSearch mSearch;

    private RideRouteResult mRideRouteResult;

    public void initMap(MapView mapView, final String starWeiDu, final String starJingdu, final String endWeiDu, final String endJingdu) {
        if (aMap!=null){
            aMap.clear();
            aMap =null;
        }
        if (aMap == null) {
            aMap = mapView.getMap();
//            aMap.setLocationSource(this);// 设置定位监听
            aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
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
                aMap.clear();// 清理地图上的所有覆盖物
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
                            rideRouteOverlay.addToMap(0,0);
                            rideRouteOverlay.zoomToSpan();
                        }
                    }
                }
            }
        });
        LatLonPoint mStarLng = new LatLonPoint(JavaTypeUtil.StringToDouble(starWeiDu), JavaTypeUtil.StringToDouble(starJingdu));//终点，116.481288,39.995576
        LatLonPoint mEndPoint = new LatLonPoint(JavaTypeUtil.StringToDouble(endWeiDu), JavaTypeUtil.StringToDouble(endJingdu));//终点，116.481288,39.995576
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(mStarLng, mEndPoint);
        RouteSearch.RideRouteQuery query = new RouteSearch.RideRouteQuery(fromAndTo, 4);
        mSearch.calculateRideRouteAsyn(query);
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





}
