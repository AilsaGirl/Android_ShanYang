package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.ChongZhiModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;
import com.liaocheng.suteng.myapplication.presenter.contract.BangDingContent;
import com.liaocheng.suteng.myapplication.presenter.contract.ChongZhiContent;

public class ChongZhiPresenter extends RxPresenter<ChongZhiContent.View> implements ChongZhiContent.Presenter{


    @Override
    public void isFirstChargedDealMoney() {
        addSubscribe(Api.createTBService().isFirstChargedDealMoney(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<ChongZhiModel>>rxSchedulerHelper())
                .compose(RxUtil.<ChongZhiModel>handleResult())
                .subscribeWith(new CommonSubscriber<ChongZhiModel>(mContext, true) {
                    @Override
                    protected void _onNext(ChongZhiModel commonRes) {
                        if (commonRes != null) {
                            mView.isFirstChargedDealMoney(commonRes);
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
    public void user_recharge(String rechargeMoney, String rechargeType) {
        addSubscribe(Api.createTBService().user_recharge(SPCommon.getString("token",""),rechargeMoney,rechargeType)
                .compose(RxUtil.<BaseResponse<PayModel>>rxSchedulerHelper())
                .compose(RxUtil.<PayModel>handleResult())
                .subscribeWith(new CommonSubscriber<PayModel>(mContext, true) {
                    @Override
                    protected void _onNext(PayModel commonRes) {
                        if (commonRes != null) {
                            mView.user_recharge(commonRes);
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
    public void user_rechargeDealMoney(String rechargeMoneyType, String rechargeType) {
        addSubscribe(Api.createTBService().user_rechargeDealMoney(SPCommon.getString("token",""),rechargeMoneyType,rechargeType)
                .compose(RxUtil.<BaseResponse<PayModel>>rxSchedulerHelper())
                .compose(RxUtil.<PayModel>handleResult())
                .subscribeWith(new CommonSubscriber<PayModel>(mContext, true) {
                    @Override
                    protected void _onNext(PayModel commonRes) {
                        if (commonRes != null) {
                            mView.user_rechargeDealMoney(commonRes);
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
    public void getAddType(String area, String city) {
        addSubscribe(Api.createTBService().getAddType(SPCommon.getString("token",""),city,area)
                .compose(RxUtil.<BaseResponse<ChongZhiModel>>rxSchedulerHelper())
                .compose(RxUtil.<ChongZhiModel>handleResult())
                .subscribeWith(new CommonSubscriber<ChongZhiModel>(mContext, true) {
                    @Override
                    protected void _onNext(ChongZhiModel commonRes) {
                        if (commonRes != null) {
                            mView.getAddType(commonRes);
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
