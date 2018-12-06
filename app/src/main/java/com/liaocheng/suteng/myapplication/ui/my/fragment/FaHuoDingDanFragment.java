package com.liaocheng.suteng.myapplication.ui.my.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.circle.common.base.BaseFragment;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MySendOrdersBean;
import com.liaocheng.suteng.myapplication.model.event.DingDanEvent;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.presenter.FaDanPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.FaDanContent;
import com.liaocheng.suteng.myapplication.ui.my.adapter.FaDanAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/11/6.
 */

@SuppressLint("ValidFragment")
public class FaHuoDingDanFragment extends BaseFragment<FaDanPresenter> implements FaDanContent.View {
    int mId;
//    @BindView(R.id.toolBar)
//    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.fans)
    LinearLayout fans;
    @BindView(R.id.tvFaDanNum)
    TextView tvFaDanNum;
    @BindView(R.id.tvZhiFuNum)
    TextView tvZhiFuNum;
    @SuppressLint("ValidFragment")
    public FaHuoDingDanFragment(int id) {
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
    public int getLayoutId() {
        return R.layout.fragment_fahuo;
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
        mPresenter.getTotel(duration);
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

            mAdapter = new FaDanAdapter(mContext, 0);
            recyclerView.setAdapter(mAdapter);
        mAdapter.setData(mList);

        recyclerView.refresh();
    }
    FaDanAdapter mAdapter;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    List<MySendOrdersBean.MySendOrdersModel> mList = new ArrayList<>();

    @Override
    public void setMySendOrder(MySendOrdersBean myBean) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (myBean.data != null) {
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
                recyclerView.setVisibility(View.GONE);
                ivNull.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void setTotel(MySendOrdersBean myBean) {
        tvFaDanNum.setText("发单总计："+myBean.orderCount+"单");
        tvZhiFuNum.setText("支付总计："+myBean.orderPayMoney+"元");
    }
}
