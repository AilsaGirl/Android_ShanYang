package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class YouHuiQuanListBean {
    public List<YouHuiQuanListModel> data;
    public class YouHuiQuanListModel{
      public String  detailName	;//String	交易单号/手机号
        public String   useType;//	String	使用类型
        public String   count;//	String	数量
        public String   createTime;//	String	产生交易时间
        public String    counts;//	String	剩余张数
    }
}
