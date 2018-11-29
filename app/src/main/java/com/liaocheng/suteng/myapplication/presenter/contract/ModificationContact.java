package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public interface ModificationContact {
    interface View extends BaseView {//结果
        void updateSuccess();
        void AddSuccess();
    }

    interface Presenter extends BasePresenter<View> {//过程
        void updateAddress( String id,String contactName,String contactPhone,String accuracy,String latitude,String address,String detailAddress,String concreteAddress);

        void addAddress(String name, String tel,String locationX, String locationY, String area, String address,String addressType,String concreteAddress);
    }
}
