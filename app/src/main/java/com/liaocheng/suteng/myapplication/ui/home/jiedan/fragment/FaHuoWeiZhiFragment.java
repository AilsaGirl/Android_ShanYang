package com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.circle.common.base.BaseFragment;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.JavaTypeUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.DingDanWeiZhiBean;
import com.liaocheng.suteng.myapplication.presenter.DingDanWeiZhiPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.DingDanWeiZhiContent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/11/6.
 */

@SuppressLint("ValidFragment")
public class FaHuoWeiZhiFragment extends BaseFragment<DingDanWeiZhiPresenter> implements DingDanWeiZhiContent.View {
    int mId;
    @BindView(R.id.mapView)
    MapView mapView;
    Unbinder unbinder;

    @SuppressLint("ValidFragment")
    public FaHuoWeiZhiFragment(int id,String code) {
        mId = id;
        mCode = code;
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dingdanweizhi;
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

    String lon;
    String lat;
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
                    lon = aMapLocation.getLongitude()+"";
                    lat = aMapLocation.getLatitude()+"";
                    SPCommon.setString("lon", aMapLocation.getLongitude() + "");
                    SPCommon.setString("lat", aMapLocation.getLatitude() + "");
                    SPCommon.setString("qu", aMapLocation.getDistrict() + "");
                    SPCommon.setString("city", aMapLocation.getCity() + "");
if (dingwei==1){
//    mPresenter.initMap(mapView, lat + "", lon + "", starWeiDu, starJingdu, endWeiDu, endJingdu, 1);
}
                  dingwei++;
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
    public void onSaveInstanceState(Bundle outState) {
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
    public void initEventAndData() {

    }

    @Override
    public void initEventAndDataNoLazy() {
        super.initEventAndDataNoLazy();
        mPresenter.getCoordByOrderCode(mCode+"");
        initListener();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        lat = "";
        lon = "";
         starWeiDu ="";
         starJingdu  ="";
         endWeiDu  ="";
         endJingdu  ="";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lat = "";
        lon = "";
        starWeiDu ="";
        starJingdu  ="";
        endWeiDu  ="";
        endJingdu  ="";

    }

    String starWeiDu;
    String starJingdu;
    String endWeiDu;
    String endJingdu;
    @Override
    public void getCoordByOrderCode(DingDanWeiZhiBean myBean) {
        lat = myBean.lat;
        lon = myBean.lon;
        starWeiDu = myBean.sendLat;
        starJingdu = myBean.sendLong;
        endWeiDu = myBean.latitude;
        endJingdu = myBean.longitude;

        if (TextUtils.isEmpty(lat)||TextUtils.isEmpty(lon)){
            mapView.getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(JavaTypeUtil.StringToDouble( endWeiDu), JavaTypeUtil.StringToDouble(endJingdu)), 10));
//
            mPresenter.initMap(mapView, lat + "", lon + "", starWeiDu, starJingdu, endWeiDu, endJingdu, 2);
//
        }else {
            mapView.getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(JavaTypeUtil.StringToDouble( lat), JavaTypeUtil.StringToDouble(lon)), 10));
//
            mPresenter.initMap(mapView, lat + "", lon + "", starWeiDu, starJingdu, endWeiDu, endJingdu, 1);
//
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        mapView.onCreate(savedInstanceState);
        return rootView;
    }
}
