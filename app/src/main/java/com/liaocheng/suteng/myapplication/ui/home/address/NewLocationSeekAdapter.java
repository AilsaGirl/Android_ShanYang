package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.LocationBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class NewLocationSeekAdapter extends BaseAdapter {
    private Context ctx;
    private List<LocationBean> list;

    public NewLocationSeekAdapter(Context context, List<LocationBean> poiList) {
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
        NewLocationSeekAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new NewLocationSeekAdapter.ViewHolder();
            convertView = View.inflate(ctx, R.layout.item_poisearch, null);
            holder.poititle = (TextView) convertView.findViewById(R.id.poititle);
            holder.poititle2 = (TextView) convertView.findViewById(R.id.poititle2);
            convertView.setTag(holder);
        } else {
            holder = (NewLocationSeekAdapter.ViewHolder) convertView.getTag();
        }
        LocationBean item = (LocationBean) getItem(position);
        holder.poititle.setText(item.getTitle());
        holder.poititle2.setText(item.getContent());
        return convertView;
    }

    private class ViewHolder {
        TextView poititle;
        TextView poititle2;
    }
}
