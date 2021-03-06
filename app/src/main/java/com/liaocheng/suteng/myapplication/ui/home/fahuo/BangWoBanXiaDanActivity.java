package com.liaocheng.suteng.myapplication.ui.home.fahuo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.FaDanXiaDanModel;
import com.liaocheng.suteng.myapplication.model.FaHuoAddressModel;
import com.liaocheng.suteng.myapplication.model.OrderCalculateBean;
import com.liaocheng.suteng.myapplication.model.PayModel;
import com.liaocheng.suteng.myapplication.model.event.RecruitEvent;
import com.liaocheng.suteng.myapplication.presenter.FaHuoXiaDanPersenter;
import com.liaocheng.suteng.myapplication.presenter.contract.FaHuoContact;
import com.liaocheng.suteng.myapplication.ui.home.address.AddressList;
import com.liaocheng.suteng.myapplication.ui.home.fahuo.adapter.MyTimeAdapter;
import com.liaocheng.suteng.myapplication.ui.my.JiJiaBiaoZhun;
import com.liaocheng.suteng.myapplication.ui.my.UpdatePhoneZhiFuMiMaActivity;
import com.liaocheng.suteng.myapplication.ui.my.XieYiActivity;
import com.liaocheng.suteng.myapplication.util.Util;
import com.liaocheng.suteng.myapplication.view.ApliyDialog;
import com.liaocheng.suteng.myapplication.view.ApplyAndAlterDialog;
import com.liaocheng.suteng.myapplication.view.PassWordDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class BangWoBanXiaDanActivity extends BaseActivity<FaHuoXiaDanPersenter> implements FaHuoContact.View {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.tvXuQiu)
    TextView tvXuQiu;
    @BindView(R.id.etXuQiu)
    EditText etXuQiu;
    @BindView(R.id.ivBianJI)
    ImageView ivBianJI;
    @BindView(R.id.ivAddress)
    ImageView ivAddress;
    @BindView(R.id.tvDiZhi)
    TextView tvDiZhi;
    @BindView(R.id.tvDiZhiXiangQing)
    TextView tvDiZhiXiangQing;
    @BindView(R.id.tvTel)
    TextView tvTel;
    @BindView(R.id.linAddress)
    LinearLayout linAddress;
    @BindView(R.id.tvDiZhiChangYong)
    TextView tvDiZhiChangYong;
    @BindView(R.id.etHuoKuan)
    EditText etHuoKuan;
    @BindView(R.id.tvShiJian)
    TextView tvShiJian;
    @BindView(R.id.etTel)
    EditText etTel;
    @BindView(R.id.ckTY)
    CheckBox ckTY;
    @BindView(R.id.tvXieYi)
    TextView tvXieYi;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvBianZun)
    TextView tvBianZun;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bangwobanxiadan;
    }

    FaHuoAddressModel addressModel = new FaHuoAddressModel();
    String mAddress;
    String mCity;
    String mXiangQing;
    Intent intent;
    long mLasttime;
    String lon;
    String lat;
    String mTel;
    String mName;
    String mShangPin;

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("补充信息").setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(BangWoBanXiaDanActivity.this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setMessage("退出后设置的信息会丢失,是否退出", "");
                dialog.setBackgroundResource(true);
                dialog.setVisibilityBtn(true);
                dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        dialog.dismiss();
                        finish();
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
        });
        Intent intent = getIntent();
        EventBus.getDefault().register(this);
        addressModel = (FaHuoAddressModel) getIntent().getSerializableExtra("address_data");
        lon = addressModel.lon;
        lat = addressModel.lat;
        mAddress = addressModel.address;
        mCity = addressModel.detailAddress;
        mXiangQing = addressModel.ConcreteAdd;
        mTel = addressModel.contactPhone;
        mName = addressModel.contactName;
        mShangPin = addressModel.content;
        tvDiZhi.setText(mAddress + "");
        tvDiZhiXiangQing.setText(mCity + "");
        tvTel.setText(mTel + "");
        etXuQiu.setText(mShangPin + "");
//        etHuoKuan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean f) {
//                if (!f){
//                    if (TextUtils.isEmpty(etHuoKuan.getText())){
//                        tvNum.setText(mLuFei +"");
//                    }else {
//                        String num = etHuoKuan.getText().toString();
//                        double a = Double.valueOf(mLuFei);
//                        double b = Double.valueOf(num);
//                        double c = a+b;
//                        BigDecimal bd   =   new   BigDecimal(c);
//                        tip   =   bd.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue();
//                        tvNum.setText(tip +"");
//                    }
//                }
//            }
//        });
        etHuoKuan.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //s:变化后的所有字符
