package com.liaocheng.suteng.myapplication.ui.my;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.util.QRCodeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyYaoQingMaActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.ivMa)
    ImageView ivMa;
    @BindView(R.id.tvMyMa)
    TextView tvMyMa;
    @BindView(R.id.ivQQ)
    ImageView ivQQ;
    @BindView(R.id.ivWeiXin)
    ImageView ivWeiXin;
    @BindView(R.id.ivPengYouQuan)
    ImageView ivPengYouQuan;

    @Override
    public int getLayoutId() {
        return R.layout.activity_youqingma;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("我的邀请码").setBackFinish();
        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap("https://1.3ypt.com/SYWeb/userregister.jsp?"+SPCommon.getString("userId",""), 480, 480);
        ivMa.setImageBitmap(mBitmap);
        tvMyMa.setText("我的邀请码："+SPCommon.getString("userId",""));
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }

    }

    @OnClick({R.id.ivMa, R.id.ivQQ, R.id.ivWeiXin, R.id.ivPengYouQuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivMa:
                break;
            case R.id.ivQQ:
                break;
            case R.id.ivWeiXin:
                break;
            case R.id.ivPengYouQuan:
                break;
        }
    }
}
