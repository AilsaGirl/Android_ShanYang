package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.ReceiveOrderModel;

public interface QuHuoContent {
    interface  View extends BaseView {
        void queryReceiveOrder(ReceiveOrderModel ReceiveOrderBean);
        void getThePickup();//到店取货
        void order_refund();//返回用户
        void order_revoke();
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  queryReceiveOrder(String page);
        void getThePickup(String code);
        void order_refund(String code);
        void order_revoke(String code);
    }
}
