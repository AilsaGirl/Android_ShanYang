package com.liaocheng.suteng.myapplication.ui.my.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.circle.common.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.ui.my.adapter.FaDanAdapter;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/11/6.
 */

@SuppressLint("ValidFragment")
public class JieDanDingDanFragment extends BaseFragment {
    int mId;
//    @BindView(R.id.toolBar)
//    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.fans)
    LinearLayout fans;

    @SuppressLint("ValidFragment")
    public JieDanDingDanFragment(int id) {
        mId = id;
    }

    @Override
    public void showError(int reqCode, String msg) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fahuo;
    }

    @Override
    public void initEventAndData() {
//        toolBar.setTitleText("我的关注").setBackFinish();

    initRecyclerView();
    }
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (mId==1) {
//                    mPresenter.getFansList(true);
                }
                if (mId==2) {
//                    mPresenter.getFollowList(true);
                }

                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                if (mId==1) {
//                    mPresenter.getFansList(false);
                }
                if (mId==2) {
//                    mPresenter.getFollowList(false);
                }

                recyclerView.loadMoreComplete();
            }
        });
//        recyclerView.refresh();
        recyclerView.setVisibility(View.VISIBLE);
//        if (mId==1) {
//            mAdapter = new FenXiangShouYiAdapter(mContext,mId);
//            recyclerView.setAdapter(mAdapter);
//        }

            mAdapter = new FaDanAdapter(mContext, mId);
            recyclerView.setAdapter(mAdapter);
//        mAdapter = new FansAdapter(mContext, null, this);
//        recyclerView.setAdapter(mAdapter);

    }
    FaDanAdapter mAdapter;
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
