package com.liaocheng.suteng.myapplication.presenter;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.ShouYiModel;
import com.liaocheng.suteng.myapplication.model.TeamModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;
import com.liaocheng.suteng.myapplication.presenter.contract.AboutContent;
import com.liaocheng.suteng.myapplication.presenter.contract.TuanDuiContent;

public class TuanDuiPresenter extends RxPresenter<TuanDuiContent.View> implements TuanDuiContent.Presenter{

    @Override
    public void getTeamUserDetail(String pageNo) {
        addSubscribe(Api.createTBService().getTeamUserDetail(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<TeamModel>>rxSchedulerHelper())
                .compose(RxUtil.<TeamModel>handleResult())
                .subscribeWith(new CommonSubscriber<TeamModel>(mContext, true) {
                    @Override
                    protected void _onNext(TeamModel commonRes) {

                        if (commonRes != null) {
                            mView.getTeamUserDetail(commonRes);
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
    public void getTeamMoney(String pageNo) {
        addSubscribe(Api.createTBService().getTeamMoney(SPCommon.getString("token",""),pageNo)
                .compose(RxUtil.<BaseResponse<ShouYiModel>>rxSchedulerHelper())
                .compose(RxUtil.<ShouYiModel>handleResult())
                .subscribeWith(new CommonSubscriber<ShouYiModel>(mContext, true) {
                    @Override
                    protected void _onNext(ShouYiModel commonRes) {

                        if (commonRes != null) {
                            mView.getTeamMoney(commonRes);
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
    public void getLevel() {
        addSubscribe(Api.createTBService().getLevel(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<BaoXianModel>>rxSchedulerHelper())
                .compose(RxUtil.<BaoXianModel>handleResult())
                .subscribeWith(new CommonSubscriber<BaoXianModel>(mContext, true) {
                    @Override
                    protected void _onNext(BaoXianModel commonRes) {

                        if (commonRes != null) {
                            mView.getLevel(commonRes);
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
