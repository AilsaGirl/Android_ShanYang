package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.presenter.MyInfopresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.MyContact;
import com.liaocheng.suteng.myapplication.ui.home.address.AddressList;
import com.liaocheng.suteng.myapplication.ui.login.LoginActivity;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;
import com.liaocheng.suteng.myapplication.view.CustomMenuView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyActivity extends BaseActivity<MyInfopresenter> implements MyContact.View {
    @BindView(R.id.civHead)
    CircleImageView civHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvFaHuoNum)
    TextView tvFaHuoNum;
    @BindView(R.id.tvTiXianNum)
    TextView tvTiXianNum;
    @BindView(R.id.cmvSet)
    CustomMenuView cmvSet;
    @BindView(R.id.cmvDingDan)
    CustomMenuView cmvDingDan;
    @BindView(R.id.cmvAddress)
    CustomMenuView cmvAddress;
    @BindView(R.id.cmvQuan)
    CustomMenuView cmvQuan;
    @BindView(R.id.cmvJieDanYan)
    CustomMenuView cmvJieDanYan;
    @BindView(R.id.cmvKeFu)
    CustomMenuView cmvKeFu;
    @BindView(R.id.cmvMore)
    CustomMenuView cmvMore;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    public void initEventAndData() {
        mPresenter.getMyInfo();
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }


    Intent intent;
    @OnClick({R.id.civHead, R.id.cmvSet, R.id.cmvDingDan, R.id.cmvAddress, R.id.cmvQuan, R.id.cmvJieDanYan, R.id.cmvKeFu, R.id.cmvMore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civHead:
                break;
            case R.id.cmvSet:
                intent = new Intent(mContext, ZhangHuSetingActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvDingDan:
                intent = new Intent(mContext, DingDanGuanLiActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvAddress:
                intent = new Intent(mContext, AddressList.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvQuan:
                    intent = new Intent(mContext, YouHuiQuanActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvJieDanYan:
                break;
            case R.id.cmvKeFu:
                final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setMessage("0635-635-5777", "聊城市新东方国际C座15楼2151");
                dialog.setBackgroundResource(true);
                dialog.setVisibilityBtn(true);
                dialog.setYesOnclickListener("拨打", new ApplyAndAlterDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + "0635-635-5777");
                        intent.setData(data);
                        startActivity(intent);
                        dialog.dismiss();

                    }
                });
                dialog.setOnOnclickListener("取消", new ApplyAndAlterDialog.onOnOnclickListener() {
                    @Override
                    public void onOnClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.cmvMore:
                break;
        }
    }

    @Override
    public void setMyInfo(MyBean myBean) {
        Picasso.with(this).load(myBean.headImg).placeholder(R.mipmap.sanyangtubiao).error(R.mipmap.sanyangtubiao).into(civHead);
         tvName.setText(myBean.nickName+"");
         tvPhone.setText(myBean.phone+"");
        tvFaHuoNum.setText(myBean.dealMoney+"");
        tvTiXianNum.setText(myBean.residueMoney+"");
        SPCommon.setString("tuisong",myBean.needRadio);
        SPCommon.setString("zhifumima",myBean.isSetSecondPwd);
        SPCommon.setString("auth",myBean.authStatus);
    }
}
