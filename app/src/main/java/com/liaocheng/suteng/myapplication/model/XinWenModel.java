package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class XinWenModel {

    public List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 13
         * title : 商家要学会维护接单员
         * content : 各位商家注意了
         平时发货的时候，一定要和接单员和和气气的，有顺路单的尽量给一个人一起给送，如果接单员等待的时间长了，加点等候费，如果定位定错了，就按实际距离给补偿费用，只有让接单员不寒心，让接单员挣到应得的钱，接单员才会每次积极接你的订单，千万不要以为定位错了或者让接单员等的时间长了或者超重了置之不理，不要以为这次给你送了，他下次还会服务，人心都是肉长的，如果接单员接你的订单的时候你伤了这个接单员的心，那么他下次不会接你的订单了，最后会导致你的订单延误，所以请商家自重，也请你尽量不要贪图小便宜而失去一个给你服务的接单员。
         三羊跑腿办公室
         2019.07.07
         * newsPicture1 :
         * newsPicture2 :
         * newsPicture3 :
         * newsPicture4 :
         * newsPicture5 :
         * author : 三羊跑腿
         * createTime : 2019-07-07 09:53:48.0
         */

        public String id;
        public String title;
        public String content;
        public String newsPicture1;
        public String newsPicture2;
        public String newsPicture3;
        public String newsPicture4;
        public String newsPicture5;
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

        public String getNewsPicture1() {
            return newsPicture1;
        }

        public void setNewsPicture1(String newsPicture1) {
            this.newsPicture1 = newsPicture1;
        }

        public String getNewsPicture2() {
            return newsPicture2;
        }

        public void setNewsPicture2(String newsPicture2) {
            this.newsPicture2 = newsPicture2;
        }

        public String getNewsPicture3() {
            return newsPicture3;
        }

        public void setNewsPicture3(String newsPicture3) {
            this.newsPicture3 = newsPicture3;
        }

        public String getNewsPicture4() {
            return newsPicture4;
        }

        public void setNewsPicture4(String newsPicture4) {
            this.newsPicture4 = newsPicture4;
        }

        public String getNewsPicture5() {
            return newsPicture5;
        }

        public void setNewsPicture5(String newsPicture5) {
            this.newsPicture5 = newsPicture5;
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
