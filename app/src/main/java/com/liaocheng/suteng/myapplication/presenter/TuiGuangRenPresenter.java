package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.MyTeaBean;
import com.liaocheng.suteng.myapplication.model.MyTuiGuangBean;
import com.liaocheng.suteng.myapplication.model.PromoteDetailBean;
import com.liaocheng.suteng.myapplication.presenter.contract.MyTuiGuangContact;
import com.liaocheng.suteng.myapplication.presenter.contract.TuiGuangRenContact;

public class TuiGuangRenPresenter extends RxPresenter<TuiGuangRenContact.View> implements TuiGuangRenContact.Presenter{


    @Override
    public void getTuiGuang(String page, String generation) {
        addSubscribe(Api.createTBService().promoteDetail(SPCommon.getString("token",""),page,generation)
                .compose(RxUtil.<BaseResponse<PromoteDetailBean>>rxSchedulerHelper())
                .compose(RxUtil.<PromoteDetailBean>handleResult())
                .subscribeWith(new CommonSubscriber<PromoteDetailBean>(mContext, true) {
                    @Override
                    protected void _onNext(PromoteDetailBean commonRes) {

                        if (commonRes != null) {
                            mView.setTuiGuang(commonRes);
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
