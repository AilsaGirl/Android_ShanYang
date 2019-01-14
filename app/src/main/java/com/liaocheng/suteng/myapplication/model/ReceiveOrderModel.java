package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class ReceiveOrderModel {
  public List<ReceiveOrderBean> data;
  public class ReceiveOrderBean{
    public String  orderCode;//	String	订单号
    public String  orderType;//	String	订单类型
    public String  sendAddress;//	String	发货地
    public String   sendConcreteAdd;//	String	发货地详情
    public String   receiveAddress;//	String	收货地
    public String   receiveConcreteAdd;//	String	收货地详情
    public String  description;//
    public String  contactPhone;//
    public String longitude;//	String	收货地经度
    public String  latitude;//	String	收货地纬度
    public String  sendLong;//	String	发货地经度
    public String  sendLat;//	String	发货地纬度
    public String goodsImg1;//	String	订单照片1
    public String goodsImg2;//	String	订单照片2
    public String goodsImg3;//	String	订单照片3
  }
}
