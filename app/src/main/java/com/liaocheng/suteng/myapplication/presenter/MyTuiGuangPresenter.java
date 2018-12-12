package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.MyTeaBean;
import com.liaocheng.suteng.myapplication.model.MyTuiGuangBean;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;
import com.liaocheng.suteng.myapplication.presenter.contract.MyContact;
import com.liaocheng.suteng.myapplication.presenter.contract.MyTuiGuangContact;

public class MyTuiGuangPresenter extends RxPresenter<MyTuiGuangContact.View> implements MyTuiGuangContact.Presenter{

    @Override
    public void getMyTea() {
        addSubscribe(Api.createTBService().inviteUser_Info(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<MyTeaBean>>rxSchedulerHelper())
                .compose(RxUtil.<MyTeaBean>handleResult())
                .subscribeWith(new CommonSubscriber<MyTeaBean>(mContext, true) {
                    @Override
                    protected void _onNext(MyTeaBean commonRes) {

                        if (commonRes != null) {
                            mView.setMyTea(commonRes);
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
    public void getTuiGuang(String city,String qu) {
        addSubscribe(Api.createTBService().extensionSumMoneyQuery(SPCommon.getString("token",""),city,qu)
                .compose(RxUtil.<BaseResponse<MyTuiGuangBean>>rxSchedulerHelper())
                .compose(RxUtil.<MyTuiGuangBean>handleResult())
                .subscribeWith(new CommonSubscriber<MyTuiGuangBean>(mContext, true) {
                    @Override
                    protected void _onNext(MyTuiGuangBean commonRes) {

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
