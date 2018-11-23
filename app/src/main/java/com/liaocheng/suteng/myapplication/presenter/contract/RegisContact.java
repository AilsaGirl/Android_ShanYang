package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.NullBean;


public interface RegisContact {
    interface  View extends BaseView{
        void setcode(NullBean RegistCodeBean);
        void setuserregister(NullBean nullBean);

    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getcode(String telnumber, String type);
        void  getuserZhuce(String phone, String password, String otherInviteCode, String messageCode);
    }
}
