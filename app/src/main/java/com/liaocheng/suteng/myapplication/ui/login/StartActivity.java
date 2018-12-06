package com.liaocheng.suteng.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.circle.common.base.BaseNoStateActivity;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.ui.MainActivity;


/**
 * @author lihongbin
 * @Version 启动加载页面
 * @since 2018, 3, 5
 */

public class StartActivity extends BaseNoStateActivity {
    private boolean isFirstRoot = true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void initEventAndData() {

        isFirstRoot = SPCommon.getBoolean("IS_FIRST_ROOT", true);
        if (!isFirstRoot) {
            if (SPCommon.getString("token","").equals("")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }, 2000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }, 2000);
            }
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(StartActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();

                }
            }, 2000);
        }

    }


    @Override
    public void showError(int reqCode, String msg) {

    }
}
