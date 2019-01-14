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
 * name:xukang
 * company:山东博界科技
 * DATA: 2018/8/16-10:10
 * Descript:
 */
public class ZhuanRangDialog extends Dialog {
    private TextView tv_name;
    private String titleStr, okmName,nomName,dialogcontext;

    private View viewid;
    private TextView tv_quxiao;
    private LinearLayout linShuLiang;
    private boolean isbtn=false;
    EditText etTel,etNum;
    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private onOnOnclickListener onOnclickListener;//取消
    public ZhuanRangDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_youhuiquan);
        tv_name=findViewById(R.id.tv_konw_un);
        etTel=findViewById(R.id.etTel);
        etNum=findViewById(R.id.etNum);
        tv_quxiao=findViewById(R.id.tv_quxiao);
        viewid=findViewById(R.id.viewid);
        linShuLiang=findViewById(R.id.linShuLiang);
        linShuLiang.setVisibility(View.GONE);
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



        if (okmName != null) {
            tv_name.setText(okmName);
        }
        if(nomName !=null){
            tv_quxiao.setText(nomName);
        }

        if(isbtn==false){
            tv_quxiao.setVisibility(View.GONE);
            viewid.setVisibility(View.GONE);
        }else {
            tv_quxiao.setVisibility(View.VISIBLE);
            viewid.setVisibility(View.VISIBLE);
            tv_quxiao.setTextColor(Color.parseColor("#999999"));
            tv_name.setTextColor(Color.parseColor("#E03D81"));

        }
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

                    if (!TextUtils.isEmpty(etTel.getText().toString().trim())){
                        if (!isMobile(etTel.getText().toString().trim())){
                            ToastUtil.show("手机号格式不正确");
                            return;
                        }
                    }else {
                        ToastUtil.show("手机号不能为空");
                        return;
                    }
                    yesOnclickListener.onYesClick(etTel.getText().toString().trim());
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
        public void onYesClick(String tel);
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
