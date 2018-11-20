package com.liaocheng.suteng.myapplication.ui.my;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.circle.common.base.BaseActivity;
import com.circle.common.view.MyToolBar;
import com.flyco.tablayout.SlidingTabLayout;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.ui.my.fragment.FaHuoDingDanFragment;
import com.liaocheng.suteng.myapplication.ui.my.fragment.JieDanDingDanFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class DingDanGuanLiActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.stlTitle)
    SlidingTabLayout stlTitle;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dingdanguanli;
    }
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    String[] titleList;
    @Override
    public void initEventAndData() {
        titleList = new String[2];
        for (int i = 1; i < 3; i++) {

            if (i == 1) {
                titleList[0] = "发单订单";
                mFragmentList.add(new FaHuoDingDanFragment(i));
            }
            if (i == 2) {
                titleList[1] = "接单订单";
                mFragmentList.add(new JieDanDingDanFragment(i));
            }

        }
        stlTitle.setViewPager(vp,titleList,this,mFragmentList);
    }

    @Override
    public void showError(int reqCode, String msg) {

    }


}
