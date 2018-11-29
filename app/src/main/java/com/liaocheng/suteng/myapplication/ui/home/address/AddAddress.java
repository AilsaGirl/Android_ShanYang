package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.liaocheng.suteng.myapplication.ui.home.fahuo.BangWoMaiActivity;

import org.greenrobot.eventbus.EventBus;

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
    @Override
    public void initEventAndData() {
        Intent intent = getIntent();
        addressModel = (FaHuoAddressModel) getIntent().getSerializableExtra("address_data");
        lon = addressModel.lat;
        lat = addressModel.lon;
        mType = addressModel.type;
        isnew = addressModel.is_new;
        mCity = addressModel.city;
        mTitle = addressModel.address;
        mAddress = addressModel.ConcreteAdd;
        is_result = addressModel.is_result;
        tag = addressModel.tag;
        if (isnew==0){
            id = addressModel.id;
        }
        toolBar.setTitleText("补充地址").setBackFinish();
        tvDiZhi.setText(mTitle + "");
        tvDiZhiXiangQing.setText(mAddress + "");
//        tvBuChong.setText("");

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null)return;
        if (resultCode == 100) {
            if (requestCode == 100) {
                addressModel = (FaHuoAddressModel) data.getSerializableExtra("address_data");
                lon = addressModel.lat;
                lat = addressModel.lon;
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
                addressModel.lat = lon+"";
                addressModel.lon = lat+"";
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
                    if (mType==1){
                        intent = new Intent(this, BangWoMaiActivity.class);
                    }
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
                    addressModel.lat = lat;
                    addressModel.lon =lon;
                    addressModel.is_result = 0;
                    intent.putExtra("address_data", addressModel);
                    if (is_result==1){
                        EventBus.getDefault().post(new RecruitEvent(addressModel));
                        finish();
                    }else {
                        startActivity(intent);
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
