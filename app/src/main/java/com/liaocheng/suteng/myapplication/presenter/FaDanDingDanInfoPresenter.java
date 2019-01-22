package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.contract.FaDanDingDanInfoContent;
import com.liaocheng.suteng.myapplication.presenter.contract.WanChengContent;

public class FaDanDingDanInfoPresenter extends RxPresenter<FaDanDingDanInfoContent.View> implements FaDanDingDanInfoContent.Presenter{

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
    public void addBlacklist(String id) {
        addSubscribe(Api.createTBService().addBlacklist(SPCommon.getString("token",""),id)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.addBlacklist();
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
