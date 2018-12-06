package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.presenter.contract.JieDanContent;

public class JieDanPresenter extends RxPresenter<JieDanContent.View> implements JieDanContent.Presenter{

    @Override
    public void getMySendOrder(String duration,String pageNo) {
        addSubscribe(Api.createTBService().queryMyReceiveOrders(SPCommon.getString("token",""),pageNo,duration)
                .compose(RxUtil.<BaseResponse<MySendOrdersBean>>rxSchedulerHelper())
                .compose(RxUtil.<MySendOrdersBean>handleResult())
                .subscribeWith(new CommonSubscriber<MySendOrdersBean>(mContext, true) {
                    @Override
                    protected void _onNext(MySendOrdersBean commonRes) {

                        if (commonRes != null) {
                            mView.setMySendOrder(commonRes);
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
    public void getTotel(String duration) {
        addSubscribe(Api.createTBService().queryMyReceiveOrdersCombined(SPCommon.getString("token",""),duration)
                .compose(RxUtil.<BaseResponse<MySendOrdersBean>>rxSchedulerHelper())
                .compose(RxUtil.<MySendOrdersBean>handleResult())
                .subscribeWith(new CommonSubscriber<MySendOrdersBean>(mContext, true) {
                    @Override
                    protected void _onNext(MySendOrdersBean commonRes) {

                        if (commonRes != null) {
                            mView.setTotel(commonRes);
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
