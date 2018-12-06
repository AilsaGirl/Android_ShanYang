package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.contract.UpdateMiMaContact;
import com.liaocheng.suteng.myapplication.presenter.contract.UpdatePhoneMiMaContact;

public class UpdatePhoneMiMaPresenter extends RxPresenter<UpdatePhoneMiMaContact.View> implements UpdatePhoneMiMaContact.Presenter{
    @Override
    public void UpdateMiMa(String oldpass,String newpass) {
        addSubscribe(Api.createTBService().user_updateSecondpwdBySms(SPCommon.getString("token",""),oldpass,newpass)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

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
        addSubscribe(Api.createTBService().registcode(telnumber, "18")
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
