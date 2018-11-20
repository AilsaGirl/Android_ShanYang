package com.liaocheng.suteng.myapplication.presenter;


import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.SiteBean;
import com.liaocheng.suteng.myapplication.presenter.contract.SiteContact;


/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class SitePresenter extends RxPresenter<SiteContact.View> implements SiteContact.Presenter {
    @Override
    public void addressListContact(String token,String page) {
        addSubscribe(Api.createTBService().addressListContact(token,page,"10")
                .compose(RxUtil.<BaseResponse<SiteBean>>rxSchedulerHelper())
                .compose(RxUtil.<SiteBean>handleResult())
                .subscribeWith(new CommonSubscriber<SiteBean>(mContext, true) {
                    @Override
                    protected void _onNext(SiteBean commonRes) {
                        if (commonRes != null) {
                            mView.AddressListContactSuccess(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(1, message);
                    }
                })
        );
    }

    @Override
    public void delAddress(String id) {
        addSubscribe(Api.createTBService().delddress(SPCommon.getString("token",""),id)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {
                        if (commonRes != null) {
                            mView.selSuccess();
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(1, message);
                    }
                })
        );
    }

}
