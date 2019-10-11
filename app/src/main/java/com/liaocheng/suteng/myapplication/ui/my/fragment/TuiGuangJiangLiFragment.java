package com.liaocheng.suteng.myapplication.ui.my.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.circle.common.base.BaseFragment;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.ExtensionDetailQueryBean;
import com.liaocheng.suteng.myapplication.model.event.DingDanEvent;
import com.liaocheng.suteng.myapplication.presenter.TuiGuangJiangLiPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.TuiGuangJiangLiContact;
import com.liaocheng.suteng.myapplication.ui.my.adapter.TuiGuangJiangLiAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/11/6.
 */

@SuppressLint("ValidFragment")
public class TuiGuangJiangLiFragment extends BaseFragment<TuiGuangJiangLiPresenter> implements TuiGuangJiangLiContact.View {
    int mId;
    String daiLiId = "1";
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.fans)
    RelativeLayout fans;
    Unbinder unbinder;
    @BindView(R.id.btnTotal)
    Button btnTotal;

    @SuppressLint("ValidFragment")
    public TuiGuangJiangLiFragment(int id, String daiLiId) {
        mId = id;
        this.daiLiId = daiLiId;
    }

    @Override
    public void showError(int reqCode, String msg) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DingDanEvent event) {
        if (event == null)
            return;
        if (!TextUtils.isEmpty(duration)) {
            duration = event.geTitle() + "";
            page = 1;
            mPresenter.getTuiGuang(page + "", daiLiId, mId + "", duration);
        } else {
            ToastUtil.show("查询失败");
        }


    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tuiguangjiangli;
    }

    @Override
    public void initEventAndData() {
//        toolBar.setTitleText("我的关注").setBackFinish();

    }

    @Override
    public void initEventAndDataNoLazy() {
        super.initEventAndDataNoLazy();
        EventBus.getDefault().register(this);
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
                mPresenter.getTuiGuang(page + "", daiLiId, mId + "", duration);
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getTuiGuang(page + "", daiLiId, mId + "", duration);
            }
        });

        recyclerView.setVisibility(View.VISIBLE);

        mAdapter = new TuiGuangJiangLiAdapter(mContext, 0);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setData(mList);

        recyclerView.refresh();
    }

    TuiGuangJiangLiAdapter mAdapter;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    List<ExtensionDetailQueryBean.ExtensionDetailQueryModel> mList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void setTuiGuang(ExtensionDetailQueryBean mBean) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        btnTotal.setText("总计："+mBean.data.get(0).sumMoney+"元");
        if (mBean.data != null) {
            if (page == 1) {
                mList.clear();
            }
            mList.addAll(mBean.data);
            recyclerView.setVisibility(View.VISIBLE);
            ivNull.setVisibility(View.GONE);
            mAdapter.setData(mList);
        } else {
            if (page != 1) {
                ToastUtil.show("最后一页");
            } else {
                recyclerView.setVisibility(View.GONE);
                ivNull.setVisibility(View.VISIBLE);
            }
        }
    }
}
