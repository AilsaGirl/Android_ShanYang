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
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.presenter.JieDanDaTingPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.JieDanDaTingContent;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.adapter.JieDanAdapter;
import com.liaocheng.suteng.myapplication.ui.my.MyActivity;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_jiedan;
    }

    int page = 1;

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
                mPresenter.getOrder(page + "");
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getOrder(page + "");
            }
        });
        if (SPCommon.getBoolean("iswork", false)) {

            tvJieDan.setText("刷新订单");
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.refresh();
            toolbar.setTitleText("下班").setTitleTextBg();
            isFrist = false;
        } else {
            tvJieDan.setText("开始接单");
            recyclerView.setVisibility(View.GONE);
            toolbar.setTitleText("接单大厅");
            isFrist = true;
        }
        adapter = new JieDanAdapter(mContext, 0, new JieDanAdapter.FollowClickListener() {
            @Override
            public void onFollowBtnClick(String type, String uid, int i) {
                mPresenter.order_grab(uid);


            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setData(null);

//        recyclerView.refresh();
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
                TextView tvChe1 = view.findViewById(R.id.tvChe1);
                TextView tvChe2 = view.findViewById(R.id.tvChe2);
                TextView tvChe3 = view.findViewById(R.id.tvChe3);
                TextView tvChe4 = view.findViewById(R.id.tvChe4);
                TextView tvChe5 = view.findViewById(R.id.tvChe5);
                tvChe1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.setWorkTraffic("1");
                        popupWindow.dismiss();
                    }
                });
                tvChe2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.setWorkTraffic("2");
                        popupWindow.dismiss();
                    }
                });
                tvChe3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.setWorkTraffic("3");
                        popupWindow.dismiss();
                    }
                });
                tvChe4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.setWorkTraffic("4");
                        popupWindow.dismiss();
                    }
                });
                tvChe5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.setWorkTraffic("5");
                        popupWindow.dismiss();
                    }
                });
                view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
                layout_pop.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in_bottom));
                //设置popwindow的宽高，这里我直接获取了手机屏幕的宽，高设置了600DP
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                 popupWindow = new PopupWindow(view, dm.widthPixels, 700);
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
                intent = new Intent(mContext,WoDeRenWuActivity.class);
                mContext.startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (popupWindow!=null&&popupWindow.isShowing()){
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
            if (page == 1) {
                list.clear();
            }
            list.addAll(myBean.data);
            adapter.setData(list);
        } else {
            if (page == 1) {
                ToastUtil.show("暂无数据");
            } else {
                ToastUtil.show("最后一页");
            }
        }
    }

    @Override
    public void updateTechnicianWorkStatus() {
        if (isFrist) {
            ToastUtil.show("上班成功");
            tvJieDan.setText("刷新订单");
            SPCommon.setBoolean("iswork", true);
            recyclerView.setVisibility(View.VISIBLE);
            toolbar.setTitleText("下班").setTitleTextBg();
            recyclerView.refresh();
            isFrist = false;
        } else {
            ToastUtil.show("下班成功");
            SPCommon.setBoolean("iswork", false);
            tvJieDan.setText("开始接单");
            recyclerView.setVisibility(View.GONE);
            toolbar.setTitleText("接单大厅");
            isFrist = true;
        }
    }

    @Override
    public void order_grab() {
        ToastUtil.show("抢单成功！");
        recyclerView.refresh();
    }

    @Override
    public void setWorkTraffic() {
        ToastUtil.show("设置交通工具成功！");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
