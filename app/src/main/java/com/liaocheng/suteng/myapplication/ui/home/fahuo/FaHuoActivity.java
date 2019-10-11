package com.liaocheng.suteng.myapplication.ui.home.fahuo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.circle.common.base.BaseActivity;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.CommonRes;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.FuJinModel;
import com.liaocheng.suteng.myapplication.model.LocationBean;
import com.liaocheng.suteng.myapplication.model.MainModel;
import com.liaocheng.suteng.myapplication.model.event.FaHuoAddressEvent;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.ui.home.address.CityListActivity;
import com.liaocheng.suteng.myapplication.ui.home.address.NewLocationActivity;
import com.liaocheng.suteng.myapplication.ui.home.address.PoiSearchAdapter;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.adapter.WindowAdapter;
import com.liaocheng.suteng.myapplication.ui.my.MyActivity;
import com.liaocheng.suteng.myapplication.ui.my.fragment.FaHuoDingDanFragment;
import com.liaocheng.suteng.myapplication.ui.my.fragment.JieDanDingDanFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class FaHuoActivity extends BaseActivity implements LocationSource,
        AMapLocationListener, AMap.OnCameraChangeListener, PoiSearch.OnPoiSearchListener, GeocodeSearch.OnGeocodeSearchListener {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.stlTitle)
    SlidingTabLayout stlTitle;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.linBom)
    LinearLayout linBom;
    @BindView(R.id.ivMy)
    ImageView ivMy;
    @BindView(R.id.ivCity)
    ImageView ivCity;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fahuo;
    }

    private AMapLocationClient mLocationClient;
    private OnLocationChangedListener mListener;
    private LatLng latlng;//经纬度
    private AMap aMap;
    private String deepType = "";// poi搜索类型
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private PoiResult poiResult; // poi返回的结果
    //    private PoiOverlay poiOverlay;// poi图层
    private ArrayList<PoiItem> poiItems;// poi数据
    double lon;//NewLocationSeekActivity回传的经纬度
    double lat;//NewLocationSeekActivity回传经纬度
    String Code = "";//NewLocationSeekActivity回传的值
    String code = "true";
    LatLonPoint lp;//经纬度
    String location;//NewLocationSeekActivity回传地址
    String city;//CityListActivity回传的城市名
    String Key = "";
    final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;
    int i = 1;//定位失败显示次数
    //权限数量
    String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    List<String> mPermissionList = new ArrayList<>();
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    String[] titleList;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initEventAndData() {
        EventBus.getDefault().register(this);
        toolBar.setTitleText("我要发货").setBackFinish();
        titleList = new String[6];
        for (int i = 1; i < 7; i++) {
            if (i == 1) {
                titleList[0] = "帮我送";
                mFragmentList.add(new BangWoSongFragment(i));
            }

            if (i == 2) {
                titleList[1] = "帮我办";
                mFragmentList.add(new BangWoBanFrafment(i));
            }
            if (i == 3) {
                titleList[2] = "帮我买";
                mFragmentList.add(new BangWoMaiFrafment(i));
            }
            if (i == 4) {
                titleList[3] = "当日达";
                mFragmentList.add(new HeZuoShangJiaFragment(i));
            }
            if (i == 5) {
                titleList[4] = "县域快送";
                mFragmentList.add(new XianYvKuaiDiFragment(i));
            }
            if (i == 6) {
                titleList[5] = "同校快送";
                mFragmentList.add(new TongXiaoKuaiSongFragment(i));
            }

        }
        stlTitle.setViewPager(vp,titleList,this,mFragmentList);

    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        mapView.onCreate(savedInstanceState);
        onCallWrapper();
        init();
    }
    private void getJieDanYuan(){
        Api.toSubscriber(Api.toScheculer(Api.createTBService().showVicinityCustomer(SPCommon.getString("token",""),lon+"",lat+""))
                .compose(RxUtil.<FuJinModel>handleResult()), new CommonSubscriber<FuJinModel>(this) {
            @Override
            protected void _onNext(FuJinModel res) {
                if (res.data!=null&&res.data.size()>0){
                    marketList.clear();
                    marketList = res.data;
                    addMoreMarket();
                }
            }

            @Override
            protected void _onError(String message) {

            }
        });
    }
    /**
     * 添加多个market
     */
    private List<FuJinModel.DataBean> marketList  = new ArrayList<>();

    private void addMoreMarket() {

        for (int i = 0; i < marketList.size(); i++) {
//            aMap.addMarker(new MarkerOptions().anchor(1.5f, 3.5f)
//                    .position(new LatLng(Double.parseDouble(marketList.get(i).lat) ,//设置纬度
//                            Double.parseDouble(marketList.get(i).lon)))//设置经度
//                    .title(marketList.get(i).name)//设置标题
//                    .snippet(marketList.get(i).phone)//设置内容
//                    // .setFlat(true) // 将Marker设置为贴地显示，可以双指下拉地图查看效果
//                    .draggable(false) //设置Marker可拖动
//                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker)));
            //设置自定义弹窗
//        aMap.setInfoWindowAdapter(new WindowAdapter(this));
//        //绑定信息窗点击事件
//        aMap.setOnInfoWindowClickListener(new WindowAdapter(this));
//        aMap.setOnMarkerClickListener(new WindowAdapter(this));

            MarkerOptions markerOption = new MarkerOptions();
            markerOption.anchor(1.5f, 3.5f).position(new LatLng(Double.parseDouble(marketList.get(i).lat) ,//设置纬度
                    Double.parseDouble(marketList.get(i).lon)));//设置经度
            markerOption.draggable(false);//设置Marker可拖动
            markerOption .title("姓名："+marketList.get(i).name)//设置标题
                    .snippet("电话："+marketList.get(i).phone);//设置内容;
//            markerOption.icon(BitmapDescriptorFactory.fromView(getMyView(marketList.get(i).name,marketList.get(i).phone )));
            markerOption.icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker));
            aMap.addMarker(markerOption);

        }


        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (!isMarker){
                    isMarker = true;
                    marker.showInfoWindow();
                }else {
                    isMarker = false;
                    marker.hideInfoWindow();
                }
                return true;
