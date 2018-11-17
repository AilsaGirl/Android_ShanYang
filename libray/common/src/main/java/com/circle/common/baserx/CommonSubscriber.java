package com.circle.common.baserx;

import android.content.Context;

import com.circle.common.R;
import com.circle.common.app.BaseApplication;
import com.circle.common.response.BaseResponse;
import com.circle.common.response.CommonRes;
import com.circle.common.util.NetWorkUtils;
import com.circle.common.view.LoadingDialog;

import java.net.SocketTimeoutException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 订阅封装
 * Created by Circle on 2017/4/3 0003.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private Context mContext;
    private String dialogMsg;
    private LoadingDialog loadingDialog;

    private boolean showDialog = true;

    protected CommonSubscriber(Context context) {
        this(context, BaseApplication.getContext().getString(R.string.loading), false);
    }

    public CommonSubscriber(Context context, boolean showDialog) {
        this(context, BaseApplication.getContext().getString(R.string.loading), showDialog);
    }

    public CommonSubscriber(Context context, String dialogMsg, boolean showDialog) {
        this.mContext = context;
        this.dialogMsg = dialogMsg;
        this.showDialog = showDialog;
    }




    @Override
    protected void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                if (loadingDialog == null)
                    loadingDialog = new LoadingDialog(mContext);
                loadingDialog.setCancelable(true);
                loadingDialog.setMsg(dialogMsg);
                loadingDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(T t) {
        try {

            if (t instanceof BaseResponse) {
                if (((BaseResponse) t).isTokenExpire()) {
                    BaseApplication.getInstance().tokenExpire();
                }
            }
            _onNext(t);
        } catch (Exception e) {
            onError(e);
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable e) {
        if (showDialog && loadingDialog != null)
            loadingDialog.cancel();
        //网络
        if (!NetWorkUtils.isNetConnected(BaseApplication.getContext())) {
            _onError(BaseApplication.getContext().getString(R.string.no_net));
        } else if (e instanceof ApiException) {  //服务器
            _onError(e.toString());
        } else if(e instanceof SocketTimeoutException){//其它
            _onError(BaseApplication.getContext().getString(R.string.net_error));
        }else{
            _onError("");
        }
        e.printStackTrace();
    }


    @Override
    public void onComplete() {
        if (showDialog && loadingDialog != null)
            loadingDialog.cancel();
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
