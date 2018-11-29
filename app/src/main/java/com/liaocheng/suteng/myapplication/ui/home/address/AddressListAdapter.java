package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.ChangYongAddressBean;
import com.liaocheng.suteng.myapplication.model.MyAddressInfoBean;
import com.liaocheng.suteng.myapplication.model.MyLiveList;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by guohao on 2017/9/6.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {



    private List<ChangYongAddressBean.ChangYongAddressModel> mMyLiveList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;


    public void setData(List<ChangYongAddressBean.ChangYongAddressModel> mMyLiveList){
        this.mMyLiveList = mMyLiveList;
        notifyDataSetChanged();
    }
    public List<ChangYongAddressBean.ChangYongAddressModel> getMyLiveList() {
        if (mMyLiveList == null) {
            mMyLiveList = new ArrayList<>();
        }
        return mMyLiveList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.tvDiZhi = (TextView) view.findViewById(R.id.tvDiZhi);
        holder.tvDiZhiXiangQing = (TextView) view.findViewById(R.id.tvDiZhiXiangQing);
        holder.tvTel = (TextView) view.findViewById(R.id.tvTel);

        return holder;
    }

    @Override
    public int getItemCount() {
        return mMyLiveList==null?0:mMyLiveList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvDiZhi.setText(mMyLiveList.get(position).sendAddress +"");
        holder.tvDiZhiXiangQing.setText(mMyLiveList.get(position).sendConcreteAdd +"");
        holder.tvTel.setText(mMyLiveList.get(position).sendPhone +"");

        holder.itemView.setTag(position);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(position);
            }
        });

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos);
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDiZhi;
        TextView tvTel;
        TextView tvDiZhiXiangQing;
        RelativeLayout mRootView;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
