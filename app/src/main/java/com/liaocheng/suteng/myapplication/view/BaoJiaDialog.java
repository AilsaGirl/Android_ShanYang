package com.liaocheng.suteng.myapplication.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.FindParcelInsuranceBean;

import java.util.List;

/**
 * 选择对话框
 *
 */

public class BaoJiaDialog extends Dialog implements OnClickListener,OnItemClickListener {
    private SelectDialogListener mListener;
    private Activity mActivity;
    private Button mMBtn_Cancel;
    private TextView mTv_Title;
    private List<FindParcelInsuranceBean.DataBean> mBaoDanList;
    private String mTitle;
    private boolean mUseCustomColor = false;
    private int mFirstItemColor;
    private int mOtherItemColor;

    public interface SelectDialogListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }


    /**
     * 取消事件监听接口
     *
     */
    private SelectDialogCancelListener mCancelListener;

    public interface SelectDialogCancelListener {
        public void onCancelClick(View v);
    }

    public BaoJiaDialog(Activity activity, int theme,
                        SelectDialogListener listener, List<FindParcelInsuranceBean.DataBean> mBaoDanList) {
        super(activity, theme);
        mActivity = activity;
        mListener = listener;
        this.mBaoDanList=mBaoDanList;

        setCanceledOnTouchOutside(true);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.dialog_baojia,
                null);
        setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
        Window window = getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = mActivity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = LayoutParams.MATCH_PARENT;
        wl.height = LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        onWindowAttributesChanged(wl);

        initViews();
    }

    private void initViews() {
        DialogAdapter dialogAdapter=new DialogAdapter(mBaoDanList);
        ListView dialogList=(ListView) findViewById(R.id.dialog_list);
        dialogList.setOnItemClickListener(this);
        dialogList.setAdapter(dialogAdapter);
        mMBtn_Cancel = (Button) findViewById(R.id.mBtn_Cancel);
        mTv_Title = (TextView) findViewById(R.id.mTv_Title);
        mTv_Title.setVisibility(View.VISIBLE);

        mMBtn_Cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(mCancelListener != null){
                    mCancelListener.onCancelClick(v);
                }
                dismiss();
            }
        });

//        if(!TextUtils.isEmpty(mTitle) && mTv_Title != null){
//            mTv_Title.setVisibility(View.VISIBLE);
//            mTv_Title.setText(mTitle);
//        }else{
//            mTv_Title.setVisibility(View.GONE);
//        }
    }

    @Override
    public void onClick(View v) {
        dismiss();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        mListener.onItemClick(parent, view, position, id);
        dismiss();
    }
    private class DialogAdapter extends BaseAdapter {
        private List<FindParcelInsuranceBean.DataBean> mBaoDanList;
        private Viewholder viewholder;
        private LayoutInflater layoutInflater;
        public DialogAdapter(List<FindParcelInsuranceBean.DataBean> mBaoDanList) {
            this.mBaoDanList = mBaoDanList;
            this.layoutInflater=mActivity.getLayoutInflater();
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mBaoDanList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return mBaoDanList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                viewholder=new Viewholder();
                convertView=layoutInflater.inflate(R.layout.dialog_baojia_item, null);
                viewholder.tvTitle=(TextView) convertView.findViewById(R.id.tvTitle);
                viewholder.tvContent=(TextView) convertView.findViewById(R.id.tvContent);
                viewholder.tvJiaGe=(TextView) convertView.findViewById(R.id.tvJiaGe);
                convertView.setTag(viewholder);
            }else{
                viewholder=(Viewholder) convertView.getTag();
            }
            viewholder.tvTitle.setText(mBaoDanList.get(position).getParcel_insurance_name()+"");
            viewholder.tvContent.setText(mBaoDanList.get(position).getParcel_insurance_content()+"");
            viewholder.tvJiaGe.setText(mBaoDanList.get(position).getParcel_insurance_fee()+"");
            return convertView;
        }

    }

    public static class Viewholder {
        public TextView tvTitle;
        public TextView tvContent;
        public TextView tvJiaGe;
    }

    /**
     * 设置列表项的文本颜色
     */
    public void setItemColor(int firstItemColor, int otherItemColor) {
        mFirstItemColor = firstItemColor;
        mOtherItemColor = otherItemColor;
        mUseCustomColor = true;
    }
}
