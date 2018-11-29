package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.FaDanXiaDanModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.OrderCalculateBean;
import com.liaocheng.suteng.myapplication.presenter.contract.FaHuoContact;

public class FaHuoXiaDanPersenter extends RxPresenter<FaHuoContact.View> implements FaHuoContact.Presenter {
    @Override
    public void detail(String token, String orderType, String sendName, String sendPhone, String sendAddress, String sendConcreteAdd, String sendDetailAdd, String sendLat, String sendLong, String receiveName, String contactPhone, String receiveAddress, String receiveConcreteAdd, String receiveDetailAdd, String longitude, String latitude, String tip, String coupon, String goods, String weight, String appointTime, String description, String trafficTool, String incubator, String receivePhone, String parcel_insurance_id) {
        addSubscribe(Api.createTBService().order_create( SPCommon.getString("token",""), orderType,  sendName,  sendPhone,  sendAddress,  sendConcreteAdd,  sendDetailAdd,  sendLat,  sendLong,  receiveName,  contactPhone,  receiveAddress, receiveConcreteAdd, receiveDetailAdd, longitude, latitude, tip, coupon, goods, weight, appointTime, description, trafficTool, incubator, receivePhone, parcel_insurance_id)
                .compose(RxUtil.<BaseResponse<FaDanXiaDanModel>>rxSchedulerHelper())
                .compose(RxUtil.<FaDanXiaDanModel>handleResult())
                .subscribeWith(new CommonSubscriber<FaDanXiaDanModel>(mContext, true) {
                    @Override
                    protected void _onNext(FaDanXiaDanModel commonRes) {
                        if (commonRes != null) {
                            mView.setData(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(1, message);
                    }
                })
        );
    }

    @Override
    public void orderNum(String token, String orderType, String sendLat, String sendLong, String longitude, String latitude, String tip, String coupon, String weight, String trafficTool, String parcel_insurance_id) {
        addSubscribe(Api.createTBService().order_calculate( SPCommon.getString("token",""), orderType,  sendLat,  sendLong,  longitude,  latitude,  tip,  coupon,  weight,  trafficTool,  parcel_insurance_id)
                .compose(RxUtil.<BaseResponse<OrderCalculateBean>>rxSchedulerHelper())
                .compose(RxUtil.<OrderCalculateBean>handleResult())
                .subscribeWith(new CommonSubscriber<OrderCalculateBean>(mContext, true) {
                    @Override
                    protected void _onNext(OrderCalculateBean commonRes) {
                        if (commonRes != null) {
                            mView.setNum(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(1, message);
                    }
                })
        );
    }
}
