package com.liaocheng.suteng.myapplication.ui.home.fahuo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
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
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.ChangYongAddressBean;
import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;
import com.liaocheng.suteng.myapplication.model.LocationBean;
import com.liaocheng.suteng.myapplication.model.MyAddressInfoBean;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.presenter.FaHuoSongPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.FaHuoSongContact;
import com.liaocheng.suteng.myapplication.ui.home.address.AddressListAdapter;
import com.liaocheng.suteng.myapplication.ui.home.address.PoiSearchAdapter;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class FaHuoSongShouHuoActivity extends BaseActivity<FaHuoSongPresenter> implements FaHuoSongContact.View, TextWatcher, LocationSource,
        AMapLocationListener, AMap.OnCameraChangeListener, PoiSearch.OnPoiSearchListener, View.OnClickListener, GeocodeSearch.OnGeocodeSearchListener {


    MapView mapView;//显示地图
    ListView mapList;//周围poi列表

    public static final String KEY_LAT = "lat";
    public static final String KEY_LNG = "lng";
    public static final String KEY_DES = "des";
    public static final String KEY_SNI = "snippet";

    GeocodeSearch geocodeSearch;
    GeocodeQuery geocodeQuery;


    private AMapLocationClient mLocationClient;
    private OnLocationChangedListener mListener;
    private LatLng latlng;//经纬度
    private AMap aMap;
    private String deepType = "";// poi搜索类型
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private PoiResult poiResult; // poi返回的结果
    private ArrayList<PoiItem> poiItems;// poi数据
    double lon;//NewLocationSeekActivity回传的经纬度
    double lat;//NewLocationSeekActivity回传经纬度
    String Code = "";//NewLocationSeekActivity回传的值
    String code = "true";
    LatLonPoint lp;//经纬度
    String location;//NewLocationSeekActivity回传地址

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
    ImageView Iv_back;//标题的back
    android.widget.AutoCompleteTextView AutoCompleteTextView;//输入框
    TextView tx_sousuo;//搜索
    TextView tvCity;//搜索
    MyToolBar toolBarl;
    LinearLayout linFuJin, linChangYong, linChangYongDiZhi;
    TextView tvFuJin, tvChangYong;
    View vChangYong, vFuJin;
    boolean isSouSuo = false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isSouSuo){
                isSouSuo = false;
                linAddress.setVisibility(View.VISIBLE);
                linSouSuo.setVisibility(View.GONE);
            }else {
                final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(FaHuoSongShouHuoActivity.this);
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
        }
        return false;
    }
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouhuo_song);
        ButterKnife.bind(this);
        initview();
        mapView = (MapView) findViewById(R.id.map_local);
        mapView.onCreate(savedInstanceState);
        toolBarl.setTitleText("收货地址").setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSouSuo){
                    isSouSuo = false;
                    linAddress.setVisibility(View.VISIBLE);
                    linSouSuo.setVisibility(View.GONE);
                }else {
                    final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(FaHuoSongShouHuoActivity.this);
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

            }
        });
        toolBarl.setRight("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvAddress.getText().toString().equals("请选择地址")){
                    ToastUtil.show("地址不能为空");
                    return;
                }
                if (addressModel==null||TextUtils.isEmpty(addressModel.address)){
                    ToastUtil.show("地址不能为空");
                    return;
                }
                if (TextUtils.isEmpty(etTel.getText().toString())){
                    ToastUtil.show("电话不能为空");
                }else {
                    isclick = true;
                addressModel.detailAddress = etDiZhi.getText().toString()+"";
                addressModel.contactName = etName.getText().toString()+"";
                addressModel.contactPhone = etTel.getText().toString()+"";
                EventBus.getDefault().postSticky(new RecruitEvent(addressModelFaHuo,addressModel));
                    Intent   intent = new Intent(mContext, FaHuoXiaDanSongActivity.class);
                    mContext.startActivity(intent);
                finish();
                }
            }
        });

        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        addressModelFaHuo = (FaHuoAddressModel) getIntent().getSerializableExtra("address_data");
