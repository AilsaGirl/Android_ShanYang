package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.PayModel;


public interface IdentityPayContract {

    interface View extends BaseView {//结果
        void IdentityInfoSucss(AuthBean mBeaan);
        void user_authPay(PayModel payModel);
        void checkSecondPassword();
        void findAreaNeedFee(BaoXianModel model);
        void isAgreementDeductions(BaoXianModel model);
    }

    interface Presenter extends BasePresenter<View> {//过程

        void IdentityInfo(String token);
        void findAreaNeedFee(String city,String area);
        //提交身份信息
      // @Field("foregift") String foregift, @Field("foregift_protocol") String foregift_protocol, @Field("insurance") String insurance, @Field("insurance_protocol") String insurance_protocol, @Field("isNeedEquip") String isNeedEquip
void user_authPay(String token, String payType, String city, String area, String foregift, String foregift_protocol, String insurance, String insurance_protocol, String isNeedEquip, String memberFee);
        void checkSecondPassword(String secondPwd);
        void isAgreementDeductions(String city,String area);
    }
}
