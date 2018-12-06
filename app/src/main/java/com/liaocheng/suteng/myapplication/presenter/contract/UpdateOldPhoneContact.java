package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.UpdatePhoneBean;

public interface UpdateOldPhoneContact {
    interface  View extends BaseView {
        void UpdateMiMa(UpdatePhoneBean nullBean);
        void setcode(NullBean nullBean);

    }
    interface Presenter extends BasePresenter<View> {//过程
        void  UpdateMiMa(String ems);
        void  getcode(String telnumber);
    }
}
