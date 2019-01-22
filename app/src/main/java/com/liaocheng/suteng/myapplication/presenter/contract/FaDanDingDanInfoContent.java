package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.PayModel;

public interface FaDanDingDanInfoContent {
    interface  View extends BaseView {
        void setDingDa(DingDanBuyInfoModel DingDanBean);
        void order_submit();
        void addOrderTip(PayModel model);
        void   user_order_refund();
        void  addBlacklist();
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getDingDa(String code);
        void order_submit(String code);
        void addOrderTip(String code, String type);
        void  user_order_refund(String code);
        void  addBlacklist(String id);
    }
}
