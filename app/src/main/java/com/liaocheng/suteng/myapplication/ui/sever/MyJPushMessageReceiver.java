package com.liaocheng.suteng.myapplication.ui.sever;

import android.content.Context;

import com.liaocheng.suteng.myapplication.model.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * 自定义JPush message 接收器,包括操作tag/alias的结果返回(仅仅包含tag/alias新接口部分)
 * */
public class MyJPushMessageReceiver extends JPushMessageReceiver {

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {

        System.out.println(jPushMessage.getErrorCode() + "" + "-------------------------" + jPushMessage.getTags().toString());
        switch (jPushMessage.getErrorCode()) {

            case 6002:
                //设置超时通知重试
                MessageEvent messageEvent = new MessageEvent("setTag");
                EventBus.getDefault().post(messageEvent);

                break;

        }
        super.onTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        System.out.println(jPushMessage.getErrorCode() + "" + "-------------------------Tag = " + jPushMessage.getTags());
        super.onCheckTagOperatorResult(context, jPushMessage);
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        System.out.println(jPushMessage.getErrorCode() + "" + "-------------------------" + jPushMessage.getAlias());
        switch (jPushMessage.getErrorCode()) {
            case 6002:
                //设置超时通知重试
                MessageEvent messageEvent = new MessageEvent("setAlias");
                EventBus.getDefault().post(messageEvent);
                break;
        }
        super.onAliasOperatorResult(context, jPushMessage);
    }
}
