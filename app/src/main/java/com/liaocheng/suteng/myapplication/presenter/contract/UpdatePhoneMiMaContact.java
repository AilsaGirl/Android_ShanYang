package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.NullBean;

public interface UpdatePhoneMiMaContact {
    interface  View extends BaseView {
        void UpdateMiMa(NullBean nullBean);
        void setcode(NullBean nullBean);

    }
    interface Presenter extends BasePresenter<View> {//过程
        void  UpdateMiMa(String ems, String newpass);
        void  getcode(String telnumber);
    }
}
