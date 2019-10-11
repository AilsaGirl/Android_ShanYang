package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class TeamModel {

    /**
     * count : 4
     * detail : [{"id":"10000775","headImg":"http://thirdqq.qlogo.cn/qqapp/1106686023/E1CD375A78D372FA722874951250CA3D/100","nickName":"止水轩","registerTime":"2018-04-03 15:13:48.0","level":"-1"},{"id":"10002368","headImg":"http://1.3ypt.com/SYWeb/userHead/20180503150536199k9mec.jpg","nickName":"小范测试","registerTime":"2018-05-02 09:41:49.0","level":"-1"},{"id":"10002372","headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/Ya7Zl2lVYdyWzLhdehaHP6VUyAo6osrZm2LpYibpKb5ogic14lUC0VXdOpEsGN2LcBQvW7U2aUPjlMy2e1CHGfLw/132","nickName":"看看快快快","registerTime":"2018-05-02 10:52:26.0","level":"-1"},{"id":"10002373","headImg":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqzGqVzQK5raDqxI0T8wWpxP1MqV0a4ZCxtI0XjouDicCXW9ibnfnhpFfdgUUqloa7EybqvEQX8U4QQ/132","nickName":"哦哦","registerTime":"2018-05-02 11:13:29.0","level":"-1"}]
     */

    private int count;
    private List<DetailBean> detail;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * id : 10000775
         * headImg : http://thirdqq.qlogo.cn/qqapp/1106686023/E1CD375A78D372FA722874951250CA3D/100
         * nickName : 止水轩
         * registerTime : 2018-04-03 15:13:48.0
         * level : -1
         */

        private String id;
        private String headImg;
        private String nickName;
        private String registerTime;
        private String level;
        private String phone;
        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(String registerTime) {
            this.registerTime = registerTime;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
