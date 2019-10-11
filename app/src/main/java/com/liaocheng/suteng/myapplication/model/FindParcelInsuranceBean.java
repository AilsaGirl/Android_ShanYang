package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class FindParcelInsuranceBean {
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * parcel_insurance_id : 0
         * parcel_insurance_name : 不保价
         * parcel_insurance_content : 发货的物品出现损坏,最高赔付30元优惠券
         * parcel_insurance_fee : 0.00
         */

        private String parcel_insurance_id;
        private String parcel_insurance_name;
        private String parcel_insurance_content;
        private String parcel_insurance_fee;

        public String getParcel_insurance_id() {
            return parcel_insurance_id;
        }

        public void setParcel_insurance_id(String parcel_insurance_id) {
            this.parcel_insurance_id = parcel_insurance_id;
        }

        public String getParcel_insurance_name() {
            return parcel_insurance_name;
        }

        public void setParcel_insurance_name(String parcel_insurance_name) {
            this.parcel_insurance_name = parcel_insurance_name;
        }

        public String getParcel_insurance_content() {
            return parcel_insurance_content;
        }

        public void setParcel_insurance_content(String parcel_insurance_content) {
            this.parcel_insurance_content = parcel_insurance_content;
        }

        public String getParcel_insurance_fee() {
            return parcel_insurance_fee;
        }

        public void setParcel_insurance_fee(String parcel_insurance_fee) {
            this.parcel_insurance_fee = parcel_insurance_fee;
        }
    }
//    parcel_insurance_id	String	保价ID
//    parcel_insurance_name	String	保价标题
//    parcel_insurance_content	String	保价内容
//    parcel_insurance_fee	String	保价金额

}
