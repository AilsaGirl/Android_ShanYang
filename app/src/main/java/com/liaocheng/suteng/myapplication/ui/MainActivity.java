package com.liaocheng.suteng.myapplication.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.circle.common.base.BaseActivity;
import com.circle.common.update.UpdateDialog;
import com.circle.common.update.UpdateManager;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.MainModel;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.NoticeModel;
import com.liaocheng.suteng.myapplication.model.VersionModel;
import com.liaocheng.suteng.myapplication.model.event.FaHuoAddressEvent;
import com.liaocheng.suteng.myapplication.model.event.MessageEvent;
import com.liaocheng.suteng.myapplication.presenter.MainPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.MainContact;
import com.liaocheng.suteng.myapplication.ui.home.GongDaoListActivity;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.FaHuoActivity;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.JieDanActivity;
import com.liaocheng.suteng.myapplication.ui.my.MyActivity;
import com.liaocheng.suteng.myapplication.ui.my.MyIdentityActivity;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;
import com.liaocheng.suteng.myapplication.view.UPMarqueeView;
import com.squareup.picasso.Picasso;
import com.tencent.bugly.beta.Beta;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContact.View {

    @BindView(R.id.ivBackground)
    Banner ivBackground;
    @BindView(R.id.ivGG)
    ImageView ivGG;
    @BindView(R.id.vf)
    UPMarqueeView vf;
    @BindView(R.id.toolbar)
    MyToolBar toolbar;
    @BindView(R.id.linFaHuo)
    LinearLayout linFaHuo;
    @BindView(R.id.linQiangDan)
    LinearLayout linQiangDan;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    List<View> views = new ArrayList<>();
    /*定位相关*/
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private void initListener() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }
int dingwei =1;
    double lon;
    double lat;
    String mQu;
    String mCity;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    mQu = aMapLocation.getDistrict();
                    mCity = aMapLocation.getCity();
                    lon = aMapLocation.getLongitude();
                    lat = aMapLocation.getLatitude();
//                    mPresenter.updateLocation(SPCommon.getString("token", ""), lat + "", lon + "");
                    SPCommon.setString("lon", aMapLocation.getLongitude() + "");
                    SPCommon.setString("lat", aMapLocation.getLatitude() + "");
                    SPCommon.setString("qu", aMapLocation.getDistrict() + "");
                    SPCommon.setString("city", aMapLocation.getCity() + "");
                    toolbar.setLeftText(SPCommon.getString("qu", "东昌府区"));
                    if (dingwei==1){
                        mPresenter.getBanner(SPCommon.getString("qu", "东昌府区"));
                        if (SPCommon.getString("auth", "").equals("1")){
                            mPresenter.updateLocation(SPCommon.getString("token", ""), lat + "", lon + "");
                        }
                    }
                    dingwei++;

                } else {

                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("yue.huang", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    private static final int NTF_SETALI = 0X2;
    private static final int NTF_SETTAG = 0x3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NTF_SETALI:
                    if (JPushInterface.isPushStopped(getApplicationContext())) {
                        JPushInterface.resumePush(getApplicationContext());
                    }
//                    SPCommon.setString("userId",loginBean.userId);
                    JPushInterface.setAlias(getApplicationContext(),100, SPCommon.getString("userId","0"));
//                    JPushInterface.setTags(getApplicationContext(),100, SPCommon.getString("userId","0"));
                    break;
                case NTF_SETTAG:
                    if (JPushInterface.isPushStopped(getApplicationContext())) {
                        JPushInterface.resumePush(getApplicationContext());
                    }
//                    SPCommon.setString("userId",loginBean.userId);
                    JPushInterface.setTags(getApplicationContext(),100, getTag("shanyangpaotui,shanyangpaotui"));
                    break;
            }
        }
    };
    private Set<String> getTag(String tag) {
        Set<String> tagSet = new LinkedHashSet<String>();
        String[] sArray = tag.split(",");
        for (String sTagItme : sArray) {
            tagSet.add(sTagItme);
        }
        return tagSet;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        switch (messageEvent.getMessage()) {

            case "setAlias":
                handler.sendMessageDelayed(handler.obtainMessage(NTF_SETALI, SPCommon.getString("userId","0")), 60000);
                break;
            case "setTag":
                handler.sendMessageDelayed(handler.obtainMessage(NTF_SETTAG, 1), 60000);
                break;

        }
    }
    @Override
    public void initEventAndData() {
//        setTransparentStatusBar();
        toolbar.setTitleText("三羊跑腿");
        toolbar.setRight(R.mipmap.icon_wode, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyActivity.class);
                startActivity(intent);
            }
        });
        toolbar.setBackDrawable();
        mPresenter.appVersion_info();
        initListener();
        mPresenter.IdentityInfo(SPCommon.getString("token", ""));
        mPresenter.getBanner(SPCommon.getString("qu", "东昌府区"));
        checkOutUpdate();

    }
    private void checkOutUpdate() {
        Beta.checkUpgrade();
    }
    @Override
    public void onResume() {
        super.onResume();
        dingwei = 1;
        initListener();
//        mPresenter.updateLocation(SPCommon.getString("token", ""), lat + "", lon + "");
    }

    Intent intent;

    @OnClick({R.id.vf, R.id.linFaHuo, R.id.linQiangDan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vf:
                break;
            case R.id.linFaHuo:
//                 intent = new Intent(mContext, LoginActivity.class);
//                    mContext.startActivity(intent);

                intent = new Intent(mContext, FaHuoActivity.class);
                mContext.startActivity(intent);
//                finish();
                break;
            case R.id.linQiangDan:
//                Util.playSound(MainActivity.this, "1");
//                intent = new Intent(mContext, JieDanActivity.class);
//                mContext.startActivity(intent);



                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                if (SPCommon.getString("auth", "").equals("0")) {
                    final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(MainActivity.this);
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.setMessage("不好意思，你还没有认证接单员。", "认证的时候需要支付200的培训费+100元的装备费用，费用一经支付以后，无论什么原因都不退，请慎重操作。");
                    dialog.setBackgroundResource(true);
                    dialog.setVisibilityBtn(true);
                    dialog.setYesOnclickListener("去申请", new ApplyAndAlterDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            intent = new Intent(mContext, MyIdentityActivity.class);
                            startActivity(intent);
                            dialog.dismiss();
//                            finish();
                        }
                    });
                    dialog.setOnOnclickListener("取消", new ApplyAndAlterDialog.onOnOnclickListener() {
                        @Override
                        public void onOnClick() {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else if (SPCommon.getString("auth", "").equals("1")) {
                    mLasttime = System.currentTimeMillis();
                    intent = new Intent(mContext, JieDanActivity.class);
                    mContext.startActivity(intent);
                    return;
                } else if (SPCommon.getString("auth", "").equals("2")) {
                    ToastUtil.show("你正在申请退出接单员，请等待");
                    return;
                } else if (SPCommon.getString("auth", "").equals("3")) {
                    ToastUtil.show("你已退出接单员");
                    return;
                } else if (SPCommon.getString("auth", "").equals("4")) {
                    ToastUtil.show("你已被限制接单");
                    return;
                } else if (SPCommon.getString("auth", "").equals("-2")) {
                    ToastUtil.show("你的接单员申请资料还未完善，请先完善资料");
                    return;
                } else if (SPCommon.getString("auth", "").equals("100")) {
                    ToastUtil.show("你还没有交付押金，请先交付押金");
                    return;
                }

//                finish();
                break;
        }
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Override
    public void setNotice(NoticeModel noticeBean) {
        if (noticeBean.data == null || noticeBean.data.size() == 0) {
            return;
        }
        for (int i = 0; i < noticeBean.data.size(); i++) {
            LayoutInflater inflater3 = LayoutInflater.from(mContext);
            View view = inflater3.inflate(R.layout.shouye_gg, null);
//                holder.vf.removeView(view);
            TextView tvTitle1 = (TextView) view
                    .findViewById(R.id.tvTitle1);
            tvTitle1.setText(noticeBean.data.get(i).content + "");
            vf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, GongDaoListActivity.class);
                    mContext.startActivity(intent);
                }
            });
            views.add(view);
        }
        vf.setViews(views);
    }

    long mLasttime;

    @Override
    public void setBanner(final MainModel mBean) {
        mPresenter.getNotice(SPCommon.getString("qu", "东昌府区"));
        if (mBean.data == null || mBean.data.size() == 0) {
            return;
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < mBean.data.size(); i++) {
            list.add(mBean.data.get(i).imgUrl);
        }
        ivBackground.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                if (path instanceof String) {
                    Picasso.with(context).load((String) path).into(imageView);
                }

            }
        }).setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (TextUtils.isEmpty(mBean.data.get(position).imgToUrl)) {
                    return;
                } else {
                    if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                        return;
                    mLasttime = System.currentTimeMillis();
                    //代码实现跳转
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(mBean.data.get(position).imgToUrl + "");//此处填链接
                    intent.setData(content_url);
                    startActivity(intent);
                }
