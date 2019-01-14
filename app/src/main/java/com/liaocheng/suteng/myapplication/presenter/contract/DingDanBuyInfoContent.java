package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.PayModel;

public interface DingDanBuyInfoContent {
    interface  View extends BaseView {
        void setDingDa(DingDanBuyInfoModel DingDanBean);
        void order_cancell();
        void  order_pay(PayModel model);
        void user_order_refund();
        void  addOrderTip(PayModel model);
        void order_grab();
        void order_submit();
        void order_revoke();
        void courier_order_submit();
        void checkReceiveCode();
        void order_refund();
        void getThePickup();
        void executeSpecificOrder();
        void executeTransferOrder();
        void transferOrder();
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getDingDa(String code);
        void  order_cancell(String code);
        void  order_pay(String code,String type);
        void user_order_refund(String code);
        void  addOrderTip(String code,String type);
        void order_grab(String code);
        void order_submit(String code);
        void order_revoke(String code);
        void  courier_order_submit(String code);
        void checkReceiveCode(String code,String receiveCode);
        void order_refund(String code);
        void getThePickup(String code);
        void executeSpecificOrder(String code,String type);
        void executeTransferOrder(String code,String type);
        void transferOrder(String code,String phone);

    }
}
