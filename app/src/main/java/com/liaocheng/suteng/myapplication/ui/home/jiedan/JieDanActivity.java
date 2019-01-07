package com.liaocheng.suteng.myapplication.ui.home.jiedan;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
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
    @Override
    public int getLayoutId() {
        return R.layout.activity_jiedan;
    }
int page=1;
    @Override
    public void initEventAndData() {
        toolbar.setTitleText("接单大厅");
        toolbar.setRight(R.mipmap.icon_wode, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(JieDanActivity.this,MyActivity.class);
                startActivity(intent);
            }
        });
        toolbar.setTitleClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SPCommon.getBoolean("iswork",false)){
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
                mPresenter.getOrder(page+"");
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getOrder(page+"");
            }
        });
        if (SPCommon.getBoolean("iswork",false)){

            tvJieDan.setText("刷新订单");
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.refresh();
            toolbar.setTitleText("下班").setTitleTextBg();
            isFrist = false;
        }else {
            tvJieDan.setText("开始接单");
            recyclerView.setVisibility(View.GONE);
            toolbar.setTitleText("接单大厅");
            isFrist = true;
        }
        adapter = new JieDanAdapter(mContext, 0, new JieDanAdapter.FollowClickListener() {
            @Override
            public void onFollowBtnClick(String type,String uid,int i) {
                if (type.equals("1")){
                    mPresenter.order_grab(uid);
//                    recyclerView.refresh();
                }
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
    @OnClick({R.id.tvBaoJing, R.id.tvJieDan, R.id.tvMyTask})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvBaoJing:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();

                break;
            case R.id.tvJieDan:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                if (isFrist){
                mPresenter.updateTechnicianWorkStatus("1");
                }else {
                    recyclerView.refresh();
                }
                break;
            case R.id.tvMyTask:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
//                intent = new Intent(mContext,FuWuDingDanActivity.class);
//                mContext.startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
List<JieDanDaTingModel.JieDanDaTingBean> list = new ArrayList<JieDanDaTingModel.JieDanDaTingBean>();
    @Override
    public void setOrder(JieDanDaTingModel myBean) {
        recyclerView.loadMoreComplete();
        recyclerView.refreshComplete();
        if (myBean.data!=null&&myBean.data.size()>0){
            if (page==1){
                list.clear();
            }
            list.addAll(myBean.data);
            adapter.setData(list);
        }else {
            if (page==1){
                ToastUtil.show("暂无数据");
            }else {
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
}
