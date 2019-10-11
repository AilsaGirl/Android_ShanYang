package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.Zcxiybean;

public interface ZhuceContact {
    interface  View extends BaseView {
        void setzhucexy(Zcxiybean zcxiybean);

    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getzhucexy(String code);
    }
}
