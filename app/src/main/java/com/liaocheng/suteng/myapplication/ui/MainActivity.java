package com.liaocheng.suteng.myapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.eyesutils.Eyes;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.ui.login.LoginActivity;
import com.liaocheng.suteng.myapplication.view.UPMarqueeView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ivBackground)
    Banner ivBackground;
    @BindView(R.id.ivGG)
    ImageView ivGG;
    @BindView(R.id.vf)
    UPMarqueeView vf;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    List<View> views = new ArrayList<>();
    @Override
    public void initEventAndData() {
        setTransparentStatusBar();
        List<String> list = new ArrayList<>();
        list.add("https://dev.emintong.com/uploads/20180622/jpeg/152963986346676.jpeg");
        list.add("https://dev.emintong.com/uploads/0518/jpeg/152661582624813.jpeg");
        list.add("https://dev.emintong.com/uploads/0621/jpeg/152954437072059.jpeg");
        ivBackground.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                if (path instanceof String) {
                    Picasso.with(context).load((String) path).into(imageView);
                }

            }
        }).setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

//                        Intent intent = new Intent(mContext, PartyBuilding5Activity.class);
//                        intent.putExtra("urlh5", model.value);
//                        mContext.startActivity(intent);


            }
        }).start();
        for (int i = 0; i < 5; i++) {
            LayoutInflater inflater3 = LayoutInflater.from(mContext);
            View view = inflater3.inflate(R.layout.shouye_gg, null);
//                holder.vf.removeView(view);
            TextView tvTitle1 = (TextView) view
                    .findViewById(R.id.tvTitle1);

            tvTitle1.setText("我是第"+i+"条");

          vf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, CommunalMinutesH5Activity.class);
//                    intent.putExtra("kangyang", "康养列表");
//                    mContext.startActivity(intent);
                }
            });
            views.add(view);
        }
       vf.setViews(views);

    }

    @OnClick({R.id.vf, R.id.linFaHuo, R.id.linQiangDan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vf:
                break;
            case R.id.linFaHuo:
                Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                break;
            case R.id.linQiangDan:
                break;
        }
    }

    @Override
    public void showError(int reqCode, String msg) {

    }
}
