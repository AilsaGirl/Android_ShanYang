package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.LocationBean;

import java.util.List;

/**
 * 业务：高德poi列表
 * Created by 徐洪泽个 on 2018/3/29 0029.
 */

public class PoiSearchAdapter extends BaseAdapter  {
    private Context ctx;
    private List<LocationBean> list;

    public PoiSearchAdapter(Context context, List<LocationBean> poiList) {
        this.ctx = context;
        this.list = poiList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(ctx, R.layout.item_poisearch, null);
            holder.poititle = (TextView) convertView.findViewById(R.id.poititle);
            holder.poititle2 = (TextView) convertView.findViewById(R.id.poititle2);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.lin);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        LocationBean item = (LocationBean) getItem(position);
        holder.poititle.setText(item.getTitle());
        holder.poititle2.setText(item.getContent());
        if (position==0){
            holder.linearLayout.setBackgroundColor(0xffffffff);
        }else {
            holder.linearLayout.setBackgroundColor(0xfff8f8f8);
        }
        return convertView;
    }

    private class ViewHolder {
        TextView poititle;
        TextView poititle2;
        LinearLayout  linearLayout;
    }
}