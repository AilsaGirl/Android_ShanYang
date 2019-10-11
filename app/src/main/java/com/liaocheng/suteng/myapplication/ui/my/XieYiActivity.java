package com.liaocheng.suteng.myapplication.ui.my;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.Zcxiybean;
import com.liaocheng.suteng.myapplication.presenter.Zhucexypresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.ZhuceContact;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XieYiActivity extends BaseActivity<Zhucexypresenter> implements ZhuceContact.View {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.tvXieYi)
    TextView tvXieYi;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xieyi;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("协议").setBackFinish();
        String code = getIntent().getStringExtra("code");
        mPresenter.getzhucexy(code+"");
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

    @Override
    public void setzhucexy(Zcxiybean zcxiybean) {
        toolBar.setTitleText(zcxiybean.agreement_title+"");
        tvXieYi.setText(zcxiybean.agreement_content+"");
        tvXieYi.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
