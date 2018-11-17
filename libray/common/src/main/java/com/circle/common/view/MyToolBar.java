package com.circle.common.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.circle.common.R;

/**
 * Created by Circle on 2017/4/2 0002.
 */

public class MyToolBar extends Toolbar {
    private TextView tvBack, tvRight, tvTitle;
    private View toolBar, toolLayout;

    public MyToolBar(Context context) {
        this(context, null);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_toolbar, this, true);
        toolBar = view.findViewById(R.id.toolBar);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvBack = (TextView) view.findViewById(R.id.tvBack);
        tvRight = (TextView) view.findViewById(R.id.tvRight);
        toolLayout = view.findViewById(R.id.toolLayout);
    }


    /**
     * 返回按钮隐藏
     *
     * @return
     */
    public MyToolBar setBackGone() {
        tvBack.setVisibility(View.GONE);
        return this;
    }

    public MyToolBar setRight(String right, View.OnClickListener clickListener) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(right);
        tvRight.setOnClickListener(clickListener);
        return this;
    }

    public MyToolBar setRightGone(int Visible) {
        tvRight.setVisibility(Visible);
        return this;
    }

    //重载 设置右边 StringId方法
    public MyToolBar setRight(int right, View.OnClickListener clickListener) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(right);
        tvRight.setOnClickListener(clickListener);
        return this;
    }

    public MyToolBar setCommonBackgroundColor(int color) {
        toolBar.setBackgroundColor(color);
        return this;
    }

    public MyToolBar setRightTextColor(int color) {
        tvRight.setTextColor(color);
        return this;
    }

    public MyToolBar setToolLayoutBackgroundColor(int color) {
        toolLayout.setBackgroundColor(color);
        return this;
    }

    public MyToolBar setRightDrawable(Drawable drawable) {
        tvRight.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        return this;
    }
    public MyToolBar setRightbackground(int photo) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(photo), null);
        return this;
    }
    public MyToolBar setBackDrawable(Drawable drawable) {
        tvBack.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        return this;
    }

    public MyToolBar setBackFinish() {
        tvBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        return this;
    }

    public MyToolBar setLeftClick(View.OnClickListener clickListener) {
        tvBack.setOnClickListener(clickListener);
        return this;
    }
    public MyToolBar setRightClick(View.OnClickListener clickListener) {
        tvRight.setOnClickListener(clickListener);
        return this;
    }

    public MyToolBar setTitleText(String title) {
        tvTitle.setText(title);
        return this;
    }

    public MyToolBar setTitleText(int textId) {
        tvTitle.setText(textId);
        return this;
    }

    public MyToolBar setTitleColor(int textId) {
        tvTitle.setTextColor(textId);
        return this;
    }

    /**
     * 设置右边字体大小
     * @param textSize
     * @return
     */
    public MyToolBar setRightSize(int textSize) {
        tvRight.setTextSize((float) textSize);
        return this;
    }

    /**
     * 设置title字体大小
     * @param textSize
     * @return
     */
    public MyToolBar setTitleSize(int textSize) {
        tvTitle.setTextSize((float) textSize);
        return this;
    }

    /**
     * 标题设置图片
     *
     * @param resourceId
     */
    public MyToolBar setTitleRes(int resourceId) {
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(resourceId), null, null, null);
        return this;
    }

}
