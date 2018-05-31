package com.yayangyang.module_live;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyNormalAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Bean> mBeans;
    private LayoutInflater inflater;

    MyNormalAdapter(Context context,ArrayList<Bean> beans){
        mContext=context;
        mBeans=beans;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(mContext,R.layout.my_item,null));
//        return new MyViewHolder(inflater.inflate(R.layout.my_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
