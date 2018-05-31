package com.yayangyang.module_live;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {


    private int size;
    private int space;

    public SpaceItemDecoration(int size,int space) {
        this.size = size;
        this.space = space;
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