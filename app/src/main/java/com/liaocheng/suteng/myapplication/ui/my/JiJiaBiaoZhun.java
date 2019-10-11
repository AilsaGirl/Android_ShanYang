package com.liaocheng.suteng.myapplication.ui.my;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.JiJiaBiaoZhunBean;
import com.liaocheng.suteng.myapplication.presenter.JiJiaBiaoZhunPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.JiJiaBiaoZhunContent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JiJiaBiaoZhun extends BaseActivity<JiJiaBiaoZhunPresenter> implements JiJiaBiaoZhunContent.View {
    @BindView(R.id.toolbar)
    MyToolBar toolbar;
    @BindView(R.id.tvT)
    TextView tvTitle;
    @BindView(R.id.tvJ3)
    TextView tvJ3;
    @BindView(R.id.tvJ6)
    TextView tvJ6;
    @BindView(R.id.tvJ10)
    TextView tvJ10;
    @BindView(R.id.tvJ15)
    TextView tvJ15;
    @BindView(R.id.tvJ20)
    TextView tvJ20;
    @BindView(R.id.tvJ21)
    TextView tvJ21;
    @BindView(R.id.tvZ20)
    TextView tvZ20;
    @BindView(R.id.tvZ150)
    TextView tvZ150;
    @BindView(R.id.tvZ500)
    TextView tvZ500;
    @BindView(R.id.tvS7)
    TextView tvS7;
    @BindView(R.id.tvS13)
    TextView tvS13;
    @BindView(R.id.tvS19)
    TextView tvS19;
    @BindView(R.id.tvS23)
    TextView tvS23;
    @BindView(R.id.tvB2)
    TextView tvB2;
    @BindView(R.id.tvC2)
    TextView tvC2;
    @BindView(R.id.tvB3)
    TextView tvB3;
    @BindView(R.id.tvC3)
    TextView tvC3;
    @BindView(R.id.tvB4)
    TextView tvB4;
    @BindView(R.id.tvC4)
    TextView tvC4;
    @BindView(R.id.tvB1)
    TextView tvB1;
    @BindView(R.id.tvC1)
    TextView tvC1;
    @BindView(R.id.tvB5)
    TextView tvB5;
    @BindView(R.id.tvC5)
    TextView tvC5;
    @BindView(R.id.tvTian)
    TextView tvTian;
    @BindView(R.id.tvJc)
    TextView tvJc;
    @BindView(R.id.tvJiChu)
    LinearLayout tvJiChu;
    @BindView(R.id.linJc)
    LinearLayout linJc;
    @BindView(R.id.linFuJia)
    LinearLayout linFuJia;
    @BindView(R.id.linChe)
    LinearLayout linChe;
    @BindView(R.id.tvJc1)
    TextView tvJc1;
    @BindView(R.id.tvJc2)
    TextView tvJc2;
    @BindView(R.id.tvJc3)
    TextView tvJc3;
    @BindView(R.id.tvJc4)
    TextView tvJc4;
    @BindView(R.id.tvJc5)
    TextView tvJc5;
    @BindView(R.id.tvJc6)
    TextView tvJc6;
    @BindView(R.id.linHeZuo)
    LinearLayout linHeZuo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jijiabiaozhun;
    }

    int type;
    String lat;
    String lon;

    @Override
    public void initEventAndData() {
        toolbar.setTitleText("计费标准").setBackFinish();
        type = getIntent().getIntExtra("type", 0);
        lat = getIntent().getStringExtra("lat");
        lon = getIntent().getStringExtra("lon");
        if (type == 1) {
            tvTitle.setText("帮我买运费标准说明");
            tvJiChu.setVisibility(View.VISIBLE);
            linJc.setVisibility(View.GONE);
            linChe.setVisibility(View.GONE);
            linFuJia.setVisibility(View.GONE);
        }
        if (type == 2) {
            tvTitle.setText("帮我办运费标准说明");
            tvJiChu.setVisibility(View.VISIBLE);
            linJc.setVisibility(View.GONE);
            linChe.setVisibility(View.GONE);
            linFuJia.setVisibility(View.GONE);
        }
        if (type == 3) {
            tvTitle.setText("帮我送运费标准说明");
            tvJiChu.setVisibility(View.GONE);
            linJc.setVisibility(View.VISIBLE);
            linChe.setVisibility(View.VISIBLE);
            linFuJia.setVisibility(View.VISIBLE);
        }
        if (type == 4) {
            tvTitle.setText("同校快递运费标准说明");
            tvJiChu.setVisibility(View.GONE);
            linJc.setVisibility(View.VISIBLE);
            linChe.setVisibility(View.VISIBLE);
            linFuJia.setVisibility(View.VISIBLE);
        }
        if (type == 5) {
            tvTitle.setText("当日达运费标准说明");
            tvJiChu.setVisibility(View.GONE);
            linJc.setVisibility(View.VISIBLE);
            linChe.setVisibility(View.VISIBLE);
            linFuJia.setVisibility(View.VISIBLE);
        }
        if (type == 6) {
            tvTitle.setText("县域快递运费标准说明");
            tvJiChu.setVisibility(View.GONE);
            linJc.setVisibility(View.VISIBLE);
            linChe.setVisibility(View.VISIBLE);
            linFuJia.setVisibility(View.VISIBLE);
        }
        mPresenter.findAreaPriceDetail(type + "", lat, lon);


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

    @Override
    public void findAreaPriceDetail(JiJiaBiaoZhunBean jiaBiaoZhunBean) {
        if (type == 1) {
            tvJc.setText("起步价" + jiaBiaoZhunBean.helpMeBuy + "元");
        }
        if (type == 2) {
            tvJc.setText("起步价" + jiaBiaoZhunBean.helpMeDo + "元");
        }
        if (type == 3) {
            tvJ3.setText("起步价" + jiaBiaoZhunBean.HlepMeSendStartPrice + "元");
            tvJ6.setText("每公里加" + jiaBiaoZhunBean.HlepMeSendOverPrice1 + "元");
            tvJ10.setText("每公里加" + jiaBiaoZhunBean.HlepMeSendOverPrice2 + "元");
            tvJ15.setText("每公里加" + jiaBiaoZhunBean.HlepMeSendOverPrice3 + "元");
            tvJ20.setText("每公里加" + jiaBiaoZhunBean.HlepMeSendOverPrice4 + "元");
            tvJ21.setText("每公里加" + jiaBiaoZhunBean.HlepMeSendOverPrice5 + "元");
        }
        if (type == 4) {
            tvJ3.setText("起步价" + jiaBiaoZhunBean.campusSendStartPrice + "元");
            tvJ6.setText("每公里加" + jiaBiaoZhunBean.campusSendOverPrice1 + "元");
            tvJ10.setText("每公里加" + jiaBiaoZhunBean.campusSendOverPrice2 + "元");
            tvJ15.setText("每公里加" + jiaBiaoZhunBean.campusSendOverPrice3 + "元");
            tvJ20.setText("每公里加" + jiaBiaoZhunBean.campusSendOverPrice4 + "元");
            tvJ21.setText("每公里加" + jiaBiaoZhunBean.campusSendOverPrice5 + "元");
        }
        if (type == 5) {
            tvJ3.setText("起步价" + jiaBiaoZhunBean.todayArriveStartPrice + "元");
            tvJ6.setText("每公里加" + jiaBiaoZhunBean.todayArriveOverPrice1 + "元");
            tvJ10.setText("每公里加" + jiaBiaoZhunBean.todayArriveOverPrice2 + "元");
            tvJ15.setText("每公里加" + jiaBiaoZhunBean.todayArriveOverPrice3 + "元");
            linHeZuo.setVisibility(View.GONE);
            tvJc1.setText("0-1公里");
            tvJc2.setText("2-3公里");
            tvJc3.setText("3-10公里");
            tvJc4.setText("10公里以上");
        }
        if (type == 6) {
            tvJ3.setText("起步价" + jiaBiaoZhunBean.directRouteStartPrice + "元");
            tvJ6.setText("每公里加" + jiaBiaoZhunBean.directRouteOverPrice1 + "元");
            tvJ10.setText("每公里加" + jiaBiaoZhunBean.directRouteOverPrice2 + "元");
            tvJ15.setText("每公里加" + jiaBiaoZhunBean.directRouteOverPrice3 + "元");
            tvJ20.setText("每公里加" + jiaBiaoZhunBean.directRouteOverPrice4 + "元");
            tvJ21.setText("每公里加" + jiaBiaoZhunBean.directRouteOverPrice5 + "元");
            tvJc1.setText("0-20公里");
            tvJc2.setText("20-40公里");
            tvJc3.setText("40-60公里");
            tvJc4.setText("60-80公里");
            tvJc5.setText("80-100公里");
            tvJc6.setText("100公里以上");
        }
        tvZ20.setText("两轮车不收重量附加费，超出每公斤加" + jiaBiaoZhunBean.basicWeightOverPrice + "元");
        tvZ150.setText("三轮车不收重量附加费，超出每公斤加" + jiaBiaoZhunBean.tricycleWeightOverPrice + "元");
        tvZ500.setText("小客车不收重量附加费，超出每公斤加" + jiaBiaoZhunBean.carriageWeightOverPrice + "元");
        tvS7.setText("" + jiaBiaoZhunBean.timePrice1 + "倍");
        tvS13.setText("" + jiaBiaoZhunBean.timePrice3 + "倍");
        tvS19.setText("" + jiaBiaoZhunBean.timePrice4 + "倍");
        tvS23.setText("" + jiaBiaoZhunBean.timePrice5 + "倍");

        tvB4.setText("" + jiaBiaoZhunBean.fouthCycle + "倍");
        tvB2.setText("" + jiaBiaoZhunBean.tricycle + "倍");
        tvB3.setText("" + jiaBiaoZhunBean.carriage + "倍");
        tvB5.setText("" + jiaBiaoZhunBean.fiveCycle + "倍");

        tvC1.setText("" + jiaBiaoZhunBean.basicVolume + "");
        tvC2.setText("" + jiaBiaoZhunBean.tricycleVolume + "");
        tvC3.setText("" + jiaBiaoZhunBean.carriageVolume + "");
        tvC4.setText("" + jiaBiaoZhunBean.fouthVolume + "");
        tvC5.setText("" + jiaBiaoZhunBean.fiveVolume + "");

        tvTian.setText("恶劣天气发货是平时发货价格的" + jiaBiaoZhunBean.weather + "倍，价格以App显示为准");

    }
}
