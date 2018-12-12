package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class NoticeModel {
    public List<NoticeBean> data;
    public class NoticeBean{
//        id	String	公告编号
//        title	String	公告标题
//        content	String	公告内容
        public String  id	;//String	轮播图编号
        public String title;//	String	图片链接地址
        public String  content;//	String	图片跳转链接
        public String   createTime;
    }

}
