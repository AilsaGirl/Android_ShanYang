package com.liaocheng.suteng.myapplication.ui.my;

import android.widget.ImageView;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;

import butterknife.BindView;

public class MyIdentityWinActivity extends BaseActivity {

    @BindView(R.id.myToolBar)
    MyToolBar myToolBar;
    //认证状态
    @BindView(R.id.imageView)
    ImageView imageView;
    //姓名
    @BindView(R.id.textView3)
    TextView textView3;
    //身份证号码
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView4)
    TextView textView4;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_identity_win;
    }

    @Override
    public void initEventAndData() {
        myToolBar.setBackFinish().setTitleText("身份验证");
        textView.setText("真实姓名");
        textView4.setText("身份证");
        if (getIntent().getStringExtra("is_identity").equals("2")) {
            imageView.setImageResource(R.mipmap.shenhezhong);
        } else if (getIntent().getStringExtra("is_identity").equals("4")) {
            imageView.setImageResource(R.mipmap.renzhengtongguo);
        } else {

        }
        textView3.setText(getIntent().getStringExtra("name"));
        textView5.setText(getIntent().getStringExtra("identity"));
    }

    @Override
    public void showError(int reqCode, String msg) {

    }

}
