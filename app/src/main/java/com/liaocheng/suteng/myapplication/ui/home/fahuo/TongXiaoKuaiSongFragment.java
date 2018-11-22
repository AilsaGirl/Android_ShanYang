package com.liaocheng.suteng.myapplication.ui.home.fahuo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
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
import butterknife.OnClick;

/**
 * 同校快送
 */

@SuppressLint("ValidFragment")
public class TongXiaoKuaiSongFragment extends BaseFragment {


    @BindView(R.id.ivFaAddress)
    ImageView ivFaAddress;
    @BindView(R.id.tvFaDiZhi)
    TextView tvFaDiZhi;
    @BindView(R.id.tvFaTel)
    TextView tvFaTel;
    @BindView(R.id.tvFaDiZhiChangYong)
    TextView tvFaDiZhiChangYong;
    @BindView(R.id.ivShouAddress)
    ImageView ivShouAddress;
    @BindView(R.id.tvShouDiZhi)
    TextView tvShouDiZhi;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.tvShouDiZhiChangYong)
    TextView tvShouDiZhiChangYong;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_xianyvkuaidi;
    }

    int mId;

    @SuppressLint("ValidFragment")
    public TongXiaoKuaiSongFragment(int id) {
        mId = id;
    }

    @Override
    public void initEventAndData() {
        EventBus.getDefault().register(this);
        tvTel.setText(SPCommon.getString("tel", "1882929292929"));
        EventBus.getDefault().post(new FaHuoAddressEvent(true));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FaHuoAddressEvent event) {
        if (event == null)
            return;
        tvFaDiZhi.setText(event.getXiangqing() + "");
    }

    @Override
    public void showError(int reqCode, String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    Intent intent;

    @OnClick({R.id.tvFaDiZhi, R.id.tvFaDiZhiChangYong, R.id.tvShouDiZhi, R.id.tvShouDiZhiChangYong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvFaDiZhi:
                break;
            case R.id.tvFaDiZhiChangYong:
                intent = new Intent(mContext, AddressList.class);
                mContext.startActivity(intent);
                break;
            case R.id.tvShouDiZhi:
                break;
            case R.id.tvShouDiZhiChangYong:
                intent = new Intent(mContext, AddressList.class);
                mContext.startActivity(intent);
                break;
        }
    }
}
