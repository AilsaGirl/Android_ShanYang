package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.PayModel;

public interface DingDanInfoContent {
    interface  View extends BaseView {
        void setDingDa(DingDanBuyInfoModel DingDanBean);
        void order_grab();
        void transferOrder();
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getDingDa(String code);
        void order_grab(String code);
        void transferOrder(String code, String phone);

    }
}
