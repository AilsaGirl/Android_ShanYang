package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.circle.common.base.BaseActivity;
import com.circle.common.util.StatusBarUtils;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.util.City;
import com.liaocheng.suteng.myapplication.util.DBManager;
import com.liaocheng.suteng.myapplication.view.LetterBar;


import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class CityListActivity extends BaseActivity {
    private ImageView clearIV;
    private EditText searchET;
    private ListView cityListLV;
    private TextView selectedTV;
    private LetterBar letterBar;
    private ListView searchedCityList;
    private List<City> cities;
    private CityAdapter adapter;
    private SearchedCityAdapter searchedCityAdapter;
    private TextView tvCity, tvTag,tvBack;
    /*定位相关*/
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    String tv_tit;

    long mLasttime;
    protected void initView() {
//        StatusBarUtils.with(this).setDrawable(mContext.getResources().getDrawable(R.drawable.bg_toolbar_gray)).init();     //设置状态栏
        Intent intent = getIntent();
        tv_tit = intent.getStringExtra("name");

        clearIV = (ImageView) findViewById(R.id.iv_search_clear);
        searchET = (EditText) findViewById(R.id.et_search);
        cityListLV = (ListView) findViewById(R.id.city_list);
        selectedTV = (TextView) findViewById(R.id.selected_tv);
        letterBar = (LetterBar) findViewById(R.id.letterbar);
        searchedCityList = (ListView) findViewById(R.id.searched_city_list);
        tvCity = (TextView) findViewById(R.id.tvCity);
        tvTag = (TextView) findViewById(R.id.tvTag);
        tvBack= (TextView) findViewById(R.id.tvBack);
        clearIV.setOnClickListener(clickListener);
        searchET.addTextChangedListener(textWatcher);
        letterBar.setSelectedListener(selectedListener);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (DBManager.copyDB(this)) {
            cities = DBManager.getAllCity();
            adapter = new CityAdapter(this, cities);
            adapter.setAdapterListener(adapterListener);
            cityListLV.setAdapter(adapter);
            cityListLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(CityListActivity.this, cities.get(position).getName(), Toast.LENGTH_SHORT).show();
                    if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                        return;
                    mLasttime = System.currentTimeMillis();
                   Intent  intent = new Intent();
                    intent.putExtra("key", String.valueOf(cities.get(position).getName()));
                    setResult(201, intent);
                    CityListActivity.this.finish();
                }
            });

            searchedCityAdapter = new SearchedCityAdapter(this);
            searchedCityList.setAdapter(searchedCityAdapter);
            searchedCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(CityListActivity.this, ((City) searchedCityAdapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
                    Intent  intent = new Intent();
                    intent.putExtra("key", ((City) searchedCityAdapter.getItem(position)).getName());
                    setResult(201, intent);
                    CityListActivity.this.finish();
                }
            });

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
//            mLocationOption.setOnceLocationLatest(true);
            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            //启动定位
            mLocationClient.startLocation();
        }
    }


    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    adapter.setCurrentPosition(aMapLocation.getCity());
                    tvCity.setText(aMapLocation.getCity() + "");
                    tvTag.setText("当前定位位置");
                } else {
                    tvTag.setText("定位失败");
                    adapter.setCurrentPosition("定位失败");
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("yue.huang", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    private CityAdapter.AdapterListener adapterListener = new CityAdapter.AdapterListener() {
        @Override
        public void startPosition() {
            mLocationClient.startLocation();
        }

        @Override
        public void hotCityClick(String city) {
            Toast.makeText(CityListActivity.this, city, Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_search_clear:
                    searchET.setText("");
                    break;
            }
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            clearIV.setVisibility(View.VISIBLE);
            searchedCityList.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            List<City> cities = DBManager.searchCity(s.toString());
            searchedCityAdapter.setCityList(cities);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().equals("")) {
                clearIV.setVisibility(View.GONE);
                searchedCityList.setVisibility(View.GONE);
            }
        }
    };

    private LetterBar.SelectedListener selectedListener = new LetterBar.SelectedListener() {
        @Override
        public void onSelected(String letter) {
            selectedTV.setText(letter);
            selectedTV.setVisibility(View.VISIBLE);
            cityListLV.setSelection(adapter.getLetterPosition(letter.toLowerCase()));
        }

        @Override
        public void onCancel() {
            selectedTV.setVisibility(View.GONE);
        }
    };


    @Override
    public void showError(int reqCode, String msg) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.citylist;
    }

    @Override
    public void initEventAndData() {
        initView();
    }
}
