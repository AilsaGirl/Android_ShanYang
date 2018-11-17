package com.circle.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.circle.common.R;
import com.circle.common.app.BaseApplication;
import com.circle.common.util.DisplayUtil;
import com.circle.common.util.StatusBarUtils;
import com.circle.common.util.TUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Circle on 2017/4/2 0002.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    protected View rootView;
    public T mPresenter;

    protected Activity mActivity;
    protected Context mContext;


    protected boolean isVisible;
    private boolean isInit = false;
    private boolean isLoad = false;

    Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        mActivity =(Activity)context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    public void setStatusBarPadding(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setPadding(0, DisplayUtil.getStatusBarHeight(BaseApplication.getInstance()), 0, 0);
        }
    }
    public void setTitleColor(int color){
        StatusBarUtils.with(getActivity()).setDrawable(getResources().getDrawable(R.drawable.toolbar_background)).init();
    }
    /**
     * 懒加载
     */
    protected void lazyLoad() {
        if (!isInit || !isVisible || isLoad) {
            return;
        }
        initEventAndData();
        isLoad = true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            unbinder =  ButterKnife.bind(this, rootView);
            mPresenter = TUtil.getT(this, 0);
            if (mPresenter != null)
                mPresenter.attachView(this, mContext);
            isInit = true;
            lazyLoad();
        }else{
            ViewGroup p = (ViewGroup) rootView.getParent();
            if (p != null) {
                p.removeView(rootView);
            }
        }
        initEventAndDataNoLazy();
        return rootView;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

//    /**
//     * 懒加载
//     * @param savedInstanceState
//     */
//    @Override
//    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
//        super.onLazyInitView(savedInstanceState);
//        initEventAndData();
//    }

    /**********子类实现**********/
    //获取布局文件
    public abstract int getLayoutId();

    /**
     * 懒加载初始化
     */
    public abstract void initEventAndData();

    /**
     * 非懒加载
     */
    public void initEventAndDataNoLazy(){}

    public boolean isFinish(){
        if(getActivity() == null || getActivity().isFinishing())
            return true;
        return false;
    }


    @Override
    public void onDestroyView() {
        isInit = false;
        isLoad = false;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        if(unbinder != null) {
            unbinder.unbind();
            unbinder= null;
        }
        rootView = null;
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    /**
     * 刷新数据
     */
    public void refreshData(){

    }


}
