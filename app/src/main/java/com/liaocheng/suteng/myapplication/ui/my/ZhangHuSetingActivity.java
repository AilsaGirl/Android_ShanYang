package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.view.CustomMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhangHuSetingActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.tvLaoShi)
    TextView tvLaoShi;
    @BindView(R.id.tvShouJi)
    TextView tvShouJi;
    @BindView(R.id.cmvSetErJiMiMa)
    CustomMenuView cmvSetErJiMiMa;
    @BindView(R.id.cmvOldErJiMiMa)
    CustomMenuView cmvOldErJiMiMa;
    @BindView(R.id.cmvPhoneErJiMiMa)
    CustomMenuView cmvPhoneErJiMiMa;
    @BindView(R.id.cmvPhone)
    CustomMenuView cmvPhone;
    @BindView(R.id.cmvAbout)
    CustomMenuView cmvAbout;
    @BindView(R.id.cmvYuYin)
    CustomMenuView cmvYuYin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhanghuset;
    }

    @Override
    public void initEventAndData() {
        toolBar.setBackFinish().setTitleText("账户设置");
    }

    @Override
    public void showError(int reqCode, String msg) {

    }

    Intent intent;
    @OnClick({R.id.cmvSetErJiMiMa, R.id.cmvOldErJiMiMa, R.id.cmvPhoneErJiMiMa, R.id.cmvPhone, R.id.cmvAbout, R.id.cmvYuYin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmvSetErJiMiMa:
                break;
            case R.id.cmvOldErJiMiMa:
                break;
            case R.id.cmvPhoneErJiMiMa:
                break;
            case R.id.cmvPhone:
                break;
            case R.id.cmvAbout:
                intent = new Intent(mContext, AboutMeActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvYuYin:
                break;
        }
    }
}
