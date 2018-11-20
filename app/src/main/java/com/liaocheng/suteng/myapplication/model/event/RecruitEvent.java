package com.liaocheng.suteng.myapplication.model.event;

/**
 *
 */

public class RecruitEvent {
    public String title;
  boolean isShow;
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
}
