package com.liaocheng.suteng.myapplication.api;

import com.circle.common.response.BaseResponse;

import com.liaocheng.suteng.myapplication.model.ChangYongAddressBean;
import com.liaocheng.suteng.myapplication.model.FaDanXiaDanModel;
import com.liaocheng.suteng.myapplication.model.MyAddressInfoBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.OrderCalculateBean;
import com.liaocheng.suteng.myapplication.model.SiteBean;
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
     * @param sendAddress
     * @return
     */
    @FormUrlEncoded
    @POST("order_calculate")
    Flowable<BaseResponse<OrderCalculateBean>> order_calculate(@Field("token") String token, @Field("orderType")  String orderType, @Field("sendLat")  String sendLat, @Field("sendLong")  String sendLong,
                                                            @Field("longitude")  String longitude, @Field("latitude")  String latitude, @Field("tip")  String tip,
                                                            @Field("coupon")  String coupon, @Field("weight")  String weight, @Field("trafficTool")  String trafficTool, @Field("parcel_insurance_id") String parcel_insurance_id
                                                            );
}







