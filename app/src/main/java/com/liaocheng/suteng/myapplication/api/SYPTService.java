package com.liaocheng.suteng.myapplication.api;

import com.circle.common.response.BaseResponse;

import com.liaocheng.suteng.myapplication.model.ChangYongAddressBean;
import com.liaocheng.suteng.myapplication.model.FaDanXiaDanModel;
import com.liaocheng.suteng.myapplication.model.LoginBean;
import com.liaocheng.suteng.myapplication.model.MyAddressInfoBean;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.MyTeaBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.OrderCalculateBean;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;
import com.liaocheng.suteng.myapplication.model.SiteBean;
import com.liaocheng.suteng.myapplication.model.UpdatePhoneBean;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanBean;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanListBean;
import com.liaocheng.suteng.myapplication.model.Zcxiybean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Circle on 2017/3/28 0028.
 */

public interface SYPTService {
    /**
     * 修改头像
     *
     * @param token
     * @param img
     * @return
     */
    @FormUrlEncoded
    @POST("user/User_Data/changeimg")
    Flowable<BaseResponse<NullBean>> UpHeadProcess(@Field("token") String token, @Field("img") String img);
    /**
     * 修改地址
     *
     * @param id
     * @param contactName
     * @return
     */
    @FormUrlEncoded
    @POST("updateUserAddress")
    Flowable<BaseResponse<NullBean>> updateUserAddress(@Field("token") String token,@Field("id") String id, @Field("contactName") String contactName, @Field("contactPhone") String contactPhone, @Field("accuracy") String accuracy, @Field("latitude") String latitude, @Field("address") String address, @Field("detailAddress") String detailAddress, @Field("concreteAddress") String concreteAddress);
    /**
     * 个人中心--->创建地址管理
     * 创建地址管理
     *
     * @param token
     * @param contactName
     * @return
     */
    @FormUrlEncoded
    @POST("addNewAddress")
    Flowable<BaseResponse<NullBean>> addNewAddress(@Field("token") String token, @Field("contactName") String contactName, @Field("contactPhone") String contactPhone, @Field("accuracy") String accuracy, @Field("latitude") String latitude, @Field("address") String address, @Field("detailAddress") String detailAddress, @Field("addressType") String addressType, @Field("concreteAddress") String concreteAddress);
    /**
     * 我的地址  家  公司 默认
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("myAddressInfo")
    Flowable<BaseResponse<MyAddressInfoBean>> myAddressInfo(@Field("token") String token);
       /**
     * 个人中心--->地址管理  常用地址
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("findOldOrderAddress")
    Flowable<BaseResponse<ChangYongAddressBean>> addressListContact(@Field("token") String token, @Field("pageNo") String startNo);
    /**
     * 修改地址
     *
     * @param id
     * @param contactName
     * @return
     */
    @FormUrlEncoded
    @POST("findOldOrderAddress")
    Flowable<BaseResponse<NullBean>> findOldOrderAddress(@Field("userId") String id, @Field("startNo") String contactName);
    //注册获取验证码
    @FormUrlEncoded
    @POST("send_sms")
    Flowable<BaseResponse<NullBean>>  registcode( @Field("phone") String phone, @Field("type") String type);
    //注册协议
    @FormUrlEncoded
    @POST("getSanYangAgreement_info")
    Flowable<BaseResponse<Zcxiybean>> zcxieyi(@Field("code") String code, @Field("type") String type);
    //注册是否成功
    @FormUrlEncoded
    @POST("user_register")
    Flowable<BaseResponse<NullBean>> userregister(@Field("phone") String phone, @Field("password") String password,@Field("otherInviteCode") String otherInviteCode,@Field("messageCode") String messageCode);
    //忘记密码
    @FormUrlEncoded
    @POST("user_resetPassword")
    Flowable<BaseResponse<NullBean>> forgetpassword(@Field("phone") String phone, @Field("password") String password,@Field("messageCode") String messageCode);
    @FormUrlEncoded
    @POST("send_sms")
    Flowable<BaseResponse<NullBean>>  forgetcode( @Field("phone") String phone, @Field("type") String type);
  //手机号密码登录
    @FormUrlEncoded
    @POST("user_login")
    Flowable<BaseResponse<LoginBean>>  user_login(@Field("phone") String phone, @Field("password") String password);
    //手机号短信登录
    @FormUrlEncoded
    @POST("user_login_byIdentifyCode")
    Flowable<BaseResponse<LoginBean>>  user_login_byIdentifyCode(@Field("phone") String phone, @Field("messageCode") String messageCode);
    //我的信息
    @FormUrlEncoded
    @POST("user_info")
    Flowable<BaseResponse<MyBean>>  user_info(@Field("token") String token);
    //我的推广老师
    @FormUrlEncoded
    @POST("inviteUser_Info")
    Flowable<BaseResponse<MyTeaBean>>  inviteUser_Info(@Field("token") String token);
    //设置支付密码
    @FormUrlEncoded
    @POST("user_setSecondPassword")
    Flowable<BaseResponse<NullBean>>  user_setSecondPassword(@Field("token") String token,@Field("secondPassword") String secondPassword);
    //修改支付密码
    @FormUrlEncoded
    @POST("user_updateSecondpwdByOld")
    Flowable<BaseResponse<NullBean>>  user_updateSecondpwdByOld(@Field("token") String token,@Field("oldSecondPwd") String oldSecondPwd,@Field("newSecondPwd") String newSecondPwd);
    //手机修改支付密码
    @FormUrlEncoded
    @POST("user_updateSecondpwdBySms")
    Flowable<BaseResponse<NullBean>>  user_updateSecondpwdBySms(@Field("token") String token,@Field("newSecondPwd") String newSecondPwd,@Field("messageCode") String messageCode);
    //验证旧手机
    @FormUrlEncoded
    @POST("checkOldPhoneBySms")
    Flowable<BaseResponse<UpdatePhoneBean>>  checkOldPhoneBySms(@Field("token") String token, @Field("messageCode") String messageCode);
    //修改新的手机
    @FormUrlEncoded
    @POST("changeUserPhone_info")
    Flowable<BaseResponse<NullBean>>  changeUserPhone_info(@Field("token") String token,@Field("messageCode") String messageCode,@Field("modifyCode") String modifyCode,@Field("newPhone") String newPhone);
    //修改新的手机
    @FormUrlEncoded
    @POST("changeUserRadio")
    Flowable<BaseResponse<NullBean>>  changeUserRadio(@Field("token") String token,@Field("turn") String turn);
    //用户退出
    @FormUrlEncoded
    @POST("user_exit")
    Flowable<BaseResponse<NullBean>>  user_exit(@Field("token") String token);
    //发货订单查询
    @FormUrlEncoded
    @POST("queryMySendOrders")
    Flowable<BaseResponse<MySendOrdersBean>>  queryMySendOrders(@Field("token") String token,@Field("pageNo") String pageNo,@Field("duration") String duration);
    //接单订单查询
    @FormUrlEncoded
    @POST("queryMyReceiveOrders")
    Flowable<BaseResponse<MySendOrdersBean>>  queryMyReceiveOrders(@Field("token") String token, @Field("pageNo") String pageNo, @Field("duration") String duration);
    //接单订单统计
    @FormUrlEncoded
    @POST("queryMyReceiveOrdersCombined")
    Flowable<BaseResponse<MySendOrdersBean>>  queryMyReceiveOrdersCombined(@Field("token") String token,  @Field("duration") String duration);
    //发单订单统计
    @FormUrlEncoded
    @POST("queryMySendOrdersCombined")
    Flowable<BaseResponse<MySendOrdersBean>>  queryMySendOrdersCombined(@Field("token") String token,  @Field("duration") String duration);
    //优惠券
    @FormUrlEncoded
    @POST("couponHaveDetail")
    Flowable<BaseResponse<YouHuiQuanBean>>  couponHaveDetail(@Field("token") String token, @Field("pageNo") String pageNo);
    //优惠券数量
    @FormUrlEncoded
    @POST("coupon_info")
    Flowable<BaseResponse<YouHuiQuanBean>>  coupon_info(@Field("token") String token);
    //优惠券明细
    @FormUrlEncoded
    @POST("couponUseDetail")
    Flowable<BaseResponse<YouHuiQuanListBean>>  couponUseDetail(@Field("token") String token, @Field("pageNo") String pageNo);
    //优惠券转出
    @FormUrlEncoded
    @POST("giveCoupons_info")
    Flowable<BaseResponse<NullBean>>  giveCoupons_info(@Field("token") String token, @Field("receiverPhone") String receiverPhone, @Field("count") String count);
    //客服电话
    @FormUrlEncoded
    @POST("getServiceCenter")
    Flowable<BaseResponse<ServiceCenterBean>>  getServiceCenter(@Field("token") String token, @Field("area") String area);

