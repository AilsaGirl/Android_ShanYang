package com.liaocheng.suteng.myapplication.model;

import java.io.Serializable;

public class FaHuoAddressModel implements Serializable {
    public String contactPhone;//": "13309877788",
    public String contactName;//": "汪汪汪",
    public String address;//":地名  百货大楼
    public String detailAddress;//": ,补充地址 楼层
    public String lon;//": "110",
    public String lat;//": "33",
    public String ConcreteAdd;//"山东聊城","东昌府区"
    public String content;//下单内容
    public String city;//定位城市
    public int type;//订单类型
    public int is_result = 0;//是否返回上一级
    public int  is_new = 0;//是否新地址
    public String id;//地址
    public  int tag;//地址类型
    public int is_new_address = 0;
}
