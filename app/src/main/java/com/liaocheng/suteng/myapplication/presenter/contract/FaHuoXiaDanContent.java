package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.DingDanWeiZhiBean;
import com.liaocheng.suteng.myapplication.model.FaDanXiaDanModel;
import com.liaocheng.suteng.myapplication.model.FindAreaWeightBean;
import com.liaocheng.suteng.myapplication.model.FindParcelInsuranceBean;
import com.liaocheng.suteng.myapplication.model.OrderCalculateBean;
import com.liaocheng.suteng.myapplication.model.PayModel;

public interface FaHuoXiaDanContent {
    interface View extends BaseView {//结果
        //        void loginSuccess(LoginBean loginBean);
        void setData(FaDanXiaDanModel xiaDanModel);
        void setNum(OrderCalculateBean orderCalculateBean);
        void order_pay(PayModel payModel);
        void checkSecondPassword();
        void findAreaWeight(FindAreaWeightBean findAreaWeightBean);
        void  findParcelInsurance(FindParcelInsuranceBean findParcelInsuranceBean);

    }
//    orderType	String	是	订单类型: 1-帮我买 2-帮我办 3-帮我送 4-校园快送 5-合作商家 6-县域快送
//    sendName	String	是	发单人姓名(最多20个字符)
//    sendPhone	String	是	发单人手机号(最多11个字符)
//    sendAddress	String	是	订单类型为1为购买地(建筑物) 订单类型为2为办事地(建筑物) 订单类型为3-6为发货地(建筑物)
//    sendConcreteAdd	String	是	上面的详细地址信息XX省XX市XXX
//    sendDetailAdd	String	是	上面的手写补充地址信息
//    sendLat	String	是	订单类型为1 为购买地纬度 订单类型为 2为办事地纬度  订单类型为3-6为发货地纬度
//    sendLong	String	是	订单类型为 1为购买地经度订单类型为 2为办事地经度  订单类型为3-6为发货地经度
//    receiveName	String	是	收货人姓名(最多10个字符)
//    contactPhone	String	是	收货人手机号(最多11个字符)
//    receiveAddress	String	是	收货地(建筑物)
//    receiveConcreteAdd	String	是	上面的详细地址信息XX省XX市XXX
//    receiveDetailAdd	String	是	上面的手写补充地址信息
//    longitude	String	是	收货地经度
//    latitude	String	是	收货地纬度
//    tip	String	是	小费(最多(10,2)数字共10位,两位小数)
//    coupon	String	是	优惠券使用金额: 使用-传2.00 不使用-传0.00
//    goods	String	是	货物类型: 1-鲜花 2-食品 3-文体 4-蛋糕 5-电子产品 6-生活用品
//    weight	String	是	重量:只能传数字(最多6个字符)
//    appointTime	String	是	约定时间
//    description	String	是	备注(最多200个字符)
//    trafficTool	String	是	交通工具: 1-二轮车 2-三轮车 3-小客车 4-小货车 5-大货车
//    incubator	String	是	保温箱:0-不需要(未选,默认填) 1-需要
//    receivePhone	String	是	接单人手机号(最多11个字符)
//    parcel_insurance_id	String	是	包裹保费id(通过接口获取)
//    token	String	是	用户令牌





    //    orderType	String	是	订单类型: 1-帮我买 2-帮我办 3-帮我送 4-校园快送 5-合作商家 6-县域快送
//    sendLat	String	是	订单类型为 1为购买地纬度订单类型为 2为办事地纬度订单类型为 3-6为发货地纬度
//    sendLong	String	是	订单类型为 1为购买地经度 订单类型为2为办事地经度订单类型为3-6为发货地经度
//    longitude	String	是	收货地经度
//    latitude	String	是	收货地纬度
//    tip	String	是	小费(最多(10,2)数字共10位,两位小数)
//    coupon	String	是	优惠券使用金额: 使用-传2.00 不使用-传0.00
//    weight	String	是	重量:只能传数字(最多6个字符)
//    trafficTool	String	是	交通工具: 1-二轮车 2-三轮车 3-小客车 4-小货车 5-大货车
//    parcel_insurance_id	String	是	包裹保费id(通过接口获取)
    interface Presenter extends BasePresenter<View> {//过程
        void detail(String token, String orderType, String sendName,
                    String sendPhone, String sendAddress, String sendConcreteAdd, String sendDetailAdd, String sendLat,
                    String sendLong, String receiveName, String contactPhone, String receiveAddress, String receiveConcreteAdd,
                    String receiveDetailAdd, String longitude,String latitude, String tip, String coupon, String goods,
                    String weight, String appointTime, String description, String trafficTool, String incubator,
                    String receivePhone, String parcel_insurance_id);
        void orderNum(String token, String orderType, String sendLat,
                      String sendLong, String longitude, String latitude, String tip, String coupon,
                      String weight, String trafficTool, String parcel_insurance_id);
        void order_pay(String code, String type);
        void checkSecondPassword(String secondPwd);
        void  findAreaWeight(String token, String sendLat, String sendLong,String trafficTool);
        void  findParcelInsurance(String token, String sendLat, String sendLong);
    }
}
