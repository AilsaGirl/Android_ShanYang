package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class YouHuiQuanListAdapter extends RecyclerView.Adapter<YouHuiQuanListAdapter.ViewHolder> {

    private Context mContext;

    private List<YouHuiQuanListBean.YouHuiQuanListModel> mList = new ArrayList<>();

    private FollowClickListener mListener;
    int type = 0;

    public YouHuiQuanListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<YouHuiQuanListBean.YouHuiQuanListModel> mySendOrdersBean) {
        mList = mySendOrdersBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_quanlist, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            holder.tvType.setText("" + mList.get(position).useType);
            holder.tvId.setText(mList.get(position).detailName + "");
            holder.tvNum.setText("" + mList.get(position).count+ "张");
            holder.tvTotal.setText("" + mList.get(position).counts+ "张");
            holder.tvTime.setText("" + mList.get(position).createTime);
        }
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvType)
        TextView tvType;
        @BindView(R.id.tvId)
        TextView tvId;
        @BindView(R.id.tvNum)
        TextView tvNum;
        @BindView(R.id.tvTotal)
        TextView tvTotal;
        @BindView(R.id.tvTime)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onFollowBtnClick(boolean isGuanzhu, String uid);
    }
}