package com.liaocheng.suteng.myapplication.ui.my;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.BaoXianModel;
import com.liaocheng.suteng.myapplication.model.ShouYiModel;
import com.liaocheng.suteng.myapplication.model.TeamModel;
import com.liaocheng.suteng.myapplication.presenter.TuanDuiPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.TuanDuiContent;
import com.liaocheng.suteng.myapplication.ui.my.adapter.TuanDuiJiangLiAdapter;
import com.liaocheng.suteng.myapplication.ui.my.adapter.TuanDuiRenShuAdapter;
import com.liaocheng.suteng.myapplication.view.CustomMenuView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuanDuiActivity extends BaseActivity<TuanDuiPresenter> implements TuanDuiContent.View {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.cmvSet)
    CustomMenuView cmvSet;
    @BindView(R.id.cmvYaoQingMa)
    CustomMenuView cmvYaoQingMa;
    @BindView(R.id.rvTuanDui)
    XRecyclerView rvTuanDui;
    @BindView(R.id.cmvDingDan)
    CustomMenuView cmvDingDan;
    @BindView(R.id.rvShouYi)
    XRecyclerView rvShouYi;


    @Override
    public int getLayoutId() {
        return R.layout.activity_tuandui;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("我的团队").setBackFinish();
        mPresenter.getLevel();
        initRecyclerViewTuanDui();
        initRecyclerViewShouYi();
    }

    int page = 1;
    TuanDuiRenShuAdapter mAdapter;

    private void initRecyclerViewTuanDui() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTuanDui.setLayoutManager(layoutManager);

        rvTuanDui.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getTeamUserDetail(page + "");
                rvTuanDui.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                page++;
//                mPresenter.getNotice( "");
//                mPresenter.getTeamUserDetail(page + "");
                rvTuanDui.loadMoreComplete();
            }
        });
        mAdapter = new TuanDuiRenShuAdapter(mContext);
        rvTuanDui.setAdapter(mAdapter);
        rvTuanDui.refresh();
    }

    int pageShouYi = 1;
    TuanDuiJiangLiAdapter jiangLiAdapter;

    private void initRecyclerViewShouYi() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShouYi.setLayoutManager(layoutManager);

        rvShouYi.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageShouYi = 1;
                mPresenter.getTeamMoney(page + "");
                rvShouYi.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                pageShouYi++;
//                mPresenter.getNotice( "");
                mPresenter.getTeamMoney(page + "");
                rvShouYi.loadMoreComplete();
            }
        });
        jiangLiAdapter = new TuanDuiJiangLiAdapter(mContext);
        rvShouYi.setAdapter(jiangLiAdapter);
        rvShouYi.refresh();
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    List<ShouYiModel.DetailBean> mShouYiList = new ArrayList<>();

    @Override
    public void getLevel(BaoXianModel model) {
        if (model.level.equals("-1")) {
            cmvSet.setRightText("未认证");
        }
        if (model.level.equals("0")) {
            cmvSet.setRightText("小羊");
        }
        if (model.level.equals("1")) {
            cmvSet.setRightText("小羊倌");
        }
        if (model.level.equals("2")) {
            cmvSet.setRightText("大羊倌");
        }

    }

    @Override
    public void getTeamMoney(ShouYiModel model) {
        rvShouYi.refreshComplete();
        rvShouYi.loadMoreComplete();
        cmvDingDan.setRightText(model.getTotal() + "元");
//        cmvDingDan.setRightImage(R.mipmap.xiala);
        if (model.getDetail() != null && model.getDetail().size() > 0) {
            if (page == 1) {
                mShouYiList.clear();
                jiangLiAdapter.setData(model.getDetail());
            } else {
                mShouYiList.addAll(model.getDetail());
                jiangLiAdapter.setData(mShouYiList);
            }

        } else {
            if (page == 1) {
                ToastUtil.show("暂无收益");
            }
        }
    }

    List<TeamModel.DetailBean> mUserList = new ArrayList<>();

    @Override
    public void getTeamUserDetail(TeamModel model) {
        rvTuanDui.refreshComplete();
        rvTuanDui.loadMoreComplete();
        cmvYaoQingMa.setRightText(model.getCount() + "人");
        if (model.getDetail() != null && model.getDetail().size() > 0) {
            if (page == 1) {
                mUserList.clear();
                mAdapter.setData(model.getDetail());
            } else {
                mUserList.addAll(model.getDetail());
                mAdapter.setData(mUserList);
            }

        } else {
            if (page == 1) {
                ToastUtil.show("暂无人员");
            }
        }
    }
boolean tudandui = true;
    boolean shouyi = true;
    @OnClick({R.id.cmvSet, R.id.cmvYaoQingMa, R.id.cmvDingDan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmvSet:
                break;
            case R.id.cmvYaoQingMa:
                if (tudandui){
                    rvShouYi.setVisibility(View.GONE);
                    rvTuanDui.setVisibility(View.VISIBLE);
                    tudandui=false;
                }else {
                    rvShouYi.setVisibility(View.GONE);
                    rvTuanDui.setVisibility(View.GONE);
                    tudandui=true;
                }

                break;
            case R.id.cmvDingDan:
                if (shouyi){
                    shouyi=false;
                    rvShouYi.setVisibility(View.VISIBLE);
                    rvTuanDui.setVisibility(View.GONE);
                }else {
                    rvShouYi.setVisibility(View.GONE);
                    rvTuanDui.setVisibility(View.GONE);
                    shouyi = true;
                }

                break;
        }
    }
}
