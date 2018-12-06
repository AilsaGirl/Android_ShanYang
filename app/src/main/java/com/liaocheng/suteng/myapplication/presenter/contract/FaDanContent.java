package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;

public interface FaDanContent {
    interface  View extends BaseView {
        void setMySendOrder(MySendOrdersBean myBean);
        void setTotel(MySendOrdersBean myBean);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getMySendOrder(String duration, String pageNo);
        void getTotel(String duration);
    }
}
