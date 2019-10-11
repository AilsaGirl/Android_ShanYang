package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.ShouYiModel;
import com.liaocheng.suteng.myapplication.model.TeamModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;

public interface TuanDuiContent {
    interface  View extends BaseView {
        void getLevel(BaoXianModel model);
        void getTeamMoney(ShouYiModel model);
        void getTeamUserDetail(TeamModel nengModel);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getTeamUserDetail(String pageNo);
        void getTeamMoney(String pageNo);
        void getLevel();

    }
}
