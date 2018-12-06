package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.UpdatePhoneBean;
import com.liaocheng.suteng.myapplication.presenter.contract.UpdateOldPhoneContact;
import com.liaocheng.suteng.myapplication.presenter.contract.UpdatePhoneContact;

public class UpdateOldPhonePresenter extends RxPresenter<UpdateOldPhoneContact.View> implements UpdateOldPhoneContact.Presenter{
    @Override
    public void UpdateMiMa(String modifyCode) {
        addSubscribe(Api.createTBService().checkOldPhoneBySms(SPCommon.getString("token",""),modifyCode)
                .compose(RxUtil.<BaseResponse<UpdatePhoneBean>>rxSchedulerHelper())
                .compose(RxUtil.<UpdatePhoneBean>handleResult())
                .subscribeWith(new CommonSubscriber<UpdatePhoneBean>(mContext, true) {
                    @Override
                    protected void _onNext(UpdatePhoneBean commonRes) {

                        if (commonRes != null) {
                            mView.UpdateMiMa(commonRes);
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
    public void getcode(String telnumber) {
        addSubscribe(Api.createTBService().registcode(telnumber, "19")
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {
                        if (commonRes != null) {
                            mView.setcode(commonRes);
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
