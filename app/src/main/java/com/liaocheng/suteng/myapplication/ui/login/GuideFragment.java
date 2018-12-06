package com.liaocheng.suteng.myapplication.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.circle.common.util.SPCommon;
import com.liaocheng.suteng.myapplication.R;

/**
 * Created by Administrator
 */

@SuppressLint("ValidFragment")
public class GuideFragment extends Fragment {
    private int featuresResource;
    private Context c;
    @SuppressLint("ValidFragment")
    public GuideFragment(int featuresResource) {
        this.featuresResource = featuresResource;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        c=getActivity();
        View view=inflater.inflate(R.layout.fragment_guide, null);
        TextView tv_tiyan=(TextView)view.findViewById(R.id.tv_tiyan);
        ImageView Im_productfeatures = (ImageView) view.findViewById(R.id.im_productfeatures);
        switch (featuresResource) {
            case 0:
                Im_productfeatures.setBackgroundResource(R.mipmap.yindao1);
                tv_tiyan.setText("");
                break;
            case 1:
                tv_tiyan.setText("");
                Im_productfeatures.setBackgroundResource(R.mipmap.yindao2);
                break;
            case 2:
                tv_tiyan.setText("");
                Im_productfeatures.setBackgroundResource(R.mipmap.yindao3);
                break;
            case 3:
                tv_tiyan.setText("");
                Im_productfeatures.setBackgroundResource(R.mipmap.yindao4);
                break;
            case 4:
                tv_tiyan.setText("");
                Im_productfeatures.setBackgroundResource(R.mipmap.yindao5);
                break;
            case 5:
                tv_tiyan.setText("立即体验");
                Im_productfeatures.setBackgroundResource(R.mipmap.yindao6);
                tv_tiyan.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        SPCommon.setBoolean("IS_FIRST_ROOT",false);
                        Intent mIntent = new Intent(c, LoginActivity.class);
                        startActivity(mIntent);
                        getActivity().finish();
                    }
                });
                break;
        }
        return view;
    }

}
