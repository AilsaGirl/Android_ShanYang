package com.circle.common.update;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.circle.common.R;
import com.circle.common.util.DisplayUtil;


/**
 * Created by Administrator on 2017/4/28 0028.
 */

public class DownloadDialog extends Dialog {
    private TextView tvCancel;
    private ProgressBar progressBar;

    public DownloadDialog(Context context) {
        super(context, R.style.CustomDialog);

        setContentView(R.layout.common_dialog_download);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = DisplayUtil.getScreenWidth(getContext().getApplicationContext()); //设置宽度
        getWindow().setAttributes(lp);

        setCancelable(false);
        setCanceledOnTouchOutside(false);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvCancel = (TextView) findViewById(R.id.tvCancel);
    }

    public void setCancelClickListener(View.OnClickListener cancelListener) {
        tvCancel.setOnClickListener(cancelListener);
    }

    public void setProgress(int progress) {
        if (progressBar != null)
            progressBar.setProgress(progress);
    }
}
