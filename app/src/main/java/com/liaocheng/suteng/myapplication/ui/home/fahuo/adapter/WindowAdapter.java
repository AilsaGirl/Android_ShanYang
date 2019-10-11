package com.liaocheng.suteng.myapplication.ui.home.fahuo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.liaocheng.suteng.myapplication.R;

public class WindowAdapter implements AMap.InfoWindowAdapter, AMap.OnMarkerClickListener,
        AMap.OnInfoWindowClickListener{

    private Context context;
    private static final String TAG = "WindowAdapter";

    public WindowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        //关联布局
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_fujin, null);
        //标题
        TextView title = (TextView) view.findViewById(R.id.tvName);
        //地址信息
        TextView address = (TextView) view.findViewById(R.id.tvPhone);

        title.setText(marker.getTitle());
        address.setText(marker.getSnippet());

        return view;
    }

    //如果用自定义的布局，不用管这个方法,返回null即可
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    // marker 对象被点击时回调的接口
    // 返回 true 则表示接口已响应事件，否则返回false
    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }

    //绑定信息窗点击事件
    @Override
    public void onInfoWindowClick(Marker marker) {

    }
}

