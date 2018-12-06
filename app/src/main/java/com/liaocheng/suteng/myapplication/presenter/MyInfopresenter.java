package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.Zcxiybean;
import com.liaocheng.suteng.myapplication.presenter.contract.MyContact;
import com.liaocheng.suteng.myapplication.presenter.contract.ZhuceContact;

public class MyInfopresenter extends RxPresenter<MyContact.View> implements MyContact.Presenter{
    @Override
    public void getMyInfo() {
        addSubscribe(Api.createTBService().user_info(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<MyBean>>rxSchedulerHelper())
                .compose(RxUtil.<MyBean>handleResult())
                .subscribeWith(new CommonSubscriber<MyBean>(mContext, true) {
                    @Override
                    protected void _onNext(MyBean commonRes) {

                        if (commonRes != null) {
                            mView.setMyInfo(commonRes);
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
