package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class YouHuiQuanBean {
    public List<YouHuiQuanModel> data;
    public String denomination	;//String	优惠券面额
    public String   count	;//String	优惠券数量
    public class YouHuiQuanModel{
        public String denomination	;//String	交易单号/手机号
        public String   couponNumber	;//String	优惠券码
        public String   createTime	;//String	优惠券发行日期
        public String   finishTime	;//String	优惠券过期日期
    }

}
