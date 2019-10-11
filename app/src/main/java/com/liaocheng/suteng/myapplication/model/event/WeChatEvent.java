package com.liaocheng.suteng.myapplication.model.event;

/**
 * Created by LHP on 2017/9/8.
 */

public class WeChatEvent {

    private String mWeChatCode;

    private boolean isFXSucc;//微信是否分享成功    true 成功 false 失败

    public WeChatEvent(String mWeChatCode) {
        this.mWeChatCode = mWeChatCode;
    }

    public WeChatEvent(boolean isFXSucc) {
        this.isFXSucc = isFXSucc;
    }

    public boolean isFXSucc() {
        return isFXSucc;
    }

    public void setFXSucc(boolean FXSucc) {
        isFXSucc = FXSucc;
    }

    public String getmWeChatCode() {
        return mWeChatCode;
    }

    public void setmWeChatCode(String mWeChatCode) {
        this.mWeChatCode = mWeChatCode;
    }
}
