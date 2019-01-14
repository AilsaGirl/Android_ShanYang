package com.liaocheng.suteng.myapplication.model;

public class PayModel {
  public String  data;//	String	支付需要的数据
    public String   result_code	;//String	微信响应码
    public String  appid;//	String	APPID
    public String   partnerid;//	String	商户号
    public String    prepayid;//	String	预付订单号
//    public String  package;//	String	支付类型
    public String    nonce_str;//	String	随机字符串
    public String    timestamp;//	String	时间戳
    public String     sign;//	String	签名
}
