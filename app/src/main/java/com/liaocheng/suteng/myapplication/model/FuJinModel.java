package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class FuJinModel {

    public List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        public String lat;
        public String name;
        public String phone;
        public String lon ;


    }
}
