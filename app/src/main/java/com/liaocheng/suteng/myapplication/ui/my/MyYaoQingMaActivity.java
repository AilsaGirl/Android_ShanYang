package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.util.QRCodeUtil;
import com.liaocheng.suteng.myapplication.util.Util;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyYaoQingMaActivity extends BaseActivity implements IUiListener{
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.ivMa)
    ImageView ivMa;
    @BindView(R.id.tvMyMa)
    TextView tvMyMa;
    @BindView(R.id.ivQQ)
    ImageView ivQQ;
    @BindView(R.id.ivWeiXin)
    ImageView ivWeiXin;
    @BindView(R.id.ivPengYouQuan)
    ImageView ivPengYouQuan;

    @Override
    public int getLayoutId() {
        return R.layout.activity_youqingma;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("我的邀请码").setBackFinish();
        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap("http://47.95.197.109:8081/sypt/userregister.jsp?"+SPCommon.getString("userId",""), 480, 480);
        ivMa.setImageBitmap(mBitmap);
        tvMyMa.setText("我的邀请码："+SPCommon.getString("userId",""));
        mTencent = Tencent.createInstance(Util.getQQAppId(),MyYaoQingMaActivity.this);
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }

    }

    @OnClick({R.id.ivMa, R.id.ivQQ, R.id.ivWeiXin, R.id.ivPengYouQuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivMa:
                break;
            case R.id.ivQQ:
//                shareToQQzone();
                shareToQZone();
                break;
            case R.id.ivWeiXin:
                Bitmap thumbBmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.app_logo);
                Util.shareWeChat(mContext, "http://47.95.197.109:8081/sypt/userregister.jsp?"+SPCommon.getString("userId",""), "三羊跑腿", "三羊跑腿", thumbBmp);

                break;
            case R.id.ivPengYouQuan://
                Bitmap thumbBmp1 = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.app_logo);
                Util.sharePengYouQuan(mContext, "http://47.95.197.109:8081/sypt/userregister.jsp?"+SPCommon.getString("userId",""), "三羊跑腿", "三羊跑腿", thumbBmp1);

                break;
        }
    }
    private void shareToQQzone() {
        final Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE,QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "三羊跑腿");
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY,  "三羊跑腿");
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL,  "http://47.95.197.109:8081/sypt/userregister.jsp?"+SPCommon.getString("userId",""));
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add(SPCommon.getString("img",""));
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
        params.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT,  QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);

        mTencent.shareToQzone(MyYaoQingMaActivity.this, params, new BaseUiListener());
    }
    Tencent mTencent;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mTencent != null) {
            Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void shareToQZone() {
        Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE,QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "三羊跑腿");
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY,  "三羊跑腿");
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL,  "http://47.95.197.109:8081/sypt/userregister.jsp?"+SPCommon.getString("userId",""));
        ArrayList<String> imageUrls = new ArrayList<String>();
//        imageUrls.add(SPCommon.getString("img",""));
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://47.95.197.109:8081/sypt/headImg/favicon.png");

//        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
        params.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT,  QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        mTencent.shareToQQ(this, params, this);
    }

    @Override
    public void onComplete(Object o) {
        Toast.makeText(this, o.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(UiError uiError) {
        Toast.makeText(this, uiError.errorMessage + "--" + uiError.errorCode + "---" + uiError.errorDetail, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (mTencent != null) {
//            Tencent.onActivityResultData(requestCode, resultCode, data, this);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
                //V2.0版本，参数类型由JSONObject 改成了Object,具体类型参考api文档
                doComplete((JSONObject)response);
            }
        protected void doComplete(JSONObject values) {
                //分享成功
        }
        @Override
        public void onError(UiError e) {
            //在这里处理错误信息
            if(e.errorDetail == null){
                ToastUtil.show("没安装qq");
            }else{
                ToastUtil.show("分享失败:"+e.errorDetail);
            }

        }
        @Override
        public void onCancel() {
            //分享被取消
            ToastUtil.show("取消分享");
        }
    }
}
