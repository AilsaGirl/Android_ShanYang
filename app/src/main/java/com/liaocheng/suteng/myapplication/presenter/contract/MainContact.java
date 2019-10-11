package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.MainModel;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.NoticeModel;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;
import com.liaocheng.suteng.myapplication.model.VersionModel;

public interface MainContact {
    interface  View extends BaseView {
        void setNotice(NoticeModel noticeBean);
        void setBanner(MainModel mBean);
        void IdentityInfoSucss(MyBean authBean);
        void updateLocation();
        void appVersion_info(VersionModel model);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getNotice(String area);
        void getBanner(String area);
        void IdentityInfo(String token);
        void updateLocation(String token,String lat,String lon);
        void appVersion_info();
    }
}
