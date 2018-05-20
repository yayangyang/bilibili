package com.yayangyang.lib_common.base;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2018/3/20.
 */

public abstract class BaseDownLoadViewHolder extends BaseViewHolder {

    public BaseDownLoadViewHolder(View view) {
        super(view);
    }

    public abstract void onAttach();

    public abstract void onDetach();

}
