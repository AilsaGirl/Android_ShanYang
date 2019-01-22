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
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.event.DingDanEvent;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment.DaiQuHuoFragment;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment.SongHuoZhongFragment;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment.WanChengFragment;
import com.liaocheng.suteng.myapplication.view.MemberDialog;
import com.liaocheng.suteng.myapplication.view.loop.OnItemSelectedListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GengDuoActivity extends BaseActivity {
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
        titleList = new String[2];
        for (int i = 1; i < 3; i++) {

            if (i == 1) {
                titleList[0] = "完成订单";
                mFragmentList.add(new WanChengFragment(i));
            }
            if (i == 2) {
                titleList[1] = "退款订单";
                mFragmentList.add(new WanChengFragment(i));
            }

        }

        stlTitle.setViewPager(vp,titleList,this,mFragmentList);

        toolBar.setBackFinish().setTitleText("更多");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

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
}
