package com.liaocheng.suteng.myapplication.presenter;


import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.contract.LoginContact;

/**
 * Created by wei on 2018/1/11 0028.
 * 各种繁杂过程    接口请求等
 */

public class LoginPresenter extends RxPresenter<LoginContact.View> implements LoginContact.Presenter {


    @Override
    public void login(String username, String password) {

        addSubscribe(Api.createTBService().updateUserAddress(SPCommon.getString("token",""),"4041","你好啊","17763515228","122","333","聊城","金鼎大厦","")
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {
                        if (commonRes != null) {
                            mView.loginSuccess(commonRes);
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
    public void logins(String username, String password) {
        addSubscribe(Api.createTBService().findOldOrderAddress("10012102","0")
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {
                        if (commonRes != null) {
                            mView.loginSuc(commonRes);
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
