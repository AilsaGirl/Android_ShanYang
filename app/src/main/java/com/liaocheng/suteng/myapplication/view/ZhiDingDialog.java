package com.liaocheng.suteng.myapplication.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.circle.common.util.ToastUtil;
import com.liaocheng.suteng.myapplication.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * DATA: 2018/8/16-10:10
 * Descript:
 */
public class ZhiDingDialog extends Dialog {
    private TextView tvTitle,tvType,tvFaHuoDi,tvShouHuoDi,tvText;

    private View viewid;
    private TextView tv_quxiao,tv_konw_un;
    private LinearLayout linShuLiang;
    private boolean isbtn=false;
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private onOnOnclickListener onOnclickListener;//取消
    public ZhiDingDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_zhiding);
        tvTitle=findViewById(R.id.tvTitle);
        tvType=findViewById(R.id.tvType);
        tvFaHuoDi =findViewById(R.id.tvFaHuoDi);
        tvShouHuoDi=findViewById(R.id.tvShouHuoDi);
        tvText=findViewById(R.id.tvText);
        tv_konw_un=findViewById(R.id.tv_konw_un);
        tv_quxiao=findViewById(R.id.tv_quxiao);
        viewid=findViewById(R.id.viewid);
        linShuLiang=findViewById(R.id.linShuLiang);
//        linShuLiang.setVisibility(View.GONE);
        initData();
        initEvent();
    }
    public void setText(String str){
        mText = str;

    }
    String mText = "";
    public void setYesOnclickListener(onYesOnclickListener onYesOnclickListener) {

        this.yesOnclickListener = onYesOnclickListener;
    }
    public void setOnOnclickListener( onOnOnclickListener onOnOnclickListener) {

        this.onOnclickListener = onOnOnclickListener;
    }
    private String mTitle, mType,mFa,mShou,mAll;

    public void setData(String mTitle,String mType,String mFa,String mShou,String mAll) {
        this.mTitle = mTitle;
        this.mType = mType;
        this.mFa = mFa;
        this.mShou = mShou;
        this.mAll = mAll;
    }
    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        tvTitle.setText(mTitle+"");
        tvFaHuoDi.setText(mFa+"");
        tvShouHuoDi.setText(mShou+"");
        tvType.setText(mType+"");
        tvText.setText(mAll+"");
    }


//取消按钮隐藏
    public void setVisibilityBtn(boolean bacisbtn){
        isbtn=bacisbtn;
    }
    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        tv_konw_un.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {

                    yesOnclickListener.onYesClick();
                }
            }
        });
        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOnclickListener != null) {
                    onOnclickListener.onOnClick();
                }
            }
        });
    }
    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener {
        public void onYesClick();
    }
    public interface onOnOnclickListener {
        public void onOnClick();
    }
    //判断是不是手机号
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,5,6,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
