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
import com.liaocheng.suteng.myapplication.presenter.contract.BaoDiContent;
import com.liaocheng.suteng.myapplication.presenter.contract.BaoXianContent;

public class BaoDiPresenter extends RxPresenter<BaoDiContent.View> implements BaoDiContent.Presenter{
    @Override
    public void IdentityInfo(String token) {
        addSubscribe(Api.createTBService().auth_info(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<AuthBean>>rxSchedulerHelper())
                .compose(RxUtil.<AuthBean>handleResult())
                .subscribeWith(new CommonSubscriber<AuthBean>(mContext, false) {
                    @Override
                    protected void _onNext(AuthBean commonRes) {
                        if (commonRes != null) {
                            mView.IdentityInfo(commonRes);
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
    public void user_memberPay(String payType, String memberFee) {
        addSubscribe(Api.createTBService().user_memberPay(SPCommon.getString("token",""),payType,memberFee)
                .compose(RxUtil.<BaseResponse<PayModel>>rxSchedulerHelper())
                .compose(RxUtil.<PayModel>handleResult())
                .subscribeWith(new CommonSubscriber<PayModel>(mContext, true) {
                    @Override
                    protected void _onNext(PayModel commonRes) {

                        if (commonRes != null) {
                            mView.user_memberPay(commonRes);
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

}