//        if (addressModelFaHuo==null)return;
        type = addressModelFaHuo.type;
        city = addressModelFaHuo.city;
        tvCity.setText(city+"");
//        tvXiangXi.setText(addressModel.ConcreteAdd+"");
//        tvAddress.setText(addressModel.address+"");
//
//        if (!TextUtils.isEmpty(addressModel.contactPhone))
//            etTel.setText(addressModel.contactPhone+"");
//        if (!TextUtils.isEmpty(addressModel.contactName))
//            etName.setText(addressModel.contactName+"");
//        if (!TextUtils.isEmpty(addressModel.detailAddress))
//            etDiZhi.setText(addressModel.detailAddress+"");

        onCallWrapper();
        init();
    }
    boolean isclick = false;
    int type =3;
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(RecruitEvent event) {
        if (event == null)
            return;
        if (isclick)return;
        if (event.getAddressModel() != null) {
            addressModelFaHuo = event.getAddressModel();
            type = addressModelFaHuo.type;
        }
        if (event.getAddressModelShou() != null) {
            addressModel = event.getAddressModel();
            tvXiangXi.setText(addressModel.ConcreteAdd+"");
            tvAddress.setText(addressModel.address+"");
            city = addressModel.city;
            if (!TextUtils.isEmpty(addressModel.contactPhone))
                etTel.setText(addressModel.contactPhone+"");
            if (!TextUtils.isEmpty(addressModel.contactName))
                etName.setText(addressModel.contactName+"");
            if (!TextUtils.isEmpty(addressModel.detailAddress))
                etDiZhi.setText(addressModel.detailAddress+"");
            tvCity.setText(city+"");
        }

    }
    /**
     * 位置权限
     */
    public void onCallWrapper() {
        mPermissionList.clear();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(FaHuoSongShouHuoActivity.this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(FaHuoSongShouHuoActivity.this, permissions, REQUEST_CODE_ASK_CALL_PHONE);
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
                    Toast.makeText(FaHuoSongShouHuoActivity.this, "Permission Denied",
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

//

        if (city.contains("聊城")){
            setUpMap();
        }else {
            getLatlon(city);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shouhuo_song;
    }

    @Override
    public void initEventAndData() {

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
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.startLocation();
        }
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

    }


    ImageView ivMoRenAddress;
    TextView tvMoRenDiZhi;
    RelativeLayout relMoRen;
    ImageView ivGongSiAddress;
    TextView tvGongSiDiZhi;
    RelativeLayout relGongSi;
    ImageView ivJiaAddress;
    TextView tvJiaDiZhi;
    RelativeLayout relJia;
    XRecyclerView mRecyclerview;
    ImageView ivSouSuo;
    EditText etDiZhi,etName,etTel;
    TextView tvAddress,tvXiangXi;
    LinearLayout linSouSuo,linAddress;
    ListView sousuo_list;
    RelativeLayout relSouSuo;


    /**
     * 绑定ID
     */
    private void initview() {
        sousuo_list = findViewById(R.id.sousuo_list);
        linSouSuo = findViewById(R.id.linSouSuo);
        linAddress = findViewById(R.id.linAddress);
        relSouSuo = findViewById(R.id.relSouSuo);

        ivSouSuo = findViewById(R.id.ivSouSuo);
        etDiZhi = findViewById(R.id.etDiZhi);
        etName = findViewById(R.id.etName);
        etTel = findViewById(R.id.etTel);
        tvAddress = findViewById(R.id.tvAddress);
        tvXiangXi = findViewById(R.id.tvXiangXi);

        mRecyclerview = findViewById(R.id.recyclerview);
        relJia = findViewById(R.id.relJia);
        tvJiaDiZhi = findViewById(R.id.tvJiaDiZhi);
        ivJiaAddress = findViewById(R.id.ivJiaAddress);
        relGongSi = findViewById(R.id.relGongSi);
        ivMoRenAddress = findViewById(R.id.ivMoRenAddress);
        tvMoRenDiZhi = findViewById(R.id.tvMoRenDiZhi);
        relMoRen = findViewById(R.id.relMoRen);
        ivGongSiAddress = findViewById(R.id.ivGongSiAddress);
        tvGongSiDiZhi = findViewById(R.id.tvGongSiDiZhi);

        mapList = findViewById(R.id.map_list);
        Iv_back = findViewById(R.id.Iv_back);
        AutoCompleteTextView = findViewById(R.id.AutoCompleteTextView);
        tx_sousuo = findViewById(R.id.tx_sousuo);
        tvCity = findViewById(R.id.tvCity);
        toolBarl = findViewById(R.id.toolBar);
        AutoCompleteTextView.addTextChangedListener(this);
        AutoCompleteTextView.setThreshold(1);
        linFuJin = findViewById(R.id.linFuJin);
        linChangYong = findViewById(R.id.linChangYong);
        linChangYongDiZhi = findViewById(R.id.linChangYongDiZhi);
        linFuJin.setOnClickListener(this);
        linChangYong.setOnClickListener(this);
        relJia.setOnClickListener(this);
        relGongSi.setOnClickListener(this);
        relMoRen.setOnClickListener(this);
        tvFuJin = findViewById(R.id.tvFuJin);
        tvChangYong = findViewById(R.id.tvChangYong);
        vChangYong = findViewById(R.id.vChangYong);
        vFuJin = findViewById(R.id.vFuJin);
        relSouSuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSouSuo = true;
                linAddress.setVisibility(View.GONE);
                linSouSuo.setVisibility(View.VISIBLE);
            }
        });



        mRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.addressListContact(SPCommon.getString("token", ""), page + "");
            }

            @Override
            public void onLoadMore() {
                page = page ++;
                mPresenter.addressListContact(SPCommon.getString("token", ""), page + "");
            }
        });
        mPresenter.getMyAddressList(SPCommon.getString("token", ""));
        mAddressListAdapter = new AddressListAdapter();
        mRecyclerview.setAdapter(mAddressListAdapter);
        mRecyclerview.refresh();
        mAddressListAdapter.setOnItemClickListener(new AddressListAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos) {

                if (addressModel==null){
                    addressModel = new FaHuoAddressModel();
                }
                    addressModel.type =type;

                addressModel.address = mChangAddressList.get(pos).sendAddress;
                addressModel.detailAddress = mChangAddressList.get(pos).sendDetailAdd;
                addressModel.contactName = mChangAddressList.get(pos).sendName;
                addressModel.contactPhone = mChangAddressList.get(pos).sendPhone;
                addressModel.ConcreteAdd = mChangAddressList.get(pos).sendConcreteAdd;
                addressModel.lat = mChangAddressList.get(pos).sendLat;
                addressModel.lon = mChangAddressList.get(pos).sendLong;
                addressModel.is_result =0;

                tvXiangXi.setText(mChangAddressList.get(pos).sendConcreteAdd+"");
                tvAddress.setText(mChangAddressList.get(pos).sendAddress+"");
                etDiZhi.setText(mChangAddressList.get(pos).sendDetailAdd+"");
                etName.setText(mChangAddressList.get(pos).sendName+"");
                etTel.setText(mChangAddressList.get(pos).sendPhone+"");

            }
        });
    }
    AddressListAdapter   mAddressListAdapter;
    /**
     * 开始进行poi搜索
     */
    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery(int type) {
        aMap.setOnMapClickListener(null);// 进行poi搜索时清除掉地图点击事件
        System.out.println(type+"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈+"+deepType);
        int currentPage = 0;
        //搜索（S：关键字，
        // S1：汽车服务|汽车销售|汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
        //住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
        //金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        // S3：城市，可以空字符串，空字符串代表全国
        // 三个个都可以为空）

        if(type==1){
            query = new PoiSearch.Query("", "", city+"");
            query.setPageSize(50);// 设置每页最多返回多少条poiitem
            query.setPageNum(currentPage);// 设置查第一页
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            lp = new LatLonPoint(latlng.latitude, latlng.longitude);//检索的经纬度
            poiSearch.setBound(new PoiSearch.SearchBound(lp, 50000, true)); // 设置搜索区域为以lp点为圆心，其周围2000米范围
        }else {
            query = new PoiSearch.Query(deepType, "", city+"");
            query.setPageSize(50);// 设置每页最多返回多少条poiitem
            query.setPageNum(currentPage);// 设置查第一页
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            lp = new LatLonPoint(latlng.latitude, latlng.longitude);//检索的经纬度
        }

//        poiSearch.setBound(new PoiSearch.SearchBound(lp, 50000, true)); // 设置搜索区域为以lp点为圆心，其周围2000米范围
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


                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 17), 1000, null);//搜索的（latlag(经纬度)，V:20(缩放大小(10-20)，l:1000(搜索范围)）

                doSearchQuery(1);
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

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        latlng = cameraPosition.target;
        aMap.clear();
        aMap.addMarker(new MarkerOptions().position(latlng));
        doSearchQuery(1);
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
                        //添加数据
                        data.add(new LocationBean(lon, lat, title, text, texta, sheng, shi, qu));
                    }

                    if (data != null && data.size() > 0) {

                        final PoiSearchAdapter adapter = new PoiSearchAdapter(this, data);
                        mapList.setAdapter(adapter);
//哈哈哈哈哈
//                        if (addressModel==null){
//                            addressModel = new FaHuoAddressModel();
//                        }
//                        addressModel.type =type;
//
//                        addressModel.address = data.get(0).getTitle()+"";
//                        addressModel.detailAddress = data.get(0).getSheng()+data.get(0).getShi()+data.get(0).getQu()+data.get(0).getContent()+"";
//                        addressModel.contactName = etName.getText().toString()+"";
//                        addressModel.contactPhone = etTel.getText().toString()+"";
//                        addressModel.ConcreteAdd = etDiZhi.getText().toString()+"";
//                        addressModel.lat = String.valueOf(data.get(0).getLat());
//                        addressModel.lon = String.valueOf(data.get(0).getLon());
//                        addressModel.is_result =0;

//                        tvXiangXi.setText(data.get(0).getSheng()+data.get(0).getShi()+data.get(0).getQu()+"");
//                        tvAddress.setText(data.get(0).getTitle()+"");
//                        etDiZhi.setText(etDiZhi.getText().toString()+"");
//                        etName.setText(etName.getText().toString()+"");
//                        etTel.setText(etTel.getText().toString()+"");

                        sousuo_list.setAdapter(adapter);
                        sousuo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                isSouSuo = false;
                                linAddress.setVisibility(View.VISIBLE);
                                linSouSuo.setVisibility(View.GONE);
//                                Intent intent=new Intent();
//                                intent.putExtra("sheng",data.get(i).getSheng());
//                                intent.putExtra("shi",data.get(i).getShi());
//                                intent.putExtra("qu",data.get(i).getQu());
//                                intent.putExtra("title",data.get(i).getTitle());
//                                intent.putExtra("lon",String.valueOf(data.get(i).getLon()));
//                                intent.putExtra("lat",String.valueOf(data.get(i).getLat()));
//                                setResult(110, intent);
//                                FaHuoSongFaHuoActivity.this.finish();

                                if (addressModel==null){
                                    addressModel = new FaHuoAddressModel();
                                }
                                addressModel.type =type;

                                addressModel.address = data.get(i).getTitle()+"";
                                addressModel.detailAddress = data.get(i).getSheng()+data.get(i).getShi()+data.get(i).getQu()+data.get(i).getContent()+"";
                                addressModel.contactName = etName.getText().toString()+"";
                                addressModel.contactPhone = etTel.getText().toString()+"";
                                addressModel.ConcreteAdd = etDiZhi.getText().toString()+"";
                                addressModel.lat = String.valueOf(data.get(i).getLat());
                                addressModel.lon = String.valueOf(data.get(i).getLon());
                                addressModel.is_result =0;

                                tvXiangXi.setText(data.get(i).getSheng()+data.get(i).getShi()+data.get(i).getQu()+"");
                                tvAddress.setText(data.get(i).getTitle()+"");
                                etDiZhi.setText(etDiZhi.getText().toString()+"");
                                etName.setText(etName.getText().toString()+"");
                                etTel.setText(etTel.getText().toString()+"");

//                                //刷新小蓝点
                                MarkerOptions mk = new MarkerOptions();
                                mk.icon(BitmapDescriptorFactory.defaultMarker());
                                //获取点击的经纬度，并且传给新的LatLng
                                LatLng ll = new LatLng(data.get(i).getLat(), data.get(i).getLon());
                                mk.position(ll);
                                //清除所有marker等，保留自身
                                aMap.clear();
                                //输入新的经纬度和地图缩放发小（数字越大就是放大）
                                CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(ll, 17);
                                aMap.animateCamera(cu);
                                aMap.addMarker(mk);
                            }
                        });
                        //poi列表的item的点击事件
                        mapList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                Intent intent=new Intent();
