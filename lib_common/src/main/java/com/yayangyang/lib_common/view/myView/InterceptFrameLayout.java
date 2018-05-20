package com.yayangyang.lib_common.view.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.yayangyang.lib_common.utils.LogUtils;

/**
 * Created by Administrator on 2017/11/4.
 */

public class InterceptFrameLayout extends FrameLayout {

    public boolean isIntercept=false;

    public InterceptFrameLayout(Context context) {
        super(context);
    }

    public InterceptFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isIntercept;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LogUtils.e("onDraw");
        super.onDraw(canvas);
    }
}
