package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.DingDanWeiZhiBean;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;

public interface DingDanWeiZhiContent {
    interface  View extends BaseView {
        void getCoordByOrderCode(DingDanWeiZhiBean myBean);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getCoordByOrderCode(String orderCode);
    }
}
