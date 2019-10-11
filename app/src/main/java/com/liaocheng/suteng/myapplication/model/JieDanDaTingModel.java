package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class JieDanDaTingModel {
   public List<JieDanDaTingBean> data;
   public class JieDanDaTingBean{
      public String  orderCode;//	String	订单号
      public String  orderType;//	String	订单类型
      public String  appointTime;//	String	约定时间
      public String  description;//	String	备注信息
      public String   remuneration;//	String	订单收益
      public String   sendAddress;//	String	发货地
      public String   sendConcreteAdd;//	String	发货地详情
      public String    sendLong;//	String	发货地经度
      public String    sendLat;//	String	发货地纬度
      public String    receiveAddress;//	String	收货地
      public String    receiveConcreteAdd;//	String	收货地详情
      public String   longitude;//	String	收货地经度
      public String   latitude;//	String	收货地纬度
      public String   distance;//	String	配送距离

      public String   finishTime;//	String	订单完成时间

      /**
       * createTime : 2019-08-02 17:57:58.0
       * status : 9
       */

      public String createTime;
      public String status;
      public String   sendDetailAdd;
      public String   receiveDetailAdd;
      public String sendDistance;
      public String receiveDistance;
      public String recIsComment;//	String	接单员是否评价0-未评价1-已评价
      public String sendIsComment;//	String	发单员是否评价0-未评价1-已评价

   }

}
