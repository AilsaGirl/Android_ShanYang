package com.liaocheng.suteng.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.circle.common.base.BaseActivity;
import com.circle.common.eyesutils.Eyes;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MainModel;
import com.liaocheng.suteng.myapplication.model.NoticeModel;
import com.liaocheng.suteng.myapplication.model.event.FaHuoAddressEvent;
import com.liaocheng.suteng.myapplication.presenter.MainPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.MainContact;
import com.liaocheng.suteng.myapplication.ui.home.GongDaoListActivity;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.FaHuoActivity;
import com.liaocheng.suteng.myapplication.ui.home.address.AddAddress;
import com.liaocheng.suteng.myapplication.ui.home.address.NewLocationActivity;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.JieDanActivity;
import com.liaocheng.suteng.myapplication.ui.login.LoginActivity;
import com.liaocheng.suteng.myapplication.ui.my.MyActivity;
import com.liaocheng.suteng.myapplication.view.UPMarqueeView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContact.View {

    @BindView(R.id.ivBackground)
    Banner ivBackground;
    @BindView(R.id.ivGG)
    ImageView ivGG;
    @BindView(R.id.vf)
    UPMarqueeView vf;

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
        mLocationOption.setOnceLocationLatest(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }
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
                    lon=aMapLocation.getLongitude();
                    lat = aMapLocation.getLatitude();
                    SPCommon.setString("lon",aMapLocation.getLongitude()+"");
                    SPCommon.setString("lat",aMapLocation.getLatitude()+"");
                    SPCommon.setString("qu",aMapLocation.getDistrict()+"");
                    SPCommon.setString("city",aMapLocation.getCity()+"");
                    mPresenter.getBanner(SPCommon.getString("qu","东昌府区"));
                    mPresenter.getNotice(SPCommon.getString("qu","东昌府区"));
                } else {

                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("yue.huang", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
    @Override
    public void initEventAndData() {
        setTransparentStatusBar();
        initListener();

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
                break;
            case R.id.linQiangDan:
                 intent = new Intent(mContext, JieDanActivity.class);
                mContext.startActivity(intent);
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
        if (noticeBean.data==null||noticeBean.data.size()==0){
            return;
        }
        for (int i = 0; i < noticeBean.data.size(); i++) {
            LayoutInflater inflater3 = LayoutInflater.from(mContext);
            View view = inflater3.inflate(R.layout.shouye_gg, null);
//                holder.vf.removeView(view);
            TextView tvTitle1 = (TextView) view
                    .findViewById(R.id.tvTitle1);
            tvTitle1.setText(noticeBean.data.get(i).content+"");
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
        if (mBean.data==null||mBean.data.size()==0){
            return;
        }
        List<String> list = new ArrayList<>();
       for (int i=0;i<mBean.data.size();i++){
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
                if (TextUtils.isEmpty(mBean.data.get(position).imgToUrl)){
                    return;
                }else {
                    if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                        return;
                    mLasttime = System.currentTimeMillis();
                    //代码实现跳转
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(mBean.data.get(position).imgToUrl+"");//此处填链接
                    intent.setData(content_url);
                    startActivity(intent);
                }
//                        Intent intent = new Intent(mContext, PartyBuilding5Activity.class);
//                        intent.putExtra("urlh5", model.value);
//                        mContext.startActivity(intent);


            }
        }).start();
    }
}
