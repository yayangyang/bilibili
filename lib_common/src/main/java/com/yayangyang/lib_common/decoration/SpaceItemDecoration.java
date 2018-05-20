package com.yayangyang.lib_common.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/11/16.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int size;

    public SpaceItemDecoration(int space,int size) {
        this.space = space;
        this.size = size;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
//        outRect.bottom = space;
//        if(parent.getAdapter() instanceof BaseQuickAdapter){
//            BaseQuickAdapter adapter = (BaseQuickAdapter) parent.getAdapter();
//        }
//        LogUtils.e("size:"+size);
//        LogUtils.e("qqqqqqq000"+parent.getChildLayoutPosition(view));
//        LogUtils.e("qqqqqqq111"+parent.getChildLayoutPosition(view) % size);
        if (parent.getChildLayoutPosition(view) % size != 0) {
//            LogUtils.e("qqqqqqq222"+parent.getChildLayoutPosition(view) % size);
            outRect.left = 0;
        }
    }

}