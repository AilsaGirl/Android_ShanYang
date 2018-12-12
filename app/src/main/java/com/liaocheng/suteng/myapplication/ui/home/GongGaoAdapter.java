package com.liaocheng.suteng.myapplication.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.NoticeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class GongGaoAdapter extends RecyclerView.Adapter<GongGaoAdapter.ViewHolder> {



    private Context mContext;

    private List<NoticeModel.NoticeBean> mList = new ArrayList<>();

    private FollowClickListener mListener;
    int type = 0;

    public GongGaoAdapter(Context context, int type) {
        this.mContext = context;
        this.type = type;
    }

    public void setData(List<NoticeModel.NoticeBean> noticeBeans) {
        mList = noticeBeans;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gonggao, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            holder.tvTitle.setText(mList.get(position).title+"");
            holder.tvContent.setText(mList.get(position).content+"");
            holder.tvTime.setText(mList.get(position).createTime+"");
        }

    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvContent)
        TextView tvContent;
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