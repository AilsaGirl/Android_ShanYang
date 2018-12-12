package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.MyTeaBean;
import com.liaocheng.suteng.myapplication.model.MyTuiGuangBean;
import com.liaocheng.suteng.myapplication.model.PromoteDetailBean;

public interface TuiGuangRenContact {
    interface  View extends BaseView {
        void setTuiGuang(PromoteDetailBean mBean);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void getTuiGuang(String page, String generation);
    }
}
