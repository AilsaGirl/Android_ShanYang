package com.liaocheng.suteng.myapplication.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;


/**
 * DATA: 2018/8/16-10:10
 * Descript:
 */
public class ApplyAndAlterDialog extends Dialog {
    private TextView tv_name,tv_commit_un,tv_committext;
    private String titleStr, okmName,nomName,dialogcontext;
    private ImageView iv_zhuang;
    private boolean isbac=false;
    private View viewid;
    private TextView tv_quxiao;
    private boolean isbtn=false;
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private onOnOnclickListener onOnclickListener;//取消
    public ApplyAndAlterDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_unemployment);
        tv_name=findViewById(R.id.tv_konw_un);
        tv_commit_un=findViewById(R.id.tv_commit_un);
        iv_zhuang=findViewById(R.id.iv_zhuang);
        tv_committext=findViewById(R.id.tv_commit_context);
        tv_quxiao=findViewById(R.id.tv_quxiao);
        viewid=findViewById(R.id.viewid);
        initData();
        initEvent();
    }
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            okmName = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }
    public void setOnOnclickListener(String str, onOnOnclickListener onOnOnclickListener) {
        if (str != null) {
            nomName = str;
        }
        this.onOnclickListener = onOnOnclickListener;
    }

    public void setTv_name(TextView tv_name) {
        this.tv_name = tv_name;
    }
    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message

        if (titleStr != null) {
            tv_commit_un.setText(titleStr);
        }
        if(dialogcontext!=null){
            tv_committext.setText(dialogcontext);
        }

        if (okmName != null) {
            tv_name.setText(okmName);
        }
        if(nomName !=null){
            tv_quxiao.setText(nomName);
        }
        if(isbac==false){
            iv_zhuang.setBackgroundResource(R.mipmap.chenggong);
        }else if(isbac==true){
            iv_zhuang.setBackgroundResource(R.mipmap.shibai);
        }
        if(isbtn==false){
            tv_quxiao.setVisibility(View.GONE);
            viewid.setVisibility(View.GONE);
        }else {
            tv_quxiao.setVisibility(View.VISIBLE);
            viewid.setVisibility(View.VISIBLE);
            tv_quxiao.setTextColor(Color.parseColor("#999999"));
            tv_name.setTextColor(Color.parseColor("#333333"));

        }
    }
    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message,String context) {
        titleStr = message;
        dialogcontext=context;
    }
//    //图片
//    public void setImg(boolean bacNum){
//        isbac=bacNum;
//    }
    //图片
    public void setBackgroundResource(boolean bacNum){
        isbac=bacNum;
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
        tv_name.setOnClickListener(new View.OnClickListener() {
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
}
