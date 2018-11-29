package com.liaocheng.suteng.myapplication.model;

import java.io.Serializable;
import java.util.List;

public class ChangYongAddressBean implements Serializable {
    public List<ChangYongAddressBean.ChangYongAddressModel> data;

    public class ChangYongAddressModel implements Serializable{
        public String sendName;//": "发货人",
        public String sendPhone;//": "18523551887",
        public String sendAddress;//": "发货地",
        public String sendDetailAdd;//": "发货地详情",
        public String sendLat;//": "36.461681",
        public String sendLong;//": "115.992064"
        public String  sendConcreteAdd;
    }
}
