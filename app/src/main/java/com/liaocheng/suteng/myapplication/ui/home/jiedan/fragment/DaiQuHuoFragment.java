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
import com.liaocheng.suteng.myapplication.model.event.FaHuoAddressEvent;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.model.event.RenWuEvent;
import com.liaocheng.suteng.myapplication.presenter.QuHuoPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.QuHuoContent;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.DingDanBuyInfoActivity;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.adapter.RenWuAdapter;
import com.liaocheng.suteng.myapplication.ui.my.adapter.FaDanAdapter;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
//        EventBus.getDefault().register(this);
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
                public void onBtnClick(String type, final String id) {
                    //type = 0  取货  1  返回大厅   2返回发货人
                    if (type.equals("0")){
                        mPresenter.getThePickup(id);
                    }
                    if (type.equals("1")){
                        final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(getContext());
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.setMessage("亲，你确定要撤销订单到大厅吗？撤销以后订单会在接单大厅显示，别的接单人可以抢单了，如果发货人投诉你恶意撤销的话，我们会处罚10-200元", "");
                        dialog.setBackgroundResource(true);
                        dialog.setVisibilityBtn(true);
                        dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                mPresenter.order_revoke(id);//退回大厅
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
                    if (type.equals("2")){
                        final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(getContext());
                        dialog.setCanceledOnTouchOutside(true);
                        dialog.setMessage("亲，您确定要把订单退回给发货人吗，退回以后，订单不在接单大厅显示，钱退回给发货人软件余额，在退回之前请先和发货人联系，协商一致，如果发货人投诉您恶意撤销订单，我们会处罚10-200元", "");
                        dialog.setBackgroundResource(true);
                        dialog.setVisibilityBtn(true);
                        dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                mPresenter.order_refund(id);//退回接单人
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
            recyclerView.setVisibility(View.VISIBLE);
            if (page==1){
                mList.clear();
            }
            mList.addAll(ReceiveOrderBean.data);

            ivNull.setVisibility(View.GONE);
            mAdapter.setData(mList);
        } else {
            if (page != 1) {
                ToastUtil.show("最后一页");
            } else {
                ivNull.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
//                if (page==1){
//                    mList.clear();
//                }
//                mList.addAll(ReceiveOrderBean.data);
            }
        }
    }

    @Override
    public void getThePickup() {
        ToastUtil.show("取货成功");
        recyclerView.refresh();
        EventBus.getDefault().post(new RenWuEvent(1));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void order_refund() {
        ToastUtil.show("撤销成功");
//        recyclerView.refresh();
        getActivity().finish();
    }

    @Override
    public void order_revoke() {
        ToastUtil.show("撤销成功");
//        recyclerView.refresh();
        getActivity().finish();
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
