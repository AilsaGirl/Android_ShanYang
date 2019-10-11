package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.ChangYongAddressBean;
import com.liaocheng.suteng.myapplication.model.JiJiaBiaoZhunBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;
import com.liaocheng.suteng.myapplication.presenter.contract.BangDingContent;
import com.liaocheng.suteng.myapplication.presenter.contract.JiJiaBiaoZhunContent;

public class JiJiaBiaoZhunPresenter extends RxPresenter<JiJiaBiaoZhunContent.View> implements JiJiaBiaoZhunContent.Presenter{



    @Override
    public void findAreaPriceDetail(String type, String lat, String lon) {

        addSubscribe(Api.createTBService().findAreaPriceDetail( SPCommon.getString("token",""),type,lat,lon)
                .compose(RxUtil.<BaseResponse<JiJiaBiaoZhunBean>>rxSchedulerHelper())
                .compose(RxUtil.<JiJiaBiaoZhunBean>handleResult())
                .subscribeWith(new CommonSubscriber<JiJiaBiaoZhunBean>(mContext, true) {
                    @Override
                    protected void _onNext(JiJiaBiaoZhunBean commonRes) {
                        if (commonRes != null) {
                            mView.findAreaPriceDetail(commonRes);
                        } else {
                            mView.showError(0, "");
                        }
                    }


                    @Override
                    protected void _onError(String message) {
                        mView.showError(1, message);
                    }
                })
        );
    }
}
