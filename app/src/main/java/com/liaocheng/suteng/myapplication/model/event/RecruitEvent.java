package com.liaocheng.suteng.myapplication.model.event;

import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;

/**
 *
 */

public class RecruitEvent {
    public String title;
  boolean isShow;

    public FaHuoAddressModel addressModelFa;
    public FaHuoAddressModel addressModelShou;
    public RecruitEvent(FaHuoAddressModel addressModelFa) {
        this.addressModelFa = addressModelFa;
    }
    public RecruitEvent(FaHuoAddressModel addressModelFa,FaHuoAddressModel addressModelShou) {
        this.addressModelShou = addressModelShou;
        this.addressModelFa = addressModelFa;
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
        return addressModelFa;
    }
    public FaHuoAddressModel getAddressModelShou() {
        return addressModelShou;
    }
    public void setAddressModel(FaHuoAddressModel addressModel) {
        this.addressModelFa = addressModel;
    }
    public void setAddressModelShou(FaHuoAddressModel addressModel) {
        this.addressModelShou = addressModelShou;
    }
}
