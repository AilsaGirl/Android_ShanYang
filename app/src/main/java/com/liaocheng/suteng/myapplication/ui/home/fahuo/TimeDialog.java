package com.liaocheng.suteng.myapplication.ui.home.fahuo;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;

/**
 * Created by bian on 2018/1/9.
 */
public class TimeDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private int layoutResID;

    /**
     * 要监听的控件id
     */
    private int[] listenedItems;

    private OnCenterItemClickListener listener;

    public TimeDialog(Context context, int layoutResID, int[] listenedItems) {
        super(context, R.style.MysjDialog);
        this.context = context;
        this.layoutResID = layoutResID;
        this.listenedItems = listenedItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Window window = getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        //window.setWindowAnimations(R.style.bottom_menu_animation); // 添加动画效果
        setContentView(layoutResID);
        // 宽度全屏
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth()*3/4; // 设置dialog宽度为屏幕的4/5
        getWindow().setAttributes(lp);
        // 点击Dialog外部消失
        setCanceledOnTouchOutside(false);
        /*
        把更新的标题及版本号写在外面的activity
        TextView tvUpdateTitle = (TextView) window.findViewById(R.id.tvUpdateTitle);
        tvUpdateTitle.setText(Html.fromHtml("<font color=\"#00bfff\" size=\"13sp\">发现新版本：</font>"+"V 1.1"));*/
       /*
        把更新内容写在外面的activity
        TextView dialog_msg_tv = (TextView) window.findViewById(R.id.dialog_msg_tv);
        dialog_msg_tv.setText("1.新增首页名医医生分页功能\n" +
                "2.合作医院详情改版，增加地图导航功能\n" +
                "3.增添支付宝付款和支付宝充值\n" +
                "4.新增分享App功能\n" +
                "5.部分功能页面优化");*/
        //点击Dialog右上角X号 dismiss Dialog
        TextView dialog_cancle_tv = (TextView) window.findViewById(R.id.dialog_cancle_tv);
        dialog_cancle_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        for (int id : listenedItems) {
            findViewById(id).setOnClickListener(this);
        }
    }

    public interface OnCenterItemClickListener {

        void OnCenterItemClick(TimeDialog dialog, View view);

    }

    public void setOnCenterItemClickListener(OnCenterItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        dismiss();
        listener.OnCenterItemClick(this, view);
    }
}
