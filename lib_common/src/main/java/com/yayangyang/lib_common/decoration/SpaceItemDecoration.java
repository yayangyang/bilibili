package com.yayangyang.lib_common.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yayangyang.lib_common.utils.LogUtils;

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
        outRect.right=space;
//        LogUtils.e("right:"+space);
        if (parent.getChildLayoutPosition(view) % size != 0) {
            outRect.left = 0;
        }
    }

}