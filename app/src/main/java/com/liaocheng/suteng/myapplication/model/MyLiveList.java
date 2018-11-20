package com.liaocheng.suteng.myapplication.model;

/**
 *
 * Description
 */

public class MyLiveList {

    private String title;
    private String source;
    public boolean isSelect;

    public String id;
    public String username;
    public String mobile;
    public String province;
    public String city;
    public String area;
    public String address;
    public String tag;
    public String user_id;
    public String x;
    public String y;


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
