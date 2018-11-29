package com.liaocheng.suteng.myapplication.model.event;

import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;

/**
 *
 */

public class RecruitEvent {
    public String title;
  boolean isShow;

    public FaHuoAddressModel addressModel;
    public RecruitEvent(FaHuoAddressModel addressModel) {
        this.addressModel = addressModel;
    }
    public RecruitEvent(boolean isShow) {
        this.isShow = isShow;
    }
    public RecruitEvent(String title) {
        this.title = title;
    }

    public String geTitle() {
        return title;
    }
    public boolean getShow() {
        return isShow;
    }
    public void setShow( boolean isShow) {
        this.isShow = isShow;
    }
    public void setTitle(String mIndex) {
        this.title = title;
    }
    public FaHuoAddressModel getAddressModel() {
        return addressModel;
    }

    public void setAddressModel(FaHuoAddressModel addressModel) {
        this.addressModel = addressModel;
    }
}
