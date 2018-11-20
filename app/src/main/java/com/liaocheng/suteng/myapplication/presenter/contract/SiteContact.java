package com.liaocheng.suteng.myapplication.presenter.contract;


import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.SiteBean;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public interface SiteContact {
    public interface View extends BaseView {//结果

        void AddressListContactSuccess(SiteBean siteBean);
        void selSuccess();

    }

    public interface Presenter extends BasePresenter<View> {//过程

        void addressListContact(String token, String page);
        void delAddress(String id);
    }
}
