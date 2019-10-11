package com.liaocheng.suteng.myapplication.ui.home.jiedan;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.event.RenWuEvent;
import com.liaocheng.suteng.myapplication.presenter.JieDanDaTingPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.JieDanDaTingContent;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.BangWoMaiXiaDanActivity;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.adapter.JieDanAdapter;
import com.liaocheng.suteng.myapplication.ui.my.MyActivity;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;
import com.liaocheng.suteng.myapplication.view.ZhiDingDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JieDanActivity extends BaseActivity<JieDanDaTingPresenter> implements JieDanDaTingContent.View {

    @BindView(R.id.toolbar)
    MyToolBar toolbar;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.tvBaoJing)
    TextView tvBaoJing;
    @BindView(R.id.tvJieDan)
    TextView tvJieDan;
    @BindView(R.id.tvMyTask)
    TextView tvMyTask;
    @BindView(R.id.linBom)
    LinearLayout linBom;
    JieDanAdapter adapter;
    @BindView(R.id.tvRel)
    RelativeLayout tvRel;
    @BindView(R.id.ivNull)
    ImageView ivNull;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jiedan;
    }

    int page = 1;
String trafficTool = "1";
    @Override
    public void initEventAndData() {
        toolbar.setTitleText("接单大厅");
        toolbar.setRight(R.mipmap.icon_wode, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(JieDanActivity.this, MyActivity.class);
                startActivity(intent);
            }
        });
        toolbar.setTitleClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SPCommon.getBoolean("iswork", false)) {
                    mPresenter.updateTechnicianWorkStatus("0");
                }

            }
        });
        toolbar.setBackFinish();
        tvJieDan.setText("开始接单");
