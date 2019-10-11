package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class ShouYiModel {

    /**
     * total : 0.9999999999999999
     * detail : [{"detailName":"间接提成","orderCode":"2019080500779523809","detailMoney":"0.100","currentMoney":"1019.400","createTime":"2019-08-05 16:18:36.0"},{"detailName":"直接提成","orderCode":"2019080500779423809","detailMoney":"0.100","currentMoney":"1019.300","createTime":"2019-08-05 16:18:36.0"}]
     */

    private String total;
    private List<DetailBean> detail;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * detailName : 间接提成
         * orderCode : 2019080500779523809
         * detailMoney : 0.100
         * currentMoney : 1019.400
         * createTime : 2019-08-05 16:18:36.0
         */

        private String detailName;
        private String orderCode;
        private String detailMoney;
        private String currentMoney;
        private String createTime;

        public String getDetailName() {
            return detailName;
        }

        public void setDetailName(String detailName) {
            this.detailName = detailName;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getDetailMoney() {
            return detailMoney;
        }

        public void setDetailMoney(String detailMoney) {
            this.detailMoney = detailMoney;
        }

        public String getCurrentMoney() {
            return currentMoney;
        }

        public void setCurrentMoney(String currentMoney) {
            this.currentMoney = currentMoney;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
