package com.liaocheng.suteng.myapplication.presenter;
import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.presenter.contract.IdentityContract;
import com.liaocheng.suteng.myapplication.presenter.contract.IdentityPayContract;


public class IdentityPayPresenter extends RxPresenter<IdentityPayContract.View> implements IdentityPayContract.Presenter {

    @Override
    public void checkSecondPassword(String secondPwd) {
        addSubscribe(Api.createTBService().checkSecondPassword(SPCommon.getString("token",""),secondPwd)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.checkSecondPassword();
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
    public void IdentityInfo(String token) {
        addSubscribe(Api.createTBService().auth_info(token)
                .compose(RxUtil.<BaseResponse<AuthBean>>rxSchedulerHelper())
                .compose(RxUtil.<AuthBean>handleResult())
                .subscribeWith(new CommonSubscriber<AuthBean>(mContext, false) {
                    @Override
                    protected void _onNext(AuthBean commonRes) {
                        if (commonRes != null) {
                            mView.IdentityInfoSucss(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                }));
    }

    @Override
    public void findAreaNeedFee(String city, String area) {
        addSubscribe(Api.createTBService().findAreaNeedFee(SPCommon.getString("token",""),city,area)
                .compose(RxUtil.<BaseResponse<BaoXianModel>>rxSchedulerHelper())
                .compose(RxUtil.<BaoXianModel>handleResult())
                .subscribeWith(new CommonSubscriber<BaoXianModel>(mContext, true) {
                    @Override
                    protected void _onNext(BaoXianModel commonRes) {

                        if (commonRes != null) {
                            mView.findAreaNeedFee(commonRes);
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
    public void isAgreementDeductions(String city, String area) {
        addSubscribe(Api.createTBService().isAgreementDeductions(SPCommon.getString("token",""),city,area)
                .compose(RxUtil.<BaseResponse<BaoXianModel>>rxSchedulerHelper())
                .compose(RxUtil.<BaoXianModel>handleResult())
                .subscribeWith(new CommonSubscriber<BaoXianModel>(mContext, true) {
                    @Override
                    protected void _onNext(BaoXianModel commonRes) {

                        if (commonRes != null) {
                            mView.isAgreementDeductions(commonRes);
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
    public void user_authPay(String token, String payType, String city, String area, String foregift, String foregift_protocol, String insurance, String insurance_protocol, String isNeedEquip,String memberFee) {
        addSubscribe(Api.createTBService().user_authPay(token,payType,city,area,foregift,foregift_protocol,insurance,insurance_protocol,isNeedEquip,memberFee)
                .compose(RxUtil.<BaseResponse<PayModel>>rxSchedulerHelper())
                .compose(RxUtil.<PayModel>handleResult())
                .subscribeWith(new CommonSubscriber<PayModel>(mContext, false) {
                    @Override
                    protected void _onNext(PayModel commonRes) {
                        if (commonRes != null) {
                            mView.user_authPay(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                }));
    }


}
