package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class MyAddressInfoBean {
    public List<MyAddressModel> data;

    public  class MyAddressModel{
//        id	String	地址ID
//        contactPhone	String	联系人手机号
//        contactName	String	联系人姓名
//        address	String	地址信息
//        detailAddress	String	地址信息详情
//        accuracy	String	地址经度
//        latitude	String	地址纬度
//        addressType	String	地址类型(0-普通,1-默认,2-家庭,3-公司)
        public String id;//": "4042",
        public String contactPhone;//": "13309877788",
        public String contactName;//": "汪汪汪",
        public String address;//": "山东聊城",
        public String detailAddress;//": "东昌府区",
        public String accuracy;//": "110",
        public String latitude;//": "33",
        public String addressType;//": "0"
        public String  concreteAddress;
    }

}
