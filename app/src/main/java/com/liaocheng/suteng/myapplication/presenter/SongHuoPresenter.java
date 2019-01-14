package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.ReceiveOrderModel;
import com.liaocheng.suteng.myapplication.presenter.contract.QuHuoContent;
import com.liaocheng.suteng.myapplication.presenter.contract.SongHuoContent;

/**
 * Created by LHB on 2018/7/23 0023.
 */

public class SongHuoPresenter extends RxPresenter<SongHuoContent.View> implements SongHuoContent.Presenter{


    @Override
    public void queryOnTheWayOrder(String page) {
        addSubscribe(Api.createTBService().queryOnTheWayOrder(SPCommon.getString("token",""),page)
                .compose(RxUtil.<BaseResponse<ReceiveOrderModel>>rxSchedulerHelper())
                .compose(RxUtil.<ReceiveOrderModel>handleResult())
                .subscribeWith(new CommonSubscriber<ReceiveOrderModel>(mContext, true) {
                    @Override
                    protected void _onNext(ReceiveOrderModel commonRes) {

                        if (commonRes != null) {
                            mView.queryOnTheWayOrder(commonRes);
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
    public void checkReceiveCode(String code, String receiveCode) {
        addSubscribe(Api.createTBService().checkReceiveCode(SPCommon.getString("token",""),code,receiveCode)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.checkReceiveCode();
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
    public void order_upPhotoByIndex(String code, String goodsImg, String index) {
        addSubscribe(Api.createTBService().order_upPhotoByIndex(SPCommon.getString("token",""),code,goodsImg,index)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.order_upPhotoByIndex();
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
