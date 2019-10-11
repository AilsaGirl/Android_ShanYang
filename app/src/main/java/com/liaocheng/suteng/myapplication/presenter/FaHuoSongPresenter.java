package com.liaocheng.suteng.myapplication.presenter;


import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.ChangYongAddressBean;
import com.liaocheng.suteng.myapplication.model.MyAddressInfoBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.contract.FaHuoSongContact;
import com.liaocheng.suteng.myapplication.presenter.contract.SiteContact;


/**
 * Created by Administrator
 */

public class FaHuoSongPresenter extends RxPresenter<FaHuoSongContact.View> implements FaHuoSongContact.Presenter {
    @Override
    public void addressListContact(String token,String page) {
        addSubscribe(Api.createTBService().addressListContact(token,page)
                .compose(RxUtil.<BaseResponse<ChangYongAddressBean>>rxSchedulerHelper())
                .compose(RxUtil.<ChangYongAddressBean>handleResult())
                .subscribeWith(new CommonSubscriber<ChangYongAddressBean>(mContext, true) {
                    @Override
                    protected void _onNext(ChangYongAddressBean commonRes) {
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
    public void getMyAddressList(String token) {
        addSubscribe(Api.createTBService().myAddressInfo(token)
                .compose(RxUtil.<BaseResponse<MyAddressInfoBean>>rxSchedulerHelper())
                .compose(RxUtil.<MyAddressInfoBean>handleResult())
                .subscribeWith(new CommonSubscriber<MyAddressInfoBean>(mContext, true) {
                    @Override
                    protected void _onNext(MyAddressInfoBean commonRes) {
                        if (commonRes != null) {
                            mView.setMyAddressList(commonRes);
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