//        recyclerView.setVisibility(View.GONE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getOrder(page + "",trafficTool);
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getOrder(page + "",trafficTool);
            }
        });
        mPresenter.getCustomerTraffic();

        adapter = new JieDanAdapter(mContext, 0, new JieDanAdapter.FollowClickListener() {
            @Override
            public void onFollowBtnClick(String type, final String uid, int i) {
                mType = type;
                final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(JieDanActivity.this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setMessage("亲，您确认要抢这个订单吗，抢单成功以后，必须要做到10分钟取货，3公里内30分钟必须送达，超过1公里加时10分钟。抢单以后不要随意撤销订单，随意撤销的话会处罚10-200元。请谨慎操作，不要胡乱抢单。", "");
                dialog.setBackgroundResource(true);
                dialog.setVisibilityBtn(true);
                dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        mPresenter.order_grab(uid);
                        dialog.dismiss();

                    }
                });
                dialog.setOnOnclickListener("取消", new ApplyAndAlterDialog.onOnOnclickListener() {
                    @Override
                    public void onOnClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();


            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setData(null);

//        recyclerView.refresh();
    }
    String mType = "";
    @Override
    public void getCustomerTraffic(BaoXianModel baoXianModel) {
        trafficTool = baoXianModel.traffic;
        if (SPCommon.getBoolean("iswork", false)) {

            tvJieDan.setText("刷新订单");
            recyclerView.setVisibility(View.VISIBLE);
            ivNull.setVisibility(View.GONE);
            recyclerView.refresh();
            toolbar.setTitleText("下班").setTitleTextBg();
            isFrist = false;
        } else {
            tvJieDan.setText("开始接单");
            recyclerView.setVisibility(View.GONE);
            ivNull.setVisibility(View.VISIBLE);
            toolbar.setTitleText("接单大厅");
            isFrist = true;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (SPCommon.getBoolean("iswork", false)) {

            tvJieDan.setText("刷新订单");
            recyclerView.setVisibility(View.VISIBLE);
            ivNull.setVisibility(View.GONE);
            recyclerView.refresh();
            toolbar.setTitleText("下班").setTitleTextBg();
            isFrist = false;
        } else {
            tvJieDan.setText("开始接单");
            recyclerView.setVisibility(View.GONE);
            ivNull.setVisibility(View.VISIBLE);
            toolbar.setTitleText("接单大厅");
            isFrist = true;
        }
    }

    @Override
    public void showError(int reqCode, String msg) {
        recyclerView.loadMoreComplete();
        recyclerView.refreshComplete();

        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    long mLasttime;
    Intent intent;
    boolean isFrist = true;
    PopupWindow popupWindow;

    @OnClick({R.id.tvBaoJing, R.id.tvJieDan, R.id.tvMyTask})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvBaoJing:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater.inflate(R.layout.dialog_jiedanshezhi, null);
                //给popwindow加上动画效果
                LinearLayout layout_pop = (LinearLayout) view.findViewById(R.id.layout_pop);
                final TextView tvChe1 = view.findViewById(R.id.tvChe1);
                final   TextView tvChe2 = view.findViewById(R.id.tvChe2);
                final   TextView tvChe3 = view.findViewById(R.id.tvChe3);
                final    TextView tvChe4 = view.findViewById(R.id.tvChe4);
                final    TextView tvChe5 = view.findViewById(R.id.tvChe5);
                if (trafficTool.equals("1")){
                tvChe1.setTextColor(0xffE03D81);
            }
                if (trafficTool.equals("2")){
                    tvChe2.setTextColor(0xffE03D81);
                }
                if (trafficTool.equals("3")){
                    tvChe3.setTextColor(0xffE03D81);
                }
                if (trafficTool.equals("4")){
                    tvChe4.setTextColor(0xffE03D81);
                }
                if (trafficTool.equals("5")){
                    tvChe5.setTextColor(0xffE03D81);
                }
                tvChe1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.setWorkTraffic("1");
                        tvChe1.setTextColor(0xffE03D81);
                        trafficTool = "1";
                        popupWindow.dismiss();
                    }
                });
                tvChe2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.setWorkTraffic("2");
                        tvChe2.setTextColor(0xffE03D81);
                        trafficTool = "2";
                        popupWindow.dismiss();
                    }
                });
                tvChe3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.setWorkTraffic("3");
                        tvChe3.setTextColor(0xffE03D81);
                        trafficTool = "3";
                        popupWindow.dismiss();
                    }
                });
                tvChe4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.setWorkTraffic("4");
                        tvChe4.setTextColor(0xffE03D81);
                        trafficTool = "4";
                        popupWindow.dismiss();
                    }
                });
                tvChe5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.setWorkTraffic("5");
                        tvChe5.setTextColor(0xffE03D81);
                        trafficTool = "5";
                        popupWindow.dismiss();
                    }
                });
                view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
                layout_pop.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in_bottom));
                //设置popwindow的宽高，这里我直接获取了手机屏幕的宽，高设置了600DP
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                popupWindow = new PopupWindow(view, dm.widthPixels, 800);
// 使其聚集
                popupWindow.setFocusable(true);
                // 设置允许在外点击消失
                popupWindow.setOutsideTouchable(true);

                // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
