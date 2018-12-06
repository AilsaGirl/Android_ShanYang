package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class YouHuiQuanAdapter extends RecyclerView.Adapter<YouHuiQuanAdapter.ViewHolder> {



    private Context mContext;

    private List<YouHuiQuanBean.YouHuiQuanModel> mList = new ArrayList<>();

    private FollowClickListener mListener;
    int type = 0;

    public YouHuiQuanAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<YouHuiQuanBean.YouHuiQuanModel> mySendOrdersBean) {
        mList = mySendOrdersBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_youhuiquan, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            holder.tvBianHao.setText("优惠券编号："+mList.get(position).couponNumber);
            holder.tvJinE.setText(mList.get(position).denomination+"元/张");
            holder.tvStartTime.setText("优惠券获取时间："+mList.get(position).createTime);
            holder.tvEndTime.setText("优惠券到期时间："+mList.get(position).finishTime);
        }
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvBianHao)
        TextView tvBianHao;
        @BindView(R.id.tvJinE)
        TextView tvJinE;
        @BindView(R.id.tvStartTime)
        TextView tvStartTime;
        @BindView(R.id.tvEndTime)
        TextView tvEndTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onFollowBtnClick(boolean isGuanzhu, String uid);
    }
}