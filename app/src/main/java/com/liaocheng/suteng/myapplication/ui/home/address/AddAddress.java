package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.presenter.ModificationPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.ModificationContact;

import com.liaocheng.suteng.myapplication.ui.home.fahuo.BangWoBanActivity;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.BangWoMaiActivity;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.FaHuoActivity;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.FaHuoXiaDanSongActivity;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2018/3/31.
 */

public class AddAddress extends BaseActivity<ModificationPresenter> implements ModificationContact.View {

    String newsheng, newshi, newqu;
    String oldsheng, oldshi, oldqu;

    String lon;
    String lat;
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.tvBuChong)
    TextView tvBuChong;
    @BindView(R.id.ivAddress)
    ImageView ivAddress;
    @BindView(R.id.tvDiZhi)
    TextView tvDiZhi;
    @BindView(R.id.tvDiZhiXiangQing)
    TextView tvDiZhiXiangQing;
    @BindView(R.id.ivTel)
    ImageView ivTel;
    @BindView(R.id.etTel)
    EditText etTel;
    @BindView(R.id.ivRen)
    ImageView ivRen;
    @BindView(R.id.etRen)
    EditText etRen;
    @BindView(R.id.btnOk)
    Button btnOk;
    @BindView(R.id.etXiangQing)
    EditText etXiangQing;


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Override
    public void updateSuccess() {
        ToastUtil.show("补充地址成功！");
        EventBus.getDefault().post(new RecruitEvent(true));
        finish();
    }

    @Override
    public void AddSuccess() {
        ToastUtil.show("补充地址成功！");
        EventBus.getDefault().post(new RecruitEvent(true));
        finish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.add_address;
    }

    long mLasttime;
    int mType;
    String mTitle;
    String mCity;
    String mAddress;
    int isnew;
    String id;
    int is_result = 0;
    int tag = 0;
    FaHuoAddressModel addressModel = new FaHuoAddressModel();
    int tip = 0;
    @Override
    public void initEventAndData() {
        Intent intent = getIntent();
        addressModel = (FaHuoAddressModel) getIntent().getSerializableExtra("address_data");
        lon = addressModel.lon;
        lat = addressModel.lat;
        mType = addressModel.type;
        isnew = addressModel.is_new;
        mCity = addressModel.city;
        mTitle = addressModel.address;
        mAddress = addressModel.ConcreteAdd;
        is_result = addressModel.is_result;
        tag = addressModel.tag;
        tip = getIntent().getIntExtra("tip",0);
        if (isnew==0){
            id = addressModel.id;
        }

        toolBar.setTitleText("补充地址").setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(AddAddress.this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setMessage("退出后设置的信息会丢失,是否退出", "");
                dialog.setBackgroundResource(true);
                dialog.setVisibilityBtn(true);
                dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        dialog.dismiss();
                        finish();
                    }
                });
                dialog.setOnOnclickListener("取消", new ApplyAndAlterDialog.onOnOnclickListener() {
                    @Override
                    public void onOnClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        tvDiZhi.setText(mTitle + "");
        tvDiZhiXiangQing.setText(mAddress + "");
        EventBus.getDefault().register(this);
//        tvBuChong.setText("");

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null)return;
        if (resultCode == 100) {
            if (requestCode == 100) {
                addressModel = (FaHuoAddressModel) data.getSerializableExtra("address_data");
                lon = addressModel.lon;
                lat = addressModel.lat;
                mType = addressModel.type;
                tag = addressModel.tag;
                isnew = addressModel.is_new;
                mCity = addressModel.city;
                mTitle = addressModel.address;
                mAddress = addressModel.ConcreteAdd;

                if (isnew==0){
                    id = addressModel.id;
                }

                tvDiZhi.setText(mTitle+"");
                tvDiZhiXiangQing.setText(mAddress+"");
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(this);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setMessage("退出后设置的信息会丢失,是否退出", "");
            dialog.setBackgroundResource(true);
            dialog.setVisibilityBtn(true);
            dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    dialog.dismiss();
                    finish();
                }
            });
            dialog.setOnOnclickListener("取消", new ApplyAndAlterDialog.onOnOnclickListener() {
                @Override
                public void onOnClick() {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    FaHuoAddressModel addressModelFa = new FaHuoAddressModel();
    FaHuoAddressModel addressModelShou = new FaHuoAddressModel();

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(RecruitEvent event) {
        if (event == null)
            return;
        if (event.getAddressModel() != null&&event.getAddressModelShou() != null){
            if (event.getAddressModel() != null) {
                addressModelFa = event.getAddressModel();
//            mPresenter.orderNum("","3",lat+"",lon+"","","","","","","","");
            }
            if (event.getAddressModelShou() != null) {
                addressModelShou = event.getAddressModelShou();
            }
        }
    }
    Intent intent;

    @OnClick({R.id.relDiZhi, R.id.btnOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relDiZhi:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(this, NewLocationSeekBuActivity.class);
                if (addressModel==null){
                    addressModel = new FaHuoAddressModel();
                }
                addressModel.lat = lat+"";
                addressModel.lon = lon+"";
                addressModel.type = mType;
                addressModel.is_new = isnew;
                addressModel.tag = tag;
                addressModel.city = mCity;
                addressModel.is_result = 1;
                if (isnew==0){
                    addressModel.id = id;
                }
                intent.putExtra("address_data", addressModel);
//                startActivity(intent);
                startActivityForResult(intent, 100);
                break;
            case R.id.btnOk:
                if (tag == 0) {
                    String address = tvDiZhi.getText().toString();
                    String city = tvDiZhiXiangQing.getText().toString();
                    String xiangqing = etXiangQing.getText().toString();
                    String tel = etTel.getText().toString();
                    String name = etRen.getText().toString();
                    if (TextUtils.isEmpty(address)||TextUtils.isEmpty(city)){
                        ToastUtil.show("请选择具体地址");
                        return;
                    }
                    if (TextUtils.isEmpty(tel)){
                        ToastUtil.show("请填写联系方式");
                        return;
                    }
                    if (addressModel==null){
                        addressModel = new FaHuoAddressModel();
                    }
                    addressModel.address = address;
                    addressModel.detailAddress = xiangqing;
                    addressModel.contactName = name;
                    addressModel.contactPhone = tel;
                    addressModel.ConcreteAdd = city;
                    addressModel.city = mCity;
                    addressModel.lat = lat;
                    addressModel.lon =lon;
                    addressModel.is_result = 0;

                    if (mType==1||mType==2){
                        if (mType==1){
                            intent = new Intent(this, BangWoMaiActivity.class);
                        }
                        if (mType==2){
                            intent = new Intent(this, BangWoBanActivity.class);
                        }
                        intent.putExtra("address_data", addressModel);
                        if (is_result==1){
                            EventBus.getDefault().post(new RecruitEvent(addressModel));
                            finish();
                        }else {
                            startActivity(intent);
                        }
                    }

                    if (mType==3||mType==4||mType==5||mType==6){
                        if (tip == 31) {
                            intent = new Intent(this, FaHuoActivity.class);
                            addressModel.type = 3;
//                            intent.putExtra("address_data", addressModel);
//                            intent.putExtra("tip",tip);
                              EventBus.getDefault().post(new RecruitEvent(addressModel));
                            startActivity(intent);
                        }
                        if (tip == 32) {
                        intent = new Intent(this, FaHuoXiaDanSongActivity.class);
                            addressModel.type = 3;
//                            intent.putExtra("address_data", addressModel);
//                            intent.putExtra("tip",tip);
                            EventBus.getDefault().postSticky(new RecruitEvent(addressModelFa, addressModel));
                            startActivity(intent);
                        }
                        if (tip == 41) {
                            intent = new Intent(this, FaHuoActivity.class);
                            addressModel.type = 4;
//                            intent.putExtra("address_data", addressModel);
//                            intent.putExtra("tip",tip);
                        EventBus.getDefault().post(new RecruitEvent(addressModel));
                            startActivity(intent);
                        }
                        if (tip == 42) {
                        intent = new Intent(this, FaHuoXiaDanSongActivity.class);
                            addressModel.type = 4;
//                            intent.putExtra("address_data", addressModel);
//                            intent.putExtra("tip",tip);
                            EventBus.getDefault().postSticky(new RecruitEvent(addressModelFa, addressModel));
                            startActivity(intent);
                        }
                        if (tip == 51) {
                            intent = new Intent(this, FaHuoActivity.class);
                            addressModel.type = 5;
//                            intent.putExtra("tip",tip);
//                            intent.putExtra("address_data", addressModel);
                        EventBus.getDefault().post(new RecruitEvent(addressModel));
                            startActivity(intent);
                        }
                        if (tip == 52) {
                        intent = new Intent(this, FaHuoXiaDanSongActivity.class);

                            addressModel.type = 5;
//                            intent.putExtra("tip",tip);
//                            intent.putExtra("address_data", addressModel);
                            EventBus.getDefault().postSticky(new RecruitEvent(addressModelFa, addressModel));
                            startActivity(intent);
                        }
                        if (tip == 61) {
                            intent = new Intent(this, FaHuoActivity.class);
                            addressModel.type = 6;
//                            intent.putExtra("tip",tip);
//                            intent.putExtra("address_data", addressModel);
                            EventBus.getDefault().post(new RecruitEvent(addressModel));
                            startActivity(intent);
                        }
                        if (tip == 62) {
                            intent = new Intent(this, FaHuoXiaDanSongActivity.class);

                            addressModel.type = 6;
//                            intent.putExtra("tip",tip);
//                            intent.putExtra("address_data", addressModel);
                            EventBus.getDefault().postSticky(new RecruitEvent(addressModelFa, addressModel));
                            startActivity(intent);
                        }
                        if (tip == 311) {
//                            intent = new Intent(this, FaHuoActivity.class);
                            addressModel.type = mType;
//                            intent.putExtra("tip",tip);
//                            intent.putExtra("address_data", addressModel);
                            EventBus.getDefault().postSticky(new RecruitEvent(addressModel, addressModelShou));
//                            startActivity(intent);
                        }
                        if (tip == 321) {
//                            intent = new Intent(this, FaHuoXiaDanSongActivity.class);

                            addressModel.type = mType;
//                            intent.putExtra("tip",tip);
//                            intent.putExtra("address_data", addressModel);
                            EventBus.getDefault().postSticky(new RecruitEvent(addressModelFa, addressModel));
//                            startActivity(intent);
                        }
                    }



                    finish();
                } else {
                    String address = tvDiZhi.getText().toString();
                    String city = tvDiZhiXiangQing.getText().toString();
                    String xiangqing = etXiangQing.getText().toString();
                    String tel = etTel.getText().toString();
                    String name = etRen.getText().toString();
                    if (TextUtils.isEmpty(address)||TextUtils.isEmpty(city)){
                        ToastUtil.show("请选择具体地址");
                        return;
                    }
                    if (TextUtils.isEmpty(tel)){
                        ToastUtil.show("请填写联系方式");
                        return;
                    }
                    if (isnew==1){
                        mPresenter.addAddress(name+"",tel+"",lon,lat,address+"",xiangqing+"",tag+"",city+"");

                    }else {
                        mPresenter.updateAddress(id,name+"",tel+"",lon,lat,address+"",xiangqing+"",city+"");

                    }
                      }

                break;
        }
    }

}
