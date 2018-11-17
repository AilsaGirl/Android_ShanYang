package com.liaocheng.suteng.myapplication.api;


import com.circle.common.baserx.CommonSubscriber;
import com.circle.common.baserx.RxUtil;
import com.circle.common.util.DeviceUtil;
import com.liaocheng.suteng.myapplication.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wei on 2018/1/11 0028.
 */

public class Api {
    //读超时长，单位：毫秒
    private static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    private static final int CONNECT_TIME_OUT = 7676;
    private static final int TYPE_TB = 0;

    private static final String HTTP_BASE_HOST = BuildConfig.SYPT_Url;
    private static Retrofit initRetrofit(int type) {
        //开启Log
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                LogManager.LogShow("http=="+message);
//            }
//        });
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor headerIntercepteor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .header("User-Agent", DeviceUtil.getPhoneBrand() + "-" + DeviceUtil.getPhoneModel() + "-" + DeviceUtil.getBuildVersion() + " -appversionName:" + DeviceUtil.getVersionName(MyApplication.getContext()))
                        .build();
                return chain.proceed(request);
            }
        };


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(headerIntercepteor);
        if (BuildConfig.LOG_DEBUG) {
            builder.addInterceptor(loggingInterceptor);
        }

        String host = "";
        if (type == TYPE_TB) {
            host =HTTP_BASE_HOST+"/";
        }
        if (host.endsWith("\\?")) {
            host = host.substring(0, host.length() - 1);
        }
        return new Retrofit.Builder().client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(host).build();


    }

    private static SYPTService PTService;


    public synchronized static SYPTService createTBService() {
        if (PTService == null)
            return PTService = initRetrofit(TYPE_TB).create(SYPTService.class);
        else
            return PTService;
    }


    public static <T> Flowable<T> toScheculer(Flowable<T> flowable) {
        return flowable.compose(RxUtil.<T>rxSchedulerHelper());
    }
    public static <T> void toSubscriber(Flowable<T> flowable, CommonSubscriber subscriber) {
        addSubscribe(flowable.subscribeWith(subscriber));
    }


    private static CompositeDisposable mCompositeDisposable;

    private  static void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected  static  void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}

