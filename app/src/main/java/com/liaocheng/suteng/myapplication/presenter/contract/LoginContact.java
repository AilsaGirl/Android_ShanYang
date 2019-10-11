package com.liaocheng.suteng.myapplication.presenter.contract;


import com.circle.common.base.BasePresenter;
import com.circle.common.base.BaseView;
import com.liaocheng.suteng.myapplication.model.LoginBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;


/**
 * Created by wei on 2018/1/11.
 */

public interface LoginContact {
    interface View extends BaseView {//结果

        void loginSuccess(NullBean loginBean);
        void loginSuc(LoginBean loginBean);
        void  setforcode(NullBean nullBean);
        void setWeChatOrTencentLogin(ThirdLoginModel model);
    }

    interface Presenter extends BasePresenter<View> {//过程
        void loginEvent();
        void  login(String username, String password);
        void  logins(String username, String password);
        void  getforgetcode(String number,String code);
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
        void getWeChatOrTencentLogin(String type, String code);
    }

}
