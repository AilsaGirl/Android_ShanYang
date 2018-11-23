package com.liaocheng.suteng.myapplication.presenter;

import android.util.Log;

import com.circle.common.base.RxPresenter;

import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.Zcxiybean;
import com.liaocheng.suteng.myapplication.presenter.contract.ZhuceContact;

public class Zhucexypresenter extends RxPresenter<ZhuceContact.View> implements ZhuceContact.Presenter{
    @Override
    public void getzhucexy(String code, String type) {
        addSubscribe(Api.createTBService().zcxieyi(code, type)
                .compose(RxUtil.<BaseResponse<Zcxiybean>>rxSchedulerHelper())
                .compose(RxUtil.<Zcxiybean>handleResult())
                .subscribeWith(new CommonSubscriber<Zcxiybean>(mContext, true) {
                    @Override
                    protected void _onNext(Zcxiybean commonRes) {

                        if (commonRes != null) {
                            mView.setzhucexy(commonRes);
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
