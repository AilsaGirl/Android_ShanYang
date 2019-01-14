package com.liaocheng.suteng.myapplication.presenter;

import android.graphics.Color;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RidePath;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.JavaTypeUtil;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.ReceiveOrderModel;
import com.liaocheng.suteng.myapplication.presenter.contract.DingDanInfoContent;
import com.liaocheng.suteng.myapplication.presenter.contract.QuHuoContent;
import com.liaocheng.suteng.myapplication.util.mapUtil.RitUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LHB on 2018/7/23 0023.
 */

public class QuHuoPresenter extends RxPresenter<QuHuoContent.View> implements QuHuoContent.Presenter{


    @Override
    public void queryReceiveOrder(String page) {
        addSubscribe(Api.createTBService().queryReceiveOrder(SPCommon.getString("token",""),page)
                .compose(RxUtil.<BaseResponse<ReceiveOrderModel>>rxSchedulerHelper())
                .compose(RxUtil.<ReceiveOrderModel>handleResult())
                .subscribeWith(new CommonSubscriber<ReceiveOrderModel>(mContext, true) {
                    @Override
                    protected void _onNext(ReceiveOrderModel commonRes) {

                        if (commonRes != null) {
                            mView.queryReceiveOrder(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    @Override
    public void getThePickup(String code) {
        addSubscribe(Api.createTBService().getThePickup(SPCommon.getString("token",""),code)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.getThePickup();
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    @Override
    public void order_refund(String code) {
        addSubscribe(Api.createTBService().order_refund(SPCommon.getString("token",""),code)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.order_refund();
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    @Override
    public void order_revoke(String code) {
        addSubscribe(Api.createTBService().order_revoke(SPCommon.getString("token",""),code)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.order_revoke();
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }
}
