package com.liaocheng.suteng.myapplication.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.circle.common.util.InputMethodUtil;
import com.liaocheng.suteng.myapplication.R;

public class PassWordDialog extends Dialog {
    public PassWordDialog(Context context) {
        super(context, R.style.CustomDialogStyle);
    }

    ImageView ivClose;
    TextView tvNum;
    PassWordEditText mPassWord;
    TextView tvError;
    TextView wangjipassword;
    TextView tvError_tishi;
    TextView tvTextNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_dialog);
        //按空白处能取消动画
        setCanceledOnTouchOutside(false);
        ivClose = (ImageView) findViewById(R.id.ivClose);
        tvNum = (TextView) findViewById(R.id.tvNum);
        mPassWord = (PassWordEditText) findViewById(R.id.pwEditText);
        mPassWord.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        tvError = (TextView) findViewById(R.id.tvError);
        tvTextNum = findViewById(R.id.tvTextNum);
        tvError_tishi = (TextView) findViewById(R.id.tvError_tishi);
        wangjipassword = (TextView) findViewById(R.id.wangjipassword);
//        InputMethodUtil.openKeybord(mPassWord);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                linInputNum.setVisibility(View.GONE);
                InputMethodUtil.closeKeybord(mPassWord);
                dismiss();
            }
        });
        wangjipassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPayClickListener.onSetPwd();
            }
        });

        mPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = mPassWord.getText();
                int len = editable.length();

                if (len == 4) {
                    String passWord = mPassWord.getText().toString();
                    onPayClickListener.onSetPass(passWord);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 设置提示内容
     *
     * @param str 内容
     */
    public void setHintText(String str) {
        tvNum.setText(str + "");
    }

    public void setErrorText(String str) {
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(str + "");
        mPassWord.setText("");
    }

    public void setError_tishi(String str) {
        tvError_tishi.setVisibility(View.VISIBLE);
        tvError_tishi.setText(str + "");
        mPassWord.setText("");
    }

    public void setMoneyNum(String str) {
        tvTextNum.setVisibility(View.VISIBLE);
        tvTextNum.setText("需支付" + str + "元");
    }

    OnPayClickListener onPayClickListener;

    public void setClick(OnPayClickListener onPayClick) {
        onPayClickListener = onPayClick;
    }


    public interface OnPayClickListener {
        void onSetPass(String text);

        void onSetPwd();


    }
}
