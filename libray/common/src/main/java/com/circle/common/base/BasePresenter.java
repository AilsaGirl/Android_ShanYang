package com.circle.common.base;

import android.content.Context;

/**
 * Created by Circle on 2017/4/2 0002.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view, Context context);

    void detachView();
}

