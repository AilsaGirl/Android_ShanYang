package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;
import com.liaocheng.suteng.myapplication.presenter.contract.AboutContent;
import com.liaocheng.suteng.myapplication.presenter.contract.BaoZhengJinContent;

public class BaoZhengJinPresenter extends RxPresenter<BaoZhengJinContent.View> implements BaoZhengJinContent.Presenter{
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
    public void user_authRelieve() {
        addSubscribe(Api.createTBService().user_authRelieve(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.user_authRelieve();
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
    public void user_cancellRelieve() {
        addSubscribe(Api.createTBService().user_cancellRelieve(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.user_cancellRelieve();
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
    public void repayTheArrears(String type, String pass) {
        addSubscribe(Api.createTBService().repayTheArrears(SPCommon.getString("token",""),type,pass)
                .compose(RxUtil.<BaseResponse<PayModel>>rxSchedulerHelper())
                .compose(RxUtil.<PayModel>handleResult())
                .subscribeWith(new CommonSubscriber<PayModel>(mContext, true) {
                    @Override
                    protected void _onNext(PayModel commonRes) {

                        if (commonRes != null) {
                            mView.repayTheArrears(commonRes);
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
