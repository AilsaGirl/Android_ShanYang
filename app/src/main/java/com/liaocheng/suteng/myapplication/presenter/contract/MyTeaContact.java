package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.MyTeaBean;

public interface MyTeaContact {
    interface  View extends BaseView {
        void setMyTea(MyTeaBean myBean);
        void  changeUserRadio();
        void  exit();
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getMyTea();
        void  changeUserRadio(String turn);
        void  exit();
    }
}
