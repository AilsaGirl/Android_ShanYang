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
import com.liaocheng.suteng.myapplication.model.XinWenModel;
import com.liaocheng.suteng.myapplication.presenter.contract.AboutContent;
import com.liaocheng.suteng.myapplication.presenter.contract.HeiMingDanContent;

public class HeiMingDanPresenter extends RxPresenter<HeiMingDanContent.View> implements HeiMingDanContent.Presenter{


    @Override
    public void removeBlackList(String id) {
        addSubscribe(Api.createTBService().removeBlackList(SPCommon.getString("token",""),id)
                .compose(RxUtil.<BaseResponse<NullBean>>rxSchedulerHelper())
                .compose(RxUtil.<NullBean>handleResult())
                .subscribeWith(new CommonSubscriber<NullBean>(mContext, true) {
                    @Override
                    protected void _onNext(NullBean commonRes) {

                        if (commonRes != null) {
                            mView.removeBlackList();
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
    public void getBlackList() {
        addSubscribe(Api.createTBService().getBlackList(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<GongNengModel>>rxSchedulerHelper())
                .compose(RxUtil.<GongNengModel>handleResult())
                .subscribeWith(new CommonSubscriber<GongNengModel>(mContext, true) {
                    @Override
                    protected void _onNext(GongNengModel commonRes) {

                        if (commonRes != null) {
                            mView.getBlackList(commonRes);
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
