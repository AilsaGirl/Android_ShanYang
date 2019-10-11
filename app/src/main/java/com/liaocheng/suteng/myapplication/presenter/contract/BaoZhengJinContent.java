package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;

public interface BaoZhengJinContent {
    interface  View extends BaseView {
        void user_authRelieve();
        void user_cancellRelieve();
        void repayTheArrears(PayModel payModel);
        void findAreaNeedFee(BaoXianModel model);

    }
    interface Presenter extends BasePresenter<View> {//过程
        void  user_authRelieve();
        void user_cancellRelieve();
        void repayTheArrears(String type,String pass);
        void findAreaNeedFee(String city, String area);

    }
}
