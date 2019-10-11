package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;

public interface BangDingContent {
    interface  View extends BaseView {
        void setCode();
        void userBindThird(ThirdLoginModel mBean);
    }
    interface Presenter extends BasePresenter<View> {//过程
        void  getCode(String phone, String type);
        void userBindThird(String type,String code,String phone,String nickName,String headImg,String messageCode);
    }
}
