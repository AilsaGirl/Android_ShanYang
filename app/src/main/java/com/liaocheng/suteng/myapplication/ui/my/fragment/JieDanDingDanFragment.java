package com.liaocheng.suteng.myapplication.ui.my.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseFragment;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.event.DingDanEvent;
import com.liaocheng.suteng.myapplication.presenter.contract.JieDanContent;
import com.liaocheng.suteng.myapplication.presenter.JieDanPresenter;
import com.liaocheng.suteng.myapplication.ui.my.adapter.FaDanAdapter;

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
public class JieDanDingDanFragment extends BaseFragment<JieDanPresenter> implements JieDanContent.View {
    int mId;
    //    @BindView(R.id.toolBar)
//    MyToolBar toolBar;

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.fans)
    RelativeLayout fans;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.tvFaDanNum)
    TextView tvFaDanNum;
    @BindView(R.id.tvZhiFuNum)
    TextView tvZhiFuNum;
    Unbinder unbinder;

    @SuppressLint("ValidFragment")
    public JieDanDingDanFragment(int id) {
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
        return R.layout.fragment_fahuo;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DingDanEvent event) {
        if (event == null)
            return;
        if (!TextUtils.isEmpty(duration)){
            duration=  event.geTitle()+"";
            page=1;
            mPresenter.getMySendOrder(duration, page + "");
            mPresenter.getTotel(duration);
        }else {
            ToastUtil.show("查询失败");
        }


    }
    @Override
    public void initEventAndData() {
//        toolBar.setTitleText("我的关注").setBackFinish();
        EventBus.getDefault().register(this);
        initRecyclerView();
    }

    int page = 1;
    String duration = "7";

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        mPresenter.getTotel(duration);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getMySendOrder(duration, page + "");
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getMySendOrder(duration, page + "");
            }
        });
        recyclerView.setVisibility(View.VISIBLE);

        mAdapter = new FaDanAdapter(mContext, 1);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setData(mList);
//        recyclerView.refresh();
        EventBus.getDefault().post(new DingDanEvent(true));
    }

    FaDanAdapter mAdapter;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

List<MySendOrdersBean.MySendOrdersModel> mList = new ArrayList<>();
    @Override
    public void setMySendOrder(MySendOrdersBean myBean) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (myBean.data != null&&myBean.data.size()>0) {
            if (page==1){
                mList.clear();
            }
                mList.addAll(myBean.data);

            recyclerView.setVisibility(View.VISIBLE);
            ivNull.setVisibility(View.GONE);
            mAdapter.setData(mList);
        } else {
            if (page != 1) {
                ToastUtil.show("最后一页");
            } else {
                ToastUtil.show("暂无数据");
                mList.clear();
                mList.addAll(myBean.data);
                ivNull.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void setTotel(MySendOrdersBean myBean) {
//        tvFaDanNum.setText("发单总计："+myBean.orderCount+"单");
//        tvZhiFuNum.setText("支付总计："+myBean.orderPayMoney+"元");
        tvFaDanNum.setText("接单总计："+myBean.orderCount+"单");
        tvZhiFuNum.setText("收入总计："+myBean.orderPayMoney+"元");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
