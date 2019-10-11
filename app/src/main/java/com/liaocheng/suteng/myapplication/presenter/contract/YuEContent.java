package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;
import com.liaocheng.suteng.myapplication.model.YuEMingXiBean;

public interface YuEContent {
    interface  View extends BaseView {
        void user_residumoneyDetail(YuEMingXiBean model);
        void user_dealmoneyDetail(YuEMingXiBean model);

    }
    interface Presenter extends BasePresenter<View> {//过程
        void  user_residumoneyDetail(String pageNo);
        void user_dealmoneyDetail(String pageNo);


    }
}
