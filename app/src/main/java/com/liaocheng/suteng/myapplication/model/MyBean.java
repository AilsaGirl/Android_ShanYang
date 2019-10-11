package com.liaocheng.suteng.myapplication.model;

public class MyBean {
   public String phone;//	String	手机号
    public String  headImg;//	String	头像地址
    public String  nickName;//	String	昵称
    public String   residueMoney;//	String	可提现余额
    public String   dealMoney;//	String	可发货余额
    public String   needRadio;//	int	推送设置0-关闭,1-打开
 public String   isSetSecondPwd;//是否设置了2级密码  0-未设置  1-已设置
 public String   authStatus;//
 public String   arrears;//
 public String   insuranceArrears;//
 public String   foregift;//保证金一次付清：1-是 0-否
 public String   foregiftProtocol;//保证金协议付款：1-是 0-否
 public String   insurance;//保险金一次付清：1-是 0-否
 public String   insuranceProtocol;//保险金协议付清：1-是 0-否
 public String equipment;
// 认证状态:
//         0    资料未提交(仅此接口)
//-2   资料已提交
//1    正常接单
//2    申请退出接单员
//3    已退出接单员
//4    限制接单
//100  芝麻认证已通过


}