//                        Intent intent = new Intent(mContext, PartyBuilding5Activity.class);
//                        intent.putExtra("urlh5", model.value);
//                        mContext.startActivity(intent);


            }
        }).start();
    }

    @Override
    public void IdentityInfoSucss(MyBean myBean) {
        SPCommon.setString("tuisong", myBean.needRadio);
        SPCommon.setString("zhifumima", myBean.isSetSecondPwd);
        SPCommon.setString("auth", myBean.authStatus);
        SPCommon.setString("username", myBean.nickName);
        SPCommon.setString("tel", myBean.phone);
        SPCommon.setString("baozhengjin", myBean.arrears);
        SPCommon.setString("baoxian", myBean.insuranceArrears);
//
        if (myBean.authStatus.equals("1")){
            mPresenter.updateLocation(SPCommon.getString("token", ""), lat + "", lon + "");
        }
        JPushInterface.setAlias(getApplicationContext(), //上下文对象
                SPCommon.getString("userId","0"), //别名
                new TagAliasCallback() {//回调接口,i=0表示成功,其它设置失败
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        Log.d("alias","111"+i);
                        Log.d("alias2",SPCommon.getString("userId","0"));
                    }
                });
    }

    @Override
    public void updateLocation() {

    }

    @Override
    public void appVersion_info(VersionModel model) {
        if (Util.packageCode(this) < Integer.parseInt(model.versionCode)) {

            SPCommon.setString("NewCode", model.versionCode);
            final boolean isForceUpdate = model.important.equals("1");
            //今天是不是第一次打开APP
            Calendar calendar = Calendar.getInstance();
            String time = (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DATE) + "号";
            boolean isOpenApp = false;
            if (!isForceUpdate)
                isOpenApp = SPCommon.getOpenAppTime().equalsIgnoreCase(time);
            new UpdateManager((Activity) mContext, isForceUpdate, isOpenApp, model.downUrl, model.versionCode, model.content,
                    new UpdateManager.UpdateListener() {
                        @Override
                        public void onCancelUpdate() {
                            if (isForceUpdate) {
                                MainActivity.this.finish();
                            }
                        }
                    });
            if (!isForceUpdate)
                SPCommon.setOpenAppTime(time);
        }else {
            ToastUtil.show("最新版本");
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
