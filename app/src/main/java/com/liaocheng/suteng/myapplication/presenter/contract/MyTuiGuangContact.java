package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.MyTeaBean;
import com.liaocheng.suteng.myapplication.model.MyTuiGuangBean;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;

public interface MyTuiGuangContact {
    interface  View extends BaseView {
        void setTuiGuang(MyTuiGuangBean mBean);
        void setMyTea(MyTeaBean myBean);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void getTuiGuang(String city,String qu);
        void  getMyTea();

    }
}
