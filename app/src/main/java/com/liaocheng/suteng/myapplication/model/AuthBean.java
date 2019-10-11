package com.liaocheng.suteng.myapplication.model;

public class AuthBean {
  public String  name;//	String	认证姓名
    public String    phone;//	String	认证手机号
    public String   idCode;//	String	身份证号
    public String   idImg1;//	String	身份证1
    public String   idImg2;//	String	身份证2
    public String   idImg3;//	String	身份证3
    public String  foregift;//	String	押金金额
    public String  insurance;//	String	保险
    public String    status	;//String	接单员状态:0    未认证-2   资料已提交1    正常接单2    申请退出接单员3    已退出接单员4    限制接单100  芝麻认证已通过
    public String   finishTime;//	String	预计退出接单员的时间
    public String    astrictDays;//	String	接单限制解除时间
    public String    astrictReason;//	String	接单限制原因
    public String    workState;//	String	上班状态
    public String     trafficTool;//	String	上班登记的交通工具
  public String sex;
  public  String ethnic;
  public  String address;
  public  String organ;
  public String validity;
  public  String insurancePayTime;
  public String insuranceEndTime;
  public String member;//0-未交1-缴纳500元2-缴纳700元
  public  String memberPayTime;
  public String memberEndTime;

}
