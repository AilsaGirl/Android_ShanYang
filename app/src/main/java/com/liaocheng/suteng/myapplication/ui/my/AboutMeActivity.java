package com.liaocheng.suteng.myapplication.ui.my;

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
import de.hdodenhof.circleimageview.CircleImageView;

public class AboutMeActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.civHead)
    CircleImageView civHead;
    @BindView(R.id.tvAppName)
    TextView tvAppName;
    @BindView(R.id.tvBanBen)
    TextView tvBanBen;
    @BindView(R.id.cmvGongGao)
    CustomMenuView cmvGongGao;
    @BindView(R.id.cmvXinWen)
    CustomMenuView cmvXinWen;
    @BindView(R.id.cmvGuiZe)
    CustomMenuView cmvGuiZe;
    @BindView(R.id.cmvGongNeng)
    CustomMenuView cmvGongNeng;
    @BindView(R.id.cmvAboutGongSi)
    CustomMenuView cmvAboutGongSi;

    @Override
    public int getLayoutId() {
        return R.layout.activity_aboutme;
    }

    @Override
    public void initEventAndData() {
        toolBar.setBackFinish().setTitleText("账户设置");
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

    @OnClick({R.id.cmvGongGao, R.id.cmvXinWen, R.id.cmvGuiZe, R.id.cmvGongNeng, R.id.cmvAboutGongSi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmvGongGao:
                break;
            case R.id.cmvXinWen:
                break;
            case R.id.cmvGuiZe:
                break;
            case R.id.cmvGongNeng:
                break;
            case R.id.cmvAboutGongSi:
                break;
        }
    }
}
