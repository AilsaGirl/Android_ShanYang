package com.liaocheng.suteng.myapplication.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindPwdActivity extends BaseActivity {
    private boolean isclicked = true;
    @BindView(R.id.etphonenumber)
    EditText etphonenumber;
    @BindView(R.id.loginUserNameLayout)
    LinearLayout loginUserNameLayout;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etYZM)
    EditText etYZM;
    @BindView(R.id.tvGetYZM)
    TextView tvGetYZM;
    @BindView(R.id.loginPwd)
    RelativeLayout loginPwd;
    @BindView(R.id.tvPPW)
    TextView tvPPW;
    @BindView(R.id.tvFindPW)
    TextView tvFindPW;
    @BindView(R.id.linZhuCe)
    LinearLayout linZhuCe;

    @Override
    public int getLayoutId() {
        return R.layout.activity_findpass;
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

    @OnClick({R.id.tvGetYZM, R.id.tvPPW})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvGetYZM:
                if(isclicked==true) {
                    String Et_phone = etphonenumber.getText().toString();
                    if (!TextUtils.isEmpty(Et_phone) && isMobile(Et_phone) == true) {
                        isclicked=false;


                    } else {
                        ToastUtil.show("请填写正确的手机号");
                    }
                }

                break;
            case R.id.tvPPW:
                break;
        }
    }
    //判断是不是手机号
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,5,6,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
