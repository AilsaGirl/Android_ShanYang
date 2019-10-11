package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.PayModel;

public interface BaoDiContent {
    interface  View extends BaseView {

        void user_memberPay(PayModel payModel);
        void checkSecondPassword();
        void IdentityInfo(AuthBean authBean);
    }
    interface Presenter extends BasePresenter<View> {//过程

        void user_memberPay(String payType, String memberFee);
        void checkSecondPassword(String secondPwd);
        void IdentityInfo(String token);
    }
}
