package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.presenter.contract.FaDanContent;
import com.liaocheng.suteng.myapplication.presenter.contract.WanChengContent;

public class WanChengPresenter extends RxPresenter<WanChengContent.View> implements WanChengContent.Presenter{

    @Override
    public void queryCompletedOrder(String pageNo) {
        addSubscribe(Api.createTBService().queryCompletedOrder(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<JieDanDaTingModel>>rxSchedulerHelper())
                .compose(RxUtil.<JieDanDaTingModel>handleResult())
                .subscribeWith(new CommonSubscriber<JieDanDaTingModel>(mContext, true) {
                    @Override
                    protected void _onNext(JieDanDaTingModel commonRes) {

                        if (commonRes != null) {
                            mView.queryCompletedOrder(commonRes);
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
    public void queryHaveRefundOrder(String pageNo) {
        addSubscribe(Api.createTBService().queryHaveRefundOrder(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<JieDanDaTingModel>>rxSchedulerHelper())
                .compose(RxUtil.<JieDanDaTingModel>handleResult())
                .subscribeWith(new CommonSubscriber<JieDanDaTingModel>(mContext, true) {
                    @Override
                    protected void _onNext(JieDanDaTingModel commonRes) {

                        if (commonRes != null) {
                            mView.queryHaveRefundOrder(commonRes);
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
