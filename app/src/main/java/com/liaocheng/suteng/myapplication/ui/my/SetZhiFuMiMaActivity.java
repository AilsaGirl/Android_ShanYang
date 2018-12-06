package com.liaocheng.suteng.myapplication.ui.my;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.circle.common.base.BaseActivity;
import com.circle.common.util.CommonUtil;
import com.circle.common.util.ToastUtil;
import com.circle.common.view.MyToolBar;
import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.NullBean;
import com.liaocheng.suteng.myapplication.presenter.SetMiMaPresenter;
import com.liaocheng.suteng.myapplication.presenter.contract.SetMiMaContact;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetZhiFuMiMaActivity extends BaseActivity<SetMiMaPresenter> implements SetMiMaContact.View {
    @BindView(R.id.toolBar)
    MyToolBar toolBar;
    @BindView(R.id.etMiMa)
    EditText etMiMa;
    @BindView(R.id.etMiMaQueRen)
    EditText etMiMaQueRen;
    @BindView(R.id.tvOk)
    TextView tvOk;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setzhifu;
    }

    @Override
    public void initEventAndData() {
        toolBar.setTitleText("设置支付密码").setBackFinish();
    }

    @Override
    public void showError(int reqCode, String msg) {
        if (msg != null && !msg.equals("")) {
            ToastUtil.show(CommonUtil.splitMsg(msg + "") + "");
        }
    }

    @OnClick(R.id.tvOk)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etMiMa.getText())){
            ToastUtil.show("设置密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(etMiMaQueRen.getText())){
            ToastUtil.show("确认密码不能为空");
            return;
        }
        if (etMiMa.getText().toString().trim().length()<4){
            ToastUtil.show("设置四位密码");
            return;
        }
        if (etMiMaQueRen.getText().toString().trim().length()<4){
            ToastUtil.show("设置四位确认密码");
            return;
        }
        if (etMiMa.getText().toString().trim().equals(etMiMaQueRen.getText().toString().trim())){
            mPresenter.getMiMa(etMiMa.getText().toString().trim()+"");
        }else {
            ToastUtil.show("设置密码和确认密码不一致");
            return;
        }
    }

    @Override
    public void setMiMa(NullBean nullBean) {
        ToastUtil.show("设置成功！");
        finish();
    }
}
