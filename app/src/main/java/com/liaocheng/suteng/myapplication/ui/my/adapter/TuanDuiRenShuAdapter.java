package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.ShouYiModel;
import com.liaocheng.suteng.myapplication.model.TeamModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class TuanDuiRenShuAdapter extends RecyclerView.Adapter<TuanDuiRenShuAdapter.ViewHolder> {



    private Context mContext;

    private List<TeamModel.DetailBean> mList = new ArrayList<>();

    private FollowClickListener mListener;
    int type = 0;

    public TuanDuiRenShuAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<TeamModel.DetailBean> promoteDetailModels) {
        mList = promoteDetailModels;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tuanduirenshu, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            if (!TextUtils.isEmpty(mList.get(position).getHeadImg()))
            Picasso.with(mContext).load(mList.get(position).getHeadImg()).placeholder(R.mipmap.app_logo).error(R.mipmap.app_logo).into(holder.ivHead);
            holder.tvName.setText("昵称：" + mList.get(position).getNickName());
            holder.tvTime.setText("认证日期：" + mList.get(position).getRegisterTime());
            if (mList.get(position).getLevel().equals("-1")){
                holder.tvDengJi.setText("等级：" +"未认证");
            }
            if (mList.get(position).getLevel().equals("0")){
                holder.tvDengJi.setText("等级：" +"小羊");
            }
            if (mList.get(position).getLevel().equals("1")){
                holder.tvDengJi.setText("等级：" +"小羊倌");
            }
            if (mList.get(position).getLevel().equals("2")){
                holder.tvDengJi.setText("等级：" +"大羊倌");
            }

            holder.tvTel.setText("手机号：" + mList.get(position).getPhone());
        }
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivHead)
        ImageView ivHead;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvDengJi)
        TextView tvDengJi;
        @BindView(R.id.tvTel)
        TextView tvTel;
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