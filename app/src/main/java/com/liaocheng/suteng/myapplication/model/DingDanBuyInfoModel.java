package com.liaocheng.suteng.myapplication.model;

public class DingDanBuyInfoModel {
  public String  createTime;//	String	订单创建时间
    public String   payTime;//	String	订单支付时间
    public String  receiveTime;//	String	订单抢单时间
    public String   pickTime;//	String	订单取货时间
    public String  finishTime;//	String	订单完成时间
    public String  remuneration;//	String	接单员订单收入
    public String   orderType;//	String	订单类型:1-帮我买2-帮我办3-帮我送4-校园快送5-合作商家6-县域快送
    public String   sendUserId;//	String	发单人ID
    public String   receiveUserId;//	String	接单人ID
    public String   transporterName;//	String	接单员姓名
    public String  transporterPhone	;//String	接单员手机号
    public String   orderCode;//	String	订单号
    public String   sendName;//	String	发货人姓名
    public String   sendPhone;//	String	发货人手机号
    public String  sendAddress;//	String	发货地(建筑物)
    public String  sendConcreteAdd;//	String	发货地具体省市区路
    public String   sendDetailAdd;//	String	发货地补充
    public String  sendLong;//	String	发货地经度
    public String  sendLat;//	String	发货地纬度
    public String  receiveName;//	String	收货人姓名
    public String   contactPhone;//	String	收货人手机号
    public String   receiveAddress;//	String	收货地(建筑物)
    public String   receiveConcreteAdd;//	String	收货地具体省市区路
    public String   receiveDetailAdd;//	String	收货地补充
    public String  longitude;//	String	收货地经度
    public String  latitude;//	String	收货地纬度
    public String  appointTime;//	String	约定时间
    public String  goods;//	String	货品类型:1-鲜花2-食品3-文体4-蛋糕5-电子产品6-生活用品
    public String  trafficTool;//	String	交通工具类型:1-二轮车2-三轮车3-小客车4-小货车5-大货车
    public String   incubator;//	String	是否需要保温箱:0-不需要1-需要
    public String  description;//	String	备注说明
    public String  standardTotal;//	String	路程费用(跑腿费)
    public String  tip;//	String	打赏小费
    public String  addTips;//	String	订单加价
    public String  weightPrice;//	String	重量溢出加价
    public String   coupon;//	String	优惠券减免
    public String   parcel_insurance_fee;//	String	保价费用
    public String   total;//	String	订单付款总金额
    public String   status;//	String	订单状态
    public String   distance;//	String	配送距离
}
