package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;

/**
 * Created by LHB on 2018/7/23 0023.
 */

public interface FaHuoContact {
    interface View extends BaseView {//结果
//        void loginSuccess(LoginBean loginBean);
        void setData();

    }
    interface Presenter extends BasePresenter<View> {//过程
        void detail(String token, String jingdu, String weidu);
    }
}