//                                intent.putExtra("sheng",data.get(i).getSheng());
//                                intent.putExtra("shi",data.get(i).getShi());
//                                intent.putExtra("qu",data.get(i).getQu());
//                                intent.putExtra("title",data.get(i).getTitle());
//                                intent.putExtra("lon",String.valueOf(data.get(i).getLon()));
//                                intent.putExtra("lat",String.valueOf(data.get(i).getLat()));
//                                setResult(110, intent);
//                                FaHuoSongFaHuoActivity.this.finish();

                                if (addressModel==null){
                                    addressModel = new FaHuoAddressModel();
                                }
                                addressModel.type =type;

                                addressModel.address = data.get(i).getTitle()+"";
                                addressModel.detailAddress = data.get(i).getSheng()+data.get(i).getShi()+data.get(i).getQu()+data.get(i).getContent()+"";
                                addressModel.contactName = etName.getText().toString()+"";
                                addressModel.contactPhone = etTel.getText().toString()+"";
                                addressModel.ConcreteAdd = etDiZhi.getText().toString()+"";
                                addressModel.lat = String.valueOf(data.get(i).getLat());
                                addressModel.lon = String.valueOf(data.get(i).getLon());
                                addressModel.is_result =0;

                                tvXiangXi.setText(data.get(i).getSheng()+data.get(i).getShi()+data.get(i).getQu()+"");
                                tvAddress.setText(data.get(i).getTitle()+"");
                                etDiZhi.setText(etDiZhi.getText().toString()+"");
                                etName.setText(etName.getText().toString()+"");
                                etTel.setText(etTel.getText().toString()+"");

