package com.liaocheng.suteng.myapplication.ui.home.fahuo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.ChangYongAddressBean;
import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.ui.home.address.AddressList;
import com.liaocheng.suteng.myapplication.ui.home.address.NewLocationSeekActivity;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BangWoMaiActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.etShangPin)
    EditText etShangPin;
    @BindView(R.id.ivAddress)
    ImageView ivAddress;
    @BindView(R.id.tvDiZhi)
    TextView tvDiZhi;
    @BindView(R.id.tvDiZhiXiangQing)
    TextView tvDiZhiXiangQing;
    @BindView(R.id.relDiZhi)
    RelativeLayout relDiZhi;
    @BindView(R.id.etXiangQing)
    EditText etXiangQing;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_bangwomai;
    }

    String mAddress;
    String mCity;
    String mXiangQing ;
    FaHuoAddressModel addressModel = new FaHuoAddressModel();
    @Override
    public void initEventAndData() {
        toolBar.setTitleText("补充信息").setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(BangWoMaiActivity.this);
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
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        addressModel = (FaHuoAddressModel) getIntent().getSerializableExtra("address_data");
        mAddress = addressModel.address;
        mCity = addressModel.ConcreteAdd;
        lon = addressModel.lon;
        lat = addressModel.lat;
        tvDiZhi.setText(mAddress+"");
        tvDiZhiXiangQing.setText(mCity+"");
        if (!TextUtils.isEmpty(addressModel.contactPhone))
            etTel.setText(addressModel.contactPhone+"");
        if (!TextUtils.isEmpty(addressModel.contactName))
            etRen.setText(addressModel.contactName+"");
        if (!TextUtils.isEmpty(addressModel.detailAddress))
            etXiangQing.setText(addressModel.detailAddress+"");
        if (!TextUtils.isEmpty(addressModel.content))
            etShangPin.setText(addressModel.content+"");
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RecruitEvent event) {
        if (event == null)
            return;
        if (event.getAddressModel()!=null) {
            addressModel = event.getAddressModel();
            mAddress = addressModel.address;
            mCity = addressModel.ConcreteAdd;
            lon = addressModel.lon;
            lat = addressModel.lat;
            tvDiZhi.setText(mAddress+"");
            tvDiZhiXiangQing.setText(mCity+"");
            if (!TextUtils.isEmpty(addressModel.contactPhone))
                etTel.setText(addressModel.contactPhone+"");
            if (!TextUtils.isEmpty(addressModel.contactName))
                etRen.setText(addressModel.contactName+"");
            if (!TextUtils.isEmpty(addressModel.detailAddress))
                etXiangQing.setText(addressModel.detailAddress+"");
            if (!TextUtils.isEmpty(addressModel.content))
                etShangPin.setText(addressModel.content+"");
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }
    Intent intent;
    long mLasttime;
    String lon;
    String lat;
    String mType = "0";
    String isnew = "1";
    String mChengShi;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null)return;
        if (resultCode == 110) {
            if (requestCode == 110) {
                addressModel = (FaHuoAddressModel) data.getSerializableExtra("address_data");
                mAddress = addressModel.address;
                mCity = addressModel.ConcreteAdd;
                lon = addressModel.lon;
                lat = addressModel.lat;
                tvDiZhi.setText(mAddress+"");
                tvDiZhiXiangQing.setText(mCity+"");
                if (!TextUtils.isEmpty(addressModel.contactPhone))
                    etTel.setText(addressModel.contactPhone+"");
                if (!TextUtils.isEmpty(addressModel.contactName))
                    etRen.setText(addressModel.contactName+"");
                if (!TextUtils.isEmpty(addressModel.detailAddress))
                    etXiangQing.setText(addressModel.detailAddress+"");
                if (!TextUtils.isEmpty(addressModel.content))
                    etShangPin.setText(addressModel.content+"");
            }
        }
        if (resultCode == 120) {
            if (requestCode == 110) {
                addressModel = (FaHuoAddressModel) data.getSerializableExtra("address_data");
                mAddress = addressModel.address;
                mCity = addressModel.ConcreteAdd;
                lon = addressModel.lon;
                lat = addressModel.lat;
                tvDiZhi.setText(mAddress+"");
                tvDiZhiXiangQing.setText(mCity+"");
                if (!TextUtils.isEmpty(addressModel.contactPhone))
                    etTel.setText(addressModel.contactPhone+"");
                if (!TextUtils.isEmpty(addressModel.contactName))
                    etRen.setText(addressModel.contactName+"");
                if (!TextUtils.isEmpty(addressModel.detailAddress))
                    etXiangQing.setText(addressModel.detailAddress+"");
                if (!TextUtils.isEmpty(addressModel.content))
                    etShangPin.setText(addressModel.content+"");
            }
        }
    }

    @OnClick({R.id.relDiZhi, R.id.btnOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relDiZhi:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, AddressList.class);
                if (addressModel==null){
                    addressModel = new FaHuoAddressModel();
                }
                addressModel.type = 1;
                addressModel.is_result = 1;
                addressModel.lon =lon;
                addressModel.lat = lat;
                addressModel.is_new_address =0;
                intent.putExtra("address_data", addressModel);
                startActivityForResult(intent, 110);
//                mContext.startActivity(intent);

                break;
            case R.id.btnOk:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                String address = tvDiZhi.getText().toString();
                String city = tvDiZhiXiangQing.getText().toString();
                String xiangqing = etXiangQing.getText().toString();
                String tel = etTel.getText().toString();
                String name = etRen.getText().toString();
                String content = etShangPin.getText().toString();
                if (TextUtils.isEmpty(address)||TextUtils.isEmpty(city)){
                    ToastUtil.show("请选择具体地址");
                    return;
                }
                if (TextUtils.isEmpty(tel)){
                    ToastUtil.show("请填写联系方式");
                    return;
                }
                addressModel.ConcreteAdd=city;
                addressModel.address = address;
                addressModel.detailAddress = xiangqing;
                addressModel.content =content;
                addressModel.contactName =name;
                addressModel.contactPhone =tel;
                addressModel.lat =lat;
                addressModel.lon =lon;
                addressModel.type = 1;
                addressModel.is_result = 0;
                addressModel.is_new_address =0;
                intent = new Intent(mContext, BangWoMaiXiaDanActivity.class);
                intent.putExtra("address_data", addressModel);
                if (addressModel.is_result==1){
                    setResult(110, intent);
                    finish();
                }else {
                    startActivity(intent);
                    finish();
                }

                break;
        }
    }
}