    /**
     * 发货下单
     *
     * @param token
     * @param sendAddress
     * @return
     */
    @FormUrlEncoded
    @POST("order_create")
    Flowable<BaseResponse<FaDanXiaDanModel>> order_create(@Field("token") String token,@Field("orderType")  String orderType,@Field("sendName")  String sendName,@Field("sendPhone")  String sendPhone,
                                                          @Field("sendAddress")  String sendAddress,@Field("sendConcreteAdd")  String sendConcreteAdd,@Field("sendDetailAdd")  String sendDetailAdd,
                                                          @Field("sendLat")  String sendLat,@Field("sendLong")  String sendLong,@Field("receiveName")  String receiveName,@Field("contactPhone") String contactPhone,
                                                          @Field("receiveAddress")  String receiveAddress,@Field("receiveConcreteAdd")  String receiveConcreteAdd,@Field("receiveDetailAdd")  String receiveDetailAdd,
                                                          @Field("longitude")  String longitude,@Field("latitude")  String latitude,@Field("tip")  String tip,@Field("coupon")  String coupon,
                                                          @Field("goods")  String goods,@Field("weight")  String weight,@Field("appointTime")  String appointTime,@Field("description")  String description,
                                                          @Field("trafficTool")  String trafficTool,@Field("incubator")  String incubator,@Field("receivePhone")  String receivePhone,
                                                          @Field("parcel_insurance_id")  String parcel_insurance_id);

    /**
     * 发货下单
     *
     * @param token
     * @param longitude
     * @return
     */
    @FormUrlEncoded
    @POST("order_calculate")
    Flowable<BaseResponse<OrderCalculateBean>> order_calculate(@Field("token") String token, @Field("orderType")  String orderType, @Field("sendLat")  String sendLat, @Field("sendLong")  String sendLong,
                                                            @Field("longitude")  String longitude, @Field("latitude")  String latitude, @Field("tip")  String tip,
                                                            @Field("coupon")  String coupon, @Field("weight")  String weight, @Field("trafficTool")  String trafficTool, @Field("parcel_insurance_id") String parcel_insurance_id
                                                            );
}







