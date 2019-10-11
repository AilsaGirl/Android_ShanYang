package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class GuiZeModel {

    public List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 27
         * title : 邀请接单员奖励活动的变动通知
         * content : 关于优惠券发放取消的通知：
         1，新用户注册赠送5张优惠券，这个活动结束。
         2，邀请用户注册赠送优惠券的活动结束。
         三羊跑腿办公室
         2019.7.5
         * author : 三羊跑腿
         * createTime : 2019-07-05 15:28:53.0
         */

        public String id;
        public String title;
        public String content;
        public String author;
        public String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