//                MarkerListEntity listEntity=list.get(Integer.valueOf(id));//拿到这个实体类了  就可以做操作了


            }
        });

    }

    protected View getMyView(String name,String phone) {
        View view = getLayoutInflater().inflate(R.layout.adapter_fujin, null);
        //标题
        TextView title = (TextView) view.findViewById(R.id.tvName);
        //地址信息
        TextView address = (TextView) view.findViewById(R.id.tvPhone);
        final LinearLayout linInfo = view.findViewById(R.id.linInfo);
        ImageView imageView = view.findViewById(R.id.ivImg);

        title.setText(name);
        address.setText(phone);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linInfo.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    /**
     * 位置权限
     */
    public void onCallWrapper() {
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_ASK_CALL_PHONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted 授予权限
                    init();
                } else {
                    // Permission Denied 权限被拒绝
                    Toast.makeText(this, "Permission Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    /**
     * 初始化Amap
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setOnCameraChangeListener(this);

        }

        setUpMap();

    }

    /**
     * 执行定位
     */
    private void setUpMap() {
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(getApplicationContext());
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mLocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setOnceLocation(true);
            mLocationOption.setMockEnable(false);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.startLocation();
        }
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.getUiSettings().setZoomGesturesEnabled(false);

    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery() {
        aMap.setOnMapClickListener(null);// 进行poi搜索时清除掉地图点击事件
        int currentPage = 0;
        //搜索（S：关键字，
        // S1：汽车服务|汽车销售|汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
        //住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
        //金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        // S3：城市，可以空字符串，空字符串代表全国
        // 三个个都可以为空）
        query = new PoiSearch.Query(deepType, "", city+"");
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        lp = new LatLonPoint(latlng.latitude, latlng.longitude);//检索的经纬度
        poiSearch.setBound(new PoiSearch.SearchBound(lp, 50000, true)); // 设置搜索区域为以lp点为圆心，其周围2000米范围
        poiSearch.searchPOIAsyn();// 异步搜索

    }

    /**
     * 定位成功执行此方法
     *
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                // 显示我的位置
                mListener.onLocationChanged(aMapLocation); //设置第一次焦点中心
                /**
                 * 通过搜索页面返回来的经纬度进行搜索
                 */
                if (Code.equals(code)) {
                    latlng = new LatLng(lat, lon);//获取经纬度
                    Code = "false";
                } else {//初始化搜索，定位自身所在位置搜索
                    lon = aMapLocation.getLongitude();
                    lat = aMapLocation.getLatitude();
                    latlng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());//获取经纬度

                }
                aMapLocation.getAccuracy();//获取精度信息
                String s1 =     aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                String s11 =     aMapLocation.getCountry();//国家信息
                String s111 =     aMapLocation.getProvince();//省信息
                city =   aMapLocation.getCity();//城市信息
                String s13 =    aMapLocation.getDistrict();//城区信息
                String s14 =    aMapLocation.getStreet();//街道信息
                String s15 =     aMapLocation.getStreetNum();//街道门牌号信息
                String s16 =     aMapLocation.getCityCode();//城市编码
                String s17 =     aMapLocation.getAdCode();//地区编码
                String s18 =      aMapLocation.getAoiName();//获取当前定位点的AOI信息
                String s19 =      aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                String s10 =      aMapLocation.getFloor();//获取当前室内定位的楼层

