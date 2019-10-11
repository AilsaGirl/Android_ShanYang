package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.MainModel;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.NoticeModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;
import com.liaocheng.suteng.myapplication.model.VersionModel;
import com.liaocheng.suteng.myapplication.presenter.contract.MainContact;
import com.liaocheng.suteng.myapplication.presenter.contract.MyContact;

public class MainPresenter extends RxPresenter<MainContact.View> implements MainContact.Presenter{
    @Override
    public void IdentityInfo(String token) {
        addSubscribe(Api.createTBService().user_info(token)
                .compose(RxUtil.<BaseResponse<MyBean>>rxSchedulerHelper())
                .compose(RxUtil.<MyBean>handleResult())
                .subscribeWith(new CommonSubscriber<MyBean>(mContext, false) {
                    @Override
                    protected void _onNext(MyBean commonRes) {
                        if (commonRes != null) {
                            mView.IdentityInfoSucss(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                }));
    }

    @Override
    public void updateLocation(String token, String lat, String lon) {
        addSubscribe(Api.createTBService().updateLocation(token,lat,lon)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, false) {
                    @Override
                    protected void _onNext(NullBean commonRes) {
                        if (commonRes != null) {
                            mView.updateLocation();
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                }));
    }

    @Override
    public void appVersion_info() {
        addSubscribe(Api.createTBService().appVersion_info()
                .compose(RxUtil.<BaseResponse<VersionModel>>rxSchedulerHelper())
                .compose(RxUtil.<VersionModel>handleResult())
                .subscribeWith(new CommonSubscriber<VersionModel>(mContext, false) {
                    @Override
                    protected void _onNext(VersionModel commonRes) {
                        if (commonRes != null) {
                            mView.appVersion_info(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                }));
    }

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
