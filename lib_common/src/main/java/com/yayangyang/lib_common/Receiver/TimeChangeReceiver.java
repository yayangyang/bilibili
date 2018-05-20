package com.yayangyang.lib_common.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/27.
 */

public class TimeChangeReceiver extends BroadcastReceiver {

    public List<OnTimeChangeListener> mOnTimeChangeListeners = new ArrayList<>();
    private static TimeChangeReceiver timeChange;

    public static TimeChangeReceiver getInstance() {
        if (timeChange == null) {
            timeChange = new TimeChangeReceiver();
        }
        return timeChange;
    }

    public interface OnTimeChangeListener {
        void onTimeChange();
    }

    public void setOnTimeChangeListener(OnTimeChangeListener onTimeChangeListener) {
        if (this.mOnTimeChangeListeners.contains(onTimeChangeListener)) {
            return;
        }
        this.mOnTimeChangeListeners.add(onTimeChangeListener);
    }

    public void delOnNetTimeChangeListener(OnTimeChangeListener onTimeChangeListener) {
        if (this.mOnTimeChangeListeners.contains(onTimeChangeListener)) {
            this.mOnTimeChangeListeners.remove(onTimeChangeListener);
        }
    }

    private void setChange() {
        for (OnTimeChangeListener change : mOnTimeChangeListeners) {
            change.onTimeChange();
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        setChange();
    }

}
