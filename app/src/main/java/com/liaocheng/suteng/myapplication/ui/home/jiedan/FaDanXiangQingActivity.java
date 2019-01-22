package com.liaocheng.suteng.myapplication.ui.home.jiedan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.flyco.tablayout.SlidingTabLayout;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment.DaiQuHuoFragment;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment.FaDanDingDanInfoFragment;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment.FaHuoWeiZhiFragment;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment.SongHuoZhongFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class FaDanXiangQingActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.stlTitle)
    SlidingTabLayout stlTitle;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fadanxiangqing;
    }
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    String[] titleList;
    boolean f = true;
     int pos =1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initEventAndData() {
        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        titleList = new String[2];
        for (int i = 1; i < 3; i++) {

            if (i == 1) {
                titleList[0] = "订单详情";
                mFragmentList.add(new FaDanDingDanInfoFragment(i,code));
            }
            if (i == 2) {
                titleList[1] = "接单员位置";
                mFragmentList.add(new FaHuoWeiZhiFragment(i,code));
            }

        }
        stlTitle.setViewPager(vp,titleList,this,mFragmentList);

        toolBar.setBackFinish().setTitleText("订单详情");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public FragmentTransaction mFragmentTransaction;
    public FragmentManager fragmentManager;
    public String curFragmentTag = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment*/
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
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
