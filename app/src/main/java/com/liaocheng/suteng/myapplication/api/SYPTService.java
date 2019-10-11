package com.liaocheng.suteng.myapplication.api;

import com.circle.common.response.BaseRespons;
import com.circle.common.response.BaseResponse;

import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.ChangYongAddressBean;
import com.liaocheng.suteng.myapplication.model.ChongZhiModel;
import com.liaocheng.suteng.myapplication.model.DingDanBuyInfoModel;
import com.liaocheng.suteng.myapplication.model.DingDanWeiZhiBean;
import com.liaocheng.suteng.myapplication.model.ExtensionDetailQueryBean;
import com.liaocheng.suteng.myapplication.model.FaDanXiaDanModel;
import com.liaocheng.suteng.myapplication.model.FindAreaWeightBean;
import com.liaocheng.suteng.myapplication.model.FindParcelInsuranceBean;
import com.liaocheng.suteng.myapplication.model.FuJinModel;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.JiJiaBiaoZhunBean;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.LoginBean;
import com.liaocheng.suteng.myapplication.model.MainModel;
import com.liaocheng.suteng.myapplication.model.MyAddressInfoBean;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.MyTeaBean;
import com.liaocheng.suteng.myapplication.model.MyTuiGuangBean;
import com.liaocheng.suteng.myapplication.model.NoticeModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.OrderCalculateBean;
import com.liaocheng.suteng.myapplication.model.POISearchResultBean;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.model.PingJiaModel;
import com.liaocheng.suteng.myapplication.model.PromoteDetailBean;
import com.liaocheng.suteng.myapplication.model.ReceiveOrderModel;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;
import com.liaocheng.suteng.myapplication.model.ShouYiModel;
import com.liaocheng.suteng.myapplication.model.SiteBean;
import com.liaocheng.suteng.myapplication.model.TeamModel;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;
import com.liaocheng.suteng.myapplication.model.UpdatePhoneBean;
import com.liaocheng.suteng.myapplication.model.VersionModel;
import com.liaocheng.suteng.myapplication.model.WeiXin;
import com.liaocheng.suteng.myapplication.model.WeiXinToken;
import com.liaocheng.suteng.myapplication.model.XinWenModel;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanBean;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanListBean;
import com.liaocheng.suteng.myapplication.model.YuEMingXiBean;
import com.liaocheng.suteng.myapplication.model.Zcxiybean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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
    @POST("getSanYangAgreement")
    Flowable<BaseResponse<Zcxiybean>> zcxieyi(@Field("code") String code);
    //注册是否成功
    @FormUrlEncoded
    @POST("user_register")
    Flowable<BaseResponse<NullBean>> userregister(@Field("phone") String phone, @Field("password") String password,@Field("otherInviteCode") String otherInviteCode,@Field("messageCode") String messageCode);
    //忘记密码
    @FormUrlEncoded
    @POST("user_resetPassword")
    Flowable<BaseResponse<NullBean>> forgetpassword(@Field("phone") String phone, @Field("newPassword") String password,@Field("messageCode") String messageCode);
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
    //首页轮播
    @FormUrlEncoded
    @POST("getBanner")
    Flowable<BaseResponse<MainModel>>  getBanner(@Field("token") String token, @Field("area") String area);

    //首页公告
    @FormUrlEncoded
    @POST("getNotice")
    Flowable<BaseResponse<NoticeModel>>  getNotice(@Field("token") String token, @Field("area") String area);

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

    /**
     * 上传一张图片
     * @param nickName
     * @param photo
     * @return
     */
    @Multipart
    @POST("user_setUserProfile")
    Call<BaseResponse<NullBean>> uploadImage(@Part("nickName") String nickName,
                             @Part("photo\"; filename=\"image.png\"") RequestBody photo);

    //修改用户名
    @FormUrlEncoded
    @POST("user_setUserNickName")
    Flowable<BaseResponse<NullBean>>  user_setUserNickName(@Field("token") String token,@Field("nickName") String nickName);
    //修改图片名
    @FormUrlEncoded
    @POST("user_setUserHeadImg")
    Flowable<BaseResponse<NullBean>>  user_setUserHeadImg(@Field("token") String token,@Field("headImg") String headImg);
    //身份认证
    @FormUrlEncoded
    @POST("user_addAuth")
    Flowable<BaseResponse<NullBean>>  user_addAuth(@Field("token") String token,@Field("name") String name,@Field("phone") String phone,@Field("idCode") String idCode,@Field("positive_idPhoto") String positive_idPhoto,@Field("reverse_idPhoto") String reverse_idPhoto,@Field("armed_idPhoto") String armed_idPhoto,@Field("sex") String sex,@Field("ethnic") String ethnic,@Field("address") String address,@Field("organ") String organ,@Field("validity") String validity);
    //身份认证
    @FormUrlEncoded
    @POST("auth_info")
    Flowable<BaseResponse<AuthBean>>  auth_info(@Field("token") String token);
    //身份认证
    @FormUrlEncoded
    @POST("auth_delete")
    Flowable<BaseResponse<NullBean>>  auth_delete(@Field("token") String token);
    //推广概览
    @FormUrlEncoded
    @POST("extensionSumMoneyQuery")
    Flowable<BaseResponse<MyTuiGuangBean>>  extensionSumMoneyQuery(@Field("token") String token,@Field("city") String city,@Field("area") String area);
    //推广人详情
    @FormUrlEncoded
    @POST("promoteDetail")
    Flowable<BaseResponse<PromoteDetailBean>>  promoteDetail(@Field("token") String token, @Field("pageNo") String pageNo, @Field("generation") String generation);
    //推广明细
    @FormUrlEncoded
    @POST("extensionDetailQuery")
    Flowable<BaseResponse<ExtensionDetailQueryBean>>  extensionDetailQuery(@Field("token") String token, @Field("pageNo") String pageNo, @Field("generation") String generation, @Field("promotionType") String promotionType, @Field("duration") String duration);
    //接单大厅
    @FormUrlEncoded
    @POST("order_info")
    Flowable<BaseResponse<JieDanDaTingModel>>  order_info(@Field("token") String token, @Field("pageNo") String pageNo,@Field("trafficTool") String trafficTool);
    //上班
    @FormUrlEncoded
    @POST("setWorkState")
    Flowable<BaseResponse<NullBean>>  setWorkState(@Field("token") String token, @Field("workState") String workState);
    //接单
    @FormUrlEncoded
    @POST("order_grab")
    Flowable<BaseResponse<NullBean>>  order_grab(@Field("token") String token, @Field("orderCode") String orderCode);
    //接单详情
    @FormUrlEncoded
    @POST("querySendOrderDetail")
    Flowable<BaseResponse<DingDanBuyInfoModel>>  querySendOrderDetail(@Field("token") String token, @Field("orderCode") String orderCode);
    //用户删除未支付订单
    @FormUrlEncoded
    @POST("order_cancell")
    Flowable<BaseResponse<NullBean>>  order_cancell(@Field("token") String token, @Field("orderCode") String orderCode);
    //验证密码
    @FormUrlEncoded
    @POST("checkSecondPassword")
    Flowable<BaseResponse<NullBean>>  checkSecondPassword(@Field("token") String token, @Field("secondPwd") String orderCode);
    //支付订单
    @FormUrlEncoded
    @POST("order_pay")
    Flowable<BaseResponse<PayModel>>  order_pay(@Field("token") String token, @Field("orderCode") String orderCode, @Field("payType") String payType);
    //用户删除未被抢订单
    @FormUrlEncoded
    @POST("user_order_refund")
    Flowable<BaseResponse<NullBean>>  user_order_refund(@Field("token") String token, @Field("orderCode") String orderCode);
    //订单加价
    @FormUrlEncoded
    @POST("addOrderTip")
    Flowable<BaseResponse<PayModel>>  addOrderTip(@Field("token") String token, @Field("orderCode") String orderCode, @Field("payType") String payType,@Field("addTips") String addTips);
    //确认订单
    @FormUrlEncoded
    @POST("order_submit")
    Flowable<BaseResponse<NullBean>>  order_submit(@Field("token") String token, @Field("orderCode") String orderCode);
   //撤销订单接单员
    @FormUrlEncoded
    @POST("order_revoke")
    Flowable<BaseResponse<NullBean>>  order_revoke(@Field("token") String token, @Field("orderCode") String orderCode);
    //确认订单
    @FormUrlEncoded
    @POST("courier_order_submit")
    Flowable<BaseResponse<NullBean>>  courier_order_submit(@Field("token") String token, @Field("orderCode") String orderCode);
    //短信确认订单
    @FormUrlEncoded
    @POST("checkReceiveCode")
    Flowable<BaseResponse<NullBean>>  checkReceiveCode(@Field("token") String token, @Field("orderCode") String orderCode,@Field("receiveCode") String receiveCode);
    //接单员撤销订单到用户余额
    @FormUrlEncoded
    @POST("order_refund")
    Flowable<BaseResponse<NullBean>>  order_refund(@Field("token") String token, @Field("orderCode") String orderCode);
    //确认到店取货
    @FormUrlEncoded
    @POST("getThePickup")
    Flowable<BaseResponse<NullBean>>  getThePickup(@Field("token") String token, @Field("orderCode") String orderCode);
    //处理被指定订单
    @FormUrlEncoded
    @POST("executeSpecificOrder")
    Flowable<BaseResponse<NullBean>>  executeSpecificOrder(@Field("token") String token, @Field("orderCode") String orderCode , @Field("executeType") String executeType);
    //处理被转让订单
    @FormUrlEncoded
    @POST("executeTransferOrder")
    Flowable<BaseResponse<NullBean>>  executeTransferOrder(@Field("token") String token, @Field("orderCode") String orderCode , @Field("executeType") String executeType);
    //转让订单
    @FormUrlEncoded
    @POST("transferOrder")
    Flowable<BaseResponse<NullBean>>  transferOrder(@Field("token") String token, @Field("orderCode") String orderCode , @Field("phone") String phone);
    //接单员设置交通工具
    @FormUrlEncoded
    @POST("setWorkTraffic")
    Flowable<BaseResponse<NullBean>>  setWorkTraffic(@Field("token") String token, @Field("trafficTool") String trafficTool);
    //取货中
    @FormUrlEncoded
    @POST("queryReceiveOrder")
    Flowable<BaseResponse<ReceiveOrderModel>>  queryReceiveOrder(@Field("token") String token, @Field("pageNo") String pageNo);
    //送货中
    @FormUrlEncoded
    @POST("queryOnTheWayOrder")
    Flowable<BaseResponse<ReceiveOrderModel>>  queryOnTheWayOrder(@Field("token") String token, @Field("pageNo") String pageNo);

    //送货中图片
    @FormUrlEncoded
    @POST("order_upPhotoByIndex")
    Flowable<BaseResponse<NullBean>>  order_upPhotoByIndex(@Field("token") String token, @Field("orderCode") String orderCode, @Field("goodsImg") String goodsImg, @Field("index") String index);
    //完成订单
    @FormUrlEncoded
    @POST("queryCompletedOrder")
    Flowable<BaseResponse<JieDanDaTingModel>>  queryCompletedOrder(@Field("token") String token, @Field("pageNo") String pageNo);
    //退款订单
    @FormUrlEncoded
    @POST("queryHaveRefundOrder")
    Flowable<BaseResponse<JieDanDaTingModel>>  queryHaveRefundOrder(@Field("token") String token, @Field("pageNo") String pageNo);
    //用户拉黑接单员
    @FormUrlEncoded
    @POST("addBlackList")
    Flowable<BaseResponse<NullBean>>  addBlacklist(@Field("token") String token, @Field("receiveUserId") String receiveUserId);
    //用户移除拉黑接单员
    @FormUrlEncoded
    @POST("removeBlackList")
    Flowable<BaseResponse<NullBean>>  removeBlackList(@Field("token") String token, @Field("receiveUserId") String receiveUserId);
    //黑名单
    @FormUrlEncoded
    @POST("getBlackList")
    Flowable<BaseResponse<GongNengModel>>  getBlackList(@Field("token") String token);
    //查询收发接单员经纬度
    @FormUrlEncoded
    @POST("getCoordByOrderCode")
    Flowable<BaseResponse<DingDanWeiZhiBean>>  getCoordByOrderCode(@Field("token") String token, @Field("orderCode") String orderCode);

    //第三方登录
    @FormUrlEncoded
    @POST("user_login_byThird")
    Flowable<BaseResponse<ThirdLoginModel>>  weChatOrQQLogin(@Field("type") String type, @Field("code") String code);
    //第三方登录

    @GET("oauth2/access_token")
    Flowable<BaseRespons>  weChatToken(@Query("appid") String appid, @Query("secret") String secret, @Query("code") String code, @Query("grant_type") String grant_type);
    //第三方登录

    @GET("userinfo")
    Flowable<BaseRespons>  weChatInfo(@Query("access_token") String access_token, @Query("openid") String openid);
    //地图

    @GET("inputtips")
    Flowable<POISearchResultBean>  diTuInfo(@Query("key") String key, @Query("keywords") String keywords, @Query("citylimit") boolean citylimit, @Query("city") String city);
    //第三方登录
    @FormUrlEncoded
    @POST("userBindThird")
    Flowable<BaseResponse<ThirdLoginModel>>  userBindThird(@Field("type") String type, @Field("code") String code, @Field("phone") String phone, @Field("nickName") String nickName, @Field("headImg") String headImg, @Field("messageCode") String messageCode);
    //获取当前地区交通工具免费重量及超出免费重量加价
    @FormUrlEncoded
    @POST("findAreaWeight")
    Flowable<BaseResponse<FindAreaWeightBean>>  findAreaWeight(@Field("token") String token, @Field("sendLong") String sendLong, @Field("sendLat") String sendLat, @Field("trafficTool") String trafficTool);
    //该地区保价
    @FormUrlEncoded
    @POST("findParcelInsurance")
    Flowable<BaseResponse<FindParcelInsuranceBean>>  findParcelInsurance(@Field("token") String token, @Field("sendLong") String sendLong, @Field("sendLat") String sendLat);
    //更新接单员位置
    @FormUrlEncoded
    @POST("updateLocation")
    Flowable<BaseResponse<NullBean>>  updateLocation(@Field("token") String token, @Field("lat") String lat, @Field("lon") String lon);
    //各地区订单价格明细表
    @FormUrlEncoded
    @POST("findAreaPriceDetail")
    Flowable<BaseResponse<JiJiaBiaoZhunBean>>  findAreaPriceDetail(@Field("token") String token,@Field("orderType") String orderType, @Field("sendLong") String sendLong, @Field("sendLat") String sendLat);
   //提现
    @FormUrlEncoded
    @POST("user_withdraw")
    Flowable<BaseResponse<NullBean>>  user_withdraw(@Field("token") String token, @Field("secondPwd") String secondPwd, @Field("withdrawAccount") String withdrawAccount, @Field("withdrawName") String withdrawName, @Field("withdrawMoney") String withdrawMoney, @Field("messageCode") String messageCode);
    //充值发货余额
    @FormUrlEncoded
    @POST("user_rechargeDealMoney")
    Flowable<BaseResponse<PayModel>>  user_rechargeDealMoney(@Field("token") String token, @Field("rechargeMoneyType") String rechargeMoneyType, @Field("rechargeType") String rechargeType);
    //充值提现余额
    @FormUrlEncoded
    @POST("user_recharge")
    Flowable<BaseResponse<PayModel>>  user_recharge(@Field("token") String token, @Field("rechargeMoney") String rechargeMoney, @Field("rechargeType") String rechargeType);
    //充值10
    @FormUrlEncoded
    @POST("isFirstChargedDealMoney")
    Flowable<BaseResponse<ChongZhiModel>>  isFirstChargedDealMoney(@Field("token") String token);
    //获取三羊新闻
    @FormUrlEncoded
    @POST("getSanYangNews")
    Flowable<BaseResponse<XinWenModel>>  getSanYangNews(@Field("token") String token, @Field("pageNo") String pageNo);
    //获取三羊规则
    @FormUrlEncoded
    @POST("getSanYangRule")
    Flowable<BaseResponse<GuiZeModel>>  getSanYangRule(@Field("token") String token, @Field("pageNo") String pageNo);
    //获取三羊功能
    @FormUrlEncoded
    @POST("appUpGrade_info")
    Flowable<BaseResponse<GongNengModel>>  appUpGrade_info(@Field("token") String token);
    //申请退出接单员
    @FormUrlEncoded
    @POST("user_authRelieve")
    Flowable<BaseResponse<NullBean>>  user_authRelieve(@Field("token") String token);
    //取消退出接单员
    @FormUrlEncoded
    @POST("user_cancellRelieve")
    Flowable<BaseResponse<NullBean>>  user_cancellRelieve(@Field("token") String token);
    //用户支付协议款
    @FormUrlEncoded
    @POST("repayTheArrears")
    Flowable<BaseResponse<PayModel>>  repayTheArrears(@Field("token") String token, @Field("payType") String payType, @Field("secondPassword") String secondPassword);
    //用户支付保险缴费
    @FormUrlEncoded
    @POST("user_insurancePay")
    Flowable<BaseResponse<PayModel>>  user_insurancePay(@Field("token") String token, @Field("payType") String payType, @Field("city") String city, @Field("area") String area);
    //查询保险
    @FormUrlEncoded
    @POST("findAreaInsurance")
    Flowable<BaseResponse<BaoXianModel>>  findAreaInsurance(@Field("token") String token, @Field("city") String city, @Field("area") String area);
    //用户支付保险缴费
    @FormUrlEncoded
    @POST("user_authPay")
    Flowable<BaseResponse<PayModel>>  user_authPay(@Field("token") String token, @Field("payType") String payType, @Field("city") String city, @Field("area") String area, @Field("foregift") String foregift, @Field("foregift_protocol") String foregift_protocol, @Field("insurance") String insurance, @Field("insurance_protocol") String insurance_protocol, @Field("isNeedEquip") String isNeedEquip, @Field("memberFee") String memberFee);
    //芝麻认证获取biz_no
    @FormUrlEncoded
    @POST("zhiMaAuth")
    Flowable<BaseResponse<BaoXianModel>>  zhiMaAuth(@Field("token") String token, @Field("realName") String realName , @Field("idCode") String idCode );
    //芝麻认证成功
    @FormUrlEncoded
    @POST("user_authOk")
    Flowable<BaseResponse<NullBean>>  user_authOk(@Field("token") String token );
    //用户万能余额明细查询
    @FormUrlEncoded
    @POST("user_residumoneyDetail")
    Flowable<BaseResponse<YuEMingXiBean>>  user_residumoneyDetail(@Field("token") String token ,@Field("pageNo") String pageNo);
    //用户发货余额明细查询
    @FormUrlEncoded
    @POST("user_dealmoneyDetail")
    Flowable<BaseResponse<YuEMingXiBean>>  user_dealmoneyDetail(@Field("token") String token ,@Field("pageNo") String pageNo);
    //查询转让中和被指定的订单
    @FormUrlEncoded
    @POST("queryTransferAndSpecificOrder")
    Flowable<BaseResponse<JieDanDaTingModel>>  queryTransferAndSpecificOrder(@Field("token") String token );
    //接单大厅详情
    @FormUrlEncoded
    @POST("order_info_detail")
    Flowable<BaseResponse<DingDanBuyInfoModel>>  order_info_detail(@Field("token") String token, @Field("orderCode") String orderCode);
    //接单详情
    @FormUrlEncoded
    @POST("queryReceiveOrderDetail")
    Flowable<BaseResponse<DingDanBuyInfoModel>>  queryReceiveOrderDetail(@Field("token") String token, @Field("orderCode") String orderCode);
    //查看接单员交通工具
    @FormUrlEncoded
    @POST("getCustomerTraffic")
    Flowable<BaseResponse<BaoXianModel>>  getCustomerTraffic(@Field("token") String token );
    //获取用户等级
    @FormUrlEncoded
    @POST("getLevel")
    Flowable<BaseResponse<BaoXianModel>>  getLevel(@Field("token") String token );
    //团队盈利明细
    @FormUrlEncoded
    @POST("getTeamMoney")
    Flowable<BaseResponse<ShouYiModel>>  getTeamMoney(@Field("token") String token , @Field("pageNo") String pageNo);
    //团队人数
    @FormUrlEncoded
    @POST("getTeamUserDetail")
    Flowable<BaseResponse<TeamModel>>  getTeamUserDetail(@Field("token") String token , @Field("pageNo") String pageNo);
    //查询地区保证金,保险费,培训费
    @FormUrlEncoded
    @POST("findAreaNeedFee")
    Flowable<BaseResponse<BaoXianModel>>  findAreaNeedFee(@Field("token") String token, @Field("city") String city, @Field("area") String area );
    //附近接单员
    @FormUrlEncoded
    @POST("showVicinityCustomer")
    Flowable<BaseResponse<FuJinModel>>  showVicinityCustomer(@Field("token") String token, @Field("lon") String lon , @Field("lat") String lat  );
    //插入评论
    @FormUrlEncoded
    @POST("insertComment")
    Flowable<BaseResponse<NullBean>>  insertComment(@Field("token") String token, @Field("eva_userId") String eva_userId , @Field("comments") String comments, @Field("level") String level, @Field("orderCode") String orderCode  );

    //获取评论标签
    @FormUrlEncoded
    @POST("getCommentLabel")
    Flowable<BaseResponse<PingJiaModel>>  getCommentLabel(@Field("token") String token );
    //保底费（会员费）缴纳
    @FormUrlEncoded
    @POST("user_memberPay")
    Flowable<BaseResponse<PayModel>>  user_memberPay(@Field("token") String token,@Field("payType") String payType  ,@Field("memberFee") String memberFee);

    //是否支持协议扣款
    @FormUrlEncoded
    @POST("isAgreementDeductions")
    Flowable<BaseResponse<BaoXianModel>>  isAgreementDeductions(@Field("token") String token , @Field("city") String city, @Field("area") String area);
    //充值
    @FormUrlEncoded
    @POST("getAddType")
    Flowable<BaseResponse<ChongZhiModel>>  getAddType(@Field("token") String token , @Field("city") String city, @Field("area") String area);
    //充值

    @POST("appVersion_info")
    Flowable<BaseResponse<VersionModel>>  appVersion_info();
}