//                EventBus.getDefault().post(new FaHuoAddressEvent(aMapLocation.getAoiName(), aMapLocation.getAddress(),aMapLocation.getLongitude()+"",aMapLocation.getLatitude()+""));

                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 17), 1000, null);//搜索的（latlag(经纬度)，V:20(缩放大小(10-20)，l:1000(搜索范围)）

                doSearchQuery();
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                if (i == 1) {
                    Toast.makeText(getApplicationContext(), "定位失败" + errText, Toast.LENGTH_LONG).show();
                    i++;
                }
            }
            Log.e("AmapError", "location Error, ErrCode:");
        }
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        mLocationClient.startLocation();
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }
    boolean isMarker = false;
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
    }
    private Map<String, Marker> markerMap = new HashMap<>(); // 管理地图标记的集合
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        latlng = cameraPosition.target;
//        aMap.clear();
        Marker storeMarker = markerMap.get("dingwei");

        if (storeMarker != null) {
            // 如果已经存在则更新

            storeMarker.setPosition(latlng);

        } else {
            aMap.clear();
            // 如果没有则创建
            MarkerOptions options = new MarkerOptions();
            options.position(latlng);
            Marker marker = aMap.addMarker(options);
            markerMap.put("dingwei", marker);
            getJieDanYuan();
        }
