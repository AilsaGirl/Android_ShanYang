package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.YuEMingXiBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class YuEListAdapter extends RecyclerView.Adapter<YuEListAdapter.ViewHolder> {


    private Context mContext;

    List<YuEMingXiBean.DataBean> mList = new ArrayList<>();

    private FollowClickListener mListener;


    public YuEListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<YuEMingXiBean.DataBean> mySendOrdersBean) {
        mList = mySendOrdersBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_yuemingxi, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final YuEMingXiBean.DataBean yebean = mList.get(position);

            holder.Disbursement.setText(yebean.detailName);
            holder.zhichuyue.setText(yebean.currentMoney);
            holder.jianqianshu.setText(yebean.detailMoney);
            holder.shijian.setText(yebean.createTime);
            holder.jiaoyidingdan.setText(yebean.orderCode);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.Disbursement)
        TextView Disbursement;
        @BindView(R.id.jianqianshu)
        TextView jianqianshu;
        @BindView(R.id.zhichuyue)
        TextView zhichuyue;
        @BindView(R.id.shijian)
        TextView shijian;
        @BindView(R.id.jiaoyidingdan)
        TextView jiaoyidingdan;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onFollowBtnClick(boolean isGuanzhu, String uid);
    }
}