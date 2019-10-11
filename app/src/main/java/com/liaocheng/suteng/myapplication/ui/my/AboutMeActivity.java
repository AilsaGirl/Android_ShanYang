package com.liaocheng.suteng.myapplication.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.base.CompositeUtil;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.update.UpdateManager;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.VersionModel;
import com.liaocheng.suteng.myapplication.ui.home.GongDaoListActivity;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.CustomMenuView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AboutMeActivity extends BaseActivity {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.civHead)
    CircleImageView civHead;
    @BindView(R.id.tvAppName)
    TextView tvAppName;
    @BindView(R.id.tvBanBen)
    TextView tvBanBen;
    @BindView(R.id.cmvGongGao)
    CustomMenuView cmvGongGao;
    @BindView(R.id.cmvXinWen)
    CustomMenuView cmvXinWen;
    @BindView(R.id.cmvGuiZe)
    CustomMenuView cmvGuiZe;
    @BindView(R.id.cmvGongNeng)
    CustomMenuView cmvGongNeng;
    @BindView(R.id.cmvAboutGongSi)
    CustomMenuView cmvAboutGongSi;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    @Override
    public int getLayoutId() {
        return R.layout.activity_aboutme;
    }

    @Override
    public void initEventAndData() {
        toolBar.setBackFinish().setTitleText("关于我们");
        tvBanBen.setText(Util.packageName(this));
    }

    @Override
    public void showError(int reqCode, String msg) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    Intent intent;

    @OnClick({R.id.cmvGongGao, R.id.cmvXinWen, R.id.cmvGuiZe, R.id.cmvGongNeng, R.id.cmvAboutGongSi, R.id.btnUpdate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmvGongGao:
                intent = new Intent(mContext, GongDaoListActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvXinWen:
                intent = new Intent(mContext, XinWenListActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvGuiZe:
                intent = new Intent(mContext, GuiZeListActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvGongNeng:
                intent = new Intent(mContext, GongNengListActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cmvAboutGongSi:
                intent = new Intent(mContext, GongSiActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.btnUpdate:

                    //更新
                    getBestVersionInfo();


                break;
        }
    }
    CompositeUtil compositeUtil = new CompositeUtil();
    private void getBestVersionInfo(){
        compositeUtil.addSubscribe(Api.createTBService().appVersion_info()
                .compose(RxUtil.<BaseResponse<VersionModel>>rxSchedulerHelper())
                .compose(RxUtil.<VersionModel>handleResult())
                .subscribeWith(new CommonSubscriber<VersionModel>(mContext, false) {
                    @Override
                    protected void _onNext(VersionModel commonRes) {
                        if (commonRes != null) {
                            if (Util.packageCode(AboutMeActivity.this)<Integer.parseInt(commonRes.versionCode)){

                            SPCommon.setString("NewCode",commonRes.versionCode);
                            final boolean isForceUpdate = commonRes.important.equals("1");
                            //今天是不是第一次打开APP
//        Calendar calendar = Calendar.getInstance();
//        String time = (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DATE) + "号";
                            boolean isOpenApp = false;
//        if(!isForceUpdate)
//            isOpenApp = SPCommon.getOpenAppTime().equalsIgnoreCase(time);
                            new UpdateManager((Activity) mContext, isForceUpdate, isOpenApp, commonRes.downUrl, commonRes.versionCode, commonRes.content,
                                    new UpdateManager.UpdateListener() {
                                        @Override
                                        public void onCancelUpdate() {
                                            if (isForceUpdate) {
                                                AboutMeActivity.this.finish();
                                            }
                                        }
                                    });
                        }else {
                                ToastUtil.show("最新版本");
                                return;
                            }

                    }}


                    @Override
                    protected void _onError(String message) {
                        ToastUtil.show( message);
                    }
                }));


    }
}
