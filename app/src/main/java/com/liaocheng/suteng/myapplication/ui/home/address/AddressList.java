package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MyAddressInfoBean;
import com.liaocheng.suteng.myapplication.model.MyLiveList;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.presenter.SitePresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.SiteContact;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2018/3/31.
 */

public class AddressList extends BaseActivity<SitePresenter> implements SiteContact.View, AddressListAdapter.OnItemClickListener {
    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;

    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.ivMoRenAddress)
    ImageView ivMoRenAddress;
    @BindView(R.id.tvMoRenDiZhi)
    TextView tvMoRenDiZhi;
    @BindView(R.id.tvMoRenDiZhiXiangQing)
    TextView tvMoRenDiZhiXiangQing;
    @BindView(R.id.tvDiZhiMoren)
    ImageView tvDiZhiMoren;
    @BindView(R.id.relMoRen)
    RelativeLayout relMoRen;
    @BindView(R.id.ivGongSiAddress)
    ImageView ivGongSiAddress;
    @BindView(R.id.tvGongSiDiZhi)
    TextView tvGongSiDiZhi;
    @BindView(R.id.tvGongSiDiZhiXiangQing)
    TextView tvGongSiDiZhiXiangQing;
    @BindView(R.id.tvGongSiGongSi)
    ImageView tvGongSiGongSi;
    @BindView(R.id.relGongSi)
    RelativeLayout relGongSi;
    @BindView(R.id.ivJiaAddress)
    ImageView ivJiaAddress;
    @BindView(R.id.tvJiaDiZhi)
    TextView tvJiaDiZhi;
    @BindView(R.id.tvJiaDiZhiXiangQing)
    TextView tvJiaDiZhiXiangQing;
    @BindView(R.id.tvDiZhiJia)
    ImageView tvDiZhiJia;
    @BindView(R.id.relJia)
    RelativeLayout relJia;
    @BindView(R.id.linMyDiZhi)
    LinearLayout linMyDiZhi;
    @BindView(R.id.tvChangYong)
    TextView tvChangYong;
    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerview;
    @BindView(R.id.tvCity)
    TextView tvCity;
    private AddressListAdapter mAddressListAdapter = null;
    private LinearLayoutManager mLinearLayoutManager;
    private List<MyLiveList> mydata = new ArrayList<>();
    private int mEditMode = MYLIVE_MODE_CHECK;
    private boolean editorStatus = false;
    private int index = 0;
    public TextView tvTitle;

    /*定位相关*/
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private void initListener() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
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
        mAddressListAdapter.setOnItemClickListener(this);

    }
