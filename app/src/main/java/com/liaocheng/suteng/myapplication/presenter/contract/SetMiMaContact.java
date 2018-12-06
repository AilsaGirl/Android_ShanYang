package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.NullBean;

public interface SetMiMaContact {
    interface  View extends BaseView {
        void setMiMa(NullBean nullBean);

    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getMiMa(String pass);
    }
}
