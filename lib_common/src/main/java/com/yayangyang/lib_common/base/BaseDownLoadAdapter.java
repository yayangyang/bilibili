package com.yayangyang.lib_common.base;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public abstract class BaseDownLoadAdapter<T, K extends BaseDownLoadViewHolder> extends BaseQuickAdapter<T,K> {

    public BaseDownLoadAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    public void onViewAttachedToWindow(K holder) {
        super.onViewAttachedToWindow(holder);
        holder.onAttach();
    }

    @Override
    public void onViewDetachedFromWindow(K holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onDetach();
    }

}
