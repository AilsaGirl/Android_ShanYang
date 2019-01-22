package com.liaocheng.suteng.myapplication.ui.my.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.DingDanBuyInfoActivity;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.DingDanInfoActivity;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.FaDanXiangQingActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public FaDanAdapter(Context context, int type) {
        this.mContext = context;
        this.type = type;
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
            final MySendOrdersBean.MySendOrdersModel ordersModel = mList.get(position);
//            1	帮我买
//            2	帮我办
//            3	帮我送
//            4	同校快送
//            5	合作商家
//            6	县域快送
            if (ordersModel.orderType.equals("1")) {
                holder.tvDingDanType.setText("帮我买");
                holder.linMai.setVisibility(View.VISIBLE);
                holder.relFa.setVisibility(View.GONE);
                holder.tvMsg.setText(ordersModel.description+"");
            }
            if (ordersModel.orderType.equals("2")) {
                holder.tvDingDanType.setText("帮我办");
                holder.linMai.setVisibility(View.VISIBLE);
                holder.relFa.setVisibility(View.GONE);
                holder.tvMsg.setText(ordersModel.description+"");
            }
            if (ordersModel.orderType.equals("3")) {
                holder.tvDingDanType.setText("帮我送");
                holder.linMai.setVisibility(View.GONE);
                holder.relFa.setVisibility(View.VISIBLE);
            }
            if (ordersModel.orderType.equals("4")) {
                holder.tvDingDanType.setText("同校快送");
                holder.linMai.setVisibility(View.GONE);
                holder.relFa.setVisibility(View.VISIBLE);
            }
            if (ordersModel.orderType.equals("5")) {
                holder.tvDingDanType.setText("合作商家");
                holder.linMai.setVisibility(View.GONE);
                holder.relFa.setVisibility(View.VISIBLE);
            }
            if (ordersModel.orderType.equals("6")) {
                holder.tvDingDanType.setText("县域快送");
                holder.linMai.setVisibility(View.GONE);
                holder.relFa.setVisibility(View.VISIBLE);
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
                holder.tvZhiFu.setText("查看详情");
            }
            if (ordersModel.status.equals("3")) {
                holder.tvDingDanStutas.setText("取货中");
                if (type==1){
                    holder.tvZhiFu.setText("去完成");
                }else {
                    holder.tvZhiFu.setText("查看详情");
                }

            }
            if (ordersModel.status.equals("4")) {
                holder.tvDingDanStutas.setText("已完成");
                holder.tvZhiFu.setText("查看详情");
            }
            if (ordersModel.status.equals("5")) {
                holder.tvDingDanStutas.setText("送货中");
                if (type==1){
                    holder.tvZhiFu.setText("去完成");
                }else {
                    holder.tvZhiFu.setText("查看详情");
                }
            }
            if (ordersModel.status.equals("6")) {
                holder.tvDingDanStutas.setText("待提交");
                if (type==1){
                    holder.tvZhiFu.setText("去完成");
                }else {
                    holder.tvZhiFu.setText("查看详情");
                }
            }
            if (ordersModel.status.equals("7")) {
                holder.tvDingDanStutas.setText("已退款");
                holder.tvZhiFu.setText("查看详情");
            }
            if (ordersModel.status.equals("8")) {
                holder.tvDingDanStutas.setText("指定接单中");
                holder.tvZhiFu.setText("查看详情");
            }
            if (ordersModel.status.equals("9")) {
                holder.tvDingDanStutas.setText("转让订单中");
                holder.tvZhiFu.setText("查看详情");
            }
            holder.tvDingDanTime.setText(ordersModel.createTime + "");
            holder.tvFaHuo.setText(ordersModel.sendAddress + "");
            holder.tvFaHUoXQ.setText(ordersModel.sendConcreteAdd + "");
            holder.tvShouHuo.setText(ordersModel.receiveAddress + "");
            holder.tvShouHuoXQ.setText(ordersModel.receiveConcreteAdd + "");
            holder.tvJuLiNum.setText("全程:" + ordersModel.distance + "");
            if (type == 0) {
                holder.tvJinELeiXing.setText("支付金额：");
                holder.tvZhiFuNum.setText("￥" + ordersModel.total + "");
            } else {
                holder.tvJinELeiXing.setText("接单金额：");
                holder.tvZhiFuNum.setText("￥" + ordersModel.remuneration + "");
            }
            holder.tvZhiFu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ordersModel.status.equals("1")) {

                    } else {

                        if (ordersModel.orderType.equals("1") || ordersModel.orderType.equals("2")) {
                            Intent intent = new Intent();
                            intent.putExtra("code", ordersModel.orderCode);
                            intent.putExtra("isJieDanYuan", false);
//                          if (type==1){
//                              intent.putExtra("isJieDanYuan", true);
//                          }else {
//                              intent.putExtra("isJieDanYuan", false);
//                          }

                            intent.setClass(mContext, DingDanBuyInfoActivity.class);
                            mContext.startActivity(intent);
                        }else {
                            Intent intent = new Intent();
                            intent.putExtra("code", ordersModel.orderCode);
                            intent.setClass(mContext, FaDanXiangQingActivity.class);
                            mContext.startActivity(intent);
//                            if (type==0){
//                                Intent intent = new Intent();
//                                intent.putExtra("code", ordersModel.orderCode);
//                                intent.setClass(mContext, FaDanXiangQingActivity.class);
//                                mContext.startActivity(intent);
//                            }else {
//                                Intent intent = new Intent();
//                                intent.putExtra("code", ordersModel.orderCode);
//                                intent.setClass(mContext, DingDanInfoActivity.class);
//                                mContext.startActivity(intent);
//                            }
                        }
                    }
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ordersModel.orderType.equals("1") || ordersModel.orderType.equals("2")) {
//                    mPresenter.order_grab(uid);
//                    recyclerView.refresh();
                        Intent intent = new Intent();
                        intent.putExtra("code", ordersModel.orderCode);
//                        if (type==1){
//                            intent.putExtra("isJieDanYuan", true);
//                        }else {
//                            intent.putExtra("isJieDanYuan", false);
//                        }
                        intent.putExtra("isJieDanYuan", false);
                        intent.setClass(mContext, DingDanBuyInfoActivity.class);
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent();
                        intent.putExtra("code", ordersModel.orderCode);
                        intent.setClass(mContext, FaDanXiangQingActivity.class);
                        mContext.startActivity(intent);
//                        if (type==0){
//                            Intent intent = new Intent();
//                            intent.putExtra("code", ordersModel.orderCode);
//                            intent.setClass(mContext, FaDanXiangQingActivity.class);
//                            mContext.startActivity(intent);
//                        }else {
//                            Intent intent = new Intent();
//                            intent.putExtra("code", ordersModel.orderCode);
//                            intent.setClass(mContext, DingDanInfoActivity.class);
//                            mContext.startActivity(intent);
//                        }
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
        @BindView(R.id.tvDingDanType)
        TextView tvDingDanType;
        @BindView(R.id.tvDingDanTime)
        TextView tvDingDanTime;
        @BindView(R.id.tvDingDanStutas)
        TextView tvDingDanStutas;
        @BindView(R.id.tvMsg)
        TextView tvMsg;
        @BindView(R.id.linMai)
        LinearLayout linMai;
        @BindView(R.id.ivFaHuo)
        ImageView ivFaHuo;
        @BindView(R.id.tvFaHuo)
        TextView tvFaHuo;
        @BindView(R.id.tvFaHUoXQ)
        TextView tvFaHUoXQ;
        @BindView(R.id.relFa)
        RelativeLayout relFa;
        @BindView(R.id.ivShouHuo)
        ImageView ivShouHuo;
        @BindView(R.id.tvShouHuo)
        TextView tvShouHuo;
        @BindView(R.id.tvShouHuoXQ)
        TextView tvShouHuoXQ;
        @BindView(R.id.tvJinELeiXing)
        TextView tvJinELeiXing;
        @BindView(R.id.tvZhiFuNum)
        TextView tvZhiFuNum;
        @BindView(R.id.tvJuLiNum)
        TextView tvJuLiNum;
        @BindView(R.id.tvZhiFu)
        TextView tvZhiFu;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static interface FollowClickListener {
        void onFollowBtnClick(boolean isGuanzhu, String uid);
    }
}