package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;

/**
 * Created by LHP on 2018/9/3.
 */

public interface HomeContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
        void onCreate();
    }
}
