package com.liaocheng.suteng.myapplication.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.amap.api.maps.MapView;
import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.flyco.tablayout.SlidingTabLayout;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.presenter.FaHuoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FaHuoActivity extends BaseActivity<FaHuoPresenter> {
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.stlTitle)
    SlidingTabLayout stlTitle;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.linBom)
    LinearLayout linBom;
    @BindView(R.id.toolBar)
    MyToolBar toolBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fahuo;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("我要发货").setBackFinish();
//        mPresenter.initMap(mapView, "36.46807421644299", "115.95567726928711", "36.47028295377302", "115.97867989379883");

    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
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
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
