package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.JiJiaBiaoZhunBean;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;

public interface JiJiaBiaoZhunContent {
    interface  View extends BaseView {
        void findAreaPriceDetail(JiJiaBiaoZhunBean jiaBiaoZhunBean);
    }
    interface Presenter extends BasePresenter<View> {//过程

        void findAreaPriceDetail(String type, String lat, String lon);
    }
}
