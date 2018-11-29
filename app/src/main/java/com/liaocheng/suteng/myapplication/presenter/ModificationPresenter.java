package com.liaocheng.suteng.myapplication.presenter;


import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.contract.ModificationContact;


/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class ModificationPresenter extends RxPresenter<ModificationContact.View> implements ModificationContact.Presenter {
    @Override
    public void updateAddress(String id, String contactName, String contactPhone, String accuracy, String latitude, String address, String detailAddress,String concreteAddress) {
        addSubscribe(Api.createTBService().updateUserAddress( SPCommon.getString("token",""),id,  contactName,  contactPhone,  accuracy,  latitude,  address,  detailAddress,concreteAddress)
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
    public void addAddress( String name, String tel,String locationX, String locationY, String area, String address,String addressType,String concreteAddress) {
        addSubscribe(Api.createTBService().addNewAddress(SPCommon.getString("token",""),name,tel, locationX, locationY, area, address,addressType,concreteAddress)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber(mContext, true) {
                    @Override
                    protected void _onNext(Object o) {
                        mView.AddSuccess();
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }


}
