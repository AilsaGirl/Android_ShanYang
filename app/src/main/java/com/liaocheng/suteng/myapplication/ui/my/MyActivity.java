package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;
import com.liaocheng.suteng.myapplication.presenter.MyInfopresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.MyContact;
import com.liaocheng.suteng.myapplication.ui.home.address.AddressList;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.BangWoMaiActivity;
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

long mLasttime;
    Intent intent;
    @OnClick({R.id.civHead, R.id.cmvSet, R.id.cmvDingDan, R.id.cmvAddress, R.id.cmvQuan, R.id.cmvJieDanYan, R.id.cmvKeFu, R.id.cmvMore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civHead:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, UpDataMyInFoActivity.class);
                intent.putExtra("tel",tel);
                intent.putExtra("name",name);
                intent.putExtra("img",img);
                mContext.startActivity(intent);

                break;
            case R.id.cmvSet:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, ZhangHuSetingActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvDingDan:

                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, DingDanGuanLiActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvAddress:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, AddressList.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvQuan:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                    intent = new Intent(mContext, YouHuiQuanActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvJieDanYan:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                if ( SPCommon.getString("auth","").equals("0")){
                    intent = new Intent(mContext, MyIdentityActivity.class);
                    startActivity(intent);
                    return;
                }else if ( SPCommon.getString("auth","").equals("-2")){
                    intent = new Intent(mContext, MyUpdateIdentityActivity.class);
                    startActivity(intent);
                    return;
                }else if ( SPCommon.getString("auth","").equals("100")){
//                    intent = new Intent(mContext, MyUpdateIdentityActivity.class);
//                    startActivity(intent);
                    ToastUtil.show("请交付押金");
                    return;
                }else if ( SPCommon.getString("auth","").equals("1")){
                    ToastUtil.show("你已是接单员，如若接单，请前往接单大厅");
                    return;
                }else if ( SPCommon.getString("auth","").equals("1")){
                    ToastUtil.show("你已是接单员，如若接单，请前往接单大厅");
                    return;
                }else if ( SPCommon.getString("auth","").equals("2")){
                    ToastUtil.show("你正在申请退出接单员，请等待");
                    return;
                }else if ( SPCommon.getString("auth","").equals("3")){
                    ToastUtil.show("你已退出接单员");
                    return;
                }else if ( SPCommon.getString("auth","").equals("4")){
                    ToastUtil.show("你已被限制接单");
                    return;
                }

                break;
            case R.id.cmvKeFu:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                mPresenter.getKeFu(SPCommon.getString("qu","东昌府区"));
                break;
            case R.id.cmvMore:
                intent = new Intent(mContext, FuWuJiangLiActivity.class);
                mContext.startActivity(intent);
//                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
//                    return;
//                if ( SPCommon.getString("auth","").equals("0")){
//                    final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(MyActivity.this);
//                    dialog.setCanceledOnTouchOutside(true);
//                    dialog.setMessage("不好意思，你还没有认证接单员。", "亲！申请通过后，6个月内退出，系统扣除25元手续费，请您考虑好之后慎重操作");
//                    dialog.setBackgroundResource(true);
//                    dialog.setVisibilityBtn(true);
//                    dialog.setYesOnclickListener("去申请", new ApplyAndAlterDialog.onYesOnclickListener() {
//                        @Override
//                        public void onYesClick() {
//                            intent = new Intent(mContext, MyIdentityActivity.class);
//                            startActivity(intent);
//                            dialog.dismiss();
//                            finish();
//                        }
//                    });
//                    dialog.setOnOnclickListener("取消", new ApplyAndAlterDialog.onOnOnclickListener() {
//                        @Override
//                        public void onOnClick() {
//                            dialog.dismiss();
//                        }
//                    });
//                    dialog.show();
//                }else  if ( SPCommon.getString("auth","").equals("1")){
//                    mLasttime = System.currentTimeMillis();
//                    intent = new Intent(mContext, FuWuJiangLiActivity.class);
//                    mContext.startActivity(intent);
//                    return;
//                }else if ( SPCommon.getString("auth","").equals("2")){
//                    ToastUtil.show("你正在申请退出接单员，请等待");
//                    return;
//                }else if ( SPCommon.getString("auth","").equals("3")){
//                    ToastUtil.show("你已退出接单员");
//                    return;
//                }else if ( SPCommon.getString("auth","").equals("4")){
//                    ToastUtil.show("你已被限制接单");
//                    return;
//                }else if ( SPCommon.getString("auth","").equals("-2")){
//                    ToastUtil.show("你的接单员申请资料还未完善，请先完善资料");
//                    return;
//                }else if ( SPCommon.getString("auth","").equals("100")){
//                    ToastUtil.show("你还没有交付押金，请先交付押金");
//                    return;
//                }

                break;
        }
    }
    String name;
    String img;
    String tel;
    @Override
    public void setMyInfo(MyBean myBean) {
        name = myBean.nickName+"";
        img = myBean.headImg;
        tel = myBean.phone+"";
        Picasso.with(this).load(myBean.headImg).placeholder(R.mipmap.sanyangtubiao).error(R.mipmap.sanyangtubiao).into(civHead);
         tvName.setText(myBean.nickName+"");
         tvPhone.setText(myBean.phone+"");
        tvFaHuoNum.setText(myBean.dealMoney+"");
        tvTiXianNum.setText(myBean.residueMoney+"");
        SPCommon.setString("tuisong",myBean.needRadio);
        SPCommon.setString("zhifumima",myBean.isSetSecondPwd);
        SPCommon.setString("auth",myBean.authStatus);
    }

    @Override
    public void setKeFu(final  ServiceCenterBean mBean) {
        final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(this);
        dialog.setCanceledOnTouchOutside(true);
        if (!TextUtils.isEmpty(mBean.address)&&!TextUtils.isEmpty(mBean.areaPhone)){
            dialog.setMessage(mBean.areaPhone+"", mBean.address+"");
        }else {
            dialog.setMessage("0635-635-5777", "聊城市新东方国际C座15楼2151");
        }
        dialog.setBackgroundResource(true);
        dialog.setVisibilityBtn(true);
        dialog.setYesOnclickListener("拨打", new ApplyAndAlterDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" +mBean.areaPhone+ "");
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
    }
}
