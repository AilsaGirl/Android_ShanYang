package com.liaocheng.suteng.myapplication.ui.home.jiedan.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.circle.common.base.BaseFragment;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.JieDanDaTingModel;
import com.liaocheng.suteng.myapplication.model.ReceiveOrderModel;
import com.liaocheng.suteng.myapplication.presenter.QuHuoPresenter;
import com.liaocheng.suteng.myapplication.presenter.WanChengPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.QuHuoContent;
import com.liaocheng.suteng.myapplication.presenter.contract.WanChengContent;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.adapter.RenWuAdapter;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.adapter.WanChengAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Created by Administrator on 2018/11/6.
 */

@SuppressLint("ValidFragment")
public class WanChengFragment extends BaseFragment<WanChengPresenter> implements WanChengContent.View {
    int mId;
//    @BindView(R.id.toolBar)
//    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    @SuppressLint("ValidFragment")
    public WanChengFragment(int id) {
        mId = id;
    }

    @Override
    public void showError(int reqCode, String msg) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_daiquhuo;
    }

    @Override
    public void initEventAndData() {
//        toolBar.setTitleText("我的关注").setBackFinish();

    }

    @Override
    public void initEventAndDataNoLazy() {
        super.initEventAndDataNoLazy();

        initRecyclerView();
    }

    int page = 1;
    String duration = "7";
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                if (mId==1){
                    mPresenter.queryCompletedOrder(page+"");
                }else {
                    mPresenter.queryHaveRefundOrder(page+"");//退款
                }

            }

            @Override
            public void onLoadMore() {
                page++;
                if (mId==1){
                    mPresenter.queryCompletedOrder(page+"");
                }else {
                    mPresenter.queryHaveRefundOrder(page+"");//退款
                }
            }
        });

        recyclerView.setVisibility(View.VISIBLE);

            mAdapter = new WanChengAdapter(mContext, 0);
            recyclerView.setAdapter(mAdapter);
        mAdapter.setData(mList);
        recyclerView.refresh();
    }
    WanChengAdapter mAdapter;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
List<JieDanDaTingModel.JieDanDaTingBean> mList = new ArrayList<>();


    @Override
    public void queryCompletedOrder(JieDanDaTingModel ReceiveOrderBean) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (ReceiveOrderBean.data != null&&ReceiveOrderBean.data.size()>0) {
            if (page==1){
                mList.clear();
            }
            mList.addAll(ReceiveOrderBean.data);

            recyclerView.setVisibility(View.VISIBLE);
            ivNull.setVisibility(View.GONE);
            mAdapter.setData(mList);
        } else {
            if (page != 1) {
                ToastUtil.show("最后一页");
            } else {
                ivNull.setVisibility(View.VISIBLE);
                if (page==1){
                    mList.clear();
                }
                mList.addAll(ReceiveOrderBean.data);
            }
        }
    }

    @Override
    public void queryHaveRefundOrder(JieDanDaTingModel ReceiveOrderBean) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (ReceiveOrderBean.data != null&&ReceiveOrderBean.data.size()>0) {
            if (page==1){
                mList.clear();
            }
            mList.addAll(ReceiveOrderBean.data);

            recyclerView.setVisibility(View.VISIBLE);
            ivNull.setVisibility(View.GONE);
            mAdapter.setData(mList);
        } else {
            if (page != 1) {
                ToastUtil.show("最后一页");
            } else {
                ivNull.setVisibility(View.VISIBLE);
                if (page==1){
                    mList.clear();
                }
                mList.addAll(ReceiveOrderBean.data);
            }
        }
    }
}
