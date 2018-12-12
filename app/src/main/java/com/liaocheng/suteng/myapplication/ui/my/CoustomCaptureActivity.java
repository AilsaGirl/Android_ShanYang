package com.liaocheng.suteng.myapplication.ui.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.liaocheng.suteng.myapplication.R;

import cn.com.bluemoon.cardocr.lib.base.BaseCaptureActivity;
import cn.com.bluemoon.cardocr.lib.common.CardType;

public class CoustomCaptureActivity extends BaseCaptureActivity {
    int i=1;


    /**
     * @param context
     * @param type        such as CardType.TYPE_ID_CARD_FRONT
     * @param requestCode
     * @param url         保存截图文件夹路径
     */
    public static void startAction(Activity context, CardType type, String url, int requestCode) {
        Intent intent = new Intent(context, CoustomCaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(CARD_TYPE, type);
        bundle.putString(URL, url);
        intent.putExtras(bundle);
        context.startActivityForResult(intent, requestCode);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_coustom_capture;
    }

    @Override
    public void initCustomView() {
        final ImageView imageBack = (ImageView) findViewById(R.id.image_back);
        final View btnTakePicture = findViewById(R.id.btn_take_picture);
        TextView txtTitle = (TextView) findViewById(R.id.txt_title);
        ImageView Iv_guohui=findViewById(R.id.Iv_guohui);
        ImageView Iv_renxiang=findViewById(R.id.Iv_renxiang);
        String titleResourceId;
        if (title == 0) {
            switch (cartType) {
                case TYPE_ID_CARD_FRONT:
                    titleResourceId ="请确保身份证头像面边缘在框内";
                    Iv_guohui.setVisibility(View.GONE);
                    Iv_renxiang.setVisibility(View.VISIBLE);
                    break;
                default:
                    titleResourceId = "请确保身份证国徽面边缘在框内";
                    Iv_guohui.setVisibility(View.VISIBLE);
                    Iv_renxiang.setVisibility(View.GONE);
                    break;
            }
            txtTitle.setText(titleResourceId);
        } else {
            txtTitle.setText(title);
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == imageBack) {
                    finish();
                } else if (v == btnTakePicture) {
                    identification();
                }
            }
        };
        imageBack.setOnClickListener(listener);
        btnTakePicture.setOnClickListener(listener);
    }

}
