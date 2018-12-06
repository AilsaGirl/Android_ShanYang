package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanBean;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanListBean;

public interface YouHuiQuanContent {
    interface  View extends BaseView {
        void setYouHuiQuan(YouHuiQuanBean quanBean);
        void setYouHuiQuanNum(YouHuiQuanBean quanBean);
        void  setYouHuiQuanList(YouHuiQuanListBean quanList);
        void giveCoupons();
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getYouHuiQuan(String pageNo);
        void  getYouHuiQuanNum( );
        void  getYouHuiQuanList(String pageNo);
        void  giveCoupons(String tel,String num);
    }
}
