package com.liaocheng.suteng.myapplication.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.AuthBean;
import com.liaocheng.suteng.myapplication.model.MainModel;
import com.liaocheng.suteng.myapplication.model.MyBean;
import com.liaocheng.suteng.myapplication.model.NoticeModel;
import com.liaocheng.suteng.myapplication.model.VersionModel;
import com.liaocheng.suteng.myapplication.presenter.MainPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.MainContact;
import com.liaocheng.suteng.myapplication.ui.my.adapter.FaDanAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GongDaoListActivity extends BaseActivity<MainPresenter> implements MainContact.View {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.fans)
    LinearLayout fans;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gonggao;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("公告").setBackFinish();
        initRecyclerView();
    }
    int page=1;
    GongGaoAdapter mAdapter;
    private List<NoticeModel.NoticeBean> mList = new ArrayList<>();
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getNotice( "");
                recyclerView.refreshComplete();;

            }

            @Override
            public void onLoadMore() {
                page++;
//                mPresenter.getNotice( "");
                recyclerView.loadMoreComplete();
            }
        });
        mPresenter.getNotice( "");
        recyclerView.setVisibility(View.VISIBLE);

        mAdapter = new GongGaoAdapter(mContext, 0);
        recyclerView.setAdapter(mAdapter);
//        mAdapter.setData(mList);

//        recyclerView.refresh();
    }
    @Override
    public void setNotice(NoticeModel noticeBean) {
        recyclerView.refreshComplete();;
        recyclerView.loadMoreComplete();
        if (noticeBean.data!=null){
            mAdapter.setData(noticeBean.data);
        }
    }

    @Override
    public void setBanner(MainModel mBean) {

    }

    @Override
    public void IdentityInfoSucss(MyBean authBean) {

    }


    @Override
    public void updateLocation() {

    }

    @Override
    public void appVersion_info(VersionModel model) {

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
}
