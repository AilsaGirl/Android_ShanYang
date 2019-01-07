package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.contract.FaDanContent;
import com.liaocheng.suteng.myapplication.presenter.contract.JieDanDaTingContent;

public class JieDanDaTingPresenter extends RxPresenter<JieDanDaTingContent.View> implements JieDanDaTingContent.Presenter{



    @Override
    public void getOrder(String pageNo) {
        addSubscribe(Api.createTBService().order_info(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<JieDanDaTingModel>>rxSchedulerHelper())
                .compose(RxUtil.<JieDanDaTingModel>handleResult())
                .subscribeWith(new CommonSubscriber<JieDanDaTingModel>(mContext, true) {
                    @Override
                    protected void _onNext(JieDanDaTingModel commonRes) {

                        if (commonRes != null) {
                            mView.setOrder(commonRes);
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
    public void updateTechnicianWorkStatus(String type) {
        addSubscribe(Api.createTBService().setWorkState(SPCommon.getString("token",""),type)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.updateTechnicianWorkStatus();
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
    public void order_grab(String code) {
        addSubscribe(Api.createTBService().order_grab(SPCommon.getString("token",""),code)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.order_grab();
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
