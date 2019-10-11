package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.circle.common.app.AppManager;
import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MyTeaBean;
import com.liaocheng.suteng.myapplication.presenter.MyTeapresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.MyTeaContact;
import com.liaocheng.suteng.myapplication.ui.login.LoginActivity;
import com.liaocheng.suteng.myapplication.view.CustomMenuView;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class ZhangHuSetingActivity extends BaseActivity<MyTeapresenter>implements MyTeaContact.View {
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
        mPresenter.getMyTea();
        if (SPCommon.getString("tuisong","").equals("0")) {
            cmvYuYin.setRightImage(R.mipmap.close);
        }else {
            cmvYuYin.setRightImage(R.mipmap.open);
        }
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    Intent intent;
    @OnClick({R.id.cmvSetErJiMiMa, R.id.cmvOldErJiMiMa, R.id.cmvPhoneErJiMiMa, R.id.cmvPhone, R.id.cmvAbout, R.id.cmvYuYin, R.id.tvTuiChu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmvSetErJiMiMa:
                if (SPCommon.getString("zhifumima","").equals("0")) {
                    intent = new Intent(mContext, SetZhiFuMiMaActivity.class);
                    mContext.startActivity(intent);
                }else {
                    ToastUtil.show("已经设置过支付密码");
                }
                break;
            case R.id.cmvOldErJiMiMa:
                intent = new Intent(mContext, UpdateZhiFuMiMaActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvPhoneErJiMiMa:
                intent = new Intent(mContext, UpdatePhoneZhiFuMiMaActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvPhone:
                intent = new Intent(mContext, UpdatePhoneActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvAbout:
                intent = new Intent(mContext, AboutMeActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvYuYin:
                 status = SPCommon.getString("tuisong","");
                if (status.equals("0")){
                    status = "1";
                    mPresenter.changeUserRadio("1");
                }else {
                    mPresenter.changeUserRadio("0");
                    status = "0";
                }
                break;
            case  R.id.tvTuiChu:
                mPresenter.exit();
                break;
        }
    }
    String status;
    @Override
    public void setMyTea(MyTeaBean myBean) {
        tvLaoShi.setText("我的推广老师："+(myBean.name+""));
        tvShouJi.setText("手机号："+(myBean.phone+""));

    }

    @Override
    public void changeUserRadio() {
        ToastUtil.show("修改成功");
        SPCommon.setString("tuisong",status);
        if (SPCommon.getString("tuisong","").equals("0")) {
            cmvYuYin.setRightImage(R.mipmap.close);
        }else {
            cmvYuYin.setRightImage(R.mipmap.open);
        }
    }

    @Override
    public void exit() {
        SPCommon.setString("token", "");
        SPCommon.setString("phone", "");
        SPCommon.setString("userId","");
        SPCommon.setString("tuisong", "");
        SPCommon.setString("zhifumima", "");
        SPCommon.setString("auth", "");
        SPCommon.setString("username", "");
        SPCommon.setString("tel","");
        SPCommon.setString("baozhengjin", "");
        SPCommon.setString("baoxian","");
        SPCommon.setBoolean("iswork", false);
        JPushInterface.deleteAlias(mContext, 100);
        JPushInterface.cleanTags(mContext, 100);
        JPushInterface.setAlias(mContext, //上下文对象
                "", //别名
                new TagAliasCallback() {//回调接口,i=0表示成功,其它设置失败
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        Log.d("alias","111"+i);
                    }
                });
        AppManager.getAppManager().finishAllActivity();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
