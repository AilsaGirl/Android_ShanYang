package com.liaocheng.suteng.myapplication.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanBean;
import com.liaocheng.suteng.myapplication.model.YouHuiQuanListBean;
import com.liaocheng.suteng.myapplication.presenter.YouHuiQuanPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.YouHuiQuanContent;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.BangWoMaiActivity;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.FaHuoXiaDanSongActivity;
import com.liaocheng.suteng.myapplication.ui.my.adapter.YouHuiQuanAdapter;
import com.liaocheng.suteng.myapplication.view.YouHuiQuanDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YouHuiQuanActivity extends BaseActivity<YouHuiQuanPresenter> implements YouHuiQuanContent.View {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.relQuan)
    RelativeLayout relQuan;
    @BindView(R.id.tvZhuanChu)
    TextView tvZhuanChu;

    @Override
    public int getLayoutId() {
        return R.layout.activity_youhuiquan;
    }

    int page = 1;
    YouHuiQuanAdapter adapter;
    int mType = 0;

    @Override
    public void initEventAndData() {
        toolBar.setBackFinish().setTitleText("优惠券").setRight("优惠券明细", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, YouHuiQuanListActivity.class);
                startActivity(intent);
            }
        });
        mType = getIntent().getIntExtra("type",0) ;
        mPresenter.getYouHuiQuanNum();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getYouHuiQuan(page + "");
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getYouHuiQuan(page + "");
            }
        });

        adapter = new YouHuiQuanAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.refresh();
        adapter.setData(mList);
        tvZhuanChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvTotal.getText().toString().equals("0")){
                    ToastUtil.show("优惠券数量为0");
                    return;
                }

                final YouHuiQuanDialog dialog = new YouHuiQuanDialog(YouHuiQuanActivity.this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setVisibilityBtn(true);
                dialog.setYesOnclickListener("确定", new YouHuiQuanDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick(String tel, String num) {
                        mPresenter.giveCoupons(tel, num);
                        dialog.dismiss();

                    }
                });
                dialog.setOnOnclickListener("取消", new YouHuiQuanDialog.onOnOnclickListener() {
                    @Override
                    public void onOnClick() {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void showError(int reqCode, String msg) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    List<YouHuiQuanBean.YouHuiQuanModel> mList = new ArrayList<>();

    @Override
    public void setYouHuiQuan(YouHuiQuanBean quanBean) {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        if (quanBean.data != null) {
            if (page == 1) {
                mList.clear();
            }
            mList.addAll(quanBean.data);

            recyclerView.setVisibility(View.VISIBLE);
//            ivNull.setVisibility(View.GONE);
            adapter.setData(mList);
        } else {
            if (page != 1) {
                ToastUtil.show("最后一页");
            } else {
                recyclerView.setVisibility(View.GONE);
//                ivNull.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void setYouHuiQuanNum(YouHuiQuanBean quanBean) {
        if (!TextUtils.isEmpty(quanBean.denomination))
        tvNum.setText(quanBean.denomination + "");
        tvTotal.setText(quanBean.count + "");
    }

    @Override
    public void setYouHuiQuanList(YouHuiQuanListBean quanList) {

    }

    @Override
    public void giveCoupons() {
        ToastUtil.show("转出成功！");
    }

    FaHuoAddressModel addressModel;
    long mLasttime;
    Intent intent;

    @OnClick(R.id.relQuan)
    public void onViewClicked() {
        if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
            return;
        mLasttime = System.currentTimeMillis();
        if (tvTotal.getText().toString().equals("0")){
            ToastUtil.show("优惠券数量为0");
            return;
        }
        if (mType==0){
            ToastUtil.show("只有发货才能使用优惠券");
            return;
        }else {
            if (mType==3){
                intent = new Intent(mContext, FaHuoXiaDanSongActivity.class);
                intent.putExtra("num","2.00");
                setResult(120, intent);
                finish();
            }


        }
//        intent = new Intent(mContext, BangWoMaiActivity.class);
//        addressModel = new FaHuoAddressModel();
//        addressModel.address = "";
//        addressModel.ConcreteAdd = "";
//        addressModel.contactName = "";
//        addressModel.contactPhone = "";
//        addressModel.lon ="";
//        addressModel.lat = "";
//        addressModel.type = 1;
//        addressModel.is_result = 0;
//        addressModel.is_new_address =0;
//        intent.putExtra("address_data", addressModel);
//        mContext.startActivity(intent);


    }



}
