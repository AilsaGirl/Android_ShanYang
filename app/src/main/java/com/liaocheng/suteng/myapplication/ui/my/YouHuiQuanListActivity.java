package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanBean;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanListBean;
import com.liaocheng.suteng.myapplication.presenter.YouHuiQuanPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.YouHuiQuanContent;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.BangWoMaiActivity;
import com.liaocheng.suteng.myapplication.ui.my.adapter.YouHuiQuanAdapter;
import com.liaocheng.suteng.myapplication.ui.my.adapter.YouHuiQuanListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class YouHuiQuanListActivity extends BaseActivity<YouHuiQuanPresenter> implements YouHuiQuanContent.View {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_youhuiquanlist;
    }

    int page = 1;
    YouHuiQuanListAdapter adapter;

    @Override
    public void initEventAndData() {
        toolBar.setBackFinish().setTitleText("优惠券明细");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getYouHuiQuanList(page + "");
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getYouHuiQuanList(page + "");
            }
        });

        adapter = new YouHuiQuanListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.refresh();
        adapter.setData(mList);
    }

    @Override
    public void showError(int reqCode, String msg) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    List<YouHuiQuanListBean.YouHuiQuanListModel> mList = new ArrayList<>();

    @Override
    public void setYouHuiQuan(YouHuiQuanBean quanBean) {

    }

    @Override
    public void setYouHuiQuanNum(YouHuiQuanBean quanBean) {

    }

    @Override
    public void setYouHuiQuanList(YouHuiQuanListBean quanList) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (quanList.data != null) {
            if (page == 1) {
                mList.clear();
            }
            mList.addAll(quanList.data);

            recyclerView.setVisibility(View.VISIBLE);
//            ivNull.setVisibility(View.GONE);
            adapter.setData(mList);
        } else {
            if (page != 1) {
                ToastUtil.show("最后一页");
            } else {
                recyclerView.setVisibility(View.GONE);
//                ivNull.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void giveCoupons() {

    }


}
