package com.liaocheng.suteng.myapplication.model.event;

public class FaHuoAddressEvent {
    public String dizhi;
    public String xiangqing;

    String lon;String lat;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String city;

    public boolean isDingWei;
    public FaHuoAddressEvent(String dizhi,String xiangqing,String lon,String lat,String city){
        this.dizhi = dizhi;
        this.xiangqing = xiangqing;
        this.lon = lon;
        this.lat =lat;
        this.city =city;
    }
    public FaHuoAddressEvent(boolean isDingWei){
        this.isDingWei = isDingWei;
    }
    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getXiangqing() {
        return xiangqing;
    }

    public void setXiangqing(String xiangqing) {
        this.xiangqing = xiangqing;
    }
    public boolean isDingWei() {
        return isDingWei;
    }

    public void setDingWei(boolean dingWei) {
        isDingWei = dingWei;
    }


    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

}
