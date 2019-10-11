package com.liaocheng.suteng.myapplication.ui.my;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.GongNengModel;
import com.liaocheng.suteng.myapplication.model.GuiZeModel;
import com.liaocheng.suteng.myapplication.model.XinWenModel;
import com.liaocheng.suteng.myapplication.model.YuEMingXiBean;
import com.liaocheng.suteng.myapplication.presenter.AboutPresenter;
import com.liaocheng.suteng.myapplication.presenter.YuEPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.AboutContent;
import com.liaocheng.suteng.myapplication.presenter.contract.YuEContent;
import com.liaocheng.suteng.myapplication.ui.my.adapter.GongNengListAdapter;
import com.liaocheng.suteng.myapplication.ui.my.adapter.YuEListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YuEListActivity extends BaseActivity<YuEPresenter> implements YuEContent.View {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yue_list;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("余额明细").setBackFinish();
        initRecyclerView();
    }
    int page=1;
    YuEListAdapter mAdapter;
//    private List<NoticeModel.NoticeBean> mList = new ArrayList<>();
    private void initRecyclerView() {
        final String type = getIntent().getStringExtra("type");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                if (type.equals("1")){
                    mPresenter.user_dealmoneyDetail(page+"");
                }else {
                    mPresenter.user_residumoneyDetail(page+"");
                }

                recyclerView.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                page++;
                if (type.equals("1")){
                    mPresenter.user_dealmoneyDetail(page+"");
                }else {
                    mPresenter.user_residumoneyDetail(page+"");
                }
                recyclerView.loadMoreComplete();
            }
        });
        if (type.equals("1")){
            mPresenter.user_dealmoneyDetail(1+"");
            toolBar.setTitleText("发货余额明细");
        }else {
            mPresenter.user_residumoneyDetail(1+"");
            toolBar.setTitleText("提现余额明细");
        }
        recyclerView.setVisibility(View.VISIBLE);

        mAdapter = new YuEListAdapter(mContext);
        recyclerView.setAdapter(mAdapter);
//        mAdapter.setData(mList);

//        recyclerView.refresh();
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
    List<YuEMingXiBean.DataBean> mList = new ArrayList<>();




    @Override
    public void user_residumoneyDetail(YuEMingXiBean model) {
        recyclerView.refreshComplete();;
        recyclerView.loadMoreComplete();
        if (model.data!=null&&model.data.size()>0){
            if (page==1){
                mList.clear();
                mAdapter.setData(model.data);
            }else {
                mList.addAll(model.data);
                mAdapter.setData(mList);
            }

        }
    }

    @Override
    public void user_dealmoneyDetail(YuEMingXiBean model) {
        recyclerView.refreshComplete();;
        recyclerView.loadMoreComplete();
        if (model.data!=null&&model.data.size()>0){
            if (page==1){
                mList.clear();
                mAdapter.setData(model.data);
            }else {
                mList.addAll(model.data);
                mAdapter.setData(mList);
            }

        }
    }
}
