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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 接单
 * Created by weihongfang
 */

public class FaDanAdapter extends RecyclerView.Adapter<FaDanAdapter.ViewHolder> {

    private Context mContext;

    private List<MySendOrdersBean.MySendOrdersModel> mList = new ArrayList<>();

    private FollowClickListener mListener;
int type = 0;
    public FaDanAdapter(Context context,int type) {
        this.mContext = context;
        this.type =type;
    }

    public void setData(List<MySendOrdersBean.MySendOrdersModel> mySendOrdersBean) {
        mList = mySendOrdersBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dingdan, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            MySendOrdersBean.MySendOrdersModel ordersModel = mList.get(position);
//            1	帮我买
//            2	帮我办
//            3	帮我送
//            4	同校快送
//            5	合作商家
//            6	县域快送
            if (ordersModel.orderType.equals("1")) {
                holder.tvDingDanType.setText("帮我买");
            }
            if (ordersModel.orderType.equals("2")) {
                holder.tvDingDanType.setText("帮我办");
            }
            if (ordersModel.orderType.equals("3")) {
                holder.tvDingDanType.setText("帮我送");
            }
            if (ordersModel.orderType.equals("4")) {
                holder.tvDingDanType.setText("同校快送");
            }
            if (ordersModel.orderType.equals("5")) {
                holder.tvDingDanType.setText("合作商家");
            }
            if (ordersModel.orderType.equals("6")) {
                holder.tvDingDanType.setText("县域快送");
            }
//            1	未付款	订单详情	/	删除订单/订单付款
//            2	未被抢	订单详情/实时订单/订单大厅	/	撤销订单/订单加价
//            3	取货中	待处理订单/订单详情	到店取货/退回大厅/退回发单人	撤销订单/订单加价/确认订单
//            4	已完成	订单详情	/	/
//            5	送货中	待处理订单/订单详情	短信验证收货	撤销订单/订单加价/确认订单
//            6	待提交	待处理订单/订单详情	确认订单	确认订单
//            7	已退款	订单详情	/	/
//            8	指定接单中	待处理订单/订单详情	同意接单/拒绝接单	/
//                    9	转让订单中	待处理订单/订单详情	同意转单/拒绝转单	/
            if (ordersModel.status.equals("1")) {
                holder.tvDingDanStutas.setText("未付款");
                holder.tvZhiFu.setText("去支付");
            }
            if (ordersModel.status.equals("2")) {
                holder.tvDingDanStutas.setText("未被抢");
            }
            if (ordersModel.status.equals("3")) {
                holder.tvDingDanStutas.setText("取货中");
            }
            if (ordersModel.status.equals("4")) {
                holder.tvDingDanStutas.setText("已完成");
            }
            if (ordersModel.status.equals("5")) {
                holder.tvDingDanStutas.setText("送货中");
            }
            if (ordersModel.status.equals("6")) {
                holder.tvDingDanStutas.setText("待提交");
            }
            if (ordersModel.status.equals("7")) {
                holder.tvDingDanStutas.setText("已退款");
            }
            if (ordersModel.status.equals("8")) {
                holder.tvDingDanStutas.setText("指定接单中");
            }
            if (ordersModel.status.equals("9")) {
                holder.tvDingDanStutas.setText("转让订单中");
            }
            holder.tvDingDanTime.setText(ordersModel.createTime + "");
            holder.tvFaHuo.setText(ordersModel.sendAddress + "");
            holder.tvFaHUoXQ.setText(ordersModel.sendConcreteAdd + "");
            holder.tvShouHuo.setText(ordersModel.receiveAddress + "");
            holder.tvShouHuoXQ.setText(ordersModel.receiveConcreteAdd + "");
            holder.tvJuLiNum.setText("全程:"+ordersModel.distance + "");
            if (type==0){
                holder.tvJinELeiXing.setText("支付金额：");
                holder.tvZhiFuNum.setText("￥"+ordersModel.total + "");
            }else {
                holder.tvJinELeiXing.setText("接单金额：");
                holder.tvZhiFuNum.setText("￥"+ordersModel.remuneration + "");
            }

        }

    }


    @Override
    public int getItemCount() {
        return mList == null ? 0: mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDingDanType)
        TextView tvDingDanType;
        @BindView(R.id.tvDingDanTime)
        TextView tvDingDanTime;
        @BindView(R.id.tvDingDanStutas)
        TextView tvDingDanStutas;
        @BindView(R.id.ivFaHuo)
        ImageView ivFaHuo;
        @BindView(R.id.tvFaHuo)
        TextView tvFaHuo;
        @BindView(R.id.tvFaHUoXQ)
        TextView tvFaHUoXQ;
        @BindView(R.id.ivShouHuo)
        ImageView ivShouHuo;
        @BindView(R.id.tvShouHuo)
        TextView tvShouHuo;
        @BindView(R.id.tvShouHuoXQ)
        TextView tvShouHuoXQ;
        @BindView(R.id.tvZhiFuNum)
        TextView tvZhiFuNum;
        @BindView(R.id.tvJuLiNum)
        TextView tvJuLiNum;
        @BindView(R.id.tvJinELeiXing)
        TextView tvJinELeiXing;
        @BindView(R.id.tvZhiFu)
        TextView  tvZhiFu;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onFollowBtnClick(boolean isGuanzhu, String uid);
    }
}