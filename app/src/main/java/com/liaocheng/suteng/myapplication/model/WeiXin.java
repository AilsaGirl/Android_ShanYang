package com.liaocheng.suteng.myapplication.model;

public class WeiXin {

    /**
     * access_token : 23_SdyfPbiVQwxObJBrEwwdwQiZ735-zyDrAyH_cWAwth0KnrINomrqFBe27K98QX_pAIaswzqynUM3goTDnSTi4LVLhkOzPrgOydiog3TFrkk
     * expires_in : 7200
     * refresh_token : 23_POHHXa90dgZ5v7AFP4GoVDj4mivsTTzb4-eR7ArFHrIjhCktMR9SZa_w2zOgg13DdVD-v6lQ-meVCazxyzHUPlciwlDNKyb0KCh-j2_WHAo
     * openid : oAcAZw-JDtb71AJ_VNqVRPWBluh8
     * scope : snsapi_userinfo
     * unionid : oQN3r0nsa8YTzZn1lAdWrM4bjpUY
     */

    public String access_token;
    public int expires_in;
    public String refresh_token;
    public String openid;
    public String scope;
    public String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
