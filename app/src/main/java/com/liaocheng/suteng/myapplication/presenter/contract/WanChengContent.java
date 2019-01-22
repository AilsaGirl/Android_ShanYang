package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;

public interface WanChengContent {
    interface  View extends BaseView {
        void queryCompletedOrder(JieDanDaTingModel myBean);
        void queryHaveRefundOrder(JieDanDaTingModel myBean);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  queryCompletedOrder( String pageNo);
        void queryHaveRefundOrder(String pageNo);
    }
}
