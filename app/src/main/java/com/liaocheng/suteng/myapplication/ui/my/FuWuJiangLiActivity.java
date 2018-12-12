package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.circle.common.base.BaseActivity;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.view.CustomMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FuWuJiangLiActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.cmvTuiGuang)
    CustomMenuView cmvTuiGuang;
    @BindView(R.id.cmvYaoQingMa)
    CustomMenuView cmvYaoQingMa;
    @BindView(R.id.cmvBaoZhengJin)
    CustomMenuView cmvBaoZhengJin;
    @BindView(R.id.cmvBaoXian)
    CustomMenuView cmvBaoXian;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fuwujiangli;
    }

    @Override
    public void initEventAndData() {
        toolBar.setBackFinish().setTitleText("更多服务/奖励");
    }

    @Override
    public void showError(int reqCode, String msg) {

    }
    long mLasttime;
    Intent intent;
    @OnClick({R.id.cmvTuiGuang, R.id.cmvYaoQingMa, R.id.cmvBaoZhengJin, R.id.cmvBaoXian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmvTuiGuang:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, MyTuiGuangActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvYaoQingMa:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, MyYaoQingMaActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvBaoZhengJin:
                break;
            case R.id.cmvBaoXian:
                break;
        }
    }
}
