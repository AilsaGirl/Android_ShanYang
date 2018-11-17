package com.liaocheng.suteng.myapplication.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.squareup.picasso.Picasso;


/**
 * Created by Administrator on 2017/6/2 0002.
 */

public class CustomMenuView extends RelativeLayout {
    private TextView tvLeft, tvRight;
    private ImageView ivLeft, ivRight;
    private View viewUnderLine,viewTopLine;
    private RelativeLayout relCustomMenu;

    public CustomMenuView(Context context) {
        super(context, null);
    }

    public CustomMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        initView(context);
        initCustomData(context,attrs);
    }

    public CustomMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initCustomData(context,attrs);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_menu, this);
        tvLeft = (TextView) view.findViewById(R.id.tvLeft);
        tvRight = (TextView) view.findViewById(R.id.tvRight);
        ivLeft = (ImageView) view.findViewById(R.id.ivLeft);
        ivRight = (ImageView) view.findViewById(R.id.ivRight);
        viewUnderLine = view.findViewById(R.id.view_bottom);
        viewTopLine = view.findViewById(R.id.view_top);
        relCustomMenu = (RelativeLayout) view.findViewById(R.id.relCustomMenu);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initCustomData(Context context,AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CustomMenuView);

           /* if (a.hasValue(R.styleable.CustomMenuView_foreground)) {
                Drawable foreground = a.getDrawable(R.styleable.CustomMenuView_foreground);
                if (foreground != null) this.setBackground(foreground);
            }*/
            if (a.hasValue(R.styleable.CustomMenuView_hlMenuLeftText)) {
                String leftTv = a.getString(R.styleable.CustomMenuView_hlMenuLeftText);
                if (null != leftTv){
                    tvLeft.setText(leftTv);
                }
            }
            if (a.hasValue(R.styleable.CustomMenuView_hlMenuLeftIcon)) {
                Drawable iconDrawable = a.getDrawable(R.styleable.CustomMenuView_hlMenuLeftIcon);
                if (iconDrawable != null) ivLeft.setImageDrawable(iconDrawable);
            }

            if (a.hasValue(R.styleable.CustomMenuView_hlMenuRightIcon)) {
                Drawable iconDrawable = a.getDrawable(R.styleable.CustomMenuView_hlMenuRightIcon);
                if (iconDrawable != null) ivRight.setImageDrawable(iconDrawable);
            }



            if (a.hasValue(R.styleable.CustomMenuView_hlMenuRightText)) {
                String rightTv = a.getString(R.styleable.CustomMenuView_hlMenuRightText);
                if (null != rightTv) tvRight.setText(rightTv);
            }

            if (a.hasValue(R.styleable.CustomMenuView_hlMenuLeftTextColor)) {
                int leftTvColor = a.getColor(R.styleable.CustomMenuView_hlMenuLeftTextColor, Color.BLACK);
                tvLeft.setTextColor(leftTvColor);
            }

            if (a.hasValue(R.styleable.CustomMenuView_hlMenuRightTextColor)) {
                int rightTvColor = a.getColor(R.styleable.CustomMenuView_hlMenuRightTextColor, Color.BLACK);
                tvRight.setTextColor(rightTvColor);
            }

            if (a.hasValue(R.styleable.CustomMenuView_hlMenuLeftTextSize)) {
                int leftTextSize = a.getDimensionPixelSize(R.styleable.CustomMenuView_hlMenuLeftTextSize,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX,leftTextSize);
            }

            if (a.hasValue(R.styleable.CustomMenuView_hlMenuRightTextSize)) {
                int rightTextSize = a.getDimensionPixelSize(R.styleable.CustomMenuView_hlMenuRightTextSize,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX,rightTextSize);
            }

            if (a.hasValue(R.styleable.CustomMenuView_hlMenuLeftIconVisible)) {
                switch (a.getInt(R.styleable.CustomMenuView_hlMenuLeftIconVisible,0)) {
                    case 0:
                        ivLeft.setVisibility(VISIBLE);
                        break;
                    case 4:
                        ivLeft.setVisibility(INVISIBLE);
                        break;
                    case 8:
                        ivLeft.setVisibility(GONE);
                        break;
                }
            }

            if (a.hasValue(R.styleable.CustomMenuView_hlMenuRightIconVisible)) {
                switch (a.getInt(R.styleable.CustomMenuView_hlMenuRightIconVisible,0)) {
                    case 0:
                        ivRight.setVisibility(VISIBLE);
                        break;
                    case 4:
                        ivRight.setVisibility(INVISIBLE);
                        break;
                    case 8:
                        ivRight.setVisibility(GONE);
                        break;
                }
            }
            if (a.hasValue(R.styleable.CustomMenuView_hlMenuRightTextVisible)) {
                switch (a.getInt(R.styleable.CustomMenuView_hlMenuRightTextVisible,0)) {
                    case 0:
                        tvRight.setVisibility(VISIBLE);
                        break;
                    case 4:
                        tvRight.setVisibility(INVISIBLE);
                        break;
                    case 8:
                        tvRight.setVisibility(GONE);
                        break;
                }
            }
            if (a.hasValue(R.styleable.CustomMenuView_hlMenuUnderLineVisible)) {
                switch (a.getInt(R.styleable.CustomMenuView_hlMenuUnderLineVisible,0)) {
                    case 0:
                        viewUnderLine.setVisibility(VISIBLE);
                        break;
                    case 4:
                        viewUnderLine.setVisibility(INVISIBLE);
                        break;
                    case 8:
                        viewUnderLine.setVisibility(GONE);
                        break;
                }
            }  if (a.hasValue(R.styleable.CustomMenuView_hlMenuTopLineVisible)) {
                switch (a.getInt(R.styleable.CustomMenuView_hlMenuTopLineVisible,0)) {
                    case 0:
                        viewTopLine.setVisibility(VISIBLE);
                        break;
                    case 4:
                        viewTopLine.setVisibility(INVISIBLE);
                        break;
                    case 8:
                        viewTopLine.setVisibility(GONE);
                        break;
                }
            }
            a.recycle();
        }

    }

    /**
     * 设置最小高度
     *
     * @param pixels
     * @return
     */
    public CustomMenuView setMinHeight(int pixels) {
        relCustomMenu.setMinimumHeight(pixels);
        return this;
    }

    /**
        * 设置左右textview颜色
                */
        public CustomMenuView setLeftTextColor(int color) {
            tvLeft.setTextColor(color);
            return this;
    }

    public CustomMenuView setRightTextColor(int color) {
        tvRight.setTextColor(color);
        return this;
    }

    /**
     * 设置左Textview的content
     *
     * @param tvContent_left
     */
    public CustomMenuView setLeftText(String tvContent_left) {
        tvLeft.setText(tvContent_left);
        return this;
    }

    /**
     * 设置右Textview的content
     *
     * @param tvContent_right
     */
    public CustomMenuView setRightText(String tvContent_right) {
        tvRight.setText(tvContent_right);
        return this;
    }

    /**
     * 设置iconLeft
     *
     * @param iconId_left
     */
    public CustomMenuView setLeftImage(int iconId_left) {
        if (iconId_left != 0) ivLeft.setImageResource(iconId_left);
        return this;
    }
    /**
     * 设置iconLeft
     *
     */
    public CustomMenuView setLeftImage(String imgUrl) {
        if (!TextUtils.isEmpty(imgUrl)) Picasso.with(getContext()).load(imgUrl)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivLeft);
        return this;
    }
    /**
     * 设置iconRight
     *
     * @param iconId_right
     */
    public CustomMenuView setRightImage(int iconId_right) {
        if (iconId_right != 0) ivRight.setImageResource(iconId_right);
        return this;
    }

    /**
     * 设置底部分割线的visible
     *
     * @param visible
     */
    public CustomMenuView setUnderLineVisable(int visible) {
        viewUnderLine.setVisibility(visible);
        return this;
    }
    /**
     * 设置top分割线的visible
     *
     * @param visible
     */
    public CustomMenuView setTopLineVisable(int visible) {
        viewTopLine.setVisibility(visible);
        return this;
    }
    /**
     * 设置右边textviewvisible
     *
     * @param visible
     */
    public void setRightTextViewVisible(int visible) {
        tvRight.setVisibility(visible);

    }
    /**
     * 得到右边textview的值
     *
     * @param
     */
    public String getRightText() {
        return tvRight.getText().toString();

    }
    /**
     * 设置右侧iv的visible
     * @param visible
     */
    public void setRightImageViewVisible(int visible) {
        ivRight.setVisibility(visible);
    }

    /**
     * 设置左侧iv的visible
     * @param visible
     */
    public void setLeftImageViewVisible(int visible) {
        ivLeft.setVisibility(visible);
    }


}


