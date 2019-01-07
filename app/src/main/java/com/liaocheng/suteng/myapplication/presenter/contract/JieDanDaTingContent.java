package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;

public interface JieDanDaTingContent {
    interface  View extends BaseView {
        void setOrder(JieDanDaTingModel myBean);
        void  updateTechnicianWorkStatus();
        void  order_grab();
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getOrder(String pageNo);
        void  updateTechnicianWorkStatus(String type);
        void  order_grab(String code);

    }
}
