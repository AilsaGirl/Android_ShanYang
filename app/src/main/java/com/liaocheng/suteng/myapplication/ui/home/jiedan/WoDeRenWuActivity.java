package com.liaocheng.suteng.myapplication.ui.home.jiedan;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.event.DingDanEvent;
import com.liaocheng.suteng.myapplication.model.event.RenWuEvent;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment.DaiQuHuoFragment;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment.SongHuoZhongFragment;
import com.liaocheng.suteng.myapplication.ui.my.fragment.FaHuoDingDanFragment;
import com.liaocheng.suteng.myapplication.ui.my.fragment.JieDanDingDanFragment;
import com.liaocheng.suteng.myapplication.view.MemberDialog;
import com.liaocheng.suteng.myapplication.view.loop.OnItemSelectedListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WoDeRenWuActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.stlTitle)
    SlidingTabLayout stlTitle;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_woderenwu;
    }
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    String[] titleList;
    boolean f = true;
     int pos =1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initEventAndData() {
        EventBus.getDefault().register(this);
//        Intent intent = new Intent();
        int tab = getIntent().getIntExtra("tab",0);
        titleList = new String[2];
        for (int i = 1; i < 3; i++) {

            if (i == 1) {
                titleList[0] = "取货中";
                mFragmentList.add(new DaiQuHuoFragment(i));
            }
            if (i == 2) {
                titleList[1] = "送货中";
                mFragmentList.add(new SongHuoZhongFragment(i));
            }

        }
        stlTitle.setViewPager(vp,titleList,this,mFragmentList);

        toolBar.setBackFinish().setTitleText("我的任务").setRight("更多", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(WoDeRenWuActivity.this,GengDuoActivity.class);
                startActivity(intent);

            }
        });
        stlTitle.setCurrentTab(tab);

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(RenWuEvent event) {
        if (event == null)
            return;
        stlTitle.setCurrentTab(event.getTab());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
    public FragmentTransaction mFragmentTransaction;
    public FragmentManager fragmentManager;
    public String curFragmentTag = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment*/
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }




}
