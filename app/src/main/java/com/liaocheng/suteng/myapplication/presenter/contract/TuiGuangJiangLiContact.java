package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.ExtensionDetailQueryBean;
import com.liaocheng.suteng.myapplication.model.PromoteDetailBean;

public interface TuiGuangJiangLiContact {
    interface  View extends BaseView {
        void setTuiGuang(ExtensionDetailQueryBean mBean);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void getTuiGuang(String page, String generation,String promotionType, String duration);
    }
}
