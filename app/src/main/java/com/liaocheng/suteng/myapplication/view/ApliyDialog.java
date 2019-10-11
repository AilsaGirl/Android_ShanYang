package com.liaocheng.suteng.myapplication.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;

import java.util.List;

/**
 * 选择对话框
 *
 */

public class ApliyDialog extends Dialog  {
    private SelectDialogCancelListener mListener;
    private Activity mActivity;
    private Button mMBtn_Cancel;
    private TextView mTv_Title;
    private List<String> mName;
    private String mTitle;
    private boolean mUseCustomColor = false;
    private int mFirstItemColor;
    private int mOtherItemColor;


    /**
     * 取消事件监听接口
     *
     */
    private SelectDialogCancelListener mCancelListener;

    public interface SelectDialogCancelListener {
        public void onCancelClick(String id);
    }
String mNum = "";
    public ApliyDialog(Activity activity, int theme,String text,
                       SelectDialogCancelListener listener) {
        super(activity, theme);
        mActivity = activity;
        mCancelListener = listener;
        mNum = text;
        setCanceledOnTouchOutside(true);
    }
public void setGone(){
    tvYve.setVisibility(View.GONE);
    tvWanNengYve.setVisibility(View.GONE);
}
    public void setGones(){
        tvYve.setVisibility(View.GONE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.dialog_apliy,
                null);
        setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        Window window = getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = mActivity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = LayoutParams.MATCH_PARENT;
        wl.height = LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        onWindowAttributesChanged(wl);

        initViews();
    }
TextView tvZhiFuBao,tvWeiXin,tvWanNengYve,tvYve,tvNum;
    ImageView ivClose;
    Button zfBtn;
    String mId = "1";
    private void initViews() {
        tvZhiFuBao = (TextView) findViewById(R.id.tvZhiFuBao);
        tvWeiXin = (TextView) findViewById(R.id.tvWeiXin);
        tvWanNengYve = (TextView) findViewById(R.id.tvWanNengYve);
        tvYve = (TextView) findViewById(R.id.tvYve);
        tvNum = (TextView) findViewById(R.id.tvNum);
        ivClose = findViewById(R.id.ivClose);
        zfBtn =  findViewById(R.id.zfBtn);
        if (mNum.equals(".00")){
            tvNum.setText("0.00元");
        }else {
            tvNum.setText(mNum+"元");
        }

        zfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCancelListener != null) {
                    mCancelListener.onCancelClick(mId);
                }
                dismiss();
            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvZhiFuBao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mId = "1";
                tvZhiFuBao.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.zhifubaoz), null, mActivity.getResources().getDrawable(R.mipmap.zhong), null);
                tvWeiXin.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.wx), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);
                tvYve.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.yuee), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);
                tvWanNengYve.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.yuee), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);

            }
        });

        tvWeiXin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mId = "2";
                tvZhiFuBao.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.zhifubaoz), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);
                tvWeiXin.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.wx), null, mActivity.getResources().getDrawable(R.mipmap.zhong), null);
                tvYve.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.yuee), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);
                tvWanNengYve.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.yuee), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);

            }
        });

        tvWanNengYve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mId = "3";
                 tvZhiFuBao.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.zhifubaoz), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);
                tvWeiXin.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.wx), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);
                tvYve.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.yuee), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);
                tvWanNengYve.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.yuee), null, mActivity.getResources().getDrawable(R.mipmap.zhong), null);

            }
        });

        tvYve.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mId = "4";
                tvZhiFuBao.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.zhifubaoz), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);
                tvWeiXin.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.wx), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);
                tvYve.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.yuee), null, mActivity.getResources().getDrawable(R.mipmap.zhong), null);
                tvWanNengYve.setCompoundDrawablesWithIntrinsicBounds(mActivity.getResources().getDrawable(R.mipmap.yuee), null, mActivity.getResources().getDrawable(R.mipmap.weixuanzhongx), null);

            }
        });

    }




}
