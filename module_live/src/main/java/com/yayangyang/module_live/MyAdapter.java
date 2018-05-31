package com.yayangyang.module_live;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<Bean, BaseViewHolder> {
    public MyAdapter(int layoutResId, List<Bean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Bean item) {

    }
}