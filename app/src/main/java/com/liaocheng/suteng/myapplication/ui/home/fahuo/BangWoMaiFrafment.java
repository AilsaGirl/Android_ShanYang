package com.liaocheng.suteng.myapplication.ui.home.fahuo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.circle.common.base.BaseFragment;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.event.FaHuoAddressEvent;
import com.liaocheng.suteng.myapplication.ui.home.address.AddressList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
/**
 * 帮我买
 */
@SuppressLint("ValidFragment")
public class BangWoMaiFrafment extends BaseFragment {
    @BindView(R.id.etMai)
    TextView etMai;
    @BindView(R.id.ivAddress)
    ImageView ivAddress;
    @BindView(R.id.tvDiZhi)
    TextView tvDiZhi;
    @BindView(R.id.tvDiZhiXiangQing)
    TextView tvDiZhiXiangQing;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.tvDiZhiChangYong)
    TextView tvDiZhiChangYong;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bangwomai;
    }

    int mId;

    @SuppressLint("ValidFragment")
    public BangWoMaiFrafment(int id) {
        mId = id;
    }

    @Override
    public void initEventAndData() {
        EventBus.getDefault().register(this);
        tvTel.setText(SPCommon.getString("tel","1882929292929"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FaHuoAddressEvent event) {
        if (event == null)
            return;
        tvDiZhi.setText(event.getDizhi()+"");
        tvDiZhiXiangQing.setText(event.getXiangqing()+"");
    }

    @Override
    public void showError(int reqCode, String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    Intent intent;
    @OnClick({R.id.etMai, R.id.tvDiZhiChangYong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.etMai:
                break;
            case R.id.tvDiZhiChangYong:
                intent = new Intent(mContext, AddressList.class);
                mContext.startActivity(intent);
                break;
        }
    }
}
