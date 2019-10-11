package com.liaocheng.suteng.myapplication.ui.my;

import android.os.Bundle;

import com.circle.common.base.BaseActivity;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GongSiActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gongsi;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("关于公司").setBackFinish();

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
}
