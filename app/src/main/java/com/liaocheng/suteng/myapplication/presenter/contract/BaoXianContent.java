package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.PayModel;

public interface BaoXianContent {
    interface  View extends BaseView {

        void user_insurancePay(PayModel payModel);
        void checkSecondPassword();
        void findAreaInsurance(BaoXianModel baoXianModel);
        void IdentityInfo(AuthBean authBean);
    }
    interface Presenter extends BasePresenter<View> {//过程

        void user_insurancePay(String type, String city, String area);
        void checkSecondPassword(String secondPwd);
        void findAreaInsurance( String city, String area);
        void IdentityInfo(String token);
    }
}
