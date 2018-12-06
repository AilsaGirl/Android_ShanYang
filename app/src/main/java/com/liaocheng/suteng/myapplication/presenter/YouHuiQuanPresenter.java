package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanBean;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanListBean;
import com.liaocheng.suteng.myapplication.presenter.contract.JieDanContent;
import com.liaocheng.suteng.myapplication.presenter.contract.YouHuiQuanContent;

public class YouHuiQuanPresenter extends RxPresenter<YouHuiQuanContent.View> implements YouHuiQuanContent.Presenter{

    @Override
    public void getYouHuiQuan(String pageNo) {
        addSubscribe(Api.createTBService().couponHaveDetail(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<YouHuiQuanBean>>rxSchedulerHelper())
                .compose(RxUtil.<YouHuiQuanBean>handleResult())
                .subscribeWith(new CommonSubscriber<YouHuiQuanBean>(mContext, true) {
                    @Override
                    protected void _onNext(YouHuiQuanBean commonRes) {

                        if (commonRes != null) {
                            mView.setYouHuiQuan(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    @Override
    public void getYouHuiQuanNum() {
        addSubscribe(Api.createTBService().coupon_info(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<YouHuiQuanBean>>rxSchedulerHelper())
                .compose(RxUtil.<YouHuiQuanBean>handleResult())
                .subscribeWith(new CommonSubscriber<YouHuiQuanBean>(mContext, true) {
                    @Override
                    protected void _onNext(YouHuiQuanBean commonRes) {

                        if (commonRes != null) {
                            mView.setYouHuiQuanNum(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    @Override
    public void getYouHuiQuanList(String pageNo) {
        addSubscribe(Api.createTBService().couponUseDetail(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<YouHuiQuanListBean>>rxSchedulerHelper())
                .compose(RxUtil.<YouHuiQuanListBean>handleResult())
                .subscribeWith(new CommonSubscriber<YouHuiQuanListBean>(mContext, true) {
                    @Override
                    protected void _onNext(YouHuiQuanListBean commonRes) {

                        if (commonRes != null) {
                            mView.setYouHuiQuanList(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }

    @Override
    public void giveCoupons(String tel, String num) {
        addSubscribe(Api.createTBService().giveCoupons_info(SPCommon.getString("token",""),tel,num)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.giveCoupons();
                        } else {
                            mView.showError(0, "");
                        }
                    }
                    @Override
                    protected void _onError(String message) {
                        mView.showError(0, message);
                    }
                })
        );
    }


}
