package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.NullBean;

public interface ForgetmimaContact {
    interface  View extends BaseView {
        void setforget(NullBean nullBean);
        void  setforcode(NullBean nullBean);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getforgetpassword(String telnumber, String password,String code);
        void  getforgetcode(String number,String code);
    }

}
