package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;
import com.liaocheng.suteng.myapplication.model.YuEMingXiBean;
import com.liaocheng.suteng.myapplication.presenter.contract.AboutContent;
import com.liaocheng.suteng.myapplication.presenter.contract.YuEContent;

public class YuEPresenter extends RxPresenter<YuEContent.View> implements YuEContent.Presenter{


    @Override
    public void user_residumoneyDetail(String pageNo) {
        addSubscribe(Api.createTBService().user_residumoneyDetail(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<YuEMingXiBean>>rxSchedulerHelper())
                .compose(RxUtil.<YuEMingXiBean>handleResult())
                .subscribeWith(new CommonSubscriber<YuEMingXiBean>(mContext, true) {
                    @Override
                    protected void _onNext(YuEMingXiBean commonRes) {

                        if (commonRes != null) {
                            mView.user_residumoneyDetail(commonRes);
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
    public void user_dealmoneyDetail(String pageNo) {
        addSubscribe(Api.createTBService().user_dealmoneyDetail(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<YuEMingXiBean>>rxSchedulerHelper())
                .compose(RxUtil.<YuEMingXiBean>handleResult())
                .subscribeWith(new CommonSubscriber<YuEMingXiBean>(mContext, true) {
                    @Override
                    protected void _onNext(YuEMingXiBean commonRes) {

                        if (commonRes != null) {
                            mView.user_dealmoneyDetail(commonRes);
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
