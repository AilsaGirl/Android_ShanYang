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
import com.liaocheng.suteng.myapplication.presenter.AboutPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.AboutContent;
import com.liaocheng.suteng.myapplication.ui.my.adapter.GuiZeListAdapter;
import com.liaocheng.suteng.myapplication.ui.my.adapter.XinWenListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuiZeListActivity extends BaseActivity<AboutPresenter> implements AboutContent.View {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_list;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("三羊规则").setBackFinish();
        initRecyclerView();
    }
    int page=1;
    GuiZeListAdapter mAdapter;
//    private List<NoticeModel.NoticeBean> mList = new ArrayList<>();
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getSanYangRule(page+"");
                recyclerView.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                page++;
//                mPresenter.getNotice( "");
                mPresenter.getSanYangRule(page+"");
                recyclerView.loadMoreComplete();
            }
        });
        mPresenter.getSanYangRule( "1");
        recyclerView.setVisibility(View.VISIBLE);

        mAdapter = new GuiZeListAdapter(mContext);
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
    List<GuiZeModel.DataBean> mList = new ArrayList<>();
    @Override
    public void getSanYangNews(XinWenModel model) {

    }

    @Override
    public void getSanYangRule(GuiZeModel model) {
        recyclerView.refreshComplete();;
        recyclerView.loadMoreComplete();
        if (model.data!=null&&model.data.size()>0){
            if (page==1){
                mList.clear();
                mAdapter.setData(model.getData());
            }else {
                mList.addAll(model.getData());
                mAdapter.setData(mList);
            }

        }
    }

    @Override
    public void appUpGrade_info(GongNengModel nengModel) {

    }


}
