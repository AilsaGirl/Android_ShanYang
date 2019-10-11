package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.ReceiveOrderModel;

public interface SongHuoContent {
    interface  View extends BaseView {
        void queryOnTheWayOrder(ReceiveOrderModel ReceiveOrderBean);
        void checkReceiveCode();//确认短信
        void order_upPhotoByIndex();
        void  queryOrder();
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  queryOnTheWayOrder(String page);
        void checkReceiveCode(String code, String receiveCode);
        void order_upPhotoByIndex(String code,String goodsImg,String index);
        void  queryOrder(String code);
    }
}
