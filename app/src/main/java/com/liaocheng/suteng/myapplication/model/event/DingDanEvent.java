package com.liaocheng.suteng.myapplication.model.event;

import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;

/**
 *
 */

public class DingDanEvent {
    public String title;
  boolean isShow;


    public DingDanEvent(boolean isShow) {
        this.isShow = isShow;
    }
    public DingDanEvent(String title) {
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

}
