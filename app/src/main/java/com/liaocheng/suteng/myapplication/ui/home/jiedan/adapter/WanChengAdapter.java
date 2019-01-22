package com.liaocheng.suteng.myapplication.ui.home.jiedan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.DingDanBuyInfoActivity;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.DingDanInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class WanChengAdapter extends RecyclerView.Adapter<WanChengAdapter.ViewHolder> {


    private Context mContext;

    private List<JieDanDaTingModel.JieDanDaTingBean> mList = new ArrayList<>();

    private FollowClickListener mListener;
    int type = 0;

    public WanChengAdapter(Context context, int type) {
        this.mContext = context;
        this.type = type;
    }

    public void setData(List<JieDanDaTingModel.JieDanDaTingBean> mySendOrdersBean) {
        mList = mySendOrdersBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wancheng, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final JieDanDaTingModel.JieDanDaTingBean model = mList.get(position);
//            orderType	说明   1帮我买2	帮我办3	帮我送4	同校快送5	合作商家6	县域快送
            holder.linMai.setVisibility(View.GONE);
            holder.relFa.setVisibility(View.VISIBLE);
            if (model.orderType.equals("1")){
                holder.tvType.setText("帮我买");
                holder.linMai.setVisibility(View.VISIBLE);
                holder.relFa.setVisibility(View.GONE);
                holder.tvMsg.setText("商品描述及备注："+model.description+"");
            }else  if (model.orderType.equals("2")){
                holder.tvType.setText("帮我办");
                holder.linMai.setVisibility(View.VISIBLE);
                holder.relFa.setVisibility(View.GONE);
                holder.tvMsg.setText("商品描述及备注："+model.description+"");
            }else  if (model.orderType.equals("3")){
                holder.tvType.setText("帮我送");
            }else  if (model.orderType.equals("4")){
                holder.tvType.setText("同校快送");
            }else  if (model.orderType.equals("5")){
                holder.tvType.setText("合作商家");
            }else  if (model.orderType.equals("6")){
                holder.tvType.setText("县域快送");
            }
            holder.tvDingDanTime.setText(model.finishTime+"");
            holder.tvNum.setText("￥"+model.remuneration+"");
            holder.tvFaJL.setText(model.distance+"");
//            holder.tvFa.setText("");
            holder.tvFaHuo.setText("******************");
            holder.tvFaHUoXQ.setText("******************");
            holder.tvShouJL.setText(model.distance+"");
//            holder.tvShou.setText("");
            holder.tvShouHuo.setText("******************");
            holder.tvShouHUoXQ.setText("******************");
            holder.tvJuLi.setText("全程："+model.distance);
            holder.tvZhiFu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (model.orderType.equals("1") || model.orderType.equals("2")) {
//                    mPresenter.order_grab(uid);
//                    recyclerView.refresh();
                        Intent intent = new Intent();
                        intent.putExtra("code", model.orderCode);
                        intent.putExtra("isJieDanYuan", true);
                        intent.setClass(mContext, DingDanBuyInfoActivity.class);
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("code", model.orderCode);
                        intent.setClass(mContext, DingDanInfoActivity.class);
                        mContext.startActivity(intent);

                    }
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (model.orderType.equals("1") || model.orderType.equals("2")) {
//                    mPresenter.order_grab(uid);
//                    recyclerView.refresh();
                        Intent intent = new Intent();
                        intent.putExtra("code", model.orderCode);
                        intent.putExtra("isJieDanYuan", true);
                        intent.setClass(mContext, DingDanBuyInfoActivity.class);
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("code", model.orderCode);
                        intent.setClass(mContext, DingDanInfoActivity.class);
                        mContext.startActivity(intent);

                    }
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvType)
        TextView tvType;
        @BindView(R.id.tvDingDanTime)
        TextView tvDingDanTime;
        @BindView(R.id.tvNum)
        TextView tvNum;
        @BindView(R.id.tvMsg)
        TextView tvMsg;
        @BindView(R.id.linMai)
        LinearLayout linMai;
        @BindView(R.id.tvFaJL)
        TextView tvFaJL;
        @BindView(R.id.tvFa)
        TextView tvFa;
        @BindView(R.id.linFa)
        LinearLayout linFa;
        @BindView(R.id.tvFaHuo)
        TextView tvFaHuo;
        @BindView(R.id.tvFaHUoXQ)
        TextView tvFaHUoXQ;
        @BindView(R.id.relFa)
        RelativeLayout relFa;
        @BindView(R.id.tvShouJL)
        TextView tvShouJL;
        @BindView(R.id.tvShou)
        TextView tvShou;
        @BindView(R.id.linShou)
        LinearLayout linShou;
        @BindView(R.id.tvShouHuo)
        TextView tvShouHuo;
        @BindView(R.id.tvShouHUoXQ)
        TextView tvShouHUoXQ;
        @BindView(R.id.tvZhiFu)
        TextView tvZhiFu;
        @BindView(R.id.tvJuLi)
        TextView tvJuLi;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onFollowBtnClick(String type, String uid, int i);
    }
}