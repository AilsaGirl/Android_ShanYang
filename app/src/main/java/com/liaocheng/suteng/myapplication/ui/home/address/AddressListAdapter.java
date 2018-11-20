package com.liaocheng.suteng.myapplication.ui.home.address;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.MyLiveList;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by guohao on 2017/9/6.
 */

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {

    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;

    private int secret = 0;
    private String title = "";
    private Context context;
    private List<MyLiveList> mMyLiveList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    String[] str = {};

    public AddressListAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<MyLiveList> mMyLiveList){
        this.mMyLiveList = mMyLiveList;
        notifyDataSetChanged();
    }
    public List<MyLiveList> getMyLiveList() {
        if (mMyLiveList == null) {
            mMyLiveList = new ArrayList<>();
        }
        return mMyLiveList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        holder.ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
        holder.mCheckBox = (ImageView) view.findViewById(R.id.ivBox);
        holder.tvHome = (TextView) view.findViewById(R.id.tvHome);

        return holder;
    }

    @Override
    public int getItemCount() {
        return mMyLiveList==null?0:mMyLiveList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvAddress.setText(mMyLiveList.get(position).address +"");
        holder.tvHome.setText(mMyLiveList.get(position).area +"");


        holder.itemView.setTag(position);
        Log.d("onBindViewHolder", mMyLiveList.get(position).province + mMyLiveList.get
                (position).city + mMyLiveList.get(position).area);


        final MyLiveList myLive = mMyLiveList.get(position);
        if (mEditMode == MYLIVE_MODE_CHECK) {
            holder.mCheckBox.setVisibility(View.GONE);
            holder.ivEdit.setVisibility(View.VISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
            holder.ivEdit.setVisibility(View.GONE);
            if (myLive.isSelect()) {
                holder.mCheckBox.setImageResource(R.mipmap.xuanzhongx);
            } else {
                holder.mCheckBox.setImageResource(R.mipmap.weixuanzhongx);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(position, mMyLiveList);
            }
        });
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(context, AddAddress.class);
//                intent.putExtra("name", "修改地址");
//                intent.putExtra("id",mMyLiveList.get(position).id);
//                intent.putExtra("username",mMyLiveList.get(position).username);
//                intent.putExtra("mobile",mMyLiveList.get(position).mobile);
//                intent.putExtra("olesheng",mMyLiveList.get(position).province );
//                intent.putExtra("oldshi",  mMyLiveList.get(position).city );
//                intent.putExtra("oldqu",mMyLiveList.get(position).area);
//                intent.putExtra("address",mMyLiveList.get(position).address);
//                intent.putExtra("tab",mMyLiveList.get(position).tag);
//                context.startActivity(intent);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<MyLiveList> myLiveList);
    }

    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivEdit;
        TextView tvAddress;
        TextView tvHome;
        RelativeLayout mRootView;
        ImageView mCheckBox;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
