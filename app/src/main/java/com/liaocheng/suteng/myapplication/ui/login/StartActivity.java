package com.liaocheng.suteng.myapplication.ui.login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.circle.common.base.BaseActivity;
import com.circle.common.base.BaseNoStateActivity;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.ui.MainActivity;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * @author lihongbin
 * @Version 启动加载页面
 * @since 2018, 3, 5
 */

public class StartActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private boolean isFirstRoot = true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_loading;
    }
    private final int REQUEST_PERMISSIONS = 100;
    private final int RC_SETTINGS = 101;
    String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA,
    };
    /**
     * 需要进行检测的权限数组
     */
    public void checkPermission() {
        if (EasyPermissions.hasPermissions(mContext, needPermissions)) {
        } else {
            // Ask for both permissions
            //提示用户开户权限 申请权限
            ActivityCompat.requestPermissions(this,needPermissions, 100);
//            EasyPermissions.requestPermissions((Activity)mContext, "去应用管理设置权限", REQUEST_PERMISSIONS, needPermissions);
        }
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == REQUEST_PERMISSIONS) {
            //        Toast.makeText(mContext,"已授予权限",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied((Activity) mContext, perms)) {
            new AppSettingsDialog.Builder((Activity) mContext,"请确认权限是否开启")
                    .setTitle(null)
                    .setPositiveButton("设置")
                    .setNegativeButton("取消", null /* click listener */)
                    .setRequestCode(RC_SETTINGS)
                    .build()
                    .show();
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        checkPermission();
    }


    @Override
    public void showError(int reqCode, String msg) {

    }
}
