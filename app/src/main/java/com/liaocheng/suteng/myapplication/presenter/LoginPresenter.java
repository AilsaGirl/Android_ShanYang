package com.liaocheng.suteng.myapplication.presenter;


/**
 * Created by wei on 2018/1/11 0028.
 * 各种繁杂过程    接口请求等
 */

public class LoginPresenter {


//    @Override
//    public void login(String username, String password) {
//
//        addSubscribe(Api.createService().login(username,password)
//                .compose(RxUtil.<BaseResponse<LoginBean>>rxSchedulerHelper())
//                .compose(RxUtil.<LoginBean>handleResult())
//                .subscribeWith(new CommonSubscriber<LoginBean>(mContext, true) {
//                    @Override
//                    protected void _onNext(LoginBean commonRes) {
//                        if (commonRes != null) {
//                            mView.loginSuccess(commonRes);
//                        } else {
//                            mView.showError(0, "");
//                        }
//                    }
//                    @Override
//                    protected void _onError(String message) {
//                        mView.showError(0, message);
//                    }
//                })
//        );
//
//    }


}
