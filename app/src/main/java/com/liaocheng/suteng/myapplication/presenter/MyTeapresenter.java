package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.MyTeaBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.contract.MyContact;
import com.liaocheng.suteng.myapplication.presenter.contract.MyTeaContact;

public class MyTeapresenter extends RxPresenter<MyTeaContact.View> implements MyTeaContact.Presenter{
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
    public void changeUserRadio(String turn) {
        addSubscribe(Api.createTBService().changeUserRadio(SPCommon.getString("token",""),turn)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.changeUserRadio();
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
    public void exit() {
        addSubscribe(Api.createTBService().user_exit(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.exit();
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
