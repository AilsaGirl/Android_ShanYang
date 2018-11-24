package com.liaocheng.suteng.myapplication.presenter.contract;


import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.MyAddressInfoBean;
import com.liaocheng.suteng.myapplication.model.SiteBean;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public interface SiteContact {
    public interface View extends BaseView {//结果

        void AddressListContactSuccess(MyAddressInfoBean siteBean);
        void setMyAddressList(MyAddressInfoBean siteBean);
        void updateSuccess();
        void addNewAddresselSuccess();

    }

    public interface Presenter extends BasePresenter<View> {//过程

        void addressListContact(String token, String page);
        void getMyAddressList(String token);
        void updateAddress( String id,String contactName,String contactPhone,String accuracy,String latitude,String address,String detailAddress);
        void addNewAddress(String token,String contactName,String contactPhone,String accuracy,String latitude,String address,String detailAddress);
    }
}
