package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;
import com.liaocheng.suteng.myapplication.ui.my.OneActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class GuiZeListAdapter extends RecyclerView.Adapter<GuiZeListAdapter.ViewHolder> {

    private Context mContext;

    List<GuiZeModel.DataBean> mList = new ArrayList<>();

    private FollowClickListener mListener;


    public GuiZeListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<GuiZeModel.DataBean> mySendOrdersBean) {
        mList = mySendOrdersBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_xinwen, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final GuiZeModel.DataBean ordersModel = mList.get(position);
            holder.tvTitle.setText("三羊规则");
            holder.tvTime.setText("" + ordersModel.createTime + "");
            holder.tvContent.setText("" + ordersModel.content + "");

           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent();
                   intent.putExtra("type","2");
                   intent.putExtra("title",ordersModel.title+"");
                   intent.putExtra("content",ordersModel.content+"");
                   intent.setClass(mContext,OneActivity.class);
                   mContext.startActivity(intent);
               }
           });

        }

    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvContent)
        TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onFollowBtnClick(boolean isGuanzhu, String uid);
    }
}