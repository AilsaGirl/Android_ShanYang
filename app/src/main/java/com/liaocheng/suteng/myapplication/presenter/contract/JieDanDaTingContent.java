package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;

public interface JieDanDaTingContent {
    interface  View extends BaseView {
        void setOrder(JieDanDaTingModel myBean);
        void  updateTechnicianWorkStatus();
        void  order_grab();
        void  setWorkTraffic();
        void queryTransferAndSpecificOrder(JieDanDaTingModel myBean);
        void executeSpecificOrder();
        void  executeTransferOrder();
        void  getCustomerTraffic(BaoXianModel baoXianModel);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getOrder(String pageNo,String trafficTool);
        void  updateTechnicianWorkStatus(String type);
        void  order_grab(String code);
        void  setWorkTraffic(String type);
        void  queryTransferAndSpecificOrder();
        void executeSpecificOrder(String code, String type);
        void  executeTransferOrder(String code, String type);
        void  getCustomerTraffic();
    }
}
