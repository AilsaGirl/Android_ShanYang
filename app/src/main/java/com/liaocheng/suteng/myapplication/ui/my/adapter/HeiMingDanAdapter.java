package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 接单
 * Created by weihongfang
 */

public class HeiMingDanAdapter extends RecyclerView.Adapter<HeiMingDanAdapter.ViewHolder> {


    private Context mContext;

    List<GongNengModel.DataBean> mList = new ArrayList<>();

    private FollowClickListener mListener;


    public HeiMingDanAdapter(Context context,FollowClickListener mListener) {
        this.mListener = mListener;
        this.mContext = context;
    }

    public void setData(List<GongNengModel.DataBean> mySendOrdersBean) {
        mList = mySendOrdersBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_heimingdan, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final GongNengModel.DataBean ordersModel = mList.get(position);
            if (!TextUtils.isEmpty(mList.get(position).headImg))
                Picasso.with(mContext).load(mList.get(position).headImg).placeholder(R.mipmap.app_logo).error(R.mipmap.app_logo).into(holder.civHead);
            holder.tvName.setText("姓名：" + ordersModel.name + "");
            holder.tvPhone.setText("手机号：" + ordersModel.phone + "");

            holder.btnYc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onFollowBtnClick(ordersModel.id+"");
                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.civHead)
        CircleImageView civHead;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPhone)
        TextView tvPhone;
        @BindView(R.id.btnYc)
        Button btnYc;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onFollowBtnClick( String uid);
    }
}