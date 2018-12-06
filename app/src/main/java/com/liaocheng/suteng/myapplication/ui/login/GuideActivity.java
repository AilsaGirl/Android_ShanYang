package com.liaocheng.suteng.myapplication.ui.login;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.StatusBarUtils;
import com.liaocheng.suteng.myapplication.R;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2018/9/7 0007.
 */

public class GuideActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    private ViewPager mainViewPager;
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
    @Override
    public void showError(int reqCode, String msg) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initEventAndData() {
        checkPermission();
//        StatusBarUtils.with(this).setDrawable(getResources().getDrawable(R.drawable.bg_toolbar_white)).init();
        mainViewPager=(ViewPager)findViewById(R.id.main_ViewPager);
        mainViewPager.setAdapter(new GuidePagerAdapter(this.getSupportFragmentManager()));
    }
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

    private class GuidePagerAdapter extends FragmentStatePagerAdapter {
        private FragmentManager fm;

        public GuidePagerAdapter(FragmentManager fm) {
            super(fm);
            this.fm=fm;
        }

        @Override
        public Fragment getItem(int position) {
            GuideFragment fragment = new GuideFragment(
                    position);
            return fragment;
        }
        @Override
        public int getCount() {
            return 6;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            super.destroyItem(container, position, object);
            // notifyDataSetChanged();
            fm.beginTransaction().remove((Fragment) object).commit();
        }

    }
}
