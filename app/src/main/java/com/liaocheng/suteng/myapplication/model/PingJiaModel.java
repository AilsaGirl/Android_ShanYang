package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class PingJiaModel {
    public List<DataBean> data;
    public static class DataBean{
        public boolean isSelect;
//        id	String	编号
//        context	String	内容
//        color	String	颜色代码
        public String id;
        public String context;
        public String color;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }


}
