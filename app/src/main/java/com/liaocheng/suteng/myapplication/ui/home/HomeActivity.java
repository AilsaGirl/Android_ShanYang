package com.liaocheng.suteng.myapplication.ui.home;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.base.BaseFragment;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.api.Config;
import com.liaocheng.suteng.myapplication.ui.home.fragment.HomeFragment;
import com.liaocheng.suteng.myapplication.ui.home.fragment.MessageFragment;
import com.liaocheng.suteng.myapplication.ui.home.fragment.MyFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/11/12.
 */

public class HomeActivity extends BaseActivity {
    @BindView(R.id.tvHome)
    TextView tvHome;

    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.tvMy)
    TextView tvMy;
    @BindView(R.id.fmLayout)
    FrameLayout fmLayout;

    private TextView[] viewList;
//    private int[] selectImg = {R.mipmap.hl_home_ok, R.mipmap.hl_paihang_ok, R.mipmap.hl_message_ok, R.mipmap.hl_my_ok};
//    private int[] unSelectImg = {R.mipmap.hl_home_no, R.mipmap.hl_paihang_no, R.mipmap.hl_message_no, R.mipmap.hl_my_no};
    private int[] selectImg = {R.mipmap.shouye_on, R.mipmap.tuangou_on, R.mipmap.dianpuzhongxin_on};
    private int[] unSelectImg = {R.mipmap.shouye, R.mipmap.tuangou, R.mipmap.dianpuzhongxin};
    private BaseFragment mHomeFm, mPaiHangFm, mMessageFm, mMyFm;
    private FragmentManager mFragmentManager;
    private String mCurFragTag;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initEventAndData() {
        mFragmentManager = getSupportFragmentManager();
        viewList = new TextView[]{tvHome, tvMessage, tvMy};
        tvHome.performClick();
    }

    @Override
    public void showError(int reqCode, String msg) {

    }

    private void selectImg(int index) {
        for (int i = 0; i < viewList.length; i++) {
            if (index == i) {
                viewList[i].setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(selectImg[i]), null, null);
            } else {
                viewList[i].setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(unSelectImg[i]), null, null);
            }
        }
    }

    private void addFm(String tag, BaseFragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        BaseFragment currentFrag = null;
        if (mCurFragTag != null) {
            currentFrag = (BaseFragment) mFragmentManager.findFragmentByTag(mCurFragTag);
        }
        transaction.add(R.id.fmLayout, fragment, tag);
        if (currentFrag != null) {
            transaction.hide(currentFrag);
        }
        mCurFragTag = tag;
        transaction.commitAllowingStateLoss();
    }

    private void showFm(String tag) {
        if (tag.equals(mCurFragTag)) return;
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        BaseFragment fragment = (BaseFragment) mFragmentManager.findFragmentByTag(tag);
        BaseFragment currentFragment = (BaseFragment) mFragmentManager.findFragmentByTag(mCurFragTag);
        transaction.show(fragment);
        transaction.hide(currentFragment);
        mCurFragTag = tag;
        transaction.commitAllowingStateLoss();
    }

    @OnClick({R.id.tvHome, R.id.tvMessage, R.id.tvMy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvHome:
                if (mHomeFm == null) {
                    mHomeFm = new HomeFragment();
                    addFm(Config.TAG_HOME, mHomeFm);
                } else {
                    showFm(Config.TAG_HOME);
                }
                selectImg(0);
                break;
//            case R.id.tvPaiHang:
//                if (mPaiHangFm == null) {
//                    mPaiHangFm = new PaiHangFragment();
//                    addFm(Config.TAG_PAIHANG, mPaiHangFm);
//                } else {
//                    showFm(Config.TAG_PAIHANG);
//                }
//                selectImg(1);
//                break;

            case R.id.tvMessage:
                if (mMessageFm == null) {
                    mMessageFm = new MessageFragment();
                    addFm(Config.TAG_MESSAGE, mMessageFm);
                } else {
                    showFm(Config.TAG_MESSAGE);
                }
                selectImg(1);
                break;
            case R.id.tvMy:
                if (mMyFm == null) {
                    mMyFm = new MyFragment();
                    addFm(Config.TAG_MY, mMyFm);
                } else {
                    showFm(Config.TAG_MY);
                }
                selectImg(2);
                break;
        }
    }
}
