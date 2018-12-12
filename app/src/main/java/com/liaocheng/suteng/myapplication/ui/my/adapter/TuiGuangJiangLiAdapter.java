package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.ExtensionDetailQueryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class TuiGuangJiangLiAdapter extends RecyclerView.Adapter<TuiGuangJiangLiAdapter.ViewHolder> {
    private Context mContext;

    List<ExtensionDetailQueryBean.ExtensionDetailQueryModel> mList = new ArrayList<>();

    private FollowClickListener mListener;
    int type = 0;

    public TuiGuangJiangLiAdapter(Context context, int type) {
        this.mContext = context;
        this.type = type;
    }

    public void setData(List<ExtensionDetailQueryBean.ExtensionDetailQueryModel> mySendOrdersBean) {
        mList = mySendOrdersBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tuiguangjiangli, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ExtensionDetailQueryBean.ExtensionDetailQueryModel ordersModel = mList.get(position);
            holder.tvTel.setText("手机号"+ordersModel.phone+"");
            holder.tvNum.setText("+"+ordersModel.money+"");
            holder.tvTiCheng.setText(""+ordersModel.sumMoney+"");
            holder.tvTime.setText(""+ordersModel.createTime+"");
            if (!TextUtils.isEmpty(ordersModel.orderCode)&&!ordersModel.orderCode.equals("0")){
                holder.linDingDan.setVisibility(View.VISIBLE);
                holder.tvID.setText(""+ordersModel.orderCode+"");
            }else {
                holder.linDingDan.setVisibility(View.GONE);
            }

        }

    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTel)
        TextView tvTel;
        @BindView(R.id.tvNum)
        TextView tvNum;
        @BindView(R.id.tvTiCheng)
        TextView tvTiCheng;
        @BindView(R.id.tvTime)
        TextView tvTime;
        @BindView(R.id.tvID)
        TextView tvID;
        @BindView(R.id.linDingDan)
        LinearLayout linDingDan;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onFollowBtnClick(boolean isGuanzhu, String uid);
    }
}