package com.liaocheng.suteng.myapplication.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.view.loop.LoopView;

import java.util.ArrayList;
import java.util.List;

/**
 * 分享Dialog
 * Created by LHB on 2018/6/6 0006.
 */

public class MemberDialog extends Dialog {

    public LoopView wheelPicker;
    TextView tv_quxiao;
    TextView tv_queding;
    Context context;
    public MemberDialog(@NonNull Context context) {

        super(context, R.style.CustomDialogStyle);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.item_member_dialog_code);
        tv_quxiao=(TextView)findViewById(R.id.tv_quxiao);
        tv_queding=(TextView)findViewById(R.id.tv_queding);
        wheelPicker = (LoopView) findViewById(R.id.wheel_picker);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        wheelPicker.setNotLoop();//屏蔽无线循环
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
    }
    public  void setTextViewB(View.OnClickListener onClickListener){
        tv_quxiao.setOnClickListener(onClickListener);
    }
    public void setTextViewP(View.OnClickListener onClickListener){
        tv_queding.setOnClickListener(onClickListener);
    }
    List<String> datas = new ArrayList<>();
    public void setWheelPicker(List<String> data){
        if (data!=null){
            datas.clear();
            for (int i =0;i<data.size();i++){
                String s = data.get(i);
                if (s.length()>10){
                    s = s.substring(0,10)+"...";
                }
                datas.add(s);
            }
            wheelPicker.setItems(datas);
        }else {
            wheelPicker.setItems(data);
        }

    }
    public void setNolop(){
        wheelPicker.setNotLoop();
    }



}
