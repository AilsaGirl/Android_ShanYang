package com.liaocheng.suteng.myapplication.ui.home.fahuo.adapter;


import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.model.PingJiaModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PingJiaAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<PingJiaModel.DataBean> mEntityList = new ArrayList<>();
    OnStarChangeListener onStarChangeListener;
    public PingJiaAdapter (Context context,OnStarChangeListener onStarChangeListener ){
        this.mContext = context;
        this.onStarChangeListener = onStarChangeListener;
    }
    //下面两个方法提供给页面刷新和加载时调用
    public void add(List<PingJiaModel.DataBean> entityList) {
        //增加数据
        this.mEntityList = entityList;
      notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_pingjia, parent, false);
        return new DemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
//        BaseEntity entity = mEntityList.get(position);
//        Picasso.with(mContext).load(mEntityList.get(position)).placeholder(R.mipmap.renxiangt).error(R.mipmap.renxiangt).into(((DemoViewHolder)holder).imageView);
        ( (DemoViewHolder)holder).imageView.setText(mEntityList.get(position).context+"");
        if (mEntityList.get(position).color.equals("0")){
            ( (DemoViewHolder)holder).imageView.setBackgroundColor(0xffff9600);
        }
        if (mEntityList.get(position).color.equals("1")){
            ( (DemoViewHolder)holder).imageView.setBackgroundColor(0xfff50000);
        }
        if (mEntityList.get(position).color.equals("2")){
            ( (DemoViewHolder)holder).imageView.setBackgroundColor(0xff56eb00);
        }
        if (mEntityList.get(position).color.equals("3")){
            ( (DemoViewHolder)holder).imageView.setBackgroundColor(0xffff007b);
        }
        if (mEntityList.get(position).color.equals("4")){
            ( (DemoViewHolder)holder).imageView.setBackgroundColor(0xff009a8a);
        }
        ( (DemoViewHolder)holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStarChangeListener.OnStarChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

    private class DemoViewHolder extends RecyclerView.ViewHolder{

        private TextView imageView;

        public DemoViewHolder(View itemView) {
            super(itemView);
            imageView = (TextView) itemView.findViewById(R.id.ivImg);
        }
    }
    public interface OnStarChangeListener {
        void OnStarChanged(int position);
    }
}