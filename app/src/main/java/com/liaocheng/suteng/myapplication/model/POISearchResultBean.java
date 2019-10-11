package com.liaocheng.suteng.myapplication.model;

import java.util.List;

public class POISearchResultBean {

  /**
   * status : 1
   * count : 10
   * info : OK
   * infocode : 10000
   * tips : [{"id":"B027B02LS5","name":"新东方广场","district":"山东省聊城市东昌府区","adcode":"371502","location":"115.989182,36.459996","address":"柳园北路68号","typecode":"060102","city":[]},{"id":"B0FFFFV6SY","name":"新星小学","district":"山东省聊城市东昌府区","adcode":"371502","location":"115.981399,36.466997","address":"振兴路10号楼","typecode":"141203","city":[]},{"id":"B0FFF2ZFNH","name":"尚东新城邦","district":"山东省聊城市东昌府区","adcode":"371502","location":"115.950513,36.494768","address":"京济路附近","typecode":"120302","city":[]},{"id":"B027B02LMR","name":"山东新华书店(聊城一门店)","district":"山东省聊城市东昌府区","adcode":"371502","location":"115.988230,36.449185","address":"柳园南路21号","typecode":"061205","city":[]},{"id":"B027B01727","name":"新世纪花园(东昌东路)","district":"山东省聊城市东昌府区","adcode":"371502","location":"116.006284,36.458813","address":"东昌东路29号","typecode":"120302","city":[]},{"id":"B027B0P4LS","name":"新世纪影城(人民广场店)","district":"山东省聊城市东昌府区","adcode":"371502","location":"115.987368,36.458016","address":"柳园北路99号人民广场最南头地下","typecode":"080601","city":[]},{"id":"B0FFFU1S3J","name":"尚东·新城邦3期","district":"山东省聊城市东昌府区","adcode":"371502","location":"115.951263,36.499068","address":"嘉园路11号","typecode":"120302","city":[]},{"id":"B0FFG5D14H","name":"新凤祥集团","district":"山东省聊城市阳谷县","adcode":"371521","location":"115.860533,36.208370","address":[],"typecode":"170200","city":[]},{"id":"BV10221626","name":"新纺街振兴路口(公交站)","district":"山东省聊城市东昌府区","adcode":"371502","location":"115.984581,36.468872","address":"134路/K134路","typecode":"150700","city":[]},{"id":"B027B02TT5","name":"新东方·尚街(柳园路)","district":"山东省聊城市东昌府区","adcode":"371502","location":"115.989010,36.458896","address":"柳园北路68-27号","typecode":"060102","city":[]}]
   */

  private String status;
  private String count;
  private String info;
  private String infocode;
  private List<TipsBean> tips;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getInfocode() {
    return infocode;
  }

  public void setInfocode(String infocode) {
    this.infocode = infocode;
  }

  public List<TipsBean> getTips() {
    return tips;
  }

  public void setTips(List<TipsBean> tips) {
    this.tips = tips;
  }

  public static class TipsBean {
    /**
     * id : B027B02LS5
     * name : 新东方广场
     * district : 山东省聊城市东昌府区
     * adcode : 371502
     * location : 115.989182,36.459996
     * address : 柳园北路68号
     * typecode : 060102
     * city : []
     */

    private String id;
    private String name;
    private String district;
    private String adcode;
    private String location;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDistrict() {
      return district;
    }

    public void setDistrict(String district) {
      this.district = district;
    }

    public String getAdcode() {
      return adcode;
    }

    public void setAdcode(String adcode) {
      this.adcode = adcode;
    }

    public String getLocation() {
      return location;
    }

    public void setLocation(String location) {
      this.location = location;
    }





  }
}
