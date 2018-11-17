package com.liaocheng.suteng.myapplication.presenter.contract;


import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.NullBean;


/**
 * Created by wei on 2018/1/11.
 */

public interface LoginContact {
    interface View extends BaseView {//结果

        void loginSuccess(NullBean loginBean);
    }

    interface Presenter extends BasePresenter<View> {//过程
        void  login(String username, String password);
    }

}
