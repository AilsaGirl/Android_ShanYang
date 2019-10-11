package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.XinWenModel;

public interface HeiMingDanContent {
    interface  View extends BaseView {

        void removeBlackList();
        void getBlackList(GongNengModel nengModel);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void removeBlackList(String id);
        void getBlackList();

    }
}
