package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;

public interface AboutContent {
    interface  View extends BaseView {
        void getSanYangNews(XinWenModel model);
        void getSanYangRule(GuiZeModel model);
        void appUpGrade_info(GongNengModel nengModel);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getSanYangNews(String pageNo);
        void getSanYangRule(String pageNo);
        void appUpGrade_info();

    }
}
