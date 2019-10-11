package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.PromoteDetailBean;
import com.liaocheng.suteng.myapplication.model.ShouYiModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class TuanDuiJiangLiAdapter extends RecyclerView.Adapter<TuanDuiJiangLiAdapter.ViewHolder> {


    private Context mContext;

    private List<ShouYiModel.DetailBean> mList = new ArrayList<>();

    private FollowClickListener mListener;
    int type = 0;

    public TuanDuiJiangLiAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<ShouYiModel.DetailBean> promoteDetailModels) {
        mList = promoteDetailModels;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tuantuijiangli, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            holder.tvName.setText("" + mList.get(position).getDetailName());
            holder.tvTime.setText("" + mList.get(position).getCreateTime());
            holder.tvNum.setText("+" + mList.get(position).getDetailMoney());
        }
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvNum)
        TextView tvNum;
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