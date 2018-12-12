package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.MainModel;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.NoticeModel;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;
import com.liaocheng.suteng.myapplication.presenter.contract.MainContact;
import com.liaocheng.suteng.myapplication.presenter.contract.MyContact;

public class MainPresenter extends RxPresenter<MainContact.View> implements MainContact.Presenter{

    @Override
    public void getNotice(String area) {
        addSubscribe(Api.createTBService().getNotice(SPCommon.getString("token",""),area)
                .compose(RxUtil.<BaseResponse<NoticeModel>>rxSchedulerHelper())
                .compose(RxUtil.<NoticeModel>handleResult())
                .subscribeWith(new CommonSubscriber<NoticeModel>(mContext, true) {
                    @Override
                    protected void _onNext(NoticeModel commonRes) {

                        if (commonRes != null) {
                            mView.setNotice(commonRes);
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
    public void getBanner(String area) {
        addSubscribe(Api.createTBService().getBanner(SPCommon.getString("token",""),area)
                .compose(RxUtil.<BaseResponse<MainModel>>rxSchedulerHelper())
                .compose(RxUtil.<MainModel>handleResult())
                .subscribeWith(new CommonSubscriber<MainModel>(mContext, true) {
                    @Override
                    protected void _onNext(MainModel commonRes) {

                        if (commonRes != null) {
                            mView.setBanner(commonRes);
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
