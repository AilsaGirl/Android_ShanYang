package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
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
    @BindView(R.id.cmvTuanDui)
    CustomMenuView cmvTuanDui;
    @BindView(R.id.cmvBaoZhengJin)
    CustomMenuView cmvBaoZhengJin;
    @BindView(R.id.cmvBaoXian)
    CustomMenuView cmvBaoXian;
    @BindView(R.id.cmvBaoDi)
    CustomMenuView cmvBaoDi;

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

    @OnClick({R.id.cmvTuiGuang, R.id.cmvTuanDui, R.id.cmvBaoZhengJin, R.id.cmvBaoXian, R.id.cmvBaoDi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmvBaoDi:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                if (!SPCommon.getString("auth", "").equals("1") ) {
                    ToastUtil.show("你不是接单员,无保底服务费");
                    return;
                } else {
                    intent = new Intent(mContext, BaoDiActivity.class);
                    mContext.startActivity(intent);
                }
                break;
            case R.id.cmvTuiGuang:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                if (!SPCommon.getString("auth", "").equals("1") ) {
                    ToastUtil.show("你不是接单员");
                    return;
                }
                intent = new Intent(mContext, MyTuiGuangActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvTuanDui:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, TuanDuiActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvBaoZhengJin:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                if (!SPCommon.getString("auth", "").equals("1")&&!SPCommon.getString("auth", "").equals("2") ) {
                    ToastUtil.show("你不是接单员");
                    return;
                }
                if (SPCommon.getString("equipment", "").equals("1") ) {
                    ToastUtil.show("你没交过保证金");
                    return;
                } else {
                    intent = new Intent(mContext, BaoZhengJinActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.cmvBaoXian:
//                intent = new Intent(mContext, BaoXianActivity.class);
//                startActivity(intent);
                if (SPCommon.getString("auth", "").equals("0") || SPCommon.getString("auth", "").equals("3")) {
                    ToastUtil.show("你不是接单员,无保险金");
                    return;
                } else {
                    intent = new Intent(mContext, BaoXianActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
