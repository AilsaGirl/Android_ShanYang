package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class ChongZhiModel {
    public String isCharged;
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * money : 100
         * offer : 冲100元送0元
         */

        private String money;
        private String offer;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOffer() {
            return offer;
        }

        public void setOffer(String offer) {
            this.offer = offer;
        }
    }
//    是否充值过:1-充值过2-没有充值过

}
