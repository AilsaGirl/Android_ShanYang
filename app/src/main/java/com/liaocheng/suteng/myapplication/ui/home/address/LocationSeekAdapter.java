package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.LocationBean;
import com.liaocheng.suteng.myapplication.model.POISearchResultBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class LocationSeekAdapter extends BaseAdapter {
    private Context ctx;
    private List<POISearchResultBean.TipsBean> list;

    public LocationSeekAdapter(Context context, List<POISearchResultBean.TipsBean> poiList) {
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
        LocationSeekAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new LocationSeekAdapter.ViewHolder();
            convertView = View.inflate(ctx, R.layout.item_poisearch, null);
            holder.poititle = (TextView) convertView.findViewById(R.id.poititle);
            holder.poititle2 = (TextView) convertView.findViewById(R.id.poititle2);
            convertView.setTag(holder);
        } else {
            holder = (LocationSeekAdapter.ViewHolder) convertView.getTag();
        }
        POISearchResultBean.TipsBean item = (POISearchResultBean.TipsBean) getItem(position);
        holder.poititle.setText(item.getName());
        holder.poititle2.setText(item.getDistrict());
        return convertView;
    }

    private class ViewHolder {
        TextView poititle;
        TextView poititle2;
    }
}
