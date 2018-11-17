package com.circle.common.update;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.circle.common.R;
import butterknife.ButterKnife;


/**
 *
 */

public class UpdateDialog extends Dialog {
    private TextView tvVersion, tvContent, tvUpdate,tvNoUpdate;
    private OnDialogClickListener clickListener;
    private boolean isShowCancle = true;// 是否显示取消 差号
    private String mVersions, mContent;
    View view;

    public UpdateDialog(Context context, boolean isShowCancle, String versions, String updataContent, OnDialogClickListener listener) {
        super(context, R.style.UpdateDialog);
        this.isShowCancle = isShowCancle;
        this.mVersions = versions;
        this.mContent = updataContent;
        this.clickListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.tb_update_dialog);
        ButterKnife.bind(this, this);
        tvVersion = (TextView) findViewById(R.id.tvVersion);
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvUpdate = (TextView) findViewById(R.id.tvUpdate);
        tvNoUpdate = (TextView) findViewById(R.id.tvNoUpdate);
        view = (View)findViewById(R.id.view);
        if (isShowCancle) {//是否可以取消更新
            tvNoUpdate.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
        } else {
            tvNoUpdate.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }
        this.setCancelable(isShowCancle);
        setCanceledOnTouchOutside(isShowCancle);
        tvVersion.setText(mVersions);
        tvContent.setText(mContent);
        initListener();
    }

    /**
     * 设置按钮点击事件
     */
    private void initListener() {
        tvNoUpdate.setOnClickListener(new View.OnClickListener() {//关闭按钮点击
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onCancelClick(UpdateDialog.this);
                } else {
                    cancel();
                }
            }
        });
        tvUpdate.setOnClickListener(new View.OnClickListener() {//立即升级点击
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onOkClick(UpdateDialog.this);
                } else {
                    cancel();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (clickListener != null) {
            clickListener.onCancelClick(UpdateDialog.this);
        }
    }

    public interface OnDialogClickListener {
        void onOkClick(Dialog dialog);

        void onCancelClick(Dialog dialog);
    }
}
