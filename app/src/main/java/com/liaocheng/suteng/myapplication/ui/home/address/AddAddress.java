package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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


/**
 * Created by Administrator on 2018/3/31.
 */

public class AddAddress extends BaseActivity<ModificationPresenter> implements ModificationContact.View {

    String newsheng, newshi, newqu;
    String oldsheng, oldshi, oldqu;
    String id;
    String lon;
    String lat;
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.locantion)
    TextView locantion;
    @BindView(R.id.linSel)
    LinearLayout linSel;
    @BindView(R.id.useraddresss)
    EditText useraddresss;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null)return;
        if (resultCode == 110) {
            if (requestCode == 110) {
                newsheng = data.getStringExtra("sheng");
                newshi = data.getStringExtra("shi");
                newqu = data.getStringExtra("qu");
                useraddresss.setText(data.getStringExtra("title"));
                useraddresss.setSelection(data.getStringExtra("title").length());
                locantion.setText(newsheng + newshi + newqu);
                lon = data.getStringExtra("lon");
                lat = data.getStringExtra("lat");
            }
        }
    }

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
    public void AddSuccess() {
       ToastUtil.show("添加地址成功！");
        EventBus.getDefault().post(new RecruitEvent(true));
       finish();
    }

    @Override
    public int getLayoutId() {
        return R.layout.add_address;
    }
    long mLasttime;
    @Override
    public void initEventAndData() {
//        Intent intent = getIntent();
//        intent.getStringExtra("id");
        toolBar.setTitleText("新增地址").setBackFinish().setRight("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(locantion.getText().toString().trim())){
                    ToastUtil.show("请先选择地区");
                    return;
                }
                if (TextUtils.isEmpty(useraddresss.getText().toString().trim())){
                    ToastUtil.show("请填写具体地址");
                    return;
                }
                mPresenter.addAddress(lon,lat,locantion.getText().toString(),useraddresss.getText().toString());
            }
        }).setRightTextColor(0xffffffff);
        linSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                Intent intent = new Intent();
                intent.setClass(AddAddress.this, NewLocationActivity.class);
                startActivityForResult(intent, 110);
            }
        });
    }
}
