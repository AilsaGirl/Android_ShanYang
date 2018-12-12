package com.liaocheng.suteng.myapplication.ui.my;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
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
import com.liaocheng.suteng.myapplication.ui.my.fragment.FaHuoDingDanFragment;
import com.liaocheng.suteng.myapplication.ui.my.fragment.JieDanDingDanFragment;
import com.liaocheng.suteng.myapplication.ui.my.fragment.TuiGuangJiangLiFragment;
import com.liaocheng.suteng.myapplication.view.MemberDialog;
import com.liaocheng.suteng.myapplication.view.loop.OnItemSelectedListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TuiGuangJiangLiActivity extends BaseActivity {
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
    boolean f = true;
     int pos =1;
     String id = "1";
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initEventAndData() {
        EventBus.getDefault().register(this);
        id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(id)){
            return;
        }
        if (id.equals("1")){
            toolBar.setBackFinish().setTitleText("总代明细").setRight("七天", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mShowCiShuDialog();
                }
            });
        }
        if (id.equals("2")){
            toolBar.setBackFinish().setTitleText("省代明细").setRight("七天", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mShowCiShuDialog();
                }
            });
        }
        if (id.equals("3")){
            toolBar.setBackFinish().setTitleText("市代明细").setRight("七天", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mShowCiShuDialog();
                }
            });
        }
        titleList = new String[4];
        for (int i = 1; i < 5; i++) {

            if (i == 1) {
                titleList[0] = "接单提成";
                mFragmentList.add(new TuiGuangJiangLiFragment(3,id));
            }
            if (i == 2) {
                titleList[1] = "发单提成";
                mFragmentList.add(new TuiGuangJiangLiFragment(2,id));
            }
            if (i == 3) {
                titleList[2] = "认证提成";
                mFragmentList.add(new TuiGuangJiangLiFragment(1,id));
            }
            if (i == 4) {
                titleList[3] = "优惠券奖励";
                mFragmentList.add(new TuiGuangJiangLiFragment(4,id));
            }

        }

        stlTitle.setViewPager(vp,titleList,this,mFragmentList);

        stlTitle.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
               if (position==1&&pos==1){
                   toolBar.setRightText("七天");
                   pos++;
               }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        stlTitle.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i==1&&pos==1){
                    toolBar.setRightText("七天");
                    pos++;
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }
    List<String> mCiShuData = new ArrayList<>();
    MemberDialog dialog;
    String mCiShu = "七天";
    int mindex = 1;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DingDanEvent event) {
        if (event == null)
            return;
        if (event.getShow()){
            EventBus.getDefault().post(new DingDanEvent(tianshu));
        }
    }
    long mLasttime;
    String tianshu = "7";
    private void mShowCiShuDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new MemberDialog(this);
        mCiShuData.clear();
        mCiShuData.add("一天");
        mCiShuData.add("七天");
        mCiShuData.add("一个月");
        mCiShuData.add("三个月");
        mCiShuData.add("一年");

        dialog.show();
        dialog.setTextViewB(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setTextViewP(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                if (!TextUtils.isEmpty(mCiShu)) {
                    toolBar.setRightText(mCiShu+"");
                   switch (mindex){
                       case 0:
                           tianshu ="1";
                           EventBus.getDefault().post(new DingDanEvent("1"));
                           break;
                       case 1:
                           tianshu ="7";
                           EventBus.getDefault().post(new DingDanEvent("7"));
                           break;
                       case 2:
                           tianshu ="30";
                           EventBus.getDefault().post(new DingDanEvent("30"));
                           break;
                       case 3:
                           tianshu ="90";
                           EventBus.getDefault().post(new DingDanEvent("90"));
                           break;
                       case 4:
                           tianshu ="365";
                           EventBus.getDefault().post(new DingDanEvent("365"));
                           break;

                   }
                    dialog.dismiss();
                }else {

                    mCiShu = mCiShuData.get(1);
                    toolBar.setRightText(mCiShu+"");
                    dialog.dismiss();
                }

            }

        });

        dialog.wheelPicker.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mindex = index;
                mCiShu = mCiShuData.get(index);
            }
        });
        dialog.setWheelPicker(mCiShuData);
        dialog.wheelPicker.setInitPosition(1);
    }

}
