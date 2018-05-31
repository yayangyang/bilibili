package com.yayangyang.bilibili.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.SparseIntArray;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yayangyang.lib_common.base.Constant;
import com.yayangyang.lib_common.utils.LogUtils;

import java.util.List;

public abstract class HomeLiveMultiItemQuickAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {

    public static final int TYPE_NOT_FOUND = -404;

    public static final int HOME_LIVE = 0;
    public static final int HOME_LIVE_RANK = 1;

    private SparseIntArray layouts;

    public HomeLiveMultiItemQuickAdapter(@Nullable List<T> data) {
        super(data);
    }

    @Override
    protected int getDefItemViewType(int position) {
        return getType();
    }

    protected void setDefaultViewTypeLayout(@LayoutRes int layoutResId) {
        addItemType(Constant.GRIDLAYOUT_MANAGER, layoutResId);
    }

    @Override
    protected K onCreateDefViewHolder(ViewGroup parent, int viewType) {
        LogUtils.e("viewType:"+viewType);
        return createBaseViewHolder(parent, getLayoutId(viewType));
    }

    private int getLayoutId(int viewType) {
        return layouts.get(viewType, TYPE_NOT_FOUND);
    }

    protected void addItemType(int type, @LayoutRes int layoutResId) {
        if (layouts == null) {
            layouts = new SparseIntArray();
        }
        layouts.put(type, layoutResId);
    }

    //-----------------------------------
    private int type=HOME_LIVE;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
