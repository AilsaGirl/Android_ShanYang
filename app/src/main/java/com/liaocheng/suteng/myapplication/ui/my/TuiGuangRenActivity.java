package com.liaocheng.suteng.myapplication.ui.my;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.PromoteDetailBean;
import com.liaocheng.suteng.myapplication.presenter.TuiGuangRenPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.TuiGuangRenContact;
import com.liaocheng.suteng.myapplication.ui.my.adapter.TuiGuangRenAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TuiGuangRenActivity extends BaseActivity<TuiGuangRenPresenter> implements TuiGuangRenContact.View {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.recyclerView)
    XRecyclerView mRecyclerview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tuiguangren;
    }
    String id ="1";
    int page =1;
    TuiGuangRenAdapter adapter;
    @Override
    public void initEventAndData() {
        id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(id)){
            return;
        }
        if (id.equals("1")){
            toolBar.setTitleText("总代人详情").setBackFinish();
        }
        if (id.equals("2")){
            toolBar.setTitleText("省代人详情").setBackFinish();
        }
        if (id.equals("3")){
            toolBar.setTitleText("市代人详情").setBackFinish();
        }
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getTuiGuang( page + "",id);
            }

            @Override
            public void onLoadMore() {
                 page ++;
                mPresenter.getTuiGuang(page + "",id);
            }
        });
adapter = new TuiGuangRenAdapter(this);
mRecyclerview.setAdapter(adapter);
        mRecyclerview.refresh();
    }
    private List<PromoteDetailBean.PromoteDetailModel> mList = new ArrayList<>();
    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Override
    public void setTuiGuang(PromoteDetailBean mBean) {
        mRecyclerview.refreshComplete();
        mRecyclerview.loadMoreComplete();
        if (mBean.data != null) {
            if (page == 1) {
                mList.clear();
            }
            mList.addAll(mBean.data);

            mRecyclerview.setVisibility(View.VISIBLE);
//            ivNull.setVisibility(View.GONE);
            adapter.setData(mList);
        } else {
            if (page != 1) {
                ToastUtil.show("最后一页");
            } else {
                mRecyclerview.setVisibility(View.GONE);
//                ivNull.setVisibility(View.VISIBLE);
            }
        }
    }
}
