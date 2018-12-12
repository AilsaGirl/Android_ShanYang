package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;
import com.liaocheng.suteng.myapplication.model.Zcxiybean;

public interface MyContact {
    interface  View extends BaseView {
        void setMyInfo(MyBean myBean);
        void setKeFu(ServiceCenterBean mBean);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getMyInfo();
        void getKeFu(String qu);
    }
}
