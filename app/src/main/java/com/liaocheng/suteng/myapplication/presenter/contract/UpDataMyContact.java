package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;

public interface UpDataMyContact {
    interface  View extends BaseView {
        void setName();
        void setImg();
    }
    interface Presenter extends BasePresenter<View> {//过程

        void getName(String qu);
        void getImg(String img);
    }
}
