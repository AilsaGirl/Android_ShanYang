package com.circle.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.circle.common.util.DisplayUtil;
import com.circle.common.util.TUtil;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/8 0008.
 */
/*
 *created by LHP at 2017/4/8 0008
 */
public abstract class BaseDialogFragment<T extends BasePresenter> extends DialogFragment implements BaseView {
    protected View rootView;
    public T mPresenter;

    protected Activity mActivity;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        mActivity =(Activity)context;
        mContext = context;
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(),container,false);
        onCreateView();
        return rootView;
    }

    public abstract void onCreateView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setClickable(true);
        ButterKnife.bind(this, rootView);
        mPresenter = TUtil.getT(this, 0);
        if(mPresenter!=null)
            mPresenter.attachView(this,mContext);
    }

    @Override
    public void onStart() {
        super.onStart();
        initEventAndData();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

//    /**
//     * 懒加载
//     * @param savedInstanceState
//     */
//    @Override
//    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
//        super.onLazyInitView(savedInstanceState);
//    }

    /**********子类实现**********/
    //获取布局文件
    public abstract int getLayoutId();

    //初始化view
    public abstract void initEventAndData();

    public boolean isFinish(){
        if(getActivity() == null || getActivity().isFinishing())
            return true;
        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public void useNightMode(boolean isNight) {

    }
    public void setStatusBarPadding(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setPadding(0, DisplayUtil.getStatusBarHeight(getActivity().getApplicationContext()), 0, 0);
        }
    }
}
