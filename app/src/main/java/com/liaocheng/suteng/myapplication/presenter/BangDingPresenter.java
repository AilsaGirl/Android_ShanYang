package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;
import com.liaocheng.suteng.myapplication.presenter.contract.BangDingContent;
import com.liaocheng.suteng.myapplication.presenter.contract.FaDanContent;

public class BangDingPresenter extends RxPresenter<BangDingContent.View> implements BangDingContent.Presenter{

    @Override
    public void getCode(String phone, String type) {
        addSubscribe(Api.createTBService().forgetcode(phone, "21")
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {
                        if (commonRes != null) {
                            mView.setCode();
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
    public void userBindThird(String type, String code, String phone, String nickName, String headImg, String messageCode) {
        addSubscribe(Api.createTBService().userBindThird(type,code,phone,nickName,headImg,messageCode)
                .compose(RxUtil.<BaseResponse<ThirdLoginModel>>rxSchedulerHelper())
                .compose(RxUtil.<ThirdLoginModel>handleResult())
                .subscribeWith(new CommonSubscriber<ThirdLoginModel>(mContext, true) {
                    @Override
                    protected void _onNext(ThirdLoginModel commonRes) {

                        if (commonRes != null) {
                            mView.userBindThird(commonRes);
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
