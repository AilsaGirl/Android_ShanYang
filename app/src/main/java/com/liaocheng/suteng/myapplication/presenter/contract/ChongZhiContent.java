package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.ChongZhiModel;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;

public interface ChongZhiContent {
    interface  View extends BaseView {
        void isFirstChargedDealMoney(ChongZhiModel chongZhiModel);
        void user_recharge(PayModel mBean);//充值提现余额
        void user_rechargeDealMoney(PayModel mBean);//充值发货余额
        void getAddType(ChongZhiModel chongZhiModel);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  isFirstChargedDealMoney();
        void user_recharge(String rechargeMoney, String rechargeType);
        void user_rechargeDealMoney(String rechargeMoneyType, String rechargeType);
        void getAddType(String area, String city);
    }
}
