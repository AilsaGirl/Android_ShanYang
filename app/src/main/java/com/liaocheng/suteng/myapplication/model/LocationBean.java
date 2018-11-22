package com.liaocheng.suteng.myapplication.model;

import java.io.Serializable;

/**
 * 新增任务地址，高德周围poi地址
 *
 */

public class LocationBean implements Serializable {

    private double lon;
    private double lat;
    private String title;
    private String content;
    private String texta;
    private String sheng;
    private String shi;
    private String qu;

    public LocationBean(double lon,double lat,String title,String content,String texta,String sheng,String shi,String qu){
        this.lon = lon;
        this.lat = lat;
        this.title = title;
        this.content = content;
        this.texta=texta;
        this.sheng=sheng;
        this.shi=shi;
        this.qu=qu;



    }


    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    public String getTexta() {
        return texta;
    }
    public String getSheng() {
        return sheng;
    }  public String getShi() {
        return shi;
    }  public String getQu() {
        return qu;
    }



}
