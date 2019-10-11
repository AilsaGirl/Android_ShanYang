package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;
import com.liaocheng.suteng.myapplication.presenter.MyInfopresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.MyContact;
import com.liaocheng.suteng.myapplication.ui.home.address.AddressList;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.PingJiaActivity;
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
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvChongZgi)
    TextView tvChongZgi;
    @BindView(R.id.tvTiXian)
    TextView tvTiXian;
    @BindView(R.id.linFaHuo)
    LinearLayout linFaHuo;
    @BindView(R.id.linTiXian)
    LinearLayout linTiXian;
    @BindView(R.id.cmvYaoQingMa)
    CustomMenuView cmvYaoQingMa;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getMyInfo();
    }

    @Override
    public void initEventAndData() {
        mPresenter.getMyInfo();
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTiXian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                if (!isSetSecondPwd.equals("1")) {
                    ToastUtil.show("你还没设置二级密码");
                    return;
                }
                if (Double.parseDouble(tvTiXianNum.getText().toString()) < 1.0) {
                    ToastUtil.show("不足1元，不能体现");
                    return;
                }
                intent = new Intent(mContext, TiXianActivity.class);
                intent.putExtra("tel", tel);
                intent.putExtra("name", name);
                intent.putExtra("num", tvTiXianNum.getText().toString());
                mContext.startActivity(intent);
            }
        });
        tvChongZgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, ChongZhiActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    long mLasttime;
    Intent intent;

    @OnClick({R.id.civHead, R.id.cmvSet, R.id.cmvDingDan, R.id.cmvAddress, R.id.cmvQuan, R.id.cmvJieDanYan, R.id.cmvKeFu, R.id.cmvMore, R.id.linFaHuo, R.id.linTiXian,R.id.cmvYaoQingMa,R.id.cmvHeiMingDan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmvHeiMingDan:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
//                PingJiaActivity
//                intent = new Intent(mContext, HeiMingDanActivity.class);
                intent = new Intent(mContext, HeiMingDanActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvYaoQingMa:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                if (!SPCommon.getString("auth", "").equals("1") ) {
                    ToastUtil.show("你不是接单员");
                    return;
                }
                intent = new Intent(mContext, MyYaoQingMaActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.linFaHuo:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, YuEListActivity.class);
                intent.putExtra("type", "1");
                mContext.startActivity(intent);
                break;
            case R.id.linTiXian:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, YuEListActivity.class);
                intent.putExtra("type", "2");
                mContext.startActivity(intent);
                break;
            case R.id.civHead:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, UpDataMyInFoActivity.class);
                intent.putExtra("tel", tel);
                intent.putExtra("name", name);
                intent.putExtra("img", img);
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
                if (SPCommon.getString("auth", "").equals("0")) {
                    intent = new Intent(mContext, MyIdentityActivity.class);
                    startActivity(intent);
                    return;
                } else if (SPCommon.getString("auth", "").equals("-2")) {
                    intent = new Intent(mContext, MyUpdateIdentityActivity.class);
                    startActivity(intent);
                    return;
                } else if (SPCommon.getString("auth", "").equals("100")) {
                    intent = new Intent(mContext, IdentityPayActivity.class);
                    startActivity(intent);
                    ToastUtil.show("请交付押金");
                    return;
                } else if (SPCommon.getString("auth", "").equals("1")) {
                    ToastUtil.show("你已是接单员，如若接单，请前往接单大厅");
                    return;
                }else if (SPCommon.getString("auth", "").equals("2")) {
                    ToastUtil.show("你正在申请退出接单员，请等待");
//                    intent = new Intent(mContext, BaoZhengJinActivity.class);
//                    intent.putExtra("type","2");
//                    startActivity(intent);
                    return;
                } else if (SPCommon.getString("auth", "").equals("3")) {
                    ToastUtil.show("你已退出接单员");
                    return;
                } else if (SPCommon.getString("auth", "").equals("4")) {
                    ToastUtil.show("你已被限制接单");
                    return;
                }

                break;
            case R.id.cmvKeFu:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                mPresenter.getKeFu(SPCommon.getString("qu", "东昌府区"));
                break;
            case R.id.cmvMore:
                intent = new Intent(mContext, FuWuJiangLiActivity.class);
                mContext.startActivity(intent);

                break;
        }
    }

    String name;
    String img;
    String tel;
    String isSetSecondPwd;

    @Override
    public void setMyInfo(MyBean myBean) {
        name = myBean.nickName + "";
        img = myBean.headImg;
        tel = myBean.phone + "";
        isSetSecondPwd = myBean.isSetSecondPwd + "";
        Picasso.with(this).load(myBean.headImg).placeholder(R.mipmap.sanyangtubiao).error(R.mipmap.sanyangtubiao).into(civHead);
        tvName.setText(myBean.nickName + "");
        tvPhone.setText(myBean.phone + "");
        tvFaHuoNum.setText(myBean.dealMoney + "");
        tvTiXianNum.setText(myBean.residueMoney + "");
        SPCommon.setString("img", myBean.headImg);
        SPCommon.setString("tuisong", myBean.needRadio);
        SPCommon.setString("zhifumima", myBean.isSetSecondPwd);
        SPCommon.setString("auth", myBean.authStatus);
        SPCommon.setString("username", myBean.nickName);
        SPCommon.setString("tel", myBean.phone);
        SPCommon.setString("baozhengjin", myBean.arrears);
        SPCommon.setString("baoxian", myBean.insuranceArrears);
        SPCommon.setString("foregift", myBean.foregift);
        SPCommon.setString("foregiftProtocol", myBean.foregiftProtocol);
        SPCommon.setString("insurance", myBean.insurance);
        SPCommon.setString("insuranceProtocol", myBean.insuranceProtocol);
        SPCommon.setString("equipment", myBean.equipment);
    }

    @Override
    public void setKeFu(final ServiceCenterBean mBean) {
        final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(this);
        dialog.setCanceledOnTouchOutside(true);
        if (!TextUtils.isEmpty(mBean.address) && !TextUtils.isEmpty(mBean.areaPhone)) {
            dialog.setMessage(mBean.areaPhone + "", mBean.address + "");
        } else {
            dialog.setMessage("0635-635-5777", "聊城市新东方国际C座15楼2151");
        }
        dialog.setBackgroundResource(true);
        dialog.setVisibilityBtn(true);
        dialog.setYesOnclickListener("拨打", new ApplyAndAlterDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + mBean.areaPhone + "");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
