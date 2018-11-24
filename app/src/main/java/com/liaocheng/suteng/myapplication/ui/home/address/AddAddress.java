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
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.presenter.ModificationPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.ModificationContact;

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

    @Override
    public void initEventAndData() {
        Intent intent = getIntent();
        mType = intent.getIntExtra("type", 0);
        lon = intent.getStringExtra("lon");
        lat = intent.getStringExtra("lat");
        mTitle = intent.getStringExtra("location");
        mCity = intent.getStringExtra("city");
        mAddress = intent.getStringExtra("address");
        isnew = intent.getIntExtra("isnew", 1);
        if (isnew==0){
            id = intent.getStringExtra("id");
        }
        toolBar.setTitleText("补充地址").setBackFinish();
        tvDiZhi.setText(mTitle + "");
        tvDiZhiXiangQing.setText(mAddress + "");
//        tvBuChong.setText("");

    }

    Intent intent;

    @OnClick({R.id.relDiZhi, R.id.btnOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.relDiZhi:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(this, NewLocationSeekActivity.class);
                intent.putExtra("lon", lon);
                intent.putExtra("lat", lat);
                intent.putExtra("type", mType);
                intent.putExtra("city", mCity + "");
                intent.putExtra("isnew",isnew);
                if (isnew==0){
                    intent.putExtra("id",id);
                }
                startActivity(intent);
                finish();
                break;
            case R.id.btnOk:
                if (mType == 0) {

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
                        mPresenter.addAddress(name+"",tel+"",lon,lat,city+address+"",xiangqing+"",mType+"");

                    }else {
                        mPresenter.updateAddress(id,name+"",tel+"",lon,lat,city+address+"",xiangqing+"");

                    }
                      }

                break;
        }
    }

}
