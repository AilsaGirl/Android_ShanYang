package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class ExtensionDetailQueryBean {
    public List<ExtensionDetailQueryModel> data;
    public class ExtensionDetailQueryModel{

//        名称	类型	描述
//        phone	String	被推广人手机号
//        money	String	交易金额
//        createTime	String	交易时间
//        sumMoney	String	当前合计金额
//        orderCode	String	交易单号
        public String  money;//	String	昵称
        public String  phone;//	String	手机号
        public String   createTime;//	String	注册时间
        public String  sumMoney;//	String	认证状态
        public String  orderCode;//	String	认证状态
    }


}
