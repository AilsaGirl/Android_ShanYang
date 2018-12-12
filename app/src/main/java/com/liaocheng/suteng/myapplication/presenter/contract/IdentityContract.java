package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.circle.common.response.CommonRes;
import com.liaocheng.suteng.myapplication.model.AuthBean;


public interface IdentityContract {

    interface View extends BaseView {//结果
        void delIdentitySucss();
        void IdentitySucss();
        void IdentityInfoSucss(AuthBean mBeaan);
    }

    interface Presenter extends BasePresenter<View> {//过程
        void delIdentity(String token);
        void IdentityInfo(String token);
        //提交身份信息
        void Identity(String token, String name, String phone, String idCode, String idPhoto1, String idPhoto2, String idPhoto3);
//
    }
}
