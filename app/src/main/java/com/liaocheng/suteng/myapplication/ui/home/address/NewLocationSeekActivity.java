package com.liaocheng.suteng.myapplication.ui.home.address;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.circle.common.base.BaseActivity;
import com.circle.common.util.StatusBarUtils;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.LocationBean;


import java.util.ArrayList;
import java.util.List;

public class NewLocationSeekActivity extends BaseActivity implements View.OnClickListener, TextWatcher, PoiSearch.OnPoiSearchListener {
    private ArrayList<PoiItem> poiItems;// poi数据
    private String keyWord = "";// 要输入的poi搜索关键字
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private PoiResult poiResult; // poi返回的结果
    ImageView Iv_back;//标题的back
    AutoCompleteTextView AutoCompleteTextView;//输入框
    TextView tx_sousuo;//搜索
    ListView listView;
    Intent intent;
    ListView listview_lishi;
    ArrayList<LocationBean> data = new ArrayList<LocationBean>();
    //权限数量
    String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    List<String> mPermissionList = new ArrayList<>();
    double lon;//NewLocationSeekActivity回传的经纬度
    double lat;//NewLocationSeekActivity回传经纬度
    double lonf;//NewLocationSeekActivity回传的经纬度
    double latf;//NewLocationSeekActivity回传经纬度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location_seek);
        Intent intent = getIntent();
        latf =  intent.getDoubleExtra("lat",0);
        lonf = intent.getDoubleExtra("lon",0);
        initview();


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_location_seek;
    }

    @Override
    public void initEventAndData() {

    }

    /**
     * 绑定ID
     */
    private void initview() {
//        StatusBarUtils.with(this).setDrawable(mContext.getResources().getDrawable(R.drawable.bg_toolbar_gray)).init();     //设置状态栏
        Iv_back = findViewById(R.id.Iv_back);
        AutoCompleteTextView = findViewById(R.id.AutoCompleteTextView);
        tx_sousuo = findViewById(R.id.tx_sousuo);
        Iv_back.setOnClickListener(this);
        tx_sousuo.setOnClickListener(this);
        AutoCompleteTextView.addTextChangedListener(this);
        AutoCompleteTextView.setThreshold(1);
        listView = findViewById(R.id.listview);
        listview_lishi=findViewById(R.id.listview_lishi);
        date();
        NewLocationSeekAdapter adapter = new NewLocationSeekAdapter(this, data);
        listview_lishi.setAdapter(adapter);
    }

    private void date() {
//        data.clear();
//        for (int i=0;i<10;i++){
//            double lon =12.00000;//经度
//            double lat = 150.00;//纬度
//            //获取标题
//            String title = "标题"+i;
//            //获取内容
//            String text = "内容"+i;
//            //获取城市名
//            String city = "城市"+i;
//            Log.d("jieshou", title);
//            //添加数据
//            data.add(new LocationBean(lon, lat, title, text, city,"","",""));
//        }
    }
    LatLonPoint lp;//经纬度
    private LatLng latlng;//经纬度
    /**
     * poi地址搜索
     */
    protected void doSearchQuery() {
        int currentPage = 0;
        //搜索（S：关键字，
        // S1：汽车服务|汽车销售|汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
        //住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
        //金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        // S3：城市，可以空字符串，空字符串代表全国
        // 三个个都可以为空）
        query = new PoiSearch.Query(keyWord, "", "");
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();// 异步搜索
        lp = new LatLonPoint(latf, lonf);//检索的经纬度
        poiSearch.setBound(new PoiSearch.SearchBound(lp, 5000, true)); // 设置搜索区域为以lp点为圆心，其周围2000米范围
        poiSearch.searchPOIAsyn();// 异步搜索

    }
    long mLasttime;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Iv_back:
                NewLocationSeekActivity.this.finish();
                break;
            case R.id.tx_sousuo:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                Log.d("jieshou",AutoCompleteTextView.getText()+"++++");
                intent = new Intent();
                intent.putExtra("key", String.valueOf(AutoCompleteTextView.getText()));
                intent.putExtra("true", "true");
                setResult(201, intent);
                NewLocationSeekActivity.this.finish();
                break;

        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("jieshou", String.valueOf(charSequence) + "++");

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        keyWord = String.valueOf(charSequence);
        Log.d("jieshou", String.valueOf(charSequence) + "--");
        if (charSequence.toString().equals("")){
            listview_lishi.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else {
            doSearchQuery();
            listview_lishi.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        keyWord = String.valueOf(editable);
        Log.d("jieshou", String.valueOf(editable) + "--");
        if (editable.toString().equals("")){
            listview_lishi.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else {
            doSearchQuery();
            listview_lishi.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /**
     * 搜索成功执行poi列表
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
                    for (com.amap.api.services.core.PoiItem item : poiItems) {
                        //获取经纬度对象
                        LatLonPoint llp = item.getLatLonPoint();
                        double lon = llp.getLongitude();//经度
                        double lat = llp.getLatitude();//纬度
                        Log.d("jieshou", String.valueOf(lon));
                        Log.d("jieshou", String.valueOf(lat));
                        //获取标题
                        String title = item.getTitle();
                        //获取内容
                        String text = item.getSnippet();
                        //获取城市名
                        String city = item.getCityName();
                        Log.d("jieshou", title);
                        String texta=item.getProvinceName()+item.getCityName()+item.getAdName();
                        String sheng=item.getProvinceName();
                        String shi=item.getCityName();
                        String qu=item.getAdName();
                        //添加数据
                        data.add(new LocationBean(lon, lat, title, text, texta,sheng,shi,qu));

                    }
                    if (data != null && data.size() > 0) {
                        NewLocationSeekAdapter adapter = new NewLocationSeekAdapter(this, data);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Log.d("jieshou", String.valueOf(data.get(i).getLon()));
                                Log.d("jieshou", String.valueOf(data.get(i).getLat()));
                                intent = new Intent();
                                intent.putExtra("lon", String.valueOf(data.get(i).getLat()));
                                intent.putExtra("lat", String.valueOf(data.get(i).getLon()));
                                intent.putExtra("location", String.valueOf(data.get(i).getTitle()));
                                intent.putExtra("city", data.get(i).getTexta());
                                intent.putExtra("true", "true");
                                setResult(20, intent);
                                NewLocationSeekActivity.this.finish();
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
    public void showError(int reqCode, String msg) {

    }
}