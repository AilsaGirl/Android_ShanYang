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
import com.liaocheng.suteng.myapplication.model.ReceiveOrderModel;
import com.liaocheng.suteng.myapplication.presenter.QuHuoPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.QuHuoContent;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.adapter.RenWuAdapter;
import com.liaocheng.suteng.myapplication.ui.my.adapter.FaDanAdapter;

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
public class DaiQuHuoFragment extends BaseFragment<QuHuoPresenter> implements QuHuoContent.View {
    int mId;
//    @BindView(R.id.toolBar)
//    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    @SuppressLint("ValidFragment")
    public DaiQuHuoFragment(int id) {
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
                mPresenter.queryReceiveOrder(page+"");
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.queryReceiveOrder(page+"");
            }
        });

        recyclerView.setVisibility(View.VISIBLE);

            mAdapter = new RenWuAdapter(mContext, 0, new RenWuAdapter.FollowClickListener() {
                @Override
                public void onBtnClick(String type, String id) {
                    //type = 0  取货  1  返回大厅   2返回发货人
                    if (type.equals("0")){
                        mPresenter.getThePickup(id);
                    }
                    if (type.equals("1")){
                        mPresenter.order_revoke(id);
                    }
                    if (type.equals("2")){
                        mPresenter.order_refund(id);
                    }
                }
            });
            recyclerView.setAdapter(mAdapter);
        mAdapter.setData(mList);
        recyclerView.refresh();
    }
    RenWuAdapter mAdapter;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
List<ReceiveOrderModel.ReceiveOrderBean> mList = new ArrayList<>();
    @Override
    public void queryReceiveOrder(ReceiveOrderModel ReceiveOrderBean) {
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
    public void getThePickup() {
        ToastUtil.show("取货成功");
        recyclerView.refresh();
    }

    @Override
    public void order_refund() {
        ToastUtil.show("撤销成功");
        recyclerView.refresh();
    }

    @Override
    public void order_revoke() {
        ToastUtil.show("撤销成功");
        recyclerView.refresh();
    }

    double bd_lat; double bd_lon;
    //高德转百度
    void bd_encrypt(double gg_lat, double gg_lon) {
        double x = gg_lon, y = gg_lat;
        double z = sqrt(x * x + y * y) + 0.00002 * sin(y * Math.PI);
        double theta = atan2(y, x) + 0.000003 * cos(x * Math.PI);
        bd_lon = z * cos(theta) + 0.0065;
        bd_lat = z * sin(theta) + 0.006;
    }
    double gg_lon;
    double gg_lat;
    //百度转高德
    void bd_decrypt(double bd_lat, double bd_lon) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * Math.PI);
        double theta = atan2(y, x) - 0.000003 * cos(x * Math.PI);
        gg_lon = z * cos(theta);
        gg_lat = z * sin(theta);
    }
}
