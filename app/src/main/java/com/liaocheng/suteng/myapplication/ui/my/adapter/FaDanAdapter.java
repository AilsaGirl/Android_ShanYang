package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 接单
 * Created by weihongfang
 */

public class FaDanAdapter extends RecyclerView.Adapter<FaDanAdapter.ViewHolder> {



    private Context mContext;

    private List<String> mList ;

    private FollowClickListener mListener;
    int mType = 0;

    public FaDanAdapter(Context context, int type) {
        this.mContext = context;
        this.mType = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dingdan, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
//            if (mType == 1) {
//                holder.tvTime.setVisibility(View.VISIBLE);
//                holder.tvNum.setVisibility(View.GONE);
//            } else {
//                holder.tvTime.setVisibility(View.GONE);
//                holder.tvNum.setVisibility(View.VISIBLE);
//            }

        }

    }


    @Override
    public int getItemCount() {
        return mList == null ? 6 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onFollowBtnClick(boolean isGuanzhu, String uid);
    }
}