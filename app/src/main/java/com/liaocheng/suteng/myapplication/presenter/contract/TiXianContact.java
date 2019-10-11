package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.NullBean;


public interface TiXianContact {
    interface  View extends BaseView{
        void setcode(NullBean RegistCodeBean);
        void setTiXian(NullBean nullBean);

    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getcode(String telnumber, String type);
        void  user_withdraw(String secondPwd, String withdrawAccount, String withdrawName, String withdrawMoney, String messageCode);
    }
}
