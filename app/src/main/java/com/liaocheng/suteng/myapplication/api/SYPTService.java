package com.liaocheng.suteng.myapplication.api;

import com.circle.common.response.BaseResponse;

import com.liaocheng.suteng.myapplication.model.NullBean;
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
    Flowable<BaseResponse<NullBean>> updateUserAddress(@Field("id") String id, @Field("contactName") String contactName, @Field("contactPhone") String contactPhone, @Field("accuracy") String accuracy, @Field("latitude") String latitude, @Field("address") String address, @Field("detailAddress") String detailAddress);
    /**
     * 个人中心--->创建地址管理
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/Location/create")
    Flowable<BaseResponse<NullBean>> createaddress(@Field("token") String token, @Field("x") String x, @Field("y") String y, @Field("area") String area, @Field("address") String address);

    /**
     * 个人中心--->删除地址管理
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/Location/del")
    Flowable<BaseResponse<NullBean>> delddress(@Field("token") String token, @Field("location_id") String location_id);
    /**
     * 个人中心--->地址管理
     *
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("api/Location/myList")
    Flowable<BaseResponse<SiteBean>> addressListContact(@Field("token") String token, @Field("page") String page, @Field("limit") String limit);

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
    @POST("user_register")
    Flowable<BaseResponse<NullBean>> forgetpassword(@Field("phone") String phone, @Field("password") String password,@Field("messageCode") String messageCode);

}







