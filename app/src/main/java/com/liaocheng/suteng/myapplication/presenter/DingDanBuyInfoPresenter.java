package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.contract.DingDanBuyInfoContent;


public class DingDanBuyInfoPresenter extends RxPresenter<DingDanBuyInfoContent.View> implements DingDanBuyInfoContent.Presenter{

    @Override
    public void getDingDa(String code) {
        addSubscribe(Api.createTBService().querySendOrderDetail(SPCommon.getString("token",""),code)
                .compose(RxUtil.<BaseResponse<DingDanBuyInfoModel>>rxSchedulerHelper())
                .compose(RxUtil.<DingDanBuyInfoModel>handleResult())
                .subscribeWith(new CommonSubscriber<DingDanBuyInfoModel>(mContext, true) {
                    @Override
                    protected void _onNext(DingDanBuyInfoModel commonRes) {

                        if (commonRes != null) {
                            mView.setDingDa(commonRes);
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
    public void order_cancell(String code) {
        addSubscribe(Api.createTBService().order_cancell(SPCommon.getString("token",""),code)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.order_cancell();
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
    public void order_pay(String code, String type) {
        addSubscribe(Api.createTBService().order_pay(SPCommon.getString("token",""),code,type)
                .compose(RxUtil.<BaseResponse<PayModel>>rxSchedulerHelper())
                .compose(RxUtil.<PayModel>handleResult())
                .subscribeWith(new CommonSubscriber<PayModel>(mContext, true) {
                    @Override
                    protected void _onNext(PayModel commonRes) {

                        if (commonRes != null) {
                            mView.order_pay(commonRes);
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
    public void user_order_refund(String code) {
        addSubscribe(Api.createTBService().user_order_refund(SPCommon.getString("token",""),code)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.user_order_refund();
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
    public void addOrderTip(String code, String type) {
        addSubscribe(Api.createTBService().addOrderTip(SPCommon.getString("token",""),code,type)
                .compose(RxUtil.<BaseResponse<PayModel>>rxSchedulerHelper())
                .compose(RxUtil.<PayModel>handleResult())
                .subscribeWith(new CommonSubscriber<PayModel>(mContext, true) {
                    @Override
                    protected void _onNext(PayModel commonRes) {

                        if (commonRes != null) {
                            mView.addOrderTip(commonRes);
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

    @Override
    public void order_submit(String code) {
        addSubscribe(Api.createTBService().order_submit(SPCommon.getString("token",""),code)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.order_submit();
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

    @Override
    public void courier_order_submit(String code) {
        addSubscribe(Api.createTBService().courier_order_submit(SPCommon.getString("token",""),code)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.courier_order_submit();
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
    public void executeSpecificOrder(String code, String type) {
        addSubscribe(Api.createTBService().executeSpecificOrder(SPCommon.getString("token",""),code,type)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
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
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
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
    public void transferOrder(String code, String phone) {
        addSubscribe(Api.createTBService().transferOrder(SPCommon.getString("token",""),code,phone)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.transferOrder();
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