double lon;
    double lat;

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    tvCity.setText(aMapLocation.getCity() + "");
                    lon=aMapLocation.getLongitude();
                    lat = aMapLocation.getLatitude();
                } else {
                    tvCity.setText("定位失败");
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("yue.huang", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    @Override
    public void showError(int reqCode, String msg) {
        mRecyclerview.refreshComplete();
        mRecyclerview.loadMoreComplete();

        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg));
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.address_list;
    }

    String mId = "";
    int page = 1;

    @Override
    public void initEventAndData() {
//        AutoSizeConfig.getInstance();
        SPCommon.setString("token", "cd53468f0db916abe6ff1da0709b7b95$10002080");
        toolBar.setBackFinish().setTitleText("地址管理");
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        if (!TextUtils.isEmpty(mId)) {
            toolBar.setBackFinish().setTitleText("选择地址");
        }

        mRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0;
                mPresenter.addressListContact(SPCommon.getString("token", ""), page + "");
            }

            @Override
            public void onLoadMore() {
                page = page + 5;
                mPresenter.addressListContact(SPCommon.getString("token", ""), page + "");
            }
        });
        mPresenter.getMyAddressList(SPCommon.getString("token", ""));
        mydata = new ArrayList<>();
        EventBus.getDefault().register(this);
        mAddressListAdapter = new AddressListAdapter();
        mRecyclerview.setAdapter(mAddressListAdapter);
        initListener();
        mRecyclerview.refresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RecruitEvent event) {
        if (event == null)
            return;
        if (event.getShow()) {
          mPresenter.getMyAddressList(SPCommon.getString("token",""));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
//        mPresenter.addressListContact(SPCommon.getString("token", ""));
        super.onStart();
    }


    @Override
    public void AddressListContactSuccess(MyAddressInfoBean siteBean) {
        mRecyclerview.refreshComplete();
        mRecyclerview.loadMoreComplete();
        if (siteBean.data != null && siteBean.data.size() > 0) {
            ivNull.setVisibility(View.GONE);
            mAddressListAdapter.setData(siteBean.data);
            initListener();
        } else {
            if (page == 1) {
                ivNull.setVisibility(View.VISIBLE);
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
                    tvMoRenDiZhi.setText(siteBean.data.get(i).address + siteBean.data.get(i).detailAddress + "");
                    tvMoRenDiZhiXiangQing.setText(siteBean.data.get(i).contactPhone + "");
                }
                if (siteBean.data.get(i).addressType.endsWith("2")) {
                    gongsi = i;
                    tvGongSiDiZhi.setText(siteBean.data.get(i).address + siteBean.data.get(i).detailAddress + "");
                    tvGongSiDiZhiXiangQing.setText(siteBean.data.get(i).contactPhone + "");
                }
                if (siteBean.data.get(i).addressType.endsWith("3")) {
                    jia = i;
                    tvJiaDiZhi.setText(siteBean.data.get(i).address + siteBean.data.get(i).detailAddress + "");
                    tvJiaDiZhiXiangQing.setText(siteBean.data.get(i).contactPhone + "");
                }
            }
            mAddressListAdapter.setData(siteBean.data);
        }
    }

    @Override
    public void updateSuccess() {

    }

    @Override
    public void addNewAddresselSuccess() {

    }

    @Override
    public void onItemClickListener(int pos) {

    }

    long mLasttime;
    Intent intent;

    @OnClick({R.id.relMoRen, R.id.relGongSi, R.id.relJia, R.id.tvDiZhiMoren, R.id.tvGongSiGongSi, R.id.tvDiZhiJia,R.id.tvCity, R.id.et_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relMoRen:
                break;
            case R.id.relGongSi:
                break;
            case R.id.relJia:
                break;
            case R.id.tvDiZhiMoren:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(this, NewLocationSeekActivity.class);
                intent.putExtra("lon",lon);
                intent.putExtra("lat",lat);
                intent.putExtra("type",1);

                if (TextUtils.isEmpty(tvMoRenDiZhi.getText().toString())){
                    intent.putExtra("isnew",1);
                }else {
                    if (listMyAddress.size()>moren){
                        intent.putExtra("id",listMyAddress.get(moren).id);
                    }
                    intent.putExtra("isnew",0);
                }
                intent.putExtra("city",tvCity.getText().toString()+"");
                startActivity(intent);
                break;
            case R.id.tvGongSiGongSi:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(this, NewLocationSeekActivity.class);
                intent.putExtra("lon",lon);
                intent.putExtra("lat",lat);
                intent.putExtra("type",2);
                if (TextUtils.isEmpty(tvGongSiDiZhi.getText().toString())){
                    intent.putExtra("isnew",1);
                }else {
                    if (listMyAddress.size()>moren){
                        intent.putExtra("id",listMyAddress.get(gongsi).id);
                    }
                    intent.putExtra("isnew",0);
                }
                intent.putExtra("city",tvCity.getText().toString()+"");
                startActivity(intent);
                break;
            case R.id.tvDiZhiJia:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(this, NewLocationSeekActivity.class);
                intent.putExtra("lon",lon);
                intent.putExtra("lat",lat);
                intent.putExtra("type",3);
                if (TextUtils.isEmpty(tvJiaDiZhi.getText().toString())){
                    intent.putExtra("isnew",1);
                }else {
                    if (listMyAddress.size()>moren){
                        intent.putExtra("id",listMyAddress.get(jia).id);
                    }
                    intent.putExtra("isnew",0);
                }
                intent.putExtra("city",tvCity.getText().toString()+"");
                startActivity(intent);

                break;
            case R.id.tvCity:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(this, CityListActivity.class);
                intent.putExtra("name","选择城市");
                startActivityForResult(intent, 200);
                break;
            case R.id.et_search:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(this, NewLocationSeekActivity.class);
                intent.putExtra("lon",lon);
                intent.putExtra("lat",lat);
                intent.putExtra("type",0);
                intent.putExtra("city",tvCity.getText().toString()+"");
                startActivity(intent);
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
                tvCity.setText(city+"");
                getLatlon (city);

            }
        }
    }

    private void getLatlon(String cityName){

        GeocodeSearch geocodeSearch=new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

                if (i==1000){
                    if (geocodeResult!=null && geocodeResult.getGeocodeAddressList()!=null &&
                            geocodeResult.getGeocodeAddressList().size()>0){
                        GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                        lat = geocodeAddress.getLatLonPoint().getLatitude();//纬度
                        lon = geocodeAddress.getLatLonPoint().getLongitude();//经度


                        Log.e("地理编码", geocodeAddress.getAdcode()+"");
                        Log.e("纬度latitude",lat+"");
                        Log.e("经度longititude",lon+"");

                    }else {
                        ToastUtil.show("地址名出错");
                    }
                }
            }
        });

        GeocodeQuery geocodeQuery=new GeocodeQuery(cityName.trim(),"29");
        geocodeSearch.getFromLocationNameAsyn(geocodeQuery);


    }
}
