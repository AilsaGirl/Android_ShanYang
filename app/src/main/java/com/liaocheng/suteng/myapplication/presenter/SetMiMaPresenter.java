package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.contract.MyContact;
import com.liaocheng.suteng.myapplication.presenter.contract.SetMiMaContact;

public class SetMiMaPresenter extends RxPresenter<SetMiMaContact.View> implements SetMiMaContact.Presenter{
    @Override
    public void getMiMa(String pass) {
        addSubscribe(Api.createTBService().user_setSecondPassword(SPCommon.getString("token",""),pass)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.setMiMa(commonRes);
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
