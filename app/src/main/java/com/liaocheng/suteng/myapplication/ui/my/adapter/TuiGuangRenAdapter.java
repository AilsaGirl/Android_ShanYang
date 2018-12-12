package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.PromoteDetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class TuiGuangRenAdapter extends RecyclerView.Adapter<TuiGuangRenAdapter.ViewHolder> {

    private Context mContext;

    private List<PromoteDetailBean.PromoteDetailModel> mList = new ArrayList<>();

    private FollowClickListener mListener;
    int type = 0;

    public TuiGuangRenAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<PromoteDetailBean.PromoteDetailModel> promoteDetailModels) {
        mList = promoteDetailModels;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tuiguangren, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            holder.tvName.setText("姓名：" + mList.get(position).nickName);
            holder.tvTel.setText("手机号："+mList.get(position).phone);
            holder.tvTime.setText("注册日期：" + mList.get(position).registerTime);
            holder.tvType.setText("" + mList.get(position).status);
        }
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTel)
        TextView tvTel;
        @BindView(R.id.tvType)
        TextView tvType;
        @BindView(R.id.tvName)
        TextView tvName;
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