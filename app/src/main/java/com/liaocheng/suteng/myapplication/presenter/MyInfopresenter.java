package com.liaocheng.suteng.myapplication.presenter;

import android.util.Log;

import com.circle.common.base.RxPresenter;
import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.response.BaseResponse;
import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.BuildConfig;
import com.liaocheng.suteng.myapplication.api.Api;
import com.liaocheng.suteng.myapplication.api.SYPTService;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.model.ServiceCenterBean;
import com.liaocheng.suteng.myapplication.model.Zcxiybean;
import com.liaocheng.suteng.myapplication.presenter.contract.MyContact;
import com.liaocheng.suteng.myapplication.presenter.contract.ZhuceContact;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyInfopresenter extends RxPresenter<MyContact.View> implements MyContact.Presenter{
    @Override
    public void getMyInfo() {
        addSubscribe(Api.createTBService().user_info(SPCommon.getString("token",""))
                .compose(RxUtil.<BaseResponse<MyBean>>rxSchedulerHelper())
                .compose(RxUtil.<MyBean>handleResult())
                .subscribeWith(new CommonSubscriber<MyBean>(mContext, true) {
                    @Override
                    protected void _onNext(MyBean commonRes) {

                        if (commonRes != null) {
                            mView.setMyInfo(commonRes);
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
    public void getKeFu(String qu) {
        addSubscribe(Api.createTBService().getServiceCenter(SPCommon.getString("token",""),qu)
                .compose(RxUtil.<BaseResponse<ServiceCenterBean>>rxSchedulerHelper())
                .compose(RxUtil.<ServiceCenterBean>handleResult())
                .subscribeWith(new CommonSubscriber<ServiceCenterBean>(mContext, true) {
                    @Override
                    protected void _onNext(ServiceCenterBean commonRes) {

                        if (commonRes != null) {
                            mView.setKeFu(commonRes);
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
