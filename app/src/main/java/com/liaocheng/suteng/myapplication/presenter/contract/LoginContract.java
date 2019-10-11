package com.liaocheng.suteng.myapplication.presenter.contract;

import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;

/**
 * Created by wang on 2017/6/27.
 */

public interface LoginContract {
    interface View extends BaseView {
        void setWeChatOrTencentLogin(ThirdLoginModel model);
    }

    interface Presenter extends BasePresenter<View> {
        void loginEvent();

        void qqLogin();

        void weChatLogin();

        /**
         uuid	YString	手机设备识别号
         type	Y		String	登录方式1微信2QQ
         code	N	''	String微信code（type为1时传）
         openid N	''	String	QQ对应openid （type为2时传）
         access_token N	''	String QQtoken（type为2时传）
         flag Y	''	String	手机类型填ios或/android
         */
        void getWeChatOrTencentLogin(String uuid, String type, String code, String openid,
                                     String access_token, String flag);
    }
}
