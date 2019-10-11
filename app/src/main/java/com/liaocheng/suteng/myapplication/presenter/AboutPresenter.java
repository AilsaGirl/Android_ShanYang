package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.NoticeModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.ThirdLoginModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;
import com.liaocheng.suteng.myapplication.presenter.contract.AboutContent;
import com.liaocheng.suteng.myapplication.presenter.contract.BangDingContent;

public class AboutPresenter extends RxPresenter<AboutContent.View> implements AboutContent.Presenter{

    @Override
    public void getSanYangNews(String pageNo) {
        addSubscribe(Api.createTBService().getSanYangNews(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<XinWenModel>>rxSchedulerHelper())
                .compose(RxUtil.<XinWenModel>handleResult())
                .subscribeWith(new CommonSubscriber<XinWenModel>(mContext, true) {
                    @Override
                    protected void _onNext(XinWenModel commonRes) {

                        if (commonRes != null) {
                            mView.getSanYangNews(commonRes);
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
    public void getSanYangRule(String pageNo) {
        addSubscribe(Api.createTBService().getSanYangRule(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<GuiZeModel>>rxSchedulerHelper())
                .compose(RxUtil.<GuiZeModel>handleResult())
                .subscribeWith(new CommonSubscriber<GuiZeModel>(mContext, true) {
                    @Override
                    protected void _onNext(GuiZeModel commonRes) {

                        if (commonRes != null) {
                            mView.getSanYangRule(commonRes);
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
    public void appUpGrade_info() {
        addSubscribe(Api.createTBService().appUpGrade_info(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<GongNengModel>>rxSchedulerHelper())
                .compose(RxUtil.<GongNengModel>handleResult())
                .subscribeWith(new CommonSubscriber<GongNengModel>(mContext, true) {
                    @Override
                    protected void _onNext(GongNengModel commonRes) {

                        if (commonRes != null) {
                            mView.appUpGrade_info(commonRes);
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