//                Toast.makeText(getContext(), "变化:"+s+";"+start+";"+before+";"+count, Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化:"+s+";"+start+";"+before+";"+count);

                if (TextUtils.isEmpty(etHuoKuan.getText())){
                    tvNum.setText(mLuFei +"");
                }else {
                    String num = etHuoKuan.getText().toString();
                    double a = Double.valueOf(mLuFei);
                    double b = Double.valueOf(num);
                    double c = a+b;
                    BigDecimal bd   =   new   BigDecimal(c);
                    tip   =   bd.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    tvNum.setText(tip +"");

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
//                Toast.makeText(getContext(), "变化前:"+s+";"+start+";"+count+";"+after, Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化前:"+s+";"+start+";"+count+";"+after);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数
//                Toast.makeText(getContext(), "变化后:"+s+";", Toast.LENGTH_SHORT).show();
//                Log.i("Seachal:","变化后:"+s+";");
                if (TextUtils.isEmpty(etHuoKuan.getText())){
                    tvNum.setText(mLuFei +"");
                }else {
                    String num = etHuoKuan.getText().toString();
                    double a = Double.valueOf(mLuFei);
                    double b = Double.valueOf(num);
                    double c = a+b;
                    BigDecimal bd   =   new   BigDecimal(c);
                    tip   =   bd.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    tvNum.setText(tip +"");

                }
            }
        });
        initData();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        Date now = new Date();
        Date afterDate = new Date(now .getTime() + 600000);

        mPresenter.orderNum("","1",lat,lon,"","","","","","","");
        tvXieYi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext,XieYiActivity.class);
                intent.putExtra("code",2001+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            final ApplyAndAlterDialog dialog = new ApplyAndAlterDialog(this);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setMessage("退出后设置的信息会丢失,是否退出", "");
            dialog.setBackgroundResource(true);
            dialog.setVisibilityBtn(true);
            dialog.setYesOnclickListener("确定", new ApplyAndAlterDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {
                    dialog.dismiss();
                    finish();
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
        return false;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RecruitEvent event) {
        if (event == null)
            return;
        if (event.getAddressModel() != null) {
            addressModel = event.getAddressModel();
            lon = addressModel.lon;
            lat = addressModel.lon;
            mAddress = addressModel.address;
            mCity = addressModel.detailAddress;
            mXiangQing = addressModel.ConcreteAdd;
            mTel = addressModel.contactPhone;
            mName = addressModel.contactName;
            mShangPin = addressModel.content;
            tvDiZhi.setText(mAddress + "");
            tvDiZhiXiangQing.setText(mCity + "");
            tvTel.setText(mTel + "");
            mPresenter.orderNum("","2",lat,lon,"","","","","","","");

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mwwHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (resultCode == 110) {
            if (requestCode == 110) {
                addressModel = (FaHuoAddressModel) data.getSerializableExtra("address_data");
                mAddress = addressModel.address;
                mCity = addressModel.detailAddress;
                lon = addressModel.lon;
                lat = addressModel.lon;
                tvDiZhi.setText(mAddress + "");
                tvDiZhiXiangQing.setText(mCity + "");
                if (!TextUtils.isEmpty(addressModel.contactPhone))
                    tvTel.setText(addressModel.contactPhone + "");
                if (!TextUtils.isEmpty(addressModel.content))
                    etXuQiu.setText(addressModel.content + "");

                mPresenter.orderNum("","2",lat,lon,"","","","","","","");

            }
        }
        if (resultCode == 120) {
            if (requestCode == 110) {

                addressModel = (FaHuoAddressModel) data.getSerializableExtra("address_data");
                mAddress = addressModel.address;
                mCity = addressModel.detailAddress;
                lon = addressModel.lon;
                lat = addressModel.lon;
                tvDiZhi.setText(mAddress + "");
                tvDiZhiXiangQing.setText(mCity + "");
                if (!TextUtils.isEmpty(addressModel.contactPhone))
                    tvTel.setText(addressModel.contactPhone + "");
                if (!TextUtils.isEmpty(addressModel.content))
                    etXuQiu.setText(addressModel.content + "");

                mPresenter.orderNum("","2",lat,lon,"","","","","","","");

            }
        }
    }

    @OnClick({R.id.ivBianJI, R.id.tvDiZhiChangYong, R.id.tvBianZun,R.id.tvXiaDan,R.id.tvShiJian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBianJI:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, BangWoMaiActivity.class);
                if (addressModel == null) {
                    addressModel = new FaHuoAddressModel();
                }
                addressModel.type = 1;
                addressModel.is_result = 1;
                addressModel.is_new_address =1;
                intent.putExtra("address_data", addressModel);
                startActivityForResult(intent, 110);
                break;
            case R.id.tvDiZhiChangYong:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, AddressList.class);
                if (addressModel == null) {
                    addressModel = new FaHuoAddressModel();
                }
                addressModel.type = 1;
                addressModel.is_result = 1;
                addressModel.lon = lon;
                addressModel.lat = lat;
                intent.putExtra("address_data", addressModel);
                startActivityForResult(intent, 110);
                break;
            case R.id.tvBianZun:
                if (System.currentTimeMillis() - mLasttime < 700)//防止快速点击操作
                    return;
                mLasttime = System.currentTimeMillis();
                intent = new Intent(mContext, JiJiaBiaoZhun.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            case R.id.tvXiaDan:
                if (!ckTY.isChecked()){
                    ToastUtil.show("请阅读协议");
                    return;
                }
                if (TextUtils.isEmpty(etHuoKuan.getText())){
                    tvNum.setText(mLuFei +"");
                }else {
                    String num = etHuoKuan.getText().toString();
                    double a = Double.valueOf(mLuFei);
                    double b = Double.valueOf(num);
                    double c = a+b;
                    BigDecimal bd   =   new   BigDecimal(c);
                    tip   =   bd.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    tvNum.setText(tip +"");
                }
                if (TextUtils.isEmpty(etXuQiu.getText().toString())){
                    ToastUtil.show("需求不能为空");
                    return;
                }
//                if (  !isMobile(etTel.getText().toString()) == true){
//                    ToastUtil.show("指定手机号不正确");
//                    return;
//                }
                if (addressModel==null){
                    ToastUtil.show("订单错误");
                }else {
                    addressModel.content = etXuQiu.getText().toString();
                    mPresenter.detail("","2",addressModel.contactName,addressModel.contactPhone,addressModel.address,addressModel.ConcreteAdd,addressModel.detailAddress,lat,lon,"","","","","","","",etHuoKuan.getText().toString(),"","6","",tvShiJian.getText().toString()+"",addressModel.content+"","","",etTel.getText().toString(),"");
                }
                break;
            case R.id.tvShiJian:
                timeDialog.show();
                mDiaLogData();
                break;
        }
    }
    //判断是不是手机号
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,5,6,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    TimeDialog timeDialog;
    private void initData() {
        timeDialog = new TimeDialog(this, R.layout.dialog_time_layout,new int[]{R.id.dialog_ok_tv});
    }

    private TextView mTime;
    private ListView mTimeList;
    private TextView today;
    private TextView tomorrow;
    private Timer timer;
    private String mYmdClickTime;
    private SimpleDateFormat format;
    private Calendar c;
    private int day;
    private static final int NTF_PRICE = 0x3;
    private static final int msgKey1 = 1;
    private void mDiaLogData() {
        //获取dialog布局窗口，进而获取其中控件id
        Window window = timeDialog.getWindow();
        mTime = (TextView) window.findViewById(R.id.Time);
        mTimeList = (ListView) window.findViewById(R.id.mTimeList);
        today = (TextView) window.findViewById(R.id.today);
        tomorrow = (TextView) window.findViewById(R.id.tomorrow);
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                today.setTextColor(Color.parseColor("#FFA500"));
                tomorrow.setTextColor(Color.parseColor("#000000"));
                day = 0;
                mTimeListData(day);
                mDiaLogData();
            }
        });
        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                today.setTextColor(Color.parseColor("#000000"));
                tomorrow.setTextColor(Color.parseColor("#f47a3d"));
                day = 1;
                mTimeListData(day);
                mDiaLogData();
            }
        });
        timeDialog.setOnCenterItemClickListener(new TimeDialog.OnCenterItemClickListener() {
            @Override
            public void OnCenterItemClick(TimeDialog dialog, View view) {
                dialog.dismiss();
                timer.cancel();
                tvShiJian.setText(mYmdClickTime);
            }
        });
        mTimeListData(day);
        if (day == 0){
            mTimer();
        }else {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            c = Calendar.getInstance();
            // 将时分秒,毫秒域清零
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.add(Calendar.DAY_OF_MONTH, 1);
            mTime.setText(format.format(c.getTime()));
            timer.cancel();
        }
    }
    private void mTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = msgKey1;
                mwwHandler.sendMessage(msg);
            }
        },0,1000);

    }
    MyTimeAdapter myTimeAdapter;
    private void mTimeListData(final int day) {
        final List<String> timeByMinuHM = getTimeByMinuHM(day);
        myTimeAdapter = new MyTimeAdapter(timeByMinuHM,this);
        myTimeAdapter.notifyDataSetChanged();
        mTimeList.setAdapter(myTimeAdapter);
        mTimeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SimpleDateFormat format = new SimpleDateFormat("MM-dd ");
                Calendar instance = Calendar.getInstance();
                if (day == 1){
                    instance.add(Calendar.DAY_OF_MONTH, 1);
                }
                String mClickTime = format.format(instance.getTime());
                mYmdClickTime = mClickTime + "" + timeByMinuHM.get(position);
                ToastUtil.show(mYmdClickTime+"");
            }
        });

    }
    @SuppressLint("SimpleDateFormat")
    public List<String> getTimeByMinuHM(int sign) {

        List<String> times=new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        long end=0;
        try {
            if (sign==0){//今天
                end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.valueOf(String.valueOf(new Date().getTime()))))+" 23:59:59").getTime();
            }else if(sign==1) {//明天
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
                calendar.set(Calendar.HOUR_OF_DAY,00);
                calendar.set(Calendar.MINUTE,00);
                calendar.set(Calendar.MILLISECOND,00);
                end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date(Long.valueOf(String.valueOf(calendar.getTimeInMillis()))))+" 23:59:59").getTime();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        while (true) {
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 10);
            String hm=new SimpleDateFormat("HH:mm").format(calendar.getTime());
            long start=Long.valueOf(calendar.getTimeInMillis());
            if (start<end) {
                times.add(hm);
            }else{
                break;
            }
        }
        return times;

    }
    private Handler mwwHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case msgKey1:
                    format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar c = Calendar.getInstance();
                    mTime.setText(format.format(c.getTime()));
                    break;
                default:
                    break;
            }
        }
    };
    PassWordDialog passWordDialog;
    String mPayType = "";
    @Override
    public void setData(FaDanXiaDanModel xiaDanModel) {
        mCode = xiaDanModel.orderCode;
        ApliyDialog dialog = new ApliyDialog(BangWoBanXiaDanActivity.this, R.style
                .transparentFrameWindowStyle,tvNum.getText().toString() +"",
                new ApliyDialog.SelectDialogCancelListener() {
                    @Override
                    public void onCancelClick(String id) {
                        if (id.equals("4")){
                            if (!TextUtils.isEmpty(etHuoKuan.getText().toString())){
                                ToastUtil.show("货款不能用发货余额");
                                return;
                            }
                        }

                        mPayType = id;
                        if (mPayType .equals("1")||mPayType .equals("2")){
                            mPresenter.order_pay(mCode, mPayType);//付款
                        }else {
                            passWordDialog = new PassWordDialog(BangWoBanXiaDanActivity.this);
                            passWordDialog.show();
                            passWordDialog.setHintText(tvNum.getText().toString() + "");
                            passWordDialog.setMoneyNum(tvNum.getText().toString()+"");
                            passWordDialog.setError_tishi("请输入支付密码");
                            passWordDialog.setClick(new PassWordDialog.OnPayClickListener() {
                                @Override
                                public void onSetPass(String text) {
                                    mPresenter.checkSecondPassword(text+"");

                                }

                                @Override
                                public void onSetPwd() {
                                    passWordDialog.cancel();
                                    Intent intent = new Intent(mContext, UpdatePhoneZhiFuMiMaActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }

                    }
                });
        if (!this.isFinishing()) {
            dialog.show();
        }
    }
    double   tip;
    String mLuFei;
    @Override
    public void setNum(OrderCalculateBean orderCalculateBean) {
        mLuFei = orderCalculateBean.total;
        if (TextUtils.isEmpty(etHuoKuan.getText())){
            tvNum.setText(orderCalculateBean.total+"");
        }else {
            String num = etHuoKuan.getText().toString();
            double a = Double.valueOf(orderCalculateBean.total);
            double b = Double.valueOf(num);
            double c = a+b;
            BigDecimal bd   =   new   BigDecimal(c);
            tip   =   bd.setScale(3,   BigDecimal.ROUND_HALF_UP).doubleValue();
            tvNum.setText(tip +"");
        }


    }

    @Override
    public void order_pay(PayModel payModel) {
        if (mPayType.equals("1")||mPayType.equals("2")){
            Util.Pay(mPayType,payModel,mContext);
        }else {
            ToastUtil.show("支付成功");
            finish();
        }
    }
    String mCode = "";
    @Override
    public void checkSecondPassword() {
        mPresenter.order_pay(mCode, mPayType);//付款
    }


}
