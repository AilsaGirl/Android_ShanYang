package com.liaocheng.suteng.myapplication.presenter;
import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.response.CommonRes;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.contract.IdentityContract;


public class IdentityPresenter extends RxPresenter<IdentityContract.View> implements IdentityContract.Presenter {

    @Override
    public void delIdentity(String token) {

    }

    @Override
    public void IdentityInfo(String token) {
        addSubscribe(Api.createTBService().auth_info(token)
                .compose(RxUtil.<BaseResponse<AuthBean>>rxSchedulerHelper())
                .compose(RxUtil.<AuthBean>handleResult())
                .subscribeWith(new CommonSubscriber<AuthBean>(mContext, false) {
                    @Override
                    protected void _onNext(AuthBean commonRes) {
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
    public void Identity(String token, String name, String phone, String idCode, String idPhoto1, String idPhoto2, String idPhoto3) {
        addSubscribe(Api.createTBService().user_addAuth(token, name, phone, idCode, idPhoto1, idPhoto2, idPhoto3)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, false) {
                    @Override
                    protected void _onNext(NullBean commonRes) {
                        if (commonRes != null) {
                            mView.IdentitySucss();
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
}
