package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.PingJiaModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;
import com.liaocheng.suteng.myapplication.presenter.contract.AboutContent;
import com.liaocheng.suteng.myapplication.presenter.contract.PingJiaContent;

public class PingJiaPresenter extends RxPresenter<PingJiaContent.View> implements PingJiaContent.Presenter{

    @Override
    public void insertComment(String eva_userId, String comments, String level, String orderCode) {
        addSubscribe(Api.createTBService().insertComment(SPCommon.getString("token",""),eva_userId,comments,level,orderCode)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.insertComment();
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
    public void getCommentLabel() {
        addSubscribe(Api.createTBService().getCommentLabel(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<PingJiaModel>>rxSchedulerHelper())
                .compose(RxUtil.<PingJiaModel>handleResult())
                .subscribeWith(new CommonSubscriber<PingJiaModel>(mContext, true) {
                    @Override
                    protected void _onNext(PingJiaModel commonRes) {

                        if (commonRes != null) {
                            mView.getCommentLabel(commonRes);
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
