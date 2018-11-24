package com.liaocheng.suteng.myapplication.presenter;


import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.MyAddressInfoBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.SiteBean;
import com.liaocheng.suteng.myapplication.presenter.contract.SiteContact;


/**
 * Created by Administrator
 */

public class SitePresenter extends RxPresenter<SiteContact.View> implements SiteContact.Presenter {
    @Override
    public void addressListContact(String token,String page) {
        addSubscribe(Api.createTBService().addressListContact(token,page)
                .compose(RxUtil.<BaseResponse<MyAddressInfoBean>>rxSchedulerHelper())
                .compose(RxUtil.<MyAddressInfoBean>handleResult())
                .subscribeWith(new CommonSubscriber<MyAddressInfoBean>(mContext, true) {
                    @Override
                    protected void _onNext(MyAddressInfoBean commonRes) {
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

    @Override
    public void updateAddress(String id, String contactName, String contactPhone, String accuracy, String latitude, String address, String detailAddress) {
        addSubscribe(Api.createTBService().updateUserAddress("", id,  contactName,  contactPhone,  accuracy,  latitude,  address,  detailAddress)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {
                        if (commonRes != null) {
                            mView.updateSuccess();
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
    public void addNewAddress(String token, String contactName, String contactPhone, String accuracy, String latitude, String address, String detailAddress) {
        addSubscribe(Api.createTBService().addNewAddress( token,  contactName,  contactPhone,  accuracy,  latitude,  address,  detailAddress,"")
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {
                        if (commonRes != null) {
                            mView.addNewAddresselSuccess();
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
