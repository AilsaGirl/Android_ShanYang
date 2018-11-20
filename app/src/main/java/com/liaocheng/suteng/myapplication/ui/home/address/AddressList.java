package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.SPCommon;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MyLiveList;
import com.liaocheng.suteng.myapplication.model.SiteBean;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.presenter.SitePresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.SiteContact;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2018/3/31.
 */

public class AddressList extends BaseActivity<SitePresenter> implements SiteContact.View, View.OnClickListener, AddressListAdapter.OnItemClickListener {
    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;

    XRecyclerView mRecyclerview;
    LinearLayout mLlBottomDialog;
    TextView mBtnEditor, mBtnDelete, btnAdd;
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.ivNull)
    ImageView ivNull;
    private AddressListAdapter mAddressListAdapter = null;
    private LinearLayoutManager mLinearLayoutManager;
    private List<MyLiveList> mydata = new ArrayList<>();
    private int mEditMode = MYLIVE_MODE_CHECK;
    private boolean editorStatus = false;
    private int index = 0;
    public TextView tvTitle;

    /**
     * 根据选择的数量是否为0来判断按钮的是否可点击.
     *
     * @param size
     */
    private void setBtnBackground(int size) {
        if (size != 0) {
            mBtnDelete.setEnabled(true);
        } else {
            mBtnDelete.setEnabled(false);
        }
    }

    private void initListener() {
        mAddressListAdapter.setOnItemClickListener(this);
        mBtnDelete.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    /**
     * 删除逻辑
     */
    private void deleteVideo() {
        final ArrayList<String> stringArrayList = new ArrayList<String>();
        if (index == 0) {
            mBtnDelete.setEnabled(false);
            return;
        }
        final AlertDialog builder = new AlertDialog.Builder(this)
                .create();
        builder.show();
        if (builder.getWindow() == null) return;
        builder.getWindow().setContentView(R.layout.pop_msg);//设置弹出框加载的布局
        TextView msg = (TextView) builder.findViewById(R.id.tv_msg);
        Button cancle = (Button) builder.findViewById(R.id.btn_cancle);
        Button sure = (Button) builder.findViewById(R.id.btn_sure);
        if (msg == null || cancle == null || sure == null) return;

        if (index == 1) {
            msg.setText("删除后不可恢复，是否删除该条目？");
        } else {
            msg.setText("删除后不可恢复，是否删除这" + index + "个条目？");
        }
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = mAddressListAdapter.getMyLiveList().size(), j = 0, h = 0; i > j; i--, h++) {
                    MyLiveList myLive = mAddressListAdapter.getMyLiveList().get(i - 1);
                    if (myLive.isSelect()) {
                        mAddressListAdapter.getMyLiveList().remove(myLive);
                        Log.d("onClick: ", myLive.id);
                        stringArrayList.add(myLive.id);
                        index--;
                    }

                }
                String[] stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
                String lll = "";
                for (int i = 0; i < stringArray.length; i++) {
                    lll = lll + stringArray[i] + ",";
                }
                lll = lll.substring(0, lll.length() - 1);
                Log.d("onClick: ", lll);
                index = 0;
                setBtnBackground(index);
                if (mAddressListAdapter.getMyLiveList().size() == 0) {
                    mLlBottomDialog.setVisibility(View.GONE);
                }
                mAddressListAdapter.notifyDataSetChanged();
                mPresenter.delAddress(lll);
                builder.dismiss();
            }
        });
    }

    private void updataEditMode() {
        mEditMode = mEditMode == MYLIVE_MODE_CHECK ? MYLIVE_MODE_EDIT : MYLIVE_MODE_CHECK;
        if (mEditMode == MYLIVE_MODE_EDIT) {
            toolBar.setRightText("取消");
            mLlBottomDialog.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
            editorStatus = true;
        } else {
            toolBar.setRightText("编辑");
            mLlBottomDialog.setVisibility(View.GONE);
            btnAdd.setVisibility(View.VISIBLE);
            editorStatus = false;
            clearAll();
        }
        mAddressListAdapter.setEditMode(mEditMode);
    }

    private void clearAll() {
        setBtnBackground(0);
    }
    long mLasttime;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                deleteVideo();
                break;
            case R.id.btnAdd:
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                Intent intent = new Intent();
                intent.setClass(AddressList.this, AddAddress.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClickListener(int pos, List<MyLiveList> myLiveList) {
        if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
            return;
        mLasttime = System.currentTimeMillis();
        MyLiveList myLive = myLiveList.get(pos);
        if (editorStatus) {
            boolean isSelect = myLive.isSelect();
            if (!isSelect) {
                index++;
                myLive.setSelect(true);

            } else {
                myLive.setSelect(false);
                index--;

            }
            setBtnBackground(index);
            mAddressListAdapter.notifyDataSetChanged();
        } else {
            if (!TextUtils.isEmpty(mId)&&mId.equals("1")) {

                String name = myLive.username;//("name");//得到新Activity 关闭后返回的数据
                String address =  myLive.address + myLive.area;//("address");//得到新Activity 关闭后返回的数据
                String id = myLive.id;//("id");//得到新Activity 关闭后返回的数据

                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("area", myLive.area + "");
                intent.putExtra("address", myLive.address + "");
                intent.putExtra("x", myLive.x + "");
                intent.putExtra("y", myLive.y + "");
                //设置返回数据
                this.setResult(1, intent);
                //关闭Activity
                this.finish();
            }
        }
    }

    @Override
    public void showError(int reqCode, String msg) {
        mRecyclerview.refreshComplete();
        mRecyclerview.loadMoreComplete();

            if (msg != null && !msg.equals("")) {
                ToastUtil.show(CommonUtil.splitMsg(msg));
            }

    }

    @Override
    public void AddressListContactSuccess(SiteBean siteBean) {
        mRecyclerview.refreshComplete();
        mRecyclerview.loadMoreComplete();
        if (siteBean.list!=null&&siteBean.list.size()>0){
            ivNull.setVisibility(View.GONE);
            List<SiteBean.addressBean> mlist = siteBean.list;
            if (page==1){
                mydata.clear();
            }
            for (int i = 0; i < mlist.size(); i++) {

                MyLiveList myLiveList = new MyLiveList();
                myLiveList.id = mlist.get(i).id;
                myLiveList.user_id = mlist.get(i).user_id;
                myLiveList.area = mlist.get(i).area;
                myLiveList.address = mlist.get(i).address;
                myLiveList.x = mlist.get(i).x;
                myLiveList.y = mlist.get(i).y;
                mydata.add(myLiveList);
            }

            mAddressListAdapter.setData(mydata);
            initListener();
        }else {
            if (page==1){
                ivNull.setVisibility(View.VISIBLE);
            }else {
                ToastUtil.show("最后一页");
            }
        }

    }

    @Override
    public void selSuccess() {
        ToastUtil.show("删除成功！");
        mRecyclerview.refresh();
    }

    @Override
    public int getLayoutId() {
        return R.layout.address_list;
    }

    String mId = "";
int page =1;
    @Override
    public void initEventAndData() {
//        AutoSizeConfig.getInstance();
        toolBar.setBackFinish().setTitleText("地址选择").setRight("编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                updataEditMode();
            }
        }).setRightTextColor(0xffffffff);
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        if (!TextUtils.isEmpty(mId)){
            toolBar.setBackFinish().setTitleText("选择地址");
        }
        mRecyclerview = (XRecyclerView) findViewById(R.id.recyclerview);
        mLlBottomDialog = (LinearLayout) findViewById(R.id.ll_bottom);

        mBtnDelete = (TextView) findViewById(R.id.btn_delete);
        btnAdd = (TextView) findViewById(R.id.btnAdd);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                mPresenter.addressListContact(SPCommon.getString("token", ""),page+"");
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.addressListContact(SPCommon.getString("token", ""),page+"");
            }
        });

        mydata = new ArrayList<>();
        EventBus.getDefault().register(this);
        mAddressListAdapter = new AddressListAdapter(this);
        mRecyclerview.setAdapter(mAddressListAdapter);
        initListener();
        mRecyclerview.refresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RecruitEvent event) {
        if (event == null)
            return;
        if (event.getShow()){
            mRecyclerview.refresh();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
//        mPresenter.addressListContact(SPCommon.getString("token", ""));
        super.onStart();
    }

}
