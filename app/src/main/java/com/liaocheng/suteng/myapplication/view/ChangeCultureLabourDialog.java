package com.liaocheng.suteng.myapplication.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.liaocheng.suteng.myapplication.R;


/**
 * name:xukang
 * company:山东博界科技
 * DATA: 2018/8/20-9:21
 * Descript:
 */
public class ChangeCultureLabourDialog extends Dialog {
//    public ImageView iv_culture, iv_havelabour, iv_unemploytime;
//    public TextView tv_culture, tv_havelabour, tv_unemploytime, tv_changetitle, tv_changesure;
    public LinearLayout layout1,layout2,layout3;
    public ChangeCultureLabourDialog(@NonNull Context context) {
        super(context, R.style.CustomDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_dialog_un_time_labour_cultrue
        setContentView(R.layout.activity_logo_company);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        layout1=findViewById(R.id.ll_logo_paizhao);
        layout2=findViewById(R.id.ll_logo_xiangze);
        layout3=findViewById(R.id.ll_logo_quxiao);
//        iv_culture = findViewById(R.id.iv_culture);
//        iv_havelabour = findViewById(R.id.iv_havelabour);
//        iv_unemploytime = findViewById(R.id.iv_unemploytime);
//        tv_culture = findViewById(R.id.tv_culture);
//        tv_havelabour = findViewById(R.id.tv_havelabour);
//        tv_unemploytime = findViewById(R.id.tv_unemploytime);
//        tv_changetitle = findViewById(R.id.tv_changetitle);
//        tv_changesure = findViewById(R.id.tv_changesure);
//        lpv_change = findViewById(R.id.lpv_change);

    }


    public void ivCultureOnClick(View.OnClickListener onClickListener) {
        layout1.setOnClickListener(onClickListener);
        layout2.setOnClickListener(onClickListener);
        layout3.setOnClickListener(onClickListener);
    }

}
