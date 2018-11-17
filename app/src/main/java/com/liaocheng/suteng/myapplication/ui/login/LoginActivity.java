package com.liaocheng.suteng.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.liaocheng.suteng.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.tvDuanXin)
    TextView tvDuanXin;
    @BindView(R.id.tvMiMa)
    TextView tvMiMa;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.loginUserNameLayout)
    LinearLayout loginUserNameLayout;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.tvGetYZM)
    TextView tvGetYZM;
    @BindView(R.id.loginPwd)
    RelativeLayout loginPwd;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.tvPPW)
    TextView tvPPW;
    @BindView(R.id.tvFindPW)
    TextView tvFindPW;
    @BindView(R.id.linZhuCe)
    LinearLayout linZhuCe;
    @BindView(R.id.ivWeiXin)
    ImageView ivWeiXin;
    @BindView(R.id.ivQQ)
    ImageView ivQQ;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initEventAndData() {

    }

    @Override
    public void showError(int reqCode, String msg) {

    }
    Intent intent;
    @OnClick({R.id.tvDuanXin, R.id.tvMiMa, R.id.tvGetYZM, R.id.loginBtn, R.id.tvPPW, R.id.tvFindPW, R.id.ivWeiXin, R.id.ivQQ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDuanXin:
                linZhuCe.setVisibility(View.GONE);
                tvGetYZM.setVisibility(View.VISIBLE);
                tvDuanXin.setTextColor(0xffE03D91);
                tvMiMa.setTextColor(0xff333333);
                etPwd.setHint("请输入验证码");
                break;
            case R.id.tvMiMa:
                linZhuCe.setVisibility(View.VISIBLE);
                tvGetYZM.setVisibility(View.GONE);
                tvDuanXin.setTextColor(0xff333333);
                tvMiMa.setTextColor(0xffE03D91);
                etPwd.setHint("请输入密码");
                break;
            case R.id.tvGetYZM:
                break;
            case R.id.loginBtn:
                break;
            case R.id.tvPPW:
                intent = new Intent();
                intent.setClass(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tvFindPW:
                intent = new Intent();
                intent.setClass(this,FindPwdActivity.class);
                startActivity(intent);
                break;
            case R.id.ivWeiXin:
                intent = new Intent();
                intent.setClass(this,BangDingActivity.class);
                startActivity(intent);
                break;
            case R.id.ivQQ:
                break;
        }
    }
}
