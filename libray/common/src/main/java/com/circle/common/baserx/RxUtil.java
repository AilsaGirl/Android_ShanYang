package com.circle.common.baserx;

import android.text.TextUtils;

import com.circle.common.app.BaseApplication;
import com.circle.common.response.BaseRespons;
import com.circle.common.response.BaseResponse;
import com.circle.common.response.CommonRes;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param
     * @return
     */
    public static <BaseRespons> FlowableTransformer<BaseRespons, BaseRespons> handleResults() {   //compose判断结果
        return new FlowableTransformer<BaseRespons, BaseRespons>() {
            @Override
            public Flowable<BaseRespons> apply(Flowable<BaseRespons> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<BaseRespons, Flowable<BaseRespons>>() {
                    @Override
                    public Flowable<BaseRespons> apply(BaseRespons baseResponse) {
                        if (baseResponse != null) {
//                            baseResponse.result = (T) baseResponse;
                            return createData(baseResponse);
                        } else {
                            return Flowable.error(new ApiException("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseResponse<T>, T> handleResult() {   //compose判断结果
        return new FlowableTransformer<BaseResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<BaseResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<BaseResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(BaseResponse<T> baseResponse) {
                        if (baseResponse.isTokenExpire()) {
                            BaseApplication.getInstance().tokenExpire();

                            return Flowable.error(new ApiException(baseResponse.msg));
                        }else if (baseResponse.success()) {
                            if (baseResponse.result != null) {
                                return createData(baseResponse.result);
                            } else {
                                ToastUtil.show(baseResponse.msg);
                                return null;
                            }
                        } else if (baseResponse.code==406){
                            BaseApplication.getInstance().tokenExpire();
                            return null;
                        } else {
                            if (baseResponse == null || TextUtils.isEmpty(baseResponse.msg)) {
                                return Flowable.error(new ApiException("服务器返回error"));
                            } else {
                                return Flowable.error(new ApiException(baseResponse.msg));
                            }
                        }
                    }
                });
            }
        };
    }
    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

}