//                backgroundAlpha(120f);  //透明度
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
//                        backgroundAlpha(1f);

                    }
                });
                //弹出的位置
                WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                popupWindow.showAtLocation(tvRel, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
            case R.id.tvJieDan:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                if (isFrist) {
                    mPresenter.updateTechnicianWorkStatus("1");
                } else {
                    recyclerView.refresh();
                }
                break;
            case R.id.tvMyTask:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, WoDeRenWuActivity.class);
                intent.putExtra("tab", 0);
                mContext.startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
            dialog = null;
        }
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    List<JieDanDaTingModel.JieDanDaTingBean> list = new ArrayList<JieDanDaTingModel.JieDanDaTingBean>();

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    public void setOrder(JieDanDaTingModel myBean) {
        recyclerView.loadMoreComplete();
        recyclerView.refreshComplete();
        if (myBean.data != null && myBean.data.size() > 0) {
            ivNull.setVisibility(View.GONE);
            if (page == 1) {
                list.clear();
            }
            list.addAll(myBean.data);
            adapter.setData(list);
        } else {
            if (page == 1) {
                ToastUtil.show("暂无数据");
                list.clear();
                adapter.setData(list);
                ivNull.setVisibility(View.VISIBLE);
            } else {
                ToastUtil.show("最后一页");
            }
        }
        if (page==1){
            mPresenter.queryTransferAndSpecificOrder();
        }
    }

    @Override
    public void updateTechnicianWorkStatus() {
        if (isFrist) {
            ToastUtil.show("上班成功");
            tvJieDan.setText("刷新订单");
            SPCommon.setBoolean("iswork", true);
            recyclerView.setVisibility(View.VISIBLE);
            ivNull.setVisibility(View.GONE);
            toolbar.setTitleText("下班").setTitleTextBg();
            recyclerView.refresh();
            isFrist = false;
        } else {
            ToastUtil.show("下班成功");
            SPCommon.setBoolean("iswork", false);
            tvJieDan.setText("开始接单");
            recyclerView.setVisibility(View.GONE);
            ivNull.setVisibility(View.VISIBLE);
            toolbar.setTitleText("接单大厅");
            isFrist = true;
        }
    }

    @Override
    public void order_grab() {
        ToastUtil.show("抢单成功！");
        intent = new Intent(mContext, WoDeRenWuActivity.class);
        if (mType.equals("1")||mType.equals("2")){
            intent.putExtra("tab",1);
//            EventBus.getDefault().postSticky(new RenWuEvent(1));
        }

        mContext.startActivity(intent);
//        recyclerView.refresh();
    }

    @Override
    public void setWorkTraffic() {
        ToastUtil.show("设置交通工具成功！");
        recyclerView.refresh();
    }
     ZhiDingDialog dialog;
    @Override
    public void queryTransferAndSpecificOrder(final JieDanDaTingModel myBean) {
        if (myBean.data!=null&&myBean.data.size()>0){
           dialog = new ZhiDingDialog(JieDanActivity.this);
            dialog.setCanceledOnTouchOutside(true);
            String title = ""; String type ="";
            switch (myBean.data.get(0).status) {
                case "8":
                    title = "处理指定订单";
                    break;
                case "9":
                    title = "处理转让订单";
                    break;
            }
            if (myBean.data.get(0).orderType.equals("1")){
                type = "帮我买";
            }else  if (myBean.data.get(0).orderType.equals("2")){
                type = "帮我办";
            }else  if (myBean.data.get(0).orderType.equals("3")){
                type = "帮我送";
            }else  if (myBean.data.get(0).orderType.equals("4")){
                type = "同校快送";
            }else  if (myBean.data.get(0).orderType.equals("5")){
                type = "当日达";
            }else  if (myBean.data.get(0).orderType.equals("6")){
                type = "县域快送";
            }
            dialog.setData(title,type,myBean.data.get(0).sendAddress,myBean.data.get(0).receiveAddress,"全程约"+myBean.data.get(0).distance+"公里，总计"+myBean.data.get(0).remuneration+"元");
            dialog.setYesOnclickListener(new ZhiDingDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    dialog.dismiss();
                    if (myBean.data.get(0).status.equals("8")){
                        mPresenter.executeSpecificOrder(myBean.data.get(0).orderCode,"1");
                    }
                    if (myBean.data.get(0).status.equals("9")){
                        mPresenter.executeTransferOrder(myBean.data.get(0).orderCode,"1");
                    }

                }
            });
            dialog.setOnOnclickListener(new ZhiDingDialog.onOnOnclickListener() {
                @Override
                public void onOnClick() {
                    dialog.dismiss();
                    if (myBean.data.get(0).status.equals("8")){
                        mPresenter.executeSpecificOrder(myBean.data.get(0).orderCode,"0");
                    }
                    if (myBean.data.get(0).status.equals("9")){
                        mPresenter.executeTransferOrder(myBean.data.get(0).orderCode,"0");
                    }

                }
            });
            dialog.show();
        }
    }

    @Override
    public void executeSpecificOrder() {
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
            dialog = null;
        }
        ToastUtil.show("处理成功");
        mPresenter.queryTransferAndSpecificOrder();
    }

    @Override
    public void executeTransferOrder() {
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
            dialog = null;
        }
        ToastUtil.show("处理成功");
        mPresenter.queryTransferAndSpecificOrder();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        //这句
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
