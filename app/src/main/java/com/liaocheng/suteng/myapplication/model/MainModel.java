package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class MainModel {
    public List<MainBean> data;
    public class MainBean{
        public String  id	;//String	轮播图编号
        public String imgUrl;//	String	图片链接地址
        public String  imgToUrl;//	String	图片跳转链接
    }

}
