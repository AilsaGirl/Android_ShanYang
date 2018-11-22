package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.ui.home.address.AddressList;
import com.liaocheng.suteng.myapplication.ui.login.LoginActivity;
import com.liaocheng.suteng.myapplication.view.CustomMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.civHead)
    CircleImageView civHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvFaHuoNum)
    TextView tvFaHuoNum;
    @BindView(R.id.tvTiXianNum)
    TextView tvTiXianNum;
    @BindView(R.id.cmvSet)
    CustomMenuView cmvSet;
    @BindView(R.id.cmvDingDan)
    CustomMenuView cmvDingDan;
    @BindView(R.id.cmvAddress)
    CustomMenuView cmvAddress;
    @BindView(R.id.cmvQuan)
    CustomMenuView cmvQuan;
    @BindView(R.id.cmvJieDanYan)
    CustomMenuView cmvJieDanYan;
    @BindView(R.id.cmvKeFu)
    CustomMenuView cmvKeFu;
    @BindView(R.id.cmvMore)
    CustomMenuView cmvMore;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void showError(int reqCode, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    Intent intent;
    @OnClick({R.id.civHead, R.id.cmvSet, R.id.cmvDingDan, R.id.cmvAddress, R.id.cmvQuan, R.id.cmvJieDanYan, R.id.cmvKeFu, R.id.cmvMore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civHead:
                break;
            case R.id.cmvSet:
                intent = new Intent(mContext, ZhangHuSetingActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvDingDan:
                intent = new Intent(mContext, DingDanGuanLiActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvAddress:
                intent = new Intent(mContext, AddressList.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvQuan:
                break;
            case R.id.cmvJieDanYan:
                break;
            case R.id.cmvKeFu:
                break;
            case R.id.cmvMore:
                break;
        }
    }
}
