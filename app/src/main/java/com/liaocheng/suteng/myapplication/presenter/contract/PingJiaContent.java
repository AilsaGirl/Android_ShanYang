package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.PingJiaModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;

public interface PingJiaContent {
    interface  View extends BaseView {
        void getCommentLabel(PingJiaModel model);
        void insertComment();
    }
    interface Presenter extends BasePresenter<View> {//过程

        void insertComment(String eva_userId,String comments,String level,String orderCode);
        void getCommentLabel();

    }
}
