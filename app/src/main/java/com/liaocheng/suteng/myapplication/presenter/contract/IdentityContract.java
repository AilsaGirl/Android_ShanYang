package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.circle.common.response.CommonRes;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;


public interface IdentityContract {

    interface View extends BaseView {//结果
        void delIdentitySucss();
        void IdentitySucss();
        void IdentityInfoSucss(AuthBean mBeaan);
        void zhiMaAuth(BaoXianModel baoXianModel);
        void user_authOk();
    }

    interface Presenter extends BasePresenter<View> {//过程
        void delIdentity(String token);
        void IdentityInfo(String token);
        void zhiMaAuth(String realName,String idCode);
        //提交身份信息
        void Identity(String token, String name, String phone, String idCode, String idPhoto1, String idPhoto2, String idPhoto3, String sex, String ethnic, String address, String organ, String valiaity);
        void user_authOk(String token);
    }
}
