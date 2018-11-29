package com.liaocheng.suteng.myapplication.ui.home.fahuo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;

import java.util.List;


/**
 * Created by bian on 2018/7/6.
 */
public class MyTimeAdapter extends BaseAdapter {

    List<String> mList;
    Context context;

    public MyTimeAdapter(List<String> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.time_item, null);
        }
        convertView.setBackgroundResource(R.drawable.selector);
        convertView = View.inflate(context, R.layout.time_item,null);
        TextView mItemTime = (TextView) convertView.findViewById(R.id.mItemTime);
        mItemTime.setText(mList.get(position));
        return convertView;
    }
}