//                                //刷新小蓝点
                                MarkerOptions mk = new MarkerOptions();
                                mk.icon(BitmapDescriptorFactory.defaultMarker());
                                //获取点击的经纬度，并且传给新的LatLng
                                LatLng ll = new LatLng(data.get(i).getLat(), data.get(i).getLon());
                                mk.position(ll);
                                //清除所有marker等，保留自身
                                aMap.clear();
                                //输入新的经纬度和地图缩放发小（数字越大就是放大）
                                CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(ll, 17);
                                aMap.animateCamera(cu);
                                aMap.addMarker(mk);
                            }
                        });
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
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    //    LinearLayout linFuJin,linChangYong,linChangYongDiZhi;
//    TextView tvFuJin,tvChangYong;
//    View vChangYong,vFuJin;
    long mLasttime;
    FaHuoAddressModel addressModel;
    FaHuoAddressModel addressModelFaHuo;
    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.linFuJin:
                tvFuJin.setTextColor(0xffE03D81);
                tvChangYong.setTextColor(0xff191919);
                vChangYong.setVisibility(View.GONE);
                vFuJin.setVisibility(View.VISIBLE);
                mapList.setVisibility(View.VISIBLE);
                linChangYongDiZhi.setVisibility(View.GONE);
                break;
            case R.id.linChangYong:
                tvChangYong.setTextColor(0xffE03D81);
                tvFuJin.setTextColor(0xff191919);
                vFuJin.setVisibility(View.GONE);
                vChangYong.setVisibility(View.VISIBLE);
                linChangYongDiZhi.setVisibility(View.VISIBLE);
                mapList.setVisibility(View.GONE);
                break;
            case R.id.relMoRen:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();

                if (TextUtils.isEmpty(tvMoRenDiZhi.getText().toString())){
                    ToastUtil.show("请先编辑地址");
                }else {

                    if (listMyAddress.size()>moren){

                        addressModel.type =type;
                        if (addressModel==null){
                            addressModel = new FaHuoAddressModel();
                        }
                        addressModel.address = listMyAddress.get(moren).address;
                        addressModel.detailAddress = listMyAddress.get(moren).detailAddress;
                        addressModel.contactName = listMyAddress.get(moren).contactName;
                        addressModel.contactPhone = listMyAddress.get(moren).contactPhone;
                        addressModel.ConcreteAdd = listMyAddress.get(moren).concreteAddress;
                        addressModel.lat = listMyAddress.get(moren).latitude;
                        addressModel.lon = listMyAddress.get(moren).accuracy;
                        addressModel.is_result =0;

                        tvXiangXi.setText(listMyAddress.get(moren).concreteAddress+"");
                        tvAddress.setText(listMyAddress.get(moren).address+"");
                        etDiZhi.setText(listMyAddress.get(moren).detailAddress+"");
                        etName.setText(listMyAddress.get(moren).contactName+"");
                        etTel.setText(listMyAddress.get(moren).contactPhone+"");
                    }else {
                        ToastUtil.show("该地址返回错误，请输入地址");
                    }

                }
                break;
            case R.id.relGongSi:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();


                if (TextUtils.isEmpty(tvGongSiDiZhi.getText().toString())){
                    ToastUtil.show("请先编辑地址");
                }else {

                    if (listMyAddress.size()>gongsi){

                            addressModel.type =type;

//                        intent.putExtra("id",listMyAddress.get(moren).id);
                        if (addressModel==null){
                            addressModel = new FaHuoAddressModel();
                        }
                        addressModel.address = listMyAddress.get(gongsi).address;
                        addressModel.detailAddress = listMyAddress.get(gongsi).detailAddress;
                        addressModel.contactName = listMyAddress.get(gongsi).contactName;
                        addressModel.contactPhone = listMyAddress.get(gongsi).contactPhone;
                        addressModel.ConcreteAdd = listMyAddress.get(gongsi).concreteAddress;
                        addressModel.lat = listMyAddress.get(gongsi).latitude;
                        addressModel.lon = listMyAddress.get(gongsi).accuracy;
//                        intent.putExtra("address_data", addressModel);
                        addressModel.is_result =0;
                        tvXiangXi.setText(listMyAddress.get(gongsi).concreteAddress+"");
                        tvAddress.setText(listMyAddress.get(gongsi).address+"");
                        etDiZhi.setText(listMyAddress.get(gongsi).detailAddress+"");
                        etName.setText(listMyAddress.get(gongsi).contactName+"");
                        etTel.setText(listMyAddress.get(gongsi).contactPhone+"");
                    }else {
                        ToastUtil.show("该地址返回错误，请输入地址");
                    }

                }

                break;
            case R.id.relJia:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();

                if (TextUtils.isEmpty(tvJiaDiZhi.getText().toString())){
                    ToastUtil.show("请先编辑地址");
                }else {
                    if (listMyAddress.size()>jia){

                            addressModel.type =type;

                        if (addressModel==null){
                            addressModel = new FaHuoAddressModel();
                        }
                        addressModel.address = listMyAddress.get(jia).address;
                        addressModel.detailAddress = listMyAddress.get(jia).detailAddress;
                        addressModel.contactName = listMyAddress.get(jia).contactName;
                        addressModel.contactPhone = listMyAddress.get(jia).contactPhone;
                        addressModel.ConcreteAdd = listMyAddress.get(jia).concreteAddress;
                        addressModel.lat = listMyAddress.get(jia).latitude;
                        addressModel.lon = listMyAddress.get(jia).accuracy;
//                        intent.putExtra("address_data", addressModel);
                        addressModel.is_result =0;
                        tvXiangXi.setText(listMyAddress.get(jia).concreteAddress+"");
                        tvAddress.setText(listMyAddress.get(jia).address+"");
                        etDiZhi.setText(listMyAddress.get(jia).detailAddress+"");
                        etName.setText(listMyAddress.get(jia).contactName+"");
                        etTel.setText(listMyAddress.get(jia).contactPhone+"");
                    }else {
                        ToastUtil.show("该地址返回错误，请输入地址");
                    }
                }
                break;

        }
    }

    /**
     * 回传的参数
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;


    }

    private void getLatlon(String cityName) {

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
                        MarkerOptions mk = new MarkerOptions();
                        mk.icon(BitmapDescriptorFactory.defaultMarker());
                        //获取点击的经纬度，并且传给新的LatLng
                        latlng = new LatLng(lat, lon);
                        mk.position(latlng);
                        //清除所有marker等，保留自身
                        aMap.clear();
                        //输入新的经纬度和地图缩放发小（数字越大就是放大）
                        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(latlng, 20);
                        aMap.animateCamera(cu);
                        aMap.addMarker(mk);

                        Log.e("地理编码", geocodeAddress.getAdcode() + "");
                        Log.e("纬度latitude", lat + "");
                        Log.e("经度longititude", lon + "");
//                        doSearchQuery(1);
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

    @Override
    public void showError(int reqCode, String msg) {
        mRecyclerview.refreshComplete();
        mRecyclerview.loadMoreComplete();

        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("jieshou", String.valueOf(charSequence) + "++");

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        deepType = String.valueOf(charSequence);
        Log.d("jieshou", String.valueOf(charSequence) + "--");
        if (charSequence.toString().equals("")) {
        } else {
            doSearchQuery(2);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        deepType = String.valueOf(editable);
        Log.d("jieshou", String.valueOf(editable) + "--");
        if (editable.toString().equals("")) {
        } else {
//            doSearchQuery();
        }

    }
    int page =1;
    List<ChangYongAddressBean.ChangYongAddressModel>   mChangAddressList = new ArrayList<>();
    @Override
    public void AddressListContactSuccess(ChangYongAddressBean siteBean) {
        mRecyclerview.refreshComplete();
        mRecyclerview.loadMoreComplete();
        if (siteBean.data != null && siteBean.data.size() > 0) {

            mAddressListAdapter.setData(siteBean.data);
            mChangAddressList = siteBean.data;
        } else {
            if (page == 1) {

            } else {
                ToastUtil.show("最后一页");
            }
        }
    }
    int jia = 0;
    int moren= 0;
    int gongsi= 0;
    List<MyAddressInfoBean.MyAddressModel> listMyAddress = new ArrayList();
    @Override
    public void setMyAddressList(MyAddressInfoBean siteBean) {
        if (siteBean.data != null && siteBean.data.size() > 0) {
            listMyAddress = siteBean.data;
            for (int i = 0; i < siteBean.data.size(); i++) {
                if (siteBean.data.get(i).addressType.endsWith("1")) {
                    moren = i;
                    tvMoRenDiZhi.setText( siteBean.data.get(i).concreteAddress +siteBean.data.get(i).address + "");
                }
                if (siteBean.data.get(i).addressType.endsWith("2")) {
                    gongsi = i;
                    tvGongSiDiZhi.setText( siteBean.data.get(i).concreteAddress +siteBean.data.get(i).address + "");
                }
                if (siteBean.data.get(i).addressType.endsWith("3")) {
                    jia = i;
                    tvJiaDiZhi.setText(siteBean.data.get(i).concreteAddress + siteBean.data.get(i).address + "");
                }
            }
        }
    }
}
