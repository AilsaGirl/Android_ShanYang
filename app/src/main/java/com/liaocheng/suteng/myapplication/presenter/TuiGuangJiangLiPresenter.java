package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.ExtensionDetailQueryBean;
import com.liaocheng.suteng.myapplication.model.PromoteDetailBean;
import com.liaocheng.suteng.myapplication.presenter.contract.TuiGuangJiangLiContact;
import com.liaocheng.suteng.myapplication.presenter.contract.TuiGuangRenContact;

public class TuiGuangJiangLiPresenter extends RxPresenter<TuiGuangJiangLiContact.View> implements TuiGuangJiangLiContact.Presenter{


    @Override
    public void getTuiGuang(String page, String generation,String promotionType, String duration) {
        addSubscribe(Api.createTBService().extensionDetailQuery(SPCommon.getString("token",""),page,generation,promotionType,duration)
                .compose(RxUtil.<BaseResponse<ExtensionDetailQueryBean>>rxSchedulerHelper())
                .compose(RxUtil.<ExtensionDetailQueryBean>handleResult())
                .subscribeWith(new CommonSubscriber<ExtensionDetailQueryBean>(mContext, true) {
                    @Override
                    protected void _onNext(ExtensionDetailQueryBean commonRes) {

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
