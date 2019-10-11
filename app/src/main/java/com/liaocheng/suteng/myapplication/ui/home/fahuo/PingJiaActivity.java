package com.liaocheng.suteng.myapplication.ui.home.fahuo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.PingJiaModel;
import com.liaocheng.suteng.myapplication.presenter.PingJiaPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.PingJiaContent;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.adapter.PingJiaAdapter;
import com.liaocheng.suteng.myapplication.view.RatingBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PingJiaActivity extends BaseActivity<PingJiaPresenter> implements PingJiaContent.View {
    @BindView(R.id.myToolBar)
    MyToolBar myToolBar;
    @BindView(R.id.civHead)
    CircleImageView civHead;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.rbAll)
    RatingBar rbAll;
    @BindView(R.id.rbPing)
    RatingBar rbPing;
    @BindView(R.id.rvPing)
    RecyclerView rvPing;
    @BindView(R.id.rvAll)
    RecyclerView rvAll;
    @BindView(R.id.btnPing)
    Button btnPing;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pingjia;
    }
    PingJiaAdapter allAdapter;
    PingJiaAdapter pingAdapter;
     List<PingJiaModel.DataBean> mPingList = new ArrayList<>();
     List<PingJiaModel.DataBean> mAllList = new ArrayList<>();
    int mNumber = 4;
    String comments = "";
    String orderCode;
    String eva_userId;
    @Override
    public void initEventAndData() {
        myToolBar.setTitleText("评价").setBackFinish();
        Intent intent = getIntent();
        orderCode = intent.getStringExtra("orderCode");
        eva_userId = intent.getStringExtra("eva_userId");
        mPresenter.getCommentLabel();
        allAdapter = new PingJiaAdapter(this, new PingJiaAdapter.OnStarChangeListener() {
            @Override
            public void OnStarChanged(int position) {
                mPingList.clear();
                if (mAllList.get(position).isSelect){
                    mAllList.get(position).isSelect = false;
                }else {
                    mAllList.get(position).isSelect = true;
                }
               for (int i =0;i<mAllList.size();i++){
                   if (mAllList.get(i).isSelect){
                       mPingList.add(mAllList.get(i));

                   }
               }
                pingAdapter.add(mPingList);
            }
        });
        pingAdapter = new PingJiaAdapter(this, new PingJiaAdapter.OnStarChangeListener() {
            @Override
            public void OnStarChanged(int position) {

            }
        });

        rbPing.setClick(new RatingBar.OnStarChangeListener() {
            @Override
            public void OnStarChanged(float selectedNumber, int position) {
                mNumber = position;
            }
        });

        //纵向线性布局
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //纵向线性布局
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        rvPing.setLayoutManager(layoutManager);
        rvPing.setAdapter(pingAdapter);
        GridLayoutManager layoutManager1 = new GridLayoutManager(this,4);
        rvAll.setLayoutManager(layoutManager1);
        rvAll.setAdapter(allAdapter);
//        PingJiaModel model = new PingJiaModel();


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
String level = "1";
    @OnClick(R.id.btnPing)
    public void onViewClicked() {
      if (mNumber<1){
          level = "-1";
      }else if (mNumber==2){
          level = "0";
      }else {
          level = "1";
      }
        for (int i =0;i<mPingList.size();i++){
            if (mPingList.get(i).isSelect){
                if (i==0){
                    comments = mPingList.get(i).context;
                }else {
                    comments =comments+","+ mPingList.get(i).context;
                }
            }
        }
        mPresenter.insertComment(eva_userId,comments,level,orderCode);
    }

    @Override
    public void getCommentLabel(PingJiaModel model) {
        if (model.data!=null&&model.data.size()>0){
            mAllList.clear();
            mAllList.addAll(model.getData());
            for (int i =0;i<mAllList.size();i++){
                mAllList.get(i).isSelect = false;
            }

            allAdapter.add(mAllList);
        }
    }

    @Override
    public void insertComment() {
        ToastUtil.show("评论成功");
        finish();
    }


}
