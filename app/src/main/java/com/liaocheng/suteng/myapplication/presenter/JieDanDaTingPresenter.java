package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.contract.FaDanContent;
import com.liaocheng.suteng.myapplication.presenter.contract.JieDanDaTingContent;

public class JieDanDaTingPresenter extends RxPresenter<JieDanDaTingContent.View> implements JieDanDaTingContent.Presenter{



    @Override
    public void getOrder(String pageNo,String trafficTool) {
        addSubscribe(Api.createTBService().order_info(SPCommon.getString("token",""),pageNo, trafficTool)
                .compose(RxUtil.<BaseResponse<JieDanDaTingModel>>rxSchedulerHelper())
                .compose(RxUtil.<JieDanDaTingModel>handleResult())
                .subscribeWith(new CommonSubscriber<JieDanDaTingModel>(mContext, false) {
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
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, false) {
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
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, false) {
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

    @Override
    public void setWorkTraffic(String type) {
        addSubscribe(Api.createTBService().setWorkTraffic(SPCommon.getString("token",""),type)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, false) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.setWorkTraffic();
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
    public void queryTransferAndSpecificOrder() {
        addSubscribe(Api.createTBService().queryTransferAndSpecificOrder(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<JieDanDaTingModel>>rxSchedulerHelper())
                .compose(RxUtil.<JieDanDaTingModel>handleResult())
                .subscribeWith(new CommonSubscriber<JieDanDaTingModel>(mContext, false) {
                    @Override
                    protected void _onNext(JieDanDaTingModel commonRes) {

                        if (commonRes != null) {
                            mView.queryTransferAndSpecificOrder(commonRes);
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
    public void executeSpecificOrder(String code, String type) {
        addSubscribe(Api.createTBService().executeSpecificOrder(SPCommon.getString("token",""),code,type)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, false) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.executeSpecificOrder();
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
    public void executeTransferOrder(String code, String type) {
        addSubscribe(Api.createTBService().executeTransferOrder(SPCommon.getString("token",""),code,type)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, false) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.executeTransferOrder();
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
    public void getCustomerTraffic() {
        addSubscribe(Api.createTBService().getCustomerTraffic(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<BaoXianModel>>rxSchedulerHelper())
                .compose(RxUtil.<BaoXianModel>handleResult())
                .subscribeWith(new CommonSubscriber<BaoXianModel>(mContext, false) {
                    @Override
                    protected void _onNext(BaoXianModel commonRes) {

                        if (commonRes != null) {
                            mView.getCustomerTraffic(commonRes);
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
