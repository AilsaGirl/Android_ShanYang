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
    public void addAddress( String locationX, String locationY, String area, String address) {
        addSubscribe(Api.createTBService().createaddress(SPCommon.getString("token",""), locationX, locationY, area, address)
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
