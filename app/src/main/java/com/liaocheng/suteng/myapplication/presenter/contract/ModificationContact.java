package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public interface ModificationContact {
    interface View extends BaseView {//结果

        void AddSuccess();
    }

    interface Presenter extends BasePresenter<View> {//过程

        void addAddress(String locationX, String locationY, String area, String address);
    }
}
