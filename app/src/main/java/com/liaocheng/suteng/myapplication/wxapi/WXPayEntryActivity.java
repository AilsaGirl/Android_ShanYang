package com.liaocheng.suteng.myapplication.wxapi;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.circle.common.util.LogManager;
import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.util.Util;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends FragmentActivity implements IWXAPIEventHandler {

    private IWXAPI api;
	private String mPrice, mName, mTel, mAddress;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	api = WXAPIFactory.createWXAPI(this, Util.getWechatAppId());
        api.handleIntent(getIntent(), this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if(resp instanceof SendAuth.Resp){
			SendAuth.Resp newResp = (SendAuth.Resp) resp;
			//获取微信传回的code
			String code = newResp.code;
			return;
		}
//		LogManager.LogShow("wxpay resp errcode="+resp.errCode  + "  tag=="+PreManager.instance().getPayTag());
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			if(resp.errCode == -2 ){ //用户取消
				ToastUtil.show("用户取消");
//				if (!TextUtils.isEmpty(PreManager.instance().geteMallTag())
//						&& PreManager.instance().geteMallTag().equals(Extra.MALL_ORDER_TAG)) {
////					PreManager.instance().saveMallTag("");
////					Intent intent_pay = new Intent();
////					intent_pay.putExtra("order_state", "1");
////					intent_pay.setClass(WXPayEntryActivity.this, MyOrderActivity.class);
////					startActivity(intent_pay);
//				}
				this.finish();
			}else if(resp.errCode == 0 ){  // 支付成功
				ToastUtil.show("支付成功");
//				MallPaymentSuccessFragment f1 = new MallPaymentSuccessFragment();
//				FragmentTransaction ft = getFragmentManager().beginTransaction();
//				ft.add(R.id.three, f1,"" );
//				ft.commit();
//				if (!TextUtils.isEmpty(PreManager.instance().geteMallTag())
//						&& PreManager.instance().geteMallTag().equals(Extra.MALL_ORDER_TAG)) {
//					if (PreManager.instance().getPayTag() == 2) {
////					startActivity(new Intent(WXPayEntryActivity.this,MyWalletActivity.class));
//					}else {
//						PreManager.instance().saveMallTag("");
//						startActivity(new Intent(WXPayEntryActivity.this, MallPaymentSuccessFragment.class));
//					}
//				}

//				ShowFragmentUtils.showFragment(this, MallPaymentSuccessFragment.class, "", null, false);

				this.finish();
			}
		}

	}
}