//        aMap.addMarker(new MarkerOptions().position(latlng));

        doSearchQuery();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FaHuoAddressEvent event) {
        if (event == null)
            return;
        if (event.isDingWei()){
            if (data != null && data.size() > 0) {
//                        final PoiSearchAdapter adapter = new PoiSearchAdapter(this, data);
                EventBus.getDefault().post(new FaHuoAddressEvent(data.get(0).getTitle(), data.get(0).getContent(),data.get(0).getLon()+"",data.get(0).getLat()+"",data.get(0).getShi()+""));
            }
        }
    }
    ArrayList<LocationBean> data = new ArrayList<LocationBean>();

    /**
     * poi列表
     *
     * @param result
     * @param rCode
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == 1000) {
            if (result != null && result.getQuery() != null) {
                // 搜索poi的结果
                if (result.getQuery().equals(query)) {
                    // 是否是同一条
                    poiResult = result;
                    poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    //清空数据
                    data.clear();
                    aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示

                    for (PoiItem item : poiItems) {
                        //获取经纬度对象
                        LatLonPoint llp = item.getLatLonPoint();
                        double lon = llp.getLongitude();//经度
                        double lat = llp.getLatitude();//纬度
                        //获取标题
                        String title = item.getTitle();
                        //获取内容
                        String text = item.getSnippet();
                        Log.d("jieshou", item.getProvinceName() + item.getCityName() + item.getAdName() + "123456789");

                        String texta = item.getProvinceName() + item.getCityName() + item.getAdName();
                        String sheng = item.getProvinceName();
                        String shi = item.getCityName();
                        String qu = item.getAdName();
//                        city = item.getCityName();
                        //添加数据
                        data.add(new LocationBean(lon, lat, title, text, texta, sheng, shi, qu));
                    }

                    if (data != null && data.size() > 0) {
//                        final PoiSearchAdapter adapter = new PoiSearchAdapter(this, data);
                        EventBus.getDefault().post(new FaHuoAddressEvent(data.get(0).getTitle(),data.get(0).getContent(),data.get(0).getLon()+"",data.get(0).getLat()+"",data.get(0).getShi()+""));
//                        mapList.setAdapter(adapter);
//                        //poi列表的item的点击事件
//                        mapList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                Intent intent=new Intent();
//                                intent.putExtra("sheng",data.get(i).getSheng());
//                                intent.putExtra("shi",data.get(i).getShi());
//                                intent.putExtra("qu",data.get(i).getQu());
//                                intent.putExtra("title",data.get(i).getTitle());
//                                intent.putExtra("lon",String.valueOf(data.get(i).getLon()));
//                                intent.putExtra("lat",String.valueOf(data.get(i).getLat()));
//                                setResult(110, intent);
//                                FaHuoActivity.this.finish();


//                                //刷新小蓝点
//                                MarkerOptions mk = new MarkerOptions();
//                                mk.icon(BitmapDescriptorFactory.defaultMarker());
//                                //获取点击的经纬度，并且传给新的LatLng
//                                LatLng ll = new LatLng(data.get(i).getLat(), data.get(i).getLon());
//                                mk.position(ll);
//                                //清除所有marker等，保留自身
//                                aMap.clear();
//                                //输入新的经纬度和地图缩放发小（数字越大就是放大）
//                                CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(ll, 20);
//                                aMap.animateCamera(cu);
//                                aMap.addMarker(mk);
//                            }
//                        });
                    }
                }
            }
        }
    }


    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();//根据activity的状态改变地图的状态
    }

    @Override
    public void onPause() {
        mapView.onPause();
        aMap.clear();

        super.onPause();

    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void getLatlon(String cityName) {
        city = cityName;

        GeocodeSearch geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

                if (i == 1000) {
                    if (geocodeResult != null && geocodeResult.getGeocodeAddressList() != null &&
                            geocodeResult.getGeocodeAddressList().size() > 0) {
                        Code = "true";
                        GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                        lat = geocodeAddress.getLatLonPoint().getLatitude();//纬度
                        lon = geocodeAddress.getLatLonPoint().getLongitude();//经度
                        String adcode = geocodeAddress.getAdcode();//区域编码
                        // 如果没有则创建

                        MarkerOptions mk = new MarkerOptions();
                        mk.icon(BitmapDescriptorFactory.defaultMarker());
                        //获取点击的经纬度，并且传给新的LatLng
                        latlng = new LatLng(lat, lon);
//                        mk.position(latlng);
//                        //清除所有marker等，保留自身
//                        aMap.clear();
//                        //输入新的经纬度和地图缩放发小（数字越大就是放大）
                        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(latlng, 17);
                        aMap.animateCamera(cu);
                        markerMap.clear();
//                        aMap.addMarker(mk);
//                        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
                        Log.e("地理编码", geocodeAddress.getAdcode() + "");
                        Log.e("纬度latitude", lat + "");
                        Log.e("经度longititude", lon + "");
//                        doSearchQuery();
                    } else {
                        ToastUtil.show("地址名出错");
                    }
                }
            }
        });

        GeocodeQuery geocodeQuery = new GeocodeQuery(cityName.trim(), "29");
        geocodeSearch.getFromLocationNameAsyn(geocodeQuery);


    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {


    }
    Intent intent;
    long mLasttime;
    @OnClick({R.id.ivMy, R.id.ivCity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivMy:
                intent = new Intent(mContext, MyActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ivCity:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(this, CityListActivity.class);
                intent.putExtra("name","新增任务地址");
                startActivityForResult(intent, 200);
                break;
        }
    }
    /**
     * 回传的参数
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null)return;

        if (resultCode == 201) {
            if (requestCode ==200){
                String   city = data.getStringExtra("key");
//                tv_new_location_area.setText(city+"");
                getLatlon (city);

//                latlng = new LatLng(lon, lat);//获取经纬度
//                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 20), 1000, null);//搜索的（latlag(经纬度)，V:20(缩放大小(10-20)，l:1000(搜索范围)）
//                                //刷新小蓝点

//                doSearchQuery();
            }
        }

    }
}
