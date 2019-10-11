package com.liaocheng.suteng.myapplication.ui.my;

import android.os.Bundle;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OneActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.tvTitl)
    TextView tvTitle;
    @BindView(R.id.tvContent)
    TextView tvContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_one;
    }

    @Override
    public void initEventAndData() {
        toolBar.setBackFinish();
        String type = getIntent().getStringExtra("type");
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        if (type.equals("1")){
            toolBar.setTitleText("新闻详情");
        }
        if (type.equals("2")){
            toolBar.setTitleText("规则详情");
        }
        tvContent.setText(content+"");
        tvTitle.setText(title+"");
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
