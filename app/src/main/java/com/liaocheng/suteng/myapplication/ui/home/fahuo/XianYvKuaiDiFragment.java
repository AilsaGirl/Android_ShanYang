package com.liaocheng.suteng.myapplication.ui.home.fahuo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.circle.common.base.BaseFragment;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;
import com.liaocheng.suteng.myapplication.model.event.FaHuoAddressEvent;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.ui.home.address.AddressList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 县域快送
 */

@SuppressLint("ValidFragment")
public class XianYvKuaiDiFragment extends BaseFragment {


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
    String city;

    @SuppressLint("ValidFragment")
    public XianYvKuaiDiFragment(int id) {
        mId = id;
    }

    @Override
    public void initEventAndData() {
        EventBus.getDefault().register(this);
        tvFaTel.setText(SPCommon.getString("tel", ""));
        EventBus.getDefault().post(new FaHuoAddressEvent(true));
    }

    String mDiZhi;
    String mXiangQing;
    String lon;
    String lat;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FaHuoAddressEvent event) {
        if (event == null)
            return;
        mDiZhi = event.getDizhi()+"";
        mXiangQing = event.getXiangqing()+"";
        lat = event.getLat();
        lon = event.getLon();
        city = event.getCity();
        tvFaDiZhi.setText(event.getDizhi()+"");
//        tvFaTel.setText(event.getDizhi());
    }
    FaHuoAddressModel addressModelFa;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RecruitEvent event) {
        if (event == null)
            return;
        if (event.getAddressModel()!=null) {
            addressModelFa = event.getAddressModel();
            tvFaDiZhi.setText(addressModelFa.address+"");
            tvFaTel.setText(addressModelFa.contactPhone+"");
        }

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
    long mLasttime;
    FaHuoAddressModel addressModel;
    @OnClick({R.id.tvFaDiZhiChangYong, R.id.linFa, R.id.tvShouDiZhiChangYong, R.id.linShou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linFa:
                if (System.currentTimeMillis() - mLasttime < 1700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();

                intent = new Intent(mContext, FaHuoSongFaHuoActivity.class);
                addressModel = new FaHuoAddressModel();
                addressModel.address = mDiZhi;
                addressModel.ConcreteAdd = mXiangQing;
                addressModel.contactName = SPCommon.getString("username", "")+"";
                addressModel.contactPhone = SPCommon.getString("tel", "")+"";
                addressModel.lon =lon;
                addressModel.lat = lat;
                addressModel.type = 6;
                addressModel.city = city;
                addressModel.is_result = 0;
                addressModel.is_new_address =0;
                intent.putExtra("address_data", addressModel);
                mContext.startActivity(intent);


                break;
            case R.id.tvFaDiZhiChangYong:
                if (System.currentTimeMillis() - mLasttime < 1700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, AddressList.class);
                addressModel = new FaHuoAddressModel();
                addressModel.address = mDiZhi;
                addressModel.ConcreteAdd = mXiangQing;
                addressModel.contactName = SPCommon.getString("username", "")+"";
                addressModel.contactPhone = SPCommon.getString("tel", "")+"";
                addressModel.lon =lon;
                addressModel.lat = lat;
                addressModel.type = 6;
                addressModel.city = city;
                addressModel.is_result = 0;
                addressModel.is_new_address =0;
                intent.putExtra("address_data", addressModel);
                intent.putExtra("tip",61);//31  帮我送跳地址  选择地址后跳选择收货地址
                mContext.startActivity(intent);
                break;
            case R.id.linShou:
                if (System.currentTimeMillis() - mLasttime < 1700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();

                intent = new Intent(mContext, FaHuoSongShouHuoActivity.class);
                addressModel = new FaHuoAddressModel();
                if (addressModelFa==null){
                    addressModel = new FaHuoAddressModel();
                    addressModel.address = mDiZhi;
                    addressModel.ConcreteAdd = mXiangQing;
                    addressModel.contactName = SPCommon.getString("username", "")+"";
                    addressModel.contactPhone = SPCommon.getString("tel", "")+"";
                    addressModel.lon =lon;
                    addressModel.lat = lat;
                    addressModel.type = 6;
                    addressModel.city = city;
                    addressModel.is_result = 0;
                    addressModel.is_new_address =0;
                    intent.putExtra("address_data", addressModel);
                }else {
                    intent.putExtra("address_data", addressModelFa);
                }

                mContext.startActivity(intent);
                break;
            case R.id.tvShouDiZhiChangYong:
                if (System.currentTimeMillis() - mLasttime < 1700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, AddressList.class);
                addressModel = new FaHuoAddressModel();
                if (addressModelFa==null){
                    addressModel = new FaHuoAddressModel();
                    addressModel.address = mDiZhi;
                    addressModel.ConcreteAdd = mXiangQing;
                    addressModel.contactName = SPCommon.getString("username", "")+"";
                    addressModel.contactPhone = SPCommon.getString("tel", "")+"";
                    addressModel.lon =lon;
                    addressModel.lat = lat;
                    addressModel.type = 6;
                    addressModel.city = city;
                    addressModel.is_result = 0;
                    addressModel.is_new_address =0;
                    intent.putExtra("address_data", addressModel);
                }else {
                    intent.putExtra("address_data", addressModelFa);
                }
                intent.putExtra("tip",62);//32  帮我送跳地址  选择地址后直接下单
                mContext.startActivity(intent);

                break;
        }
    }
}
