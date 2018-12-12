package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MyTeaBean;
import com.liaocheng.suteng.myapplication.model.MyTuiGuangBean;
import com.liaocheng.suteng.myapplication.presenter.MyTuiGuangPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.MyTuiGuangContact;
import com.liaocheng.suteng.myapplication.view.CustomMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTuiGuangActivity extends BaseActivity<MyTuiGuangPresenter> implements MyTuiGuangContact.View {
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.cmv)
    CustomMenuView cmv;
    @BindView(R.id.cmvZongDai)
    CustomMenuView cmvZongDai;
    @BindView(R.id.cmvShengDai)
    CustomMenuView cmvShengDai;
    @BindView(R.id.cmvShiDai)
    CustomMenuView cmvShiDai;
    @BindView(R.id.cmvZongDaiShouRu)
    CustomMenuView cmvZongDaiShouRu;
    @BindView(R.id.cmvShengDaiShouRu)
    CustomMenuView cmvShengDaiShouRu;
    @BindView(R.id.cmvShiDaiShouRu)
    CustomMenuView cmvShiDaiShouRu;
    @BindView(R.id.cmvXinRenJiang)
    CustomMenuView cmvXinRenJiang;
    @BindView(R.id.cmvTuiJianXianRen)
    CustomMenuView cmvTuiJianXianRen;
    @BindView(R.id.cmvDiQuJiang)
    CustomMenuView cmvDiQuJiang;
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.cmvZongShouRu)
    CustomMenuView cmvZongShouRu;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tuiguang;
    }

    @Override
    public void initEventAndData() {
        toolBar.setBackFinish().setTitleText("我的推广");
        mPresenter.getMyTea();
        mPresenter.getTuiGuang(SPCommon.getString("city", ""), SPCommon.getString("qu", ""));
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    long mLasttime;
    Intent intent;

    @OnClick({R.id.cmvZongDai, R.id.cmvShengDai, R.id.cmvShiDai, R.id.cmvZongDaiShouRu, R.id.cmvShengDaiShouRu, R.id.cmvShiDaiShouRu, R.id.cmvXinRenJiang, R.id.cmvTuiJianXianRen, R.id.cmvDiQuJiang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmvZongDai:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, TuiGuangRenActivity.class);
                intent.putExtra("id","1");
                mContext.startActivity(intent);
                break;
            case R.id.cmvShengDai:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, TuiGuangRenActivity.class);
                intent.putExtra("id","2");
                mContext.startActivity(intent);
                break;
            case R.id.cmvShiDai:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, TuiGuangRenActivity.class);
                intent.putExtra("id","3");
                mContext.startActivity(intent);
                break;
            case R.id.cmvZongDaiShouRu:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, TuiGuangJiangLiActivity.class);
                intent.putExtra("id","1");
                mContext.startActivity(intent);
                break;
            case R.id.cmvShengDaiShouRu:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, TuiGuangJiangLiActivity.class);
                intent.putExtra("id","2");
                mContext.startActivity(intent);
                break;
            case R.id.cmvShiDaiShouRu:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, TuiGuangJiangLiActivity.class);
                intent.putExtra("id","3");
                mContext.startActivity(intent);
                break;
            case R.id.cmvXinRenJiang:

                break;
            case R.id.cmvTuiJianXianRen:

                break;
            case R.id.cmvDiQuJiang:

                break;
        }
    }

    @Override
    public void setTuiGuang(MyTuiGuangBean mBean) {
        cmv.setRightText(mBean.sumCount + "");
        cmvZongDai.setRightText(mBean.firstCount + "");
        cmvShengDai.setRightText(mBean.secondCount + "");
        cmvShiDai.setRightText(mBean.thirdCount + "");
        cmvZongShouRu.setRightText(mBean.sumMoney + "");
        cmvZongDaiShouRu.setRightText(mBean.firstMoney + "");
        cmvShengDaiShouRu.setRightText(mBean.secondMoney + "");
        cmvShiDaiShouRu.setRightText(mBean.thirdMoney + "");
        cmvXinRenJiang.setRightText(mBean.remaining + "");//倒计时
        cmvDiQuJiang.setLeftText(mBean.area + "");
        cmvDiQuJiang.setRightText(mBean.rewardPool + "");
    }

    @Override
    public void setMyTea(MyTeaBean myBean) {
        tvName.setText("我的推广老师：" + (myBean.name + ""));
        tvTel.setText("手机号：" + (myBean.phone + ""));
    }

}
