package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class MySendOrdersBean {
    public String orderCount;
    public String orderPayMoney;
    public List<MySendOrdersModel> data;
    public class MySendOrdersModel{
        public String orderCode;//	String	订单号
        public String  orderType;//	String	订单类型
        public String  createTime;//	String	创建时间
        public String  description;//	String	备注信息
        public String  sendAddress;//	String	发货地
        public String  sendConcreteAdd;//	String	发货地详情
        public String  receiveAddress;//	String	收货地
        public String  receiveConcreteAdd;//	String	收货地详情
        public String  total;//	String	付款金额
        public String  distance;//	String	配送距离
        public String  status;//	String	订单状态

        public String remuneration;

        public String recIsComment;//	String	接单员是否评价0-未评价1-已评价
        public String sendIsComment;//	String	发单员是否评价0-未评价1-已评价
    }

}